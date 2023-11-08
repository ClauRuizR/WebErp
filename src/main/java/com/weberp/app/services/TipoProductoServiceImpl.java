package com.weberp.app.services;

import java.util.List;
import java.util.stream.Collectors;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.Empresa;
import com.weberp.app.dto.config.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.TipoProducto;
import com.weberp.app.dto.TipoProductoDTO;
import com.weberp.app.repositories.TipoProductoRepository;
@Service
public class TipoProductoServiceImpl extends ConfigMapper implements TipoProductoService {


	TipoProducto tipoProducto;

	@Autowired
	private TipoProductoRepository tipoProductoRepository;


	
	@Override
	public Page<TipoProductoDTO> listaTipoProductos(Pageable pageable) {


		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();
		Page<TipoProducto> productoPage = tipoProductoRepository.findByEmpresa_Id(empresaId,pageable);


		final Page<TipoProductoDTO> contactDtoPage = productoPage.map(this::convertToDtoTipoProducto);
		return contactDtoPage;
	}

	@Override
	public TipoProducto guardar(TipoProducto tipoProducto) {
		Empresa empresa = UsuarioUtil.getCurrentUserEmpresa().getEmpresa();

		tipoProducto.setEmpresa(empresa);


		return tipoProductoRepository.save(tipoProducto);
	}

	@Override
	public TipoProducto getTipoProductoById(Long id) {
return tipoProductoRepository.findOne(id);

	}

	@Override
	public void borrar(Long id) {
		tipoProductoRepository.delete(id);

	}

	@Override
	public boolean existeTipoProducto(TipoProducto tipoProducto) {

		return tipoProductoRepository.findOne(tipoProducto.getId())!= null;
	}

	@Override
	public List<TipoProducto> buscarTipoProducto(TipoProductoDTO tipoProductoDTO) {

		return  null;
	}

	@Override
	public List<TipoProducto> listaTipoProductoPorEmpresa() {


		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

		return tipoProductoRepository.findAll().stream().filter(o -> o.getEmpresa().getId().equals(empresaId)).collect(Collectors.toList());
	}

	@Override
	public Page<TipoProductoDTO> listaTipoProductoPorEmpresa(Pageable pageable) {


		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();
		Page<TipoProducto> tipoProductoPage = tipoProductoRepository.findByEmpresa_Id(empresaId,pageable);

		final Page<TipoProductoDTO> contactDtoPage = tipoProductoPage.map(this::convertToDtoTipoProducto);
		return contactDtoPage;
	}

	@Override
	public Page<TipoProducto> findPaginated(int page, int size) {
		return tipoProductoRepository.findAll(new PageRequest(page,size));

	}

	@Override
	public Page<TipoProducto> findTipoProductoAndPaginated(String nombre, Pageable pageRequest) {

		 Page<TipoProducto> resultado= (Page<TipoProducto>) tipoProductoRepository.findByNombre(nombre,  pageRequest);

		 return  resultado;
	}



}
