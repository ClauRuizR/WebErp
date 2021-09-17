package com.weberp.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.Empresa;
import com.weberp.app.domain.Usuario;
import com.weberp.app.dto.config.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.Proveedor;
import com.weberp.app.dto.ProveedorDTO;
import com.weberp.app.repositories.ProveedorRepository;

@Service
public class ProveedorServiceImpl extends ConfigMapper implements ProveedorService {
	
	@Autowired
	ProveedorRepository proveedorRepository;

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private EntityManager entityManager;
	@Override
	public List<Proveedor> getProveedores() {


		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

		return proveedorRepository.findByEmpresa_Id(empresaId);
	}

	@Override
	public Proveedor getProveedorById(Long id) {
		return proveedorRepository.findOne(id);
	}

	@Override
	public Proveedor guardar(Proveedor proveedor) {


		Empresa empresa = usuarioService.findByUsuario(UsuarioUtil.getCurrentUser()).getEmpresa();
		proveedor.setEmpresa(empresa);
		return proveedorRepository.save(proveedor);
	}

	@Override
	public List<Proveedor> buscarProveedor(ProveedorDTO proveedorDTO) {
		 CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Proveedor> cq = cb.createQuery(Proveedor.class);
	        Root<Proveedor> rootProveedor = cq.from(Proveedor.class);

	        List<Predicate> predicates = new ArrayList<Predicate>();
	        if(!proveedorDTO.getNombre().equals("")){
	        	predicates.add(cb.like(rootProveedor.get("nombre"),"%"+proveedorDTO.getNombre()+"%"));		
	        }
	        cq.select(rootProveedor).where(predicates.toArray(new Predicate[]{}));
	        
	        List<Proveedor> paroveedorList = new ArrayList<>();
	        paroveedorList = entityManager.createQuery(cq).getResultList();
			return paroveedorList;
	}

	@Override
	public Page<ProveedorDTO> findPaginated(int page, int size) {
		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();
		Page<Proveedor> clientePage=  (proveedorRepository.findByEmpresa_Id(empresaId,new PageRequest(page,size, Sort.Direction.ASC,"id")));

		final Page<ProveedorDTO> contactDtoPage = clientePage.map(this::convertProveedorToDto);
		return contactDtoPage;
	}

	@Override
	public Page<ProveedorDTO> findProveedorAndPaginated(String nombre, Pageable pageRequest) {
		Page<Proveedor> facturaPage=  proveedorRepository.findByNombre(nombre,pageRequest);

		final Page<ProveedorDTO> contactDtoPage = facturaPage.map(this::convertProveedorToDto);
		return contactDtoPage;
	}


}
