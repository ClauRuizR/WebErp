package com.weberp.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.weberp.app.dto.RolDTO;

@Component
public class RolValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return RolDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rol", "rol", "El rol es obligatorio.");



	}

}
