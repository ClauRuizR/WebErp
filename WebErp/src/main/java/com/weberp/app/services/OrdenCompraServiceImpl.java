package com.weberp.app.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.*;
import com.weberp.app.dto.OrdenCompraDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.exception.FacturaException;
import com.weberp.app.exception.OrdenCompraException;
import com.weberp.app.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weberp.app.enums.EstatusEnum;
import com.weberp.app.enums.TipoMovimientoInventario;
import com.weberp.app.enums.TipoDocumentoEnum;
import com.weberp.app.repositories.OrdenCompraRepository;

@Service
@Transactional(noRollbackFor = Exception.class)
public class OrdenCompraServiceImpl extends ConfigMapper implements OrdenCompraService {

	OrdenCompra ordenCompra;

	Proveedor proveedor;

	@Autowired
	private AlmacenService almacenService;
	@Autowired
	private OrdenCompraRepository ordenCompraRepository;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private ProveedorService proveedorService;

	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MovimientoInventarioService moviemintoInventarioService;

	@Autowired
	private DiarioGeneralService diarioGeneralService;

	@Override
	public Page<OrdenCompraDTO> listaOrdenCompra(Pageable pageable) {
		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

		Page<OrdenCompra> ordenCompraPage = ordenCompraRepository.findByEmpresa_Id(empresaId,pageable);


		final Page<OrdenCompraDTO> contactDtoPage = ordenCompraPage.map(this::convertOrdenCompraToDto);
		return contactDtoPage;
	}

	public void vaidaOrdenCompra(OrdenCompra ordenCompra){
        Long[] productosId= new Long[ordenCompra.getDetalleOrdenCompra().size()];

        for(int i = 0; i < ordenCompra.getDetalleOrdenCompra().size();i++){

            productosId[i] = ordenCompra.getDetalleOrdenCompra().get(i).getProducto().getId();
        }

        if(Utility.checkDuplicated_withSet(productosId)){
            throw new IllegalArgumentException("Existen productos duplicados en el detalle de la orden de compra.");

        }
    }
	@Override
	public OrdenCompra guardar(OrdenCompra ordenCompra) {


        vaidaOrdenCompra(ordenCompra);

		Empresa empresa = UsuarioUtil.getCurrentUserEmpresa().getEmpresa();
        ordenCompra.setEmpresa(empresa);
		if (null == ordenCompra.getId()) {
            ordenCompra.setFecha(new Date());


			String numeroOrdenCompra = tipoDocumentoService.getNumeroDocumento(TipoDocumentoEnum.ORDEN_COMPRA,empresa.getId());

			ordenCompra.setNumeroOrdenCompra(numeroOrdenCompra);

			tipoDocumentoService.incrementaNumeroControl(TipoDocumentoEnum.ORDEN_COMPRA,empresa.getId());
		}


		for (int i = 0; i < ordenCompra.getDetalleOrdenCompra().size(); i++) {
			BigDecimal monto = ordenCompra.getDetalleOrdenCompra().get(i).getPrecio().multiply(new BigDecimal(ordenCompra.getDetalleOrdenCompra().get(i).getCantidad()));

			ordenCompra.getDetalleOrdenCompra().get(i).setOrdenCompra(ordenCompra);

			ordenCompra.getDetalleOrdenCompra().get(i).setMonto(monto);



		}

		return ordenCompraRepository.save(ordenCompra);
	}

	@Override
	public OrdenCompra getOrdenCompraById(Long id) {

		return ordenCompraRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		ordenCompraRepository.delete(id);
	}

