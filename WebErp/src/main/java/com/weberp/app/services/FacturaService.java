package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.Factura;
import com.weberp.app.model.TipoFactura;

/**
 * Created by claudioruiz on 7/27/16.
 */
public interface FacturaService {

	List<Factura> listaFacturas();

	Factura guardar(Factura factura);

	Factura getFacturaById(Long id);

	void borrar(Long id);

	List<TipoFactura> listaTipoFacturas();



	boolean generaSalidaProductos(Long Id);

	void pagarFactura(Long id);

	void cambiarEstatusFactura(Factura factura);

	boolean validEstatusActualFactura(Long id, String estatus);

	List<Factura> facturasPorCobrar();
}
