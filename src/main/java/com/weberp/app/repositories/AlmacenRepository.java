package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.Almacen;

public interface AlmacenRepository extends CrudRepository<Almacen, Long> {

	Almacen findByPrincipalAndEstadoAndLocalidadEmpresa_Id(boolean principal, int estado,Long empresaId);

    List<Almacen> findByLocalidadEmpresa_Id(Long empresaId);

	Page<Almacen> findByLocalidadEmpresa_Id (Long empresaId, Pageable pageRequest);

	Page<Almacen> findByLocalidadEmpresaId(Long empresaId, Pageable pageRequest);

	Page<Almacen> findByCodigo(String codigo, Pageable pageCriteira);



}
