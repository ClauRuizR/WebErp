package com.weberp.app.repositories;

import com.weberp.app.domain.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by claudioruiz on 6/8/17.
 */
public interface EmpresaRepository extends JpaRepository<Empresa,Long> {

    List<Empresa> findById(Long id);




    Page<Empresa> findByNombre(String nombre, Pageable pageCriteira);
}
