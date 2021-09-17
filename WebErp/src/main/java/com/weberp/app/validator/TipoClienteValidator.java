package com.weberp.app.validator;

import com.weberp.app.dto.ServicioDTO;
import com.weberp.app.dto.TipoClienteDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

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


		TipoClienteDTO tipoClienteDTO = (TipoClienteDTO)target;


//		if (null==tipoClienteDTO.getTipoNcf().getId())
//			errors.rejectValue("tipoNcf",
//					"El Tipo Ncf es obligatorio.");



	}

}
