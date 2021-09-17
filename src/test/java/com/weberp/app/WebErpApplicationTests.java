package com.weberp.app;

import com.weberp.app.domain.ComprobanteFiscal;
import com.weberp.app.domain.DiarioGeneral;
import com.weberp.app.domain.Producto;
import com.weberp.app.dto.CuentasCobrarDTO;
import com.weberp.app.reportes.IngresosMensualesReporte;
import com.weberp.app.repositories.ComprobanteFiscalRepository;
import com.weberp.app.services.*;
import org.apache.poi.ss.formula.functions.T;
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

import javax.validation.constraints.AssertTrue;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebErpApplicationTests {

	@Autowired
	ProductoService productoService;

	@Autowired
	DiarioGeneralService diarioGeneralService;

	@Autowired
	private ComprobanteFiscalService comprobanteFiscalService;

	@Autowired
	private CuentasCobrarService cuentasCobrarService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void encodePassword() {
		String yourpassword = "12345678";

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(yourpassword);

	}

	@Test
	public void testNumbers(){
		double valorTransado = 50_000_000.00;
		System.out.println(valorTransado);
		BigDecimal valorTransadoB  = new BigDecimal(50_000_000.00);
		System.out.println(valorTransadoB);
	}


}
