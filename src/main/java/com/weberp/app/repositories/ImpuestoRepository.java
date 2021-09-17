package com.weberp.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.Impuesto;

public interface ImpuestoRepository extends CrudRepository<Impuesto, Long> {

	
	Impuesto findByLlaveAndEstado(String llave, int estado);
}
