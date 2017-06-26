package com.weberp.app;

import com.weberp.app.domain.DiarioGeneral;
import com.weberp.app.domain.Producto;
import com.weberp.app.reportes.IngresosMensualesReporte;
import com.weberp.app.services.DiarioGeneralService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.weberp.app.services.ProductoService;

import javax.validation.constraints.AssertTrue;
import java.text.ParseException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebErpApplicationTests {

	@Autowired
	ProductoService productoService;

	@Autowired
	DiarioGeneralService diarioGeneralService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void encodePassword() {
		String yourpassword = "12";

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(yourpassword);

		System.err.println(hashedPassword);
	}

	@Test
	public void testUnitRest() {
		
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.get("http://192.168.50.5:7001/api/aduanas/expedientesaduanales")
					  .header("accept", "application/json")
					  .asJson();
			
			
			//GetRequest request = Unirest.get("http://192.168.50.5:7001/api/aduanas/expedientesaduanales");
			
			System.err.println(jsonResponse.getBody());
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testArrayTwoDimension(){


		ConcurrentHashMap<String,Long> prodStock = new ConcurrentHashMap<String,Long>();

		List<Producto> productoList = productoService.listaProductos().stream().filter(o-> o.getCantidad() > 0).collect(Collectors.toList());


		for (Producto prod: productoList) {

			prodStock.put(prod.getNombre(),prod.getCantidad());


		}

		for(Map.Entry<String,Long> entrys : prodStock.entrySet()){

			System.err.println("key: "+entrys.getKey()+", Valor: "+entrys.getValue());

		}



	}



}
