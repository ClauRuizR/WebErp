package com.weberp.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.ComprobanteFiscal;

import java.util.List;

/**
 * Created by claudioruiz on 8/14/16.
 */
public interface ComprobanteFiscalRepository extends CrudRepository<ComprobanteFiscal,Long> {


List<ComprobanteFiscal> findByEmpresa_Id(Long empresaId);

}
