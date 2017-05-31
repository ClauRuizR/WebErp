package com.weberp.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.weberp.app.domain.Contacto;
import com.weberp.app.utils.EmailValidator;

@Component
public class ContactoValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Contacto.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Contacto contacto = (Contacto) target;

		if (contacto.getTipoContacto().equals("email") && contacto.getValor().equals(""))
			errors.rejectValue("contactos", "valor", "El Email es obligatorio");
		
		if (contacto.getTipoContacto().equals("email") && !EmailValidator.validate(contacto.getValor()))
			errors.rejectValue("contactos", "valor", "El Email no es valido");

	}

}
