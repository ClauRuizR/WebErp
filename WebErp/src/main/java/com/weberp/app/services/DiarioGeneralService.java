package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.DiarioGeneral;

public interface DiarioGeneralService {
	List<DiarioGeneral> listaDiarioGeneral();

	DiarioGeneral guardar(DiarioGeneral diarioGeneral);

	DiarioGeneral getDiarioGeneralById(Long id);

	void borrar(Long id);
	

}
