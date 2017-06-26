package com.weberp.app.services;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.weberp.app.domain.DiarioGeneral;
import com.weberp.app.dto.DiarioGeneralDTO;
import com.weberp.app.reportes.IngresosMensualesReporte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiarioGeneralService {
	Page<DiarioGeneralDTO> listaDiarioGeneral(Pageable pageable);
	List<DiarioGeneral> listaDiarioGeneral();
	DiarioGeneral guardar(DiarioGeneral diarioGeneral);

	DiarioGeneral getDiarioGeneralById(Long id);

	void borrar(Long id);


	Page<DiarioGeneralDTO> findPaginated(int page, int size);

	Page<DiarioGeneralDTO> findDiarioGeneralAndPaginated(String fechaDesde,String fechaHasta, Pageable pageRequest) throws ParseException;

	List<Double> getIngresosMensuales() throws ParseException;

}
