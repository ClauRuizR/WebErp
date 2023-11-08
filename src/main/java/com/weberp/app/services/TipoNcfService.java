package com.weberp.app.services;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.weberp.app.dto.TipoNcfDTO;

public interface TipoNcfService {

	List<TipoNcfDTO> listaTipoDTONcf();

	TipoNcfDTO guardar(TipoNcfDTO tipoNcfDTO);

	TipoNcfDTO getTipoNcfById(Long id);

	void borrar(Long id);

	Page<TipoNcfDTO> findPaginated(int page, int size);

	Page<TipoNcfDTO> findTipoNcfDTOAndPaginated(String codigo, Pageable pageRequest) throws ParseException;


}
