package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.Factura;

/**
 * Created by claudioruiz on 7/27/16.
 */
public interface FacturaRepository extends CrudRepository<Factura, Long> {

	Factura findByIdAndEstatus(Long id, String estatus);
	
	List<Factura> findByEstatus(String estatus);

}
