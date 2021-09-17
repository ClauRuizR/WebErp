package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.OrdenCompra;
import com.weberp.app.dto.OrdenCompraDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdenCompraService {

	Page<OrdenCompraDTO> listaOrdenCompra(Pageable pageable);

	OrdenCompra guardar(OrdenCompra ordenCompra);

	OrdenCompra getOrdenCompraById(Long id);

	void borrar(Long id);

	boolean generaEntradaProductos(OrdenCompra ordenCompra);

	void crearRegistroDiarioGeneral(OrdenCompra ordenCompra);

	OrdenCompra cambiarEstatusOrdenCompra(OrdenCompra ordenCompra);

	boolean validEstatusActualOrdenCompra(Long id, String estatus);

	List<OrdenCompra> ordensPorPagar();


	Page<OrdenCompraDTO> findPaginated(int page, int size);

	Page<OrdenCompraDTO> findOrdenCompraAndPaginated(String numeroOrdenCompra, Pageable pageRequest);


}
