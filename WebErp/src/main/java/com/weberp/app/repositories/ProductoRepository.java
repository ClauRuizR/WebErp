package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
