package com.weberp.app.services;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.weberp.app.dto.TipoClienteDTO;

/**
 * Created by claudioruiz on 7/6/17.
 */
public interface TipoClienteService {
    List<TipoClienteDTO> listaTipoCliente();

    TipoClienteDTO guardar(TipoClienteDTO tipoClienteDTO);

    TipoClienteDTO getTipoClienteDTOById(Long id);

    void borrar(Long id);


       Page<TipoClienteDTO> findPaginated(int page, int size);

    Page<TipoClienteDTO> findTipoClienteAndPaginated(String codigo, Pageable pageRequest) throws ParseException;

}
