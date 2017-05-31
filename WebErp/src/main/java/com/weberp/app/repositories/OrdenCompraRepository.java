package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.OrdenCompra;

public interface OrdenCompraRepository extends CrudRepository<OrdenCompra, Long> {

	OrdenCompra findByIdAndEstatus(Long id, String estatus);
	
	List<OrdenCompra> findByEstatus(String estatus);
}
