package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.Estatus;

public interface EstatusRepository extends CrudRepository<Estatus, Long> {

	List<Estatus> findByEstatus(String estatus);

}
