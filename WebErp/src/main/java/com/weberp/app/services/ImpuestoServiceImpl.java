package com.weberp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.Impuesto;
import com.weberp.app.repositories.ImpuestoRepository;

@Service
public class ImpuestoServiceImpl implements ImpuestoService {

	@Autowired
	private ImpuestoRepository impuestoRepository;
	
	@Override
	public List<Impuesto> listaImpuesto() {
		// TODO Auto-generated method stub
		return (List<Impuesto>) impuestoRepository.findAll();
	}

	@Override
	public Impuesto guardar(Impuesto impuesto) {
		// TODO Auto-generated method stub
		return impuestoRepository.save(impuesto);
	}

	@Override
	public Impuesto getImpuestoById(Long id) {
		// TODO Auto-generated method stub
		return impuestoRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		impuestoRepository.delete(id);

	}

	@Override
	public Impuesto findByLlaveAndEstado(String llave, int estado) {
		// TODO Auto-generated method stub
		return impuestoRepository.findByLlaveAndEstado(llave, estado);
	}
	
	

}
