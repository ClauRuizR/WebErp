package com.weberp.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.weberp.app.domain.Proveedor;
import com.weberp.app.utils.EmailValidator;

@Component
public class ProveedorValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Proveedor.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre", "El nombre es obligatorio.");


		Proveedor proveedor = (Proveedor) target;

		for (int i = 0; i < proveedor.getContactos().size(); i++) {

			if (proveedor.getContactos().get(i).getTipoContacto().equals("email")
					&& proveedor.getContactos().get(i).getValor().equals(""))
				errors.rejectValue("contactos[" + i + "].valor", "valor", "El Email es obligatorio");

			if (proveedor.getContactos().get(i).getTipoContacto().equals("email")
					&& !EmailValidator.validate(proveedor.getContactos().get(i).getValor()))
				errors.rejectValue("contactos[" + i + "].valor", "contactos[" + i + "].valor", "El email no es valido");

		}
	}

}
