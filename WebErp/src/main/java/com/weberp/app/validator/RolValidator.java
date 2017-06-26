package com.weberp.app.validator;

import com.weberp.app.dto.RolDTO;
import com.weberp.app.dto.ServicioDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Component
public class RolValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return RolDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rol", "rol", "El rol es obligatorio.");

		RolDTO rolDTO	 = (RolDTO)target;



	}

}
