package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.weberp.app.domain.Empresa;

/**
 * Created by claudioruiz on 6/8/17.
 */
public interface EmpresaRepository extends JpaRepository<Empresa,Long> {

    List<Empresa> findById(Long id);




    Page<Empresa> findByNombre(String nombre, Pageable pageCriteira);
}
