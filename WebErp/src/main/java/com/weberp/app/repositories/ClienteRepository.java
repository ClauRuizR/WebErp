package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	
	List<Cliente> findAllByOrderByNombre();

	List<Cliente> findByEmpresa_Id(Long empresaId);

	Page<Cliente> findByEmpresa_Id(Long empresaId, Pageable pageRequest);


	Page<Cliente> findByNombre(String nombre, Pageable pageCriteira);


}
