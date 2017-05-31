package com.weberp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.DiarioGeneral;
import com.weberp.app.repositories.DiarioGeneralRepository;
@Service
public class DiarioGeneralServiceImpl implements DiarioGeneralService {

	
	@Autowired
	private DiarioGeneralRepository diarioGeneralRepository;
	
	@Override
	public List<DiarioGeneral> listaDiarioGeneral() {
		// TODO Auto-generated method stub
		return (List<DiarioGeneral>) diarioGeneralRepository.findAll();
	}

	@Override
	public DiarioGeneral guardar(DiarioGeneral diarioGeneral) {
		// TODO Auto-generated method stub
		return diarioGeneralRepository.save(diarioGeneral);
	}

	@Override
	public DiarioGeneral getDiarioGeneralById(Long id) {
		// TODO Auto-generated method stub
		return diarioGeneralRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		diarioGeneralRepository.delete(id);

	}

}
