package com.weberp.app.services;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.weberp.app.domain.Producto;
import com.weberp.app.dto.DetalleAlmacenDTO;
import com.weberp.app.dto.ProductoDTO;

public interface ProductoService {

	List<Producto> listaProductos();

	Producto guardar(Producto producto);

	Producto getProductoById(Long id);

	void borrar(Long id);

	boolean existeProducto(Producto producto);

	List<Producto> buscarProducto(ProductoDTO productoDTO);

	List<DetalleAlmacenDTO> buscarProductosEnAlmacenes(Long productoId);

	Long buscarCantidadEnAlmacen(Long productoId);


	List<Producto> listaProductosPorEmpresa();


	Page<ProductoDTO> findPaginated(Pageable pageRequest);

	Page<ProductoDTO> findProductoAndPaginated(String codigoAlfaNumerico, Pageable pageRequest);

	void afectaEntradaProducto(Producto producto	,Long cantidad);

	void afectaSalidaProducto(Producto producto	,Long cantidad);

	List<Long> getStockProductos() throws ParseException;

	List<String> getStockProductoLabel() throws ParseException;


}
