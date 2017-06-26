package com.weberp.app.repositories;

import com.weberp.app.domain.Localidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by claudioruiz on 5/29/17.
 */
public interface LocalidadRepository extends CrudRepository<Localidad,Long> {

    List<Localidad> findByEmpresa_Id(Long empresaId);


    Page<Localidad> findByEmpresa_Id(Long empresaId, Pageable pageRequest);

    Page<Localidad> findByNombre(String nombre, Pageable pageCriteira);
}
