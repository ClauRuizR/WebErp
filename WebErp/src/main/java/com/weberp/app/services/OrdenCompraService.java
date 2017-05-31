package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.OrdenCompra;

public interface OrdenCompraService {

	List<OrdenCompra> listaOrdenCompra();

	OrdenCompra guardar(OrdenCompra ordenCompra);

	OrdenCompra getOrdenCompraById(Long id);

	void borrar(Long id);

	boolean generaEntradaProductos(Long id);

	void pagarOrdenComprar(Long id);

	void cambiarEstatusOrdenCompra(OrdenCompra ordenCompra);

	boolean validEstatusActualOrdenCompra(Long id, String estatus);

	List<OrdenCompra> ordensPorPagar();
}
