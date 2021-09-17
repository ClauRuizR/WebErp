package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.OrdenCompra;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {

	OrdenCompra findByIdAndEstatus(Long id, String estatus);
	
	List<OrdenCompra> findByEstatus(String estatus);

	Page<OrdenCompra> findByEmpresa_Id(Long empresaId,Pageable pageRequest);

	Page<OrdenCompra> findByNumeroOrdenCompra(String numeroOrdenCompra, Pageable pageCriteira);
}
