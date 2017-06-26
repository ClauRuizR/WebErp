package com.weberp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weberp.app.domain.MovimientoInventario;
import com.weberp.app.repositories.MovimientoInventarioRepository;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

	
	@Autowired
	private MovimientoInventarioRepository movimientoInventarioRepository;
	
	@Override
	public List<MovimientoInventario> listaInventario() {
		// TODO Auto-generated method stub
		return (List<MovimientoInventario>) movimientoInventarioRepository.findAll();
	}

	@Override
	public MovimientoInventario guardar(MovimientoInventario invetario) {



		return movimientoInventarioRepository.save(invetario);
	}

	@Override
	public MovimientoInventario getInventarioById(Long id) {
		// TODO Auto-generated method stub
		return movimientoInventarioRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub

		movimientoInventarioRepository.delete(id);
	}

	@Override
	public boolean findByTipoMovimientoAndNumeroDocumento(String tipoMovimiento, String numeroDocumento) {
		
		return movimientoInventarioRepository.findByTipoMovimientoAndNumeroDocumento(tipoMovimiento, numeroDocumento);
	}

	@Override
	public void saveBatch(Iterable<MovimientoInventario> movimientoInventarios) {

		movimientoInventarioRepository.save(movimientoInventarios);

	}

}
