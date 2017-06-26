package com.weberp.app.services;

import static java.util.Calendar.getInstance;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.weberp.app.common.model.UsuarioUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.ComprobanteFiscal;
import com.weberp.app.repositories.ComprobanteFiscalRepository;

/**
 * Created by claudioruiz on 8/14/16.
 */
@Service
public class ComprobanteFiscalServiceImpl implements ComprobanteFiscalService {

	ComprobanteFiscal comprobanteFiscal;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ComprobanteFiscalRepository comprobanteFiscalRepository;

	@Override
	public List<ComprobanteFiscal> listaComprobanteFiscal() {
		return (List<ComprobanteFiscal>) comprobanteFiscalRepository.findAll();
	}

	@Override
	public ComprobanteFiscal guardar(ComprobanteFiscal comprobanteFiscal) {
		return comprobanteFiscalRepository.save(comprobanteFiscal);
	}

	@Override
	public ComprobanteFiscal getComprobanteFiscalById(Long id) {
		return comprobanteFiscalRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		comprobanteFiscalRepository.delete(id);
	}

	@Override
	public String obtenerComprobanteFiscal(Long empresaId) {
		Date fechaActual = new Date();
		fechaActual.setTime(getInstance().getTime().getTime());
		List<ComprobanteFiscal> listaComprobanteFiscal = (List<ComprobanteFiscal>) comprobanteFiscalRepository
				.findByEmpresa_Id(empresaId);
		for (int i = 0; i < listaComprobanteFiscal.size() ; i++) {
			if (null != listaComprobanteFiscal().get(i).getFechaEfectividad()
					&& listaComprobanteFiscal().get(i).getFechaEfectividad().before(fechaActual)) {
				return listaComprobanteFiscal.get(i).getCodigo() + ""
						+ listaComprobanteFiscal.get(i).getSecuenciaCaracteres();

			}
		}
		return "";
	}

	@Override
	public void incrementarComprobanteFiscal(Long id) {
		
		ComprobanteFiscal comprobanteFiscal = comprobanteFiscalRepository.findOne(id);
		int comprobanteFiscalControl = comprobanteFiscal.getSecuencia()+1;
		comprobanteFiscal.setSecuenciaCaracteres(StringUtils.leftPad(String.valueOf(comprobanteFiscalControl), 8, "0"));
		comprobanteFiscal.setSecuencia(comprobanteFiscalControl);
				
		comprobanteFiscalRepository.save(comprobanteFiscal);		
		
		
	}


}
