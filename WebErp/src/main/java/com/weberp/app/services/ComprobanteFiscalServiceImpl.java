package com.weberp.app.services;

import static java.util.Calendar.getInstance;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.dto.ComprobanteFiscalDTO;
import com.weberp.app.dto.config.ConfigMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.ComprobanteFiscal;
import com.weberp.app.repositories.ComprobanteFiscalRepository;

/**
 * Created by claudioruiz on 8/14/16.
 */
@Service
public class ComprobanteFiscalServiceImpl extends ConfigMapper implements ComprobanteFiscalService {

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
	public ComprobanteFiscalDTO guardar(ComprobanteFiscalDTO comprobanteFiscalDTO) throws  ParseException{

		ComprobanteFiscal comprobanteFiscal = convertComprobanteFiscalToEntity(comprobanteFiscalDTO);

		 comprobanteFiscalRepository.save(comprobanteFiscal);

		 return convertComprobanteFiscalToDto(comprobanteFiscal);
	}

	@Override
	public ComprobanteFiscalDTO getComprobanteFiscalById(Long id) {
		return convertComprobanteFiscalToDto(comprobanteFiscalRepository.findOne(id));
	}

	@Override
	public void borrar(Long id) {
		comprobanteFiscalRepository.delete(id);
	}



	@Override
	public void incrementarComprobanteFiscal(Long id) {
		
		ComprobanteFiscal comprobanteFiscal = comprobanteFiscalRepository.findOne(id);
		Long contador = comprobanteFiscal.getContador()+1;
		Long cantidad = comprobanteFiscal.getCantidad()-1;
		comprobanteFiscal.setCantidad(cantidad);
		comprobanteFiscal.setContador(contador);

		comprobanteFiscalRepository.save(comprobanteFiscal);
		
		
	}

	@Override
	public Page<ComprobanteFiscalDTO> findPaginated(int page, int size) {
		Page<ComprobanteFiscal> comprobanteFiscalPage=  (comprobanteFiscalRepository.findAll(new PageRequest(page,size, Sort.Direction.ASC,"id")));

		final Page<ComprobanteFiscalDTO> comprobanteFiscalDTOPage = comprobanteFiscalPage.map(this::convertComprobanteFiscalToDto);
		return comprobanteFiscalDTOPage;
	}

	@Override
	public Page<ComprobanteFiscalDTO> findComprobanteFiscalDTOAndPaginated(String letra, Pageable pageRequest) throws ParseException {
		Page<ComprobanteFiscal> comprobanteFiscalPage=  (comprobanteFiscalRepository.findByLetra(letra,pageRequest));

		final Page<ComprobanteFiscalDTO> comprobanteFiscalDTOPage = comprobanteFiscalPage.map(this::convertComprobanteFiscalToDto);
		return comprobanteFiscalDTOPage;
	}


}
