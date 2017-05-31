package com.weberp.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.MovimientoInventario;

public interface MovimientoInventarioRepository extends CrudRepository<MovimientoInventario, Long> {

	boolean findByTipoMovimientoAndNumeroDocumento(String tipoMovimiento, String numeroDocumento);

}
