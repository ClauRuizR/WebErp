package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.Estatus;

public interface EstatusService {
	List<Estatus> listaEstatus();

	Estatus guardar(Estatus estatus);

	Estatus getEstatusById(Long id);

	void borrar(Long id);
	
	List<Estatus> findByEstatus(String estatus);

}
