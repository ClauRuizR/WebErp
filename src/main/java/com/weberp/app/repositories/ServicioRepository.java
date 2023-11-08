package com.weberp.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.Servicio;

/**
 * Created by claudioruiz on 6/12/17.
 */
public interface ServicioRepository extends CrudRepository<Servicio, Long> {

    Page<Servicio> findByEmpresa_Id(Long empresaId, Pageable pageRequest);

    Page<Servicio> findByNombre(String nombre, Pageable pageCriteira);
}
