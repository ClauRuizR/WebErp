package com.weberp.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.weberp.app.domain.TipoProducto;
import com.weberp.app.dto.TipoProductoDTO;


public interface TipoProductoService {
	
	Page<TipoProductoDTO>listaTipoProductos(Pageable pageable);

	TipoProducto guardar(TipoProducto tipoProducto);

	TipoProducto getTipoProductoById(Long id);
	
	void borrar(Long id);
	
	boolean existeTipoProducto(TipoProducto tipoProducto);
	
	List<TipoProducto> buscarTipoProducto(TipoProductoDTO tipoProductoDTO);
	//public List<Paciente> buscarPaciente(PacienteDTO pacienteDTO);
	List<TipoProducto> listaTipoProductoPorEmpresa();
	Page<TipoProductoDTO> listaTipoProductoPorEmpresa(Pageable pageable);

	Page<TipoProducto> findPaginated(int page, int size);

	Page<TipoProducto> findTipoProductoAndPaginated(String nombre, Pageable pageRequest);
}