package com.weberp.app.repositories;

import com.weberp.app.domain.GrupoEmpresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by claudioruiz on 6/8/17.
 */
public interface GrupoEmpresaRepository extends JpaRepository<GrupoEmpresa,Long> {





    Page<GrupoEmpresa> findByNombre(String nombre, Pageable pageCriteira);
}
