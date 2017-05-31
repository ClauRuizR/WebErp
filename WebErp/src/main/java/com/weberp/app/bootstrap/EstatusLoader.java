package com.weberp.app.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.weberp.app.domain.Estatus;
import com.weberp.app.repositories.EstatusRepository;

@Component
public class EstatusLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private EstatusRepository estatusRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		Estatus estatus = new Estatus();
		Estatus estatus2 = new Estatus();
		Estatus estatus3 = new Estatus();
		Estatus estatus4 = new Estatus();
		Estatus estatus5 = new Estatus();
		
		estatus.setEstatus("P");
		estatus.setNombreEstatus("Pendiente");
		estatus.setProximoEstatus("P");
		estatus.setNombreProximoEstatus("Pendiente");

		//estatusRepository.save(estatus);

		estatus2.setEstatus("P");
		estatus2.setNombreEstatus("Pendiente");
		estatus2.setProximoEstatus("A");
		estatus2.setNombreProximoEstatus("Aprobada");

		//estatusRepository.save(estatus2);
		
		
		
		estatus3.setEstatus("A");
		estatus3.setNombreEstatus("Aprobada");
		estatus3.setProximoEstatus("A");
		estatus3.setNombreProximoEstatus("Aprobada");

		//estatusRepository.save(estatus3);
		
		
		estatus4.setEstatus("A");
		estatus4.setNombreEstatus("Aprobada");
		estatus4.setProximoEstatus("X");
		estatus4.setNombreProximoEstatus("Pagada");

	//	estatusRepository.save(estatus4);
		
		estatus5.setEstatus("X");
		estatus5.setNombreEstatus("Pagada");
		estatus5.setProximoEstatus("X");
		estatus5.setNombreProximoEstatus("Pagada");

	//	estatusRepository.save(estatus5);

	}

	public EstatusRepository getEstatusRepository() {
		return estatusRepository;
	}

	public void setEstatusRepository(EstatusRepository estatusRepository) {
		this.estatusRepository = estatusRepository;
	}

}
