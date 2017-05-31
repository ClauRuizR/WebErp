package com.weberp.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.Proveedor;
import com.weberp.app.dto.ProveedorDTO;
import com.weberp.app.repositories.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService {
	
	@Autowired
	ProveedorRepository proveedorRepository;
	@Autowired
	private EntityManager entityManager;
	@Override
	public List<Proveedor> getProveedores() {
		return proveedorRepository.findAllByOrderByNombre();
	}

	@Override
	public Proveedor getProveedorById(Long id) {
		return proveedorRepository.findOne(id);
	}

	@Override
	public Proveedor guardar(Proveedor proveedor) {
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

	
	
}
