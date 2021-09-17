package com.weberp.app.services;

import java.util.List;
import java.util.Map;

import com.weberp.app.domain.Almacen;
import com.weberp.app.dto.AlmacenDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlmacenService {
	List<Almacen> listaAlmacen();

	Almacen guardar(Almacen almacen);

	Almacen getAlmacenById(Long id);

	void borrar(Long id);

	Almacen findByPrincipalAndEstado(boolean principal, int estado);

	void afectaCantidadEntradaProductos(Long productoId, Long cantidad);
	
	void afectaCantidadSalidaProductos(Long productoId, Long cantidad);

    boolean existeProductoRegistradoEnAlmacen(Long productoId,Long almacenId);

    void afectaSalidaProductosBatch(final Map<Long,Long> mapProductos);


	void afectaEntradaProductosBatch(final Map<Long,Long> mapProductos);

	boolean validaExisteAlmacenPrincipal(String codigo);

	Page<AlmacenDTO> findPaginated(int page, int size);

	Page<AlmacenDTO> findAlmacenAndPaginated(String codigo, Pageable pageRequest);
}
