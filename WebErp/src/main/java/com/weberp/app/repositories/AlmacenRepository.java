package com.weberp.app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.Almacen;

public interface AlmacenRepository extends CrudRepository<Almacen, Long> {

	Almacen findByPrincipalAndEstado(boolean principal, int estado);




}
