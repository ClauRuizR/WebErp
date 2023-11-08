package com.weberp.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.weberp.app.domain.Factura;

/**
 * Created by claudioruiz on 7/27/16.
 */
public interface FacturaRepository extends JpaRepository<Factura, Long> {

	Factura findByIdAndEstatus(Long id, String estatus);
	
	List<Factura> findByEstatus(String estatus);

	Page<Factura> findByEmpresa_Id(Long empresaId,Pageable pageRequest);

	Page<Factura> findByNumeroDocumento(String numeroDocumento, Pageable pageCriteira);

}
