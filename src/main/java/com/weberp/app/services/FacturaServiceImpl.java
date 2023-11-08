package com.weberp.app.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.*;
import com.weberp.app.dto.FacturaDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weberp.app.enums.EstatusEnum;
import com.weberp.app.enums.TipoImpuesto;
import com.weberp.app.enums.TipoMovimientoInventario;
import com.weberp.app.enums.TipoDocumentoEnum;
import com.weberp.app.exception.FacturaException;
import com.weberp.app.model.TipoFactura;
import com.weberp.app.repositories.FacturaRepository;

/**
 * Created by claudioruiz on 7/27/16.
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FacturaServiceImpl extends ConfigMapper implements FacturaService  {

	Factura factura;

	Producto producto;

	Cliente cliente;

	@Autowired
	private AlmacenService almacenService;

	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private ComprobanteFiscalService comprobanteFiscalService;

	@Autowired
	private ImpuestoService impuestoService;


	@Autowired
	private ProduccionProductoService produccionProductoService;

	@Autowired
	private MovimientoInventarioService moviemintoInventarioService;

	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	@Autowired
	private DiarioGeneralService diarioGeneralService;

	@Override
	public Page<FacturaDTO> listaFacturas(Pageable pageRequest) {
		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

		 Page<Factura> facturaPage = facturaRepository.findByEmpresa_Id(empresaId,pageRequest);

		final Page<FacturaDTO> contactDtoPage = facturaPage.map(this::convertFacturaToDto);
		return contactDtoPage;
	}



	@Override
	public Factura guardar(Factura factura) {
        validaFactura(factura);
		Empresa   empresa = UsuarioUtil.getCurrentUserEmpresa().getEmpresa();


		Impuesto impuesto = impuestoService.findByLlaveAndEstado(TipoImpuesto.ITBIS, 1);


        factura.setEmpresa(empresa);
		if (null == factura.getId()) {

            factura.setFecha(new Date());


		}

		BigDecimal importe = new BigDecimal(0);
		BigDecimal itbis = new BigDecimal(0);
		BigDecimal subTotal = new BigDecimal(0);

		BigDecimal total = new BigDecimal(0);
		for (int i = 0; i < factura.getDetalleFactura().size(); i++) {
			factura.getDetalleFactura().get(i).setFactura(factura);

			if(factura.getDetalleFactura().get(i).getProducto().getTipoProducto().isFacturable()) {
				factura.getDetalleFactura().get(i).setMonto(factura.getDetalleFactura().get(i).getProducto().getPrecioVenta());
				factura.getDetalleFactura().get(i).setImporte(factura.getDetalleFactura().get(i).getMonto().multiply(new BigDecimal(factura.getDetalleFactura().get(i).getCantidad())));
				factura.getDetalleFactura().get(i).setSubTotal(factura.getDetalleFactura().get(i).getImporte().subtract(factura.getDescuento()));
				factura.getDetalleFactura().get(i).setItbis(factura.getDetalleFactura().get(i).getSubTotal().multiply(impuesto.getValor()).divide(new BigDecimal(100)));
				factura.getDetalleFactura().get(i).setSubTotal(factura.getDetalleFactura().get(i).getSubTotal().subtract(factura.getDetalleFactura().get(i).getItbis()));
				factura.getDetalleFactura().get(i).setTotal(factura.getDetalleFactura().get(i).getSubTotal().add(factura.getDetalleFactura().get(i).getItbis()));

			}else{
				factura.getDetalleFactura().get(i).setItbis(new BigDecimal(0));
				factura.getDetalleFactura().get(i).setMonto(new BigDecimal(0));
				factura.getDetalleFactura().get(i).setSubTotal(new BigDecimal(0));
				factura.getDetalleFactura().get(i).setTotal(new BigDecimal(0));
				factura.getDetalleFactura().get(i).setImporte(new BigDecimal(0));
			}


			importe = importe.add(factura.getDetalleFactura().get(i).getImporte());
			itbis  = itbis.add(factura.getDetalleFactura().get(i).getItbis());
			subTotal = subTotal.add(factura.getDetalleFactura().get(i).getSubTotal());
			total = total.add(factura.getDetalleFactura().get(i).getTotal());

		}

		factura.setImporte(importe);
		factura.setItbis(itbis);

		factura.setSubTotal(subTotal);
		factura.setTotal(total);


		return facturaRepository.save(factura);
	}

	@Override
	public Factura getFacturaById(Long id) {

		return facturaRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		facturaRepository.delete(id);
	}

	@Override
	public List<TipoFactura> listaTipoFacturas() {

		List<TipoFactura> listaTipoFactura = new ArrayList<TipoFactura>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7042385651422192690L;

			{
				add(new TipoFactura(1L, "Cotizacion"));
				add(new TipoFactura(2L, "Factura"));

			}
		};
		return listaTipoFactura;
	}


	public void validaFactura(Factura factura) {

	Long[] productosId= new Long[factura.getDetalleFactura().size()];

	for(int i = 0; i < factura.getDetalleFactura().size();i++){

		productosId[i] = factura.getDetalleFactura().get(i).getProducto().getId();
	}

	if(Utility.checkDuplicated_withSet(productosId)){
		throw new IllegalArgumentException("Existen productos duplicados en el detalle de la factura.");

	}


	}

	@Override
	public boolean generaSalidaProductos(Factura factura) {

		MovimientoInventario movimientoInventario;

		try {
			if (factura.getEstatus().equals(EstatusEnum.APROBADA)) {

				List<DetalleFactura> detalleFactura = factura.getDetalleFactura();
				Almacen almacen = almacenService.findByPrincipalAndEstado(true, 1);

				if (null == almacen) {
					throw new FacturaException(
							"Almacen debe estar creado para aprobar la factura " + factura.getNumeroDocumento());
				}
				List<MovimientoInventario> movimientoInventarioList = new ArrayList<>();
				ConcurrentHashMap<Long,Long> productosSalida = new ConcurrentHashMap<Long,Long>();


				for (int i = 0; i < detalleFactura.size(); i++) {

					movimientoInventario = new MovimientoInventario();

					movimientoInventario.setCantidad(detalleFactura.get(i).getCantidad());
					movimientoInventario.setProducto(detalleFactura.get(i).getProducto());
					movimientoInventario.setNumeroDocumento(factura.getNumeroDocumento());
					movimientoInventario.setTipoDocumento(factura.getTipoDocumento());
					movimientoInventario.setTipoMovimiento(TipoMovimientoInventario.SALIDA);
					movimientoInventario.setAlmacen(almacen);




					movimientoInventarioList.add(movimientoInventario);

					productosSalida.put(detalleFactura.get(i).getProducto().getId(),
							detalleFactura.get(i).getCantidad());


				}

				almacenService.afectaSalidaProductosBatch(productosSalida);

                produccionProductoService.generaProduccionProducto(factura.getDetalleFactura(),factura.getNumeroDocumento());

				moviemintoInventarioService.saveBatch(movimientoInventarioList);

				return true;

			}

		} catch (FacturaException	 ex) {
			return false;
		}
		return false; 

	}

	@Override
	public void crearRegistroEnDiarioGeneral(Factura factura) {



		DiarioGeneral diarioGeneral = new DiarioGeneral();

		diarioGeneral.setFecha(new Date());
		diarioGeneral.setNumeroDocumento(factura.getNumeroDocumento());
		diarioGeneral.setDescripcion("VENTA AL CLIENTE: " + factura.getCliente().getNombre());
		diarioGeneral.setDebito(factura.getMontoTotal());
		diarioGeneral.setCredito(new BigDecimal(0));
		diarioGeneral.setEmpresa(factura.getEmpresa());

		diarioGeneralService.guardar(diarioGeneral);

	}

	@Override
	public Factura cambiarEstatusFactura(Factura factura) {
		String tipoDocumento = factura.getTipoDocumento().getLlaveDocumento();
		Long idFactura=0L;
		if (tipoDocumento.toString().equals(TipoDocumentoEnum.FACTURA)) {

			idFactura = factura.getId();

			if (factura.getEstatus().equals(EstatusEnum.APROBADA)
					&& !validEstatusActualFactura(idFactura, factura.getEstatus())) {

				String numeroDocumento = tipoDocumentoService.getNumeroDocumento(factura.getTipoDocumento().getLlaveDocumento(),factura.getEmpresa().getId());

				factura.setNumeroDocumento(numeroDocumento);

				tipoDocumentoService.incrementaNumeroControl(factura.getTipoDocumento().getLlaveDocumento(),factura.getEmpresa().getId());

				if (factura.isComprobanteFiscal()) {
					ComprobanteFiscal comprobanteFiscal = factura.getCliente().getTipoCliente().getTipoNcf().getComprobanteFiscal();
					factura.setNumeroComprobanteFiscal(comprobanteFiscal.getNcf());
					comprobanteFiscalService.incrementarComprobanteFiscal(comprobanteFiscal.getId());
				}


				this.generaSalidaProductos(factura);
			}

			if (factura.getEstatus().equals(EstatusEnum.PAGADA)
					&& !validEstatusActualFactura(idFactura, factura.getEstatus())) {

                this.crearRegistroEnDiarioGeneral(factura);
			}
		}


        for (DetalleFactura detalleFactura :factura.getDetalleFactura())
		    detalleFactura.setFactura(factura);


		factura = facturaRepository.save(factura);

		return factura;

	}

	@Override
	public boolean validEstatusActualFactura(Long id, String estatus) {

		factura = facturaRepository.findByIdAndEstatus(id, estatus);

		return factura == null ? false : true;
	}

	@Override
	public List<Factura> facturasPorCobrar() {

		return facturaRepository.findByEstatus(EstatusEnum.APROBADA);
	}

	@Override
	public Page<FacturaDTO> findPaginated(int page, int size) {
		Page<Factura> facturaPage=  (facturaRepository.findAll(new PageRequest(page,size)));

		final Page<FacturaDTO> contactDtoPage = facturaPage.map(this::convertFacturaToDto);
		return contactDtoPage;
	}

	@Override
	public Page<FacturaDTO> findFacturaAndPaginated(String numeroDocumento, Pageable pageRequest) {
		Page<Factura> facturaPage=  facturaRepository.findByNumeroDocumento(numeroDocumento,pageRequest);

		final Page<FacturaDTO> contactDtoPage = facturaPage.map(this::convertFacturaToDto);
		return contactDtoPage;
	}

	public void registraCuentasCobrar(Factura factura){

		CuentasCobrar cuentasCobrar = new CuentasCobrar();

		cuentasCobrar.setCliente(factura.getCliente());
		cuentasCobrar.setEmpresa(factura.getEmpresa());
		cuentasCobrar.setNumeroDocumento(factura.getNumeroDocumento());
		//cuentasCobrar.setImporte(factura.getImporte());
	}

}
