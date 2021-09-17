package com.weberp.app.repositories;

import com.weberp.app.domain.CuentasCobrar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by claudioruiz on 7/21/17.
 */
public interface CuentasCobrarRepository extends JpaRepository<CuentasCobrar,Long> {

    Page<CuentasCobrar> findByEmpresa_Id(Long pEmpresaId, Pageable pageable);
}
