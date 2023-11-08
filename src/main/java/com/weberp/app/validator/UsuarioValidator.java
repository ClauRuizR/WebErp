package com.weberp.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.weberp.app.domain.Usuario;

@Component
public class UsuarioValidator implements Validator {

	
	
	@Override
	public boolean supports(Class<?> arg0) {
		return Usuario.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {

		
	}

}
