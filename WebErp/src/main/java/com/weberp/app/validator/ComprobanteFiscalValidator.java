package com.weberp.app.validator;

import com.weberp.app.dto.ComprobanteFiscalDTO;
import com.weberp.app.dto.TipoNcfDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ComprobanteFiscalValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return ComprobanteFiscalDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "letra", "letra", "El campo Letra es obligatorio.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroBase", "numeroBase", "El campo numero base es obligatorio.");





	}

}