	@Override
	public boolean generaEntradaProductos(Long id) {

		ordenCompra = ordenCompraRepository.findOne(id);


		Empresa empresa = UsuarioUtil.getCurrentUserEmpresa().getEmpresa();

		com.weberp.app.domain.MovimientoInventario moviemintoInventario;

		try {
			if (ordenCompra.getEstatus().equals(EstatusEnum.PENDIENTE)) {
				com.weberp.app.domain.TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoPorLlave(TipoDocumentoEnum.ORDEN_COMPRA,empresa.getId());

				List<DetalleOrdenCompra> detalleOrdenCompra = ordenCompra.getDetalleOrdenCompra();
				String usuario = UsuarioUtil.getCurrentUser();

				Almacen almacen = buscarAlmacenPorUsuario(usuario);

                if (null == almacen) {
                    throw new OrdenCompraException(
                            "Almacen debe estar creado para aprobar la orden de compra " + ordenCompra.getNumeroOrdenCompra());
                }

                List<MovimientoInventario> movimientoInventarioList = new ArrayList<>();
                ConcurrentHashMap<Long,Long> productosEntrada = new ConcurrentHashMap<Long,Long>();


                for (int i = 0; i < detalleOrdenCompra.size(); i++) {

					moviemintoInventario = new com.weberp.app.domain.MovimientoInventario();

					moviemintoInventario.setCantidad(detalleOrdenCompra.get(i).getCantidad());
					moviemintoInventario.setProducto(detalleOrdenCompra.get(i).getProducto());
					moviemintoInventario.setNumeroDocumento(ordenCompra.getNumeroOrdenCompra());
					moviemintoInventario.setTipoDocumento(tipoDocumento);
					moviemintoInventario.setTipoMovimiento(TipoMovimientoInventario.ENTRADA);
					moviemintoInventario.setAlmacen(almacen);

                    movimientoInventarioList.add(moviemintoInventario);

                    productosEntrada.put(detalleOrdenCompra.get(i).getProducto().getId(),
							detalleOrdenCompra.get(i).getCantidad());

				}
                almacenService.afectaEntradaProductosBatch(productosEntrada);

                moviemintoInventarioService.saveBatch(movimientoInventarioList);



				ordenCompra.setEstatus(EstatusEnum.APROBADO);

				ordenCompraRepository.save(ordenCompra);
            }

        } catch (OrdenCompraException ex) {
            return false;
        }
        return false;

	}

	@Override
	public void pagarOrdenComprar(Long id) {

		try
		{
			ordenCompra = ordenCompraRepository.findOne(id);


		ordenCompra.setEstatus(EstatusEnum.PAGADA);

		DiarioGeneral diarioGeneral = new DiarioGeneral();

		diarioGeneral.setFecha(new Date());
		diarioGeneral.setNumeroDocumento(ordenCompra.getNumeroOrdenCompra());
		diarioGeneral.setDescripcion("ORDEN COMPRA AL SUPLIDOR: " + ordenCompra.getProveedor().getNombre());
		diarioGeneral.setCredito(ordenCompra.getMontoTotal());
		diarioGeneral.setDebito(new BigDecimal(0));
		diarioGeneral.setEmpresa(ordenCompra.getEmpresa());

		diarioGeneralService.guardar(diarioGeneral);

		ordenCompraRepository.save(ordenCompra);
		}catch(Exception ex){
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@Override
	public OrdenCompra cambiarEstatusOrdenCompra(OrdenCompra ordenCompra) {
		Long idOrdenCompra = ordenCompra.getId();

		if (ordenCompra.getEstatus().equals(EstatusEnum.APROBADO)
				&& !validEstatusActualOrdenCompra(idOrdenCompra, ordenCompra.getEstatus())) {
			generaEntradaProductos(idOrdenCompra);
		}

		if (ordenCompra.getEstatus().equals(EstatusEnum.PAGADA)
				&& !validEstatusActualOrdenCompra(idOrdenCompra, ordenCompra.getEstatus())) {
			pagarOrdenComprar(idOrdenCompra);
		}

		ordenCompra = ordenCompraRepository.findOne(idOrdenCompra);

		return ordenCompra;
	}

	public Almacen buscarAlmacenPorUsuario(String usuario){
		Almacen almacen = usuarioService.findAlmacenAsignado(usuario);
		return almacen;
	}

	@Override
	public boolean validEstatusActualOrdenCompra(Long id, String estatus) {
		ordenCompra = ordenCompraRepository.findByIdAndEstatus(id, estatus);
		return ordenCompra == null ? false : true;
	}

	@Override
	public List<OrdenCompra> ordensPorPagar() {
		return ordenCompraRepository.findByEstatus(EstatusEnum.APROBADO);
	}

	@Override
	public Page<OrdenCompraDTO> findPaginated(int page, int size) {
		Page<OrdenCompra> ordenPage=  (ordenCompraRepository.findAll(new PageRequest(page,size)));

		final Page<OrdenCompraDTO> contactDtoPage = ordenPage.map(this::convertOrdenCompraToDto);
		return contactDtoPage;
	}

	@Override
	public Page<OrdenCompraDTO> findOrdenCompraAndPaginated(String numeroOrdenCompra, Pageable pageRequest) {
		Page<OrdenCompra> ordenPage=  ordenCompraRepository.findByNumeroOrdenCompra(numeroOrdenCompra,pageRequest);

		final Page<OrdenCompraDTO> contactDtoPage = ordenPage.map(this::convertOrdenCompraToDto);
		return contactDtoPage;
	}

}
