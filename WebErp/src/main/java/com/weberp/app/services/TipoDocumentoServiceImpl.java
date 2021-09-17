package com.weberp.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.Empresa;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberp.app.enums.TipoDocumentoEnum;
import com.weberp.app.repositories.TipoDocumentoRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

	com.weberp.app.domain.TipoDocumento tipoDocumento;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Override
	public List<com.weberp.app.domain.TipoDocumento> listaTipoDocumento() {
		// TODO Auto-generated method stub
		return (List<com.weberp.app.domain.TipoDocumento>) tipoDocumentoRepository.findAll();
	}

	@Override
	public com.weberp.app.domain.TipoDocumento guardar(com.weberp.app.domain.TipoDocumento tipoDocumento) {
		// TODO Auto-generated method stub

		Empresa empresa = UsuarioUtil.getCurrentUserEmpresa().getEmpresa();

		tipoDocumento.setEmpresa(empresa);
		return tipoDocumentoRepository.save(tipoDocumento);
	}

	@Override
	public com.weberp.app.domain.TipoDocumento getTipoDocumentoById(Long id) {
		// TODO Auto-generated method stub
		return tipoDocumentoRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		tipoDocumentoRepository.delete(id);
	}

	@Override
	public void incrementaNumeroControl(String llave, Long empresaId) {

		com.weberp.app.domain.TipoDocumento tipoDocumento = tipoDocumentoRepository.findByLlaveDocumentoAndEmpresa_Id(llave,empresaId);
		tipoDocumento.setNumeroControl(tipoDocumento.getNumeroControl() + 1);
		tipoDocumentoRepository.save(tipoDocumento);

	}

	@Override
	public com.weberp.app.domain.TipoDocumento buscarTipoDocumentoPorLlave(String llave, Long empresaId) {
		// TODO Auto-generated method stub
		return tipoDocumentoRepository.findByLlaveDocumentoAndEmpresa_Id(llave,empresaId);
	}

	@Override
	public List<com.weberp.app.domain.TipoDocumento> listaTiposDocumentosSalida() {
		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

		List<String> llaves = new ArrayList();
		llaves.add(TipoDocumentoEnum.COTIZACION);
		llaves.add(TipoDocumentoEnum.FACTURA);
		List<com.weberp.app.domain.TipoDocumento> tipoDocumentoList = tipoDocumentoRepository.findByLlaveDocumentoInAndEmpresa_Id(llaves,empresaId);

		return tipoDocumentoList;
	}

	@Override
	public com.weberp.app.domain.TipoDocumento getTipoDocumentoByEmpresaId(Long empresaId) {
		return tipoDocumentoRepository.findByEmpresa_Id(empresaId);
	}

	@Override
	public String getNumeroDocumento(String llaveDocumento,Long empresaId) {
		com.weberp.app.domain.TipoDocumento tipoDocumento = buscarTipoDocumentoPorLlave(llaveDocumento,empresaId);
		String numeroDocumento = tipoDocumento.getLlaveDocumento() + "-"
				+ StringUtils.leftPad(tipoDocumento.getNumeroControl().toString(), 5, "0");
		return numeroDocumento;
	}

}
