package com.weberp.app.repositories;

import com.weberp.app.domain.TipoCliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

/**
 * Created by claudioruiz on 7/6/17.
 */


public interface TipoClienteRepository extends JpaRepository<TipoCliente,Long>{

Page<TipoCliente> findByCodigo(String codigo, Pageable pageable);
}


