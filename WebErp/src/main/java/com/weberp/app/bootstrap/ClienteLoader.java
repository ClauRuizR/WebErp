package com.weberp.app.bootstrap;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.weberp.app.domain.Cliente;
import com.weberp.app.domain.Contacto;
import com.weberp.app.domain.Producto;
import com.weberp.app.domain.TipoProducto;
import com.weberp.app.repositories.ClienteRepository;
import com.weberp.app.repositories.ContactoRepository;
import com.weberp.app.repositories.ProductoRepository;
import com.weberp.app.repositories.TipoProductoRepository;




@Component
public class ClienteLoader implements ApplicationListener<ContextRefreshedEvent> {

	private ClienteRepository clienteRepository; 
	private ContactoRepository contactoRepository;

	
	private Logger log = Logger.getLogger(ClienteLoader.class);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	Cliente p1 = new Cliente();
	Cliente p2 = new Cliente();
	
	
	
	List<Contacto> contactoList = new ArrayList<>();
	Contacto contacto = new Contacto();
	contacto.setValor("@claudioruizr");
	contacto.setTipoContacto("Twitter");
	contacto.setEstado(1);
	
	
	p1.setNombre("Pedro Pablo");
	p1.setApellido("Leon Jaramillo");
	p1.setDni("El Capo");
	p1.setTipoDni(1L);
	
	LocalDate today = LocalDate.now();

	
	contactoList.add(contacto);
	
	p1.setContactos(contactoList);

	//clienteRepository.save(p1);
	
	
	p2.setNombre("Elias Pablo");
	p2.setApellido("Jimenez");
	p2.setDni("El Capo2");
	p2.setTipoDni(2L);

	
	//clienteRepository.save(p2);
//	log.info("Cliente - Id " + p2.getId());
		
	}

	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	@Autowired
	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public ContactoRepository getContactoRepository() {
		return contactoRepository;
	}

	@Autowired
	public void setContactoRepository(ContactoRepository contactoRepository) {
		this.contactoRepository = contactoRepository;
	}

}
