package com.weberp.app.bootstrap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.weberp.app.domain.TipoDocumento;
import com.weberp.app.repositories.TipoDocumentoRepository;

@Component
public class TipoDocumentoLoader implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;
	
	
	private Logger log = Logger.getLogger(TipoDocumentoLoader.class);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		TipoDocumento tipoDocumento = new TipoDocumento();
		
		tipoDocumento.setLlaveDocumento("FACT");
		tipoDocumento.setNumeroControl(1L);
		tipoDocumento.setDocumento("Factura");
		
//		tipoDocumentoRepository.save(tipoDocumento);
		
//		log.info("Tipo Docuento - Id " + tipoDocumento.getId());
		
		TipoDocumento tipoDocumento2 = new TipoDocumento();
		tipoDocumento2.setLlaveDocumento("COT");
		tipoDocumento2.setNumeroControl(1L);
		tipoDocumento2.setDocumento("Cotizacion");
		
	//	tipoDocumentoRepository.save(tipoDocumento2);
		
//		log.info("Tipo Docuento - Id " + tipoDocumento2.getId());
		
		TipoDocumento tipoDocumento3 = new TipoDocumento();
		tipoDocumento3.setLlaveDocumento("OC");
		tipoDocumento3.setNumeroControl(1L);
		tipoDocumento3.setDocumento("Orden de Compra");
		
	//	tipoDocumentoRepository.save(tipoDocumento3);
		
//		log.info("Tipo Docuento - Id " + tipoDocumento3.getId());


		TipoDocumento tipoDocumento4 = new TipoDocumento();
		tipoDocumento4.setLlaveDocumento("NC");
		tipoDocumento4.setNumeroControl(1L);
		tipoDocumento4.setDocumento("Nota de Credito");

	//		tipoDocumentoRepository.save(tipoDocumento4);

//		log.info("Tipo Docuento - Id " + tipoDocumento4.getId());

		TipoDocumento tipoDocumento5 = new TipoDocumento();
		tipoDocumento5.setLlaveDocumento("ND");
		tipoDocumento5.setNumeroControl(1L);
		tipoDocumento5.setDocumento("Nota de Debito");

	//		tipoDocumentoRepository.save(tipoDocumento5);

		log.info("Tipo Docuento - Id " + tipoDocumento5.getId());


		TipoDocumento tipoDocumento6= new TipoDocumento();
		tipoDocumento6.setLlaveDocumento("AB");
		tipoDocumento6.setNumeroControl(1L);
		tipoDocumento6.setDocumento("Abono");

		//	tipoDocumentoRepository.save(tipoDocumento6);

//		log.info("Tipo Docuento - Id " + tipoDocumento6.getId());

	}

	public TipoDocumentoRepository getTipoDocumentoRepository() {
		return tipoDocumentoRepository;
	}

	public void setTipoDocumentoRepository(TipoDocumentoRepository tipoDocumentoRepository) {
		this.tipoDocumentoRepository = tipoDocumentoRepository;
	}
	
	
	
	

}
