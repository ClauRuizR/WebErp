package com.weberp.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.weberp.app.domain.CuentasPagar;



/**
 * Created by claudioruiz on 7/21/17.
 */
public interface CuentasPagarRepository extends JpaRepository<CuentasPagar,Long> {

    Page<CuentasPagar> findByEmpresa_Id(Long pEmpresaId, Pageable pageable);
}
