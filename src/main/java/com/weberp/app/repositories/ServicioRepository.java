package com.weberp.app.repositories;

import com.weberp.app.domain.Servicio;
import com.weberp.app.domain.TipoServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by claudioruiz on 6/12/17.
 */
public interface ServicioRepository extends CrudRepository<Servicio, Long> {

    Page<Servicio> findByEmpresa_Id(Long empresaId, Pageable pageRequest);

    Page<Servicio> findByNombre(String nombre, Pageable pageCriteira);
}
