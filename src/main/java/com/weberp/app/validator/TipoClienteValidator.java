package com.weberp.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.weberp.app.dto.TipoClienteDTO;

@Component
public class TipoClienteValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return TipoClienteDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre", "El nombre es obligatorio.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", "codigo", "El codigo es obligatorio.");
	}

}
