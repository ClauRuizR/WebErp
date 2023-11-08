package com.weberp.app.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.weberp.app.dto.ServicioDTO;

@Component
public class ServicioValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return ServicioDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre", "El nombre es obligatorio.");

		ServicioDTO servicioDTO = (ServicioDTO)target;


		if (null==servicioDTO.getCosto())
			errors.rejectValue("costo",
					"El costo es obligatorio.");

		if (servicioDTO.getCosto().equals(BigDecimal.ZERO))
			errors.rejectValue("costo",
					"El costo no puede ser cero.");


		if (BigDecimal.ZERO.compareTo(servicioDTO.getCosto()) > 0)
			errors.rejectValue("costo",
					"El costo no puede ser negativo.");

	}

}
