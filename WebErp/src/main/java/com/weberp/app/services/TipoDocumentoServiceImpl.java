package com.weberp.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.TipoDocumento;
import com.weberp.app.enums.TipoDocumentoEnums;
import com.weberp.app.repositories.TipoDocumentoRepository;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

	TipoDocumento tipoDocumento;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Override
	public List<TipoDocumento> listaTipoDocumento() {
		// TODO Auto-generated method stub
		return (List<TipoDocumento>) tipoDocumentoRepository.findAll();
	}

	@Override
	public TipoDocumento guardar(TipoDocumento tipoDocumento) {
		// TODO Auto-generated method stub
		return tipoDocumentoRepository.save(tipoDocumento);
	}

	@Override
	public TipoDocumento getTipoDocumentoById(Long id) {
		// TODO Auto-generated method stub
		return tipoDocumentoRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		tipoDocumentoRepository.delete(id);
	}

	@Override
	public void incrementaNumeroControl(String llave) {
		TipoDocumento tipoDocumento = tipoDocumentoRepository.findByLlaveDocumento(llave);
		tipoDocumento.setNumeroControl(tipoDocumento.getNumeroControl() + 1);
		tipoDocumentoRepository.save(tipoDocumento);

	}

	@Override
	public TipoDocumento buscarTipoDocumentoPorLlave(String llave) {
		// TODO Auto-generated method stub
		return tipoDocumentoRepository.findByLlaveDocumento(llave);
	}

	@Override
	public List<TipoDocumento> listaTiposDocumentosSalida() {
		List<String> llaves = new ArrayList();
		llaves.add(TipoDocumentoEnums.COTIZACION);
		llaves.add(TipoDocumentoEnums.FACTURA);
		List<TipoDocumento> tipoDocumentoList = tipoDocumentoRepository.findByLlaveDocumentoIn(llaves);

		return tipoDocumentoList;
	}

}
