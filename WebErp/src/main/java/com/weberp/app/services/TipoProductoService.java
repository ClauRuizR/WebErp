package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.Producto;
import com.weberp.app.domain.TipoProducto;
import com.weberp.app.dto.TipoProductoDTO;



public interface TipoProductoService {
	
	List<TipoProducto>listaTipoProductos();

	TipoProducto guardar(TipoProducto tipoProducto);
	
	TipoProducto getTipoProductoById(Long id);
	
	void borrar(Long id);
	
	boolean existeTipoProducto(TipoProducto tipoProducto);
	
	List<TipoProducto> buscarTipoProducto(TipoProductoDTO tipoProductoDTO);
	//public List<Paciente> buscarPaciente(PacienteDTO pacienteDTO);
}
