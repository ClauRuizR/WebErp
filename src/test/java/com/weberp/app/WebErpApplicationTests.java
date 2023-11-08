package com.weberp.app;

import com.weberp.app.services.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

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
		String yourpassword = "12345678";

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		passwordEncoder.encode(yourpassword);

	}

	@Test
	public void testNumbers(){
		double valorTransado = 50_000_000.00;
		System.out.println(valorTransado);
		BigDecimal valorTransadoB  = new BigDecimal(50_000_000.00);
		System.out.println(valorTransadoB);
	}


}
