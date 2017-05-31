package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.DetalleAlmacen;
import com.weberp.app.domain.Producto;
import com.weberp.app.dto.ProductoDTO;

public interface ProductoService {

	List<Producto> listaProductos();

	Producto guardar(Producto producto);

	Producto getProductoById(Long id);

	void borrar(Long id);

	boolean existeProducto(Producto producto);

	List<Producto> buscarProducto(ProductoDTO productoDTO);

	List<DetalleAlmacen> buscarProductosEnAlmacenes(Long productoId);

	Long buscarCantidadEnAlmacen(Long productoId);
}
