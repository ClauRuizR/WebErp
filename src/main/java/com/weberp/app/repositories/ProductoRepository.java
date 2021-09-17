package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.Producto;
import scala.Product$class;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Page<Producto> findByTipoProducto_Empresa_Id(Long empresaId,Pageable pageCriteira);

    List<Producto> findByTipoProducto_Empresa_Id(Long empresaId);

    Page<Producto> findByCodigoAlfanumerico(String codigoAlfaNumerico, Pageable pageCriteira);
}
