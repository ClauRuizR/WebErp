package com.weberp.app.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.weberp.app.domain.Proveedor;
import com.weberp.app.repositories.ProveedorRepository;

@Component
public class ProveedorLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ProveedorRepository proveedorRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		Proveedor proveedor = new Proveedor();
		proveedor.setNombre("Rotulpak");
	//	proveedorRepository.save(proveedor);

	}

	public ProveedorRepository getProveedorRepository() {
		return proveedorRepository;
	}

	public void setProveedorRepository(ProveedorRepository proveedorRepository) {
		this.proveedorRepository = proveedorRepository;
	}

}
