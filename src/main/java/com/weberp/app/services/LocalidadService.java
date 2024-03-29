package com.weberp.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.weberp.app.domain.Localidad;
import com.weberp.app.dto.LocalidadDTO;

/**
 * Created by claudioruiz on 5/29/17.
 */
public interface LocalidadService {
    List<Localidad> listaLocalidades();

    Localidad guardar(Localidad localidad);

    Localidad getLocalidadById(Long id);
    List<Localidad> listaLocalidadesPorEmpresa();

    Page<LocalidadDTO> findPaginated(int page, int size);

    Page<LocalidadDTO> findLocalidadAndPaginated(String nombre, Pageable pageRequest);
}
