package com.weberp.app.repositories;

import com.weberp.app.domain.TipoCliente;
import com.weberp.app.domain.TipoNcf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * Created by claudioruiz on 7/6/17.
 */


public interface TipoNcfRepository extends JpaRepository<TipoNcf,Long>{

    Page<TipoNcf> findByCodigo(String codigo, Pageable pageable);



}
