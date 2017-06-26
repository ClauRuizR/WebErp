package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.TipoDocumento;

public interface TipoDocumentoRepository extends CrudRepository<TipoDocumento, Long> {

	TipoDocumento findByLlaveDocumentoAndEmpresa_Id(String llave,Long empresaId);
	List<TipoDocumento> findByLlaveDocumentoInAndEmpresa_Id(List<String> llaves,Long empresaId);
	
    TipoDocumento findByEmpresa_Id(Long empresaId);
}
