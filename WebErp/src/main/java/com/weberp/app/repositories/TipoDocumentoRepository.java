package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.TipoDocumento;

public interface TipoDocumentoRepository extends CrudRepository<TipoDocumento, Long> {

	TipoDocumento findByLlaveDocumento(String llave);
	List<TipoDocumento> findByLlaveDocumentoIn(List<String> llaves);
	

}
