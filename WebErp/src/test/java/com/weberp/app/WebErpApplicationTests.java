package com.weberp.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.weberp.app.services.ProductoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebErpApplicationTests {

	@Autowired
	ProductoService productoService;

	@Test
	public void contextLoads() {
	}

	
	@Test
	public void encodePassword(){
		String yourpassword = "12";
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(yourpassword);
		
		System.err.println(hashedPassword);
	}
	

}
