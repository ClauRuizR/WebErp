package com.weberp.app.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weberp.app.domain.Almacen;
import com.weberp.app.domain.Cliente;
import com.weberp.app.domain.DetalleFactura;
import com.weberp.app.domain.DiarioGeneral;
import com.weberp.app.domain.Factura;
import com.weberp.app.domain.Impuesto;
import com.weberp.app.domain.MovimientoInventario;
import com.weberp.app.domain.Producto;
import com.weberp.app.domain.TipoDocumento;
import com.weberp.app.enums.EstatusEnum;
import com.weberp.app.enums.ImpuestoEnum;
import com.weberp.app.enums.MovimientoInventarioEnums;
import com.weberp.app.enums.TipoDocumentoEnums;
import com.weberp.app.model.TipoFactura;
import com.weberp.app.repositories.FacturaRepository;

/**
 * Created by claudioruiz on 7/27/16.
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
public class FacturaServiceImpl implements FacturaService {

	Factura factura;

	Producto producto;

	Cliente cliente;

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private AlmacenService almacenService;

	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private ComprobanteFiscalService comprobanteFiscalService;

	@Autowired
	private ImpuestoService impuestoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private MovimientoInventarioService moviemintoInventarioService;

	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	@Autowired
	private DiarioGeneralService diarioGeneralService;

	@Override
	public List<Factura> listaFacturas() {

		return (List<Factura>) facturaRepository.findAll();
	}

	@Override
	public Factura guardar(Factura factura) {

		List<Factura> listFactura = new ArrayList<>();

		Impuesto impuesto = impuestoService.findByLlaveAndEstado(ImpuestoEnum.ITBIS, 1);
		TipoDocumento tipoDocumento = tipoDocumentoService
				.buscarTipoDocumentoPorLlave(factura.getTipoDocumento().getLlaveDocumento());
		factura.setTipoDocumento(tipoDocumento);

		cliente = clienteService.getClienteById(factura.getCliente().getId());
		listFactura.add(factura);

		factura.setCliente(cliente);
		if (null == factura.getId()) {

			factura.setNumeroDocumento(tipoDocumento.getLlaveDocumento() + "-"
					+ StringUtils.leftPad(tipoDocumento.getNumeroControl().toString(), 5, "0"));

			tipoDocumentoService.incrementaNumeroControl(tipoDocumento.getLlaveDocumento());

		}
		List<DetalleFactura> listaDetalleAlmacen = new ArrayList<>();

		factura.setFecha(new Date());

		for (int i = 0; i < factura.getDetalleFactura().size(); i++) {
			factura.getDetalleFactura().get(i).setFactura(factura);

			producto = productoService.getProductoById(factura.getDetalleFactura().get(i).getProducto().getId());
			listaDetalleAlmacen.add(factura.getDetalleFactura().get(i));

			factura.getDetalleFactura().get(i).setProducto(producto);
			factura.getDetalleFactura().get(i).setMonto(factura.getDetalleFactura().get(i).getTotal());
			factura.getDetalleFactura().get(i).setItbis(factura.getDetalleFactura().get(i).getTotal()
					.multiply(impuesto.getValor()).divide(new BigDecimal(100)));

		}
		if (factura.isComprobanteFiscal()) {
			comprobanteFiscalService.incrementarComprobanteFiscal(1L);
		}

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
			{
				add(new TipoFactura(1L, "Cotizacion"));
				add(new TipoFactura(2L, "Factura"));

			}
		};
		return listaTipoFactura;
	}

	public Factura validaFactura(Factura factura) {

		return factura;

	}

	@Override
	public boolean generaSalidaProductos(Long Id) {

		factura = facturaRepository.findOne(Id);

		MovimientoInventario movimientoInventario;

		try {
			if (factura.getEstatus().equals(EstatusEnum.PENDIENTE)) {

				List<DetalleFactura> detalleFactura = factura.getDetalleFactura();

				for (int i = 0; i < detalleFactura.size(); i++) {
					Almacen almacen = almacenService.findByPrincipalAndEstado(true, 1);
					movimientoInventario = new MovimientoInventario();

					movimientoInventario.setCantidad(detalleFactura.get(i).getCantidad());
					movimientoInventario.setProducto(detalleFactura.get(i).getProducto());
					movimientoInventario.setNumeroDocumento(factura.getNumeroDocumento());
					movimientoInventario.setTipoDocumento(factura.getTipoDocumento());
					movimientoInventario.setTipoMovimiento(MovimientoInventarioEnums.SALIDA);
					movimientoInventario.setAlmacen(almacen);

					moviemintoInventarioService.guardar(movimientoInventario);

					almacenService.afectaCantidadSalidaProductos(detalleFactura.get(i).getProducto().getId(),
							detalleFactura.get(i).getCantidad());

				}
				factura.setEstatus(EstatusEnum.APROBADO);
				facturaRepository.save(factura);

			}

		} catch (Exception ex) {
			return false;
		} finally {
			return true;
		}

	}

	@Override
	public void pagarFactura(Long id) {

		factura = facturaRepository.findOne(id);
		factura.setEstatus(EstatusEnum.PAGADA);

		DiarioGeneral diarioGeneral = new DiarioGeneral();

		diarioGeneral.setFecha(new Date());
		diarioGeneral.setNumeroDocumento(factura.getNumeroDocumento());
		diarioGeneral.setDescripcion("VENTA AL CLIENTE: " + factura.getCliente().getNombre());
		diarioGeneral.setDebito(factura.getMontoTotal());

		facturaRepository.save(factura);
		diarioGeneralService.guardar(diarioGeneral);

	}

	@Override
	public void cambiarEstatusFactura(Factura factura) {
		String[] tipoDocumento = factura.getNumeroDocumento().split("-");

		if (tipoDocumento[0].toString().equals(TipoDocumentoEnums.FACTURA)) {

			Long idFactura = factura.getId();

			if (factura.getEstatus().equals(EstatusEnum.APROBADO)
					&& !validEstatusActualFactura(idFactura, factura.getEstatus())) {
				generaSalidaProductos(idFactura);
			}

			if (factura.getEstatus().equals(EstatusEnum.PAGADA)
					&& !validEstatusActualFactura(idFactura, factura.getEstatus())) {
				pagarFactura(idFactura);
			}
		}

	}

	@Override
	public boolean validEstatusActualFactura(Long id, String estatus) {

		factura = facturaRepository.findByIdAndEstatus(id, estatus);

		return factura == null ? false : true;
	}

	@Override
	public List<Factura> facturasPorCobrar() {

		return facturaRepository.findByEstatus(EstatusEnum.APROBADO);
	}
}
