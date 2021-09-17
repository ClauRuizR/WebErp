package com.weberp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.Reporte;
import com.weberp.app.repositories.ReporteRepository;

@Service
public class ReporteServiceImpl implements ReporteService {

	@Autowired
	private ReporteRepository reporteRepository;

	@Override
	public List<Reporte> listaReporte() {
		// TODO Auto-generated method stub
		return (List<Reporte>) reporteRepository.findAll();
	}

	@Override
	public Reporte guardar(Reporte reporte) {
		// TODO Auto-generated method stub
		return reporteRepository.save(reporte);
	}

	@Override
	public Reporte getReporteById(Long id) {
		// TODO Auto-generated method stub
		return reporteRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		reporteRepository.delete(id);
	}

}
