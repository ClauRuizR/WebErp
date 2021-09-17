package com.weberp.app.repositories;

import com.weberp.app.domain.TipoNcf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.ComprobanteFiscal;

import java.util.List;

/**
 * Created by claudioruiz on 8/14/16.
 */
public interface ComprobanteFiscalRepository extends JpaRepository<ComprobanteFiscal,Long> {


    Page<ComprobanteFiscal> findByLetra(String letra, Pageable pageable);

}
