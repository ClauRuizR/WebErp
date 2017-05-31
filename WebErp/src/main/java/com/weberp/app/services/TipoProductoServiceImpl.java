package com.weberp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.TipoProducto;
import com.weberp.app.dto.TipoProductoDTO;
import com.weberp.app.repositories.TipoProductoRepository;
@Service
public class TipoProductoServiceImpl implements TipoProductoService {

	@Autowired
	private TipoProductoRepository tipoProductoRepository;
	
	@Override
	public List<TipoProducto> listaTipoProductos() {
		// TODO Auto-generated method stub
		return (List<TipoProducto>) tipoProductoRepository.findAll();
	}

	@Override
	public TipoProducto guardar(TipoProducto tipoProducto) {
		// TODO Auto-generated method stub
		return tipoProductoRepository.save(tipoProducto);
	}

	@Override
	public TipoProducto getTipoProductoById(Long id) {
		// TODO Auto-generated method stub
		return tipoProductoRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		tipoProductoRepository.delete(id);

	}

	@Override
	public boolean existeTipoProducto(TipoProducto tipoProducto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TipoProducto> buscarTipoProducto(TipoProductoDTO tipoProductoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
