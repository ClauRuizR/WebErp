package com.weberp.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.TipoProducto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long> {

    Page<TipoProducto> findByEmpresa_Id(Long empresaId,Pageable pageCriteira);

    Page<TipoProducto> findByNombre(String nombre, Pageable pageCriteira);

}
