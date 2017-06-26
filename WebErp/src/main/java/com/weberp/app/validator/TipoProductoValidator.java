package com.weberp.app.validator;

import com.weberp.app.dto.TipoProductoDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.weberp.app.domain.TipoProducto;

@Component
public class TipoProductoValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return TipoProductoDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre", "El nombre es obligatorio.");
		
	}

}
