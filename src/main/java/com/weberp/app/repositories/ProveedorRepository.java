package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.Proveedor;

public interface ProveedorRepository extends CrudRepository<Proveedor, Long> {

	List<Proveedor> findAllByOrderByNombre();
	List<Proveedor> findByEmpresa_Id(Long empresaId);


	Page<Proveedor> findByEmpresa_Id(Long empresaId, Pageable pageRequest);

	Page<Proveedor> findByNombre(String nombre, Pageable pageCriteira);
}
