package com.weberp.app.services;

import com.weberp.app.domain.TipoNcf;
import com.weberp.app.dto.TipoNcfDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface TipoNcfService {

	List<TipoNcfDTO> listaTipoDTONcf();

	TipoNcfDTO guardar(TipoNcfDTO tipoNcfDTO);

	TipoNcfDTO getTipoNcfById(Long id);

	void borrar(Long id);

	Page<TipoNcfDTO> findPaginated(int page, int size);

	Page<TipoNcfDTO> findTipoNcfDTOAndPaginated(String codigo, Pageable pageRequest) throws ParseException;


}
