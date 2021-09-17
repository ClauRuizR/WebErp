package com.weberp.app.validator;

import com.weberp.app.domain.TipoNcf;
import com.weberp.app.dto.TipoClienteDTO;
import com.weberp.app.dto.TipoNcfDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TipoNcfValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return TipoNcfDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre", "El nombre es obligatorio.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", "codigo", "El codigo es obligatorio.");


		TipoNcfDTO tipoNcfDTO = (TipoNcfDTO)target;


		if (null==tipoNcfDTO.getComprobanteFiscal())
			errors.rejectValue("tipoNcf",
					"El Comprobante Fiscal es obligatorio.");



	}

}
