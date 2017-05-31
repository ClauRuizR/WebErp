package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.Reporte;

public interface ReporteService {
	List<Reporte>listaReporte();

	Reporte guardar(Reporte reporte);
	
	Reporte getReporteById(Long id);
	
	void borrar(Long id);
	
}
