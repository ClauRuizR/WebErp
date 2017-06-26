package com.weberp.app.validator;

import com.weberp.app.dto.GrupoEmpresaDTO;
import com.weberp.app.dto.TipoProductoDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class GrupoEmpresaValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return GrupoEmpresaDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre", "El nombre es obligatorio.");
		
	}

}
