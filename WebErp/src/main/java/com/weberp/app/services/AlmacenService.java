package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.Almacen;

public interface AlmacenService {
	List<Almacen> listaAlmacen();

	Almacen guardar(Almacen almacen);

	Almacen getAlmacenById(Long id);

	void borrar(Long id);

	Almacen findByPrincipalAndEstado(boolean principal, int estado);

	void afectaCantidadEntradaProductos(Long productoId, Long cantidad);
	
	void afectaCantidadSalidaProductos(Long productoId, Long cantidad);


	
}
