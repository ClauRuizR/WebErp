package com.weberp.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.weberp.app.domain.TipoProducto;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long> {

    Page<TipoProducto> findByEmpresa_Id(Long empresaId,Pageable pageCriteira);

    Page<TipoProducto> findByNombre(String nombre, Pageable pageCriteira);

}
