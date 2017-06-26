package com.weberp.app.validator;

import com.weberp.app.dto.EmpresaDTO;
import com.weberp.app.dto.GrupoEmpresaDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmpresaValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return EmpresaDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre", "El nombre es obligatorio.");


		
	}

}
