package com.weberp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.Estatus;
import com.weberp.app.repositories.EstatusRepository;

@Service
public class EstatusServiceImpl implements EstatusService {

	@Autowired
	private EstatusRepository estatusRepository;
	
	
	@Override
	public List<Estatus> listaEstatus() {
		// TODO Auto-generated method stub
		return (List<Estatus>) estatusRepository.findAll();
	}

	@Override
	public Estatus guardar(Estatus estatus) {
		// TODO Auto-generated method stub
		return estatusRepository.save(estatus);
	}

	@Override
	public Estatus getEstatusById(Long id) {
		// TODO Auto-generated method stub
		return estatusRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		estatusRepository.delete(id);

	}

	@Override
	public List<Estatus> findByEstatus(String estatus) {
		// TODO Auto-generated method stub
		return estatusRepository.findByEstatus(estatus);
	}

}
