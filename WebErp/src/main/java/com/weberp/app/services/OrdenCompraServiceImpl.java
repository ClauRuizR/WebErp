package com.weberp.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weberp.app.domain.DetalleOrdenCompra;
import com.weberp.app.domain.DiarioGeneral;
import com.weberp.app.domain.MovimientoInventario;
import com.weberp.app.domain.OrdenCompra;
import com.weberp.app.domain.Proveedor;
import com.weberp.app.domain.TipoDocumento;
import com.weberp.app.enums.EstatusEnum;
import com.weberp.app.enums.MovimientoInventarioEnums;
import com.weberp.app.enums.TipoDocumentoEnums;
import com.weberp.app.repositories.OrdenCompraRepository;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class OrdenCompraServiceImpl implements OrdenCompraService {

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
	private MovimientoInventarioService moviemintoInventarioService;

	@Autowired
	private DiarioGeneralService diarioGeneralService;

	@Override
	public List<OrdenCompra> listaOrdenCompra() {
		// TODO Auto-generated method stub
		return (List<OrdenCompra>) ordenCompraRepository.findAll();
	}

	@Override
	public OrdenCompra guardar(OrdenCompra ordenCompra) {
		List<OrdenCompra> listaOrdenCompra = new ArrayList<>();

		if (null == ordenCompra.getId()) {
			TipoDocumento tipoDocumento = tipoDocumentoService
					.buscarTipoDocumentoPorLlave(TipoDocumentoEnums.ORDEN_COMPRA);

			ordenCompra.setNumeroOrdenCompra(TipoDocumentoEnums.ORDEN_COMPRA + "-"
					+ StringUtils.leftPad(tipoDocumento.getNumeroControl().toString(), 5, "0"));

			tipoDocumentoService.incrementaNumeroControl(TipoDocumentoEnums.ORDEN_COMPRA);
		}
		listaOrdenCompra.add(ordenCompra);
		proveedor = proveedorService.getProveedorById(ordenCompra.getProveedor().getId());

		ordenCompra.setProveedor(proveedor);
		for (int i = 0; i < ordenCompra.getDetalleOrdenCompra().size(); i++) {
			ordenCompra.getDetalleOrdenCompra().get(i).setProducto(
					productoService.getProductoById(ordenCompra.getDetalleOrdenCompra().get(i).getProducto().getId()));
			ordenCompra.getDetalleOrdenCompra().get(i).setOrdenCompra(ordenCompra);
		}

		return ordenCompraRepository.save(ordenCompra);
	}

	@Override
	public OrdenCompra getOrdenCompraById(Long id) {
		// TODO Auto-generated method stub
		return ordenCompraRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		ordenCompraRepository.delete(id);
	}

	@Override
	public boolean generaEntradaProductos(Long id) {

		ordenCompra = ordenCompraRepository.findOne(id);

		MovimientoInventario moviemintoInventario;

		try {
			if (ordenCompra.getEstatus().equals(EstatusEnum.PENDIENTE)) {
				TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoPorLlave("OC");

				List<DetalleOrdenCompra> detalleOrdenCompra = ordenCompra.getDetalleOrdenCompra();

				for (int i = 0; i < detalleOrdenCompra.size(); i++) {

					moviemintoInventario = new MovimientoInventario();

					moviemintoInventario.setCantidad(detalleOrdenCompra.get(i).getCantidad());
					moviemintoInventario.setProducto(detalleOrdenCompra.get(i).getProducto());
					moviemintoInventario.setNumeroDocumento(ordenCompra.getNumeroOrdenCompra());
					moviemintoInventario.setTipoDocumento(tipoDocumento);
					moviemintoInventario.setTipoMovimiento(MovimientoInventarioEnums.ENTRADA);

					moviemintoInventarioService.guardar(moviemintoInventario);

					almacenService.afectaCantidadEntradaProductos(detalleOrdenCompra.get(i).getProducto().getId(),
							detalleOrdenCompra.get(i).getCantidad());

				}
				ordenCompra.setEstatus(EstatusEnum.APROBADO);

				ordenCompraRepository.save(ordenCompra);

			}

		} catch (Exception ex) {
			return false;
		} finally {
			return true;
		}
	}

	@Override
	public void pagarOrdenComprar(Long id) {
		ordenCompra = ordenCompraRepository.findOne(id);

		ordenCompra.setEstatus(EstatusEnum.PAGADA);

		DiarioGeneral diarioGeneral = new DiarioGeneral();

		diarioGeneral.setFecha(new Date());
		diarioGeneral.setNumeroDocumento(ordenCompra.getNumeroOrdenCompra());
		diarioGeneral.setDescripcion("ORDEN COMPRA AL SUPLIDOR: " + ordenCompra.getProveedor().getNombre());
		diarioGeneral.setCredito(ordenCompra.getMontoTotal());

		diarioGeneralService.guardar(diarioGeneral);

		ordenCompraRepository.save(ordenCompra);
	}

	@Override
	public void cambiarEstatusOrdenCompra(OrdenCompra ordenCompra) {
		Long idOrdenCompra = ordenCompra.getId();

		if (ordenCompra.getEstatus().equals(EstatusEnum.APROBADO)
				&& !validEstatusActualOrdenCompra(idOrdenCompra, ordenCompra.getEstatus())) {
			generaEntradaProductos(idOrdenCompra);
		}

		if (ordenCompra.getEstatus().equals(EstatusEnum.PAGADA)
				&& !validEstatusActualOrdenCompra(idOrdenCompra, ordenCompra.getEstatus())) {
			pagarOrdenComprar(idOrdenCompra);
		}
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

}
