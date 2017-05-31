package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.TipoDocumento;

public interface TipoDocumentoService {

	List<TipoDocumento> listaTipoDocumento();

	TipoDocumento guardar(TipoDocumento tipoDocumento);

	TipoDocumento getTipoDocumentoById(Long id);

	void borrar(Long id);
	
	void incrementaNumeroControl(String llave);
	
	TipoDocumento buscarTipoDocumentoPorLlave(String llave);
	
	List<TipoDocumento> listaTiposDocumentosSalida();
}
