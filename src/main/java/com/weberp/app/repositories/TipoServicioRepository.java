package com.weberp.app.repositories;

import com.weberp.app.domain.TipoServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by claudioruiz on 6/12/17.
 */
public interface TipoServicioRepository extends CrudRepository<TipoServicio, Long> {

    List<TipoServicio> findByEmpresa_Id(Long Id);

    TipoServicio findByTipoProducto_IdAndEmpresa_Id(Long tipoProductoId, Long empresaId);


    Page<TipoServicio> findByEmpresa_Id(Long empresaId, Pageable pageRequest);

    Page<TipoServicio> findByNombre(String nombre, Pageable pageCriteira);
}
