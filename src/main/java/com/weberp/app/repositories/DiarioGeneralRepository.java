package com.weberp.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.weberp.app.domain.DiarioGeneral;

import java.util.Date;
import java.util.List;

public interface DiarioGeneralRepository extends JpaRepository<DiarioGeneral, Long> {

    Page<DiarioGeneral> findByFechaBetween(Date fechaDesde,Date fechaHasta, Pageable pageCriteira);

    List<DiarioGeneral> findByFechaBetween(Date fechaInicio, Date fechaFin);


    List<DiarioGeneral> findByEmpresa_Id(Long empresaId);
    Page<DiarioGeneral> findByEmpresa_Id(Long empresaId, Pageable pageable);

}
