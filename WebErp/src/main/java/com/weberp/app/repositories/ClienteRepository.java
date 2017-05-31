package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	
	List<Cliente> findAllByOrderByNombre();
	
}
