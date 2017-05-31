package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.MovimientoInventario;

public interface MovimientoInventarioService {
	List<MovimientoInventario> listaInventario();

	MovimientoInventario guardar(MovimientoInventario invetario);

	MovimientoInventario getInventarioById(Long id);

	void borrar(Long id);
	
	boolean findByTipoMovimientoAndNumeroDocumento(String tipoMovimiento, String numeroDocumento);
}
