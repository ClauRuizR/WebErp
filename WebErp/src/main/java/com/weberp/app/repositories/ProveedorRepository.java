package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.Proveedor;

public interface ProveedorRepository extends CrudRepository<Proveedor, Long> {

	List<Proveedor> findAllByOrderByNombre();
	
}
