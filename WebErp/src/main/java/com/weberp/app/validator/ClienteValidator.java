package com.weberp.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.weberp.app.domain.Cliente;
import com.weberp.app.utils.EmailValidator;

@Component
public class ClienteValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Cliente.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre", "El nombre es obligatorio.");

		ValidationUtils.rejectIfEmpty(errors, "apellido", "apellido", "El apellido es obligatorio.");
		ValidationUtils.rejectIfEmpty(errors, "sexo", "sexo", "El sexo es obligatorio.");

		Cliente cliente = (Cliente) target;

		if(cliente.getContactos().size()==0)
			errors.rejectValue("contactos", "contactos", "Favor agregar al menos un contracto");


		for (int i = 0; i < cliente.getContactos().size(); i++) {

			if (cliente.getContactos().get(i).getTipoContacto().equals("email")
					&& cliente.getContactos().get(i).getValor().equals(""))
				errors.rejectValue("contactos[" + i + "].valor", "valor", "El Email es obligatorio");

			if (cliente.getContactos().get(i).getTipoContacto().equals("email")
					&& !EmailValidator.validate(cliente.getContactos().get(i).getValor()))
				errors.rejectValue("contactos[" + i + "].valor", "contactos[" + i + "].valor", "El email no es valido");

		}
	}

}
