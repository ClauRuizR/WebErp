package com.weberp.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.weberp.app.dto.TransferenciaAlmacen;

@Component
public class TransferenciaAlmacenValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return TransferenciaAlmacen.class.isAssignableFrom(arg0);

	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "almacenOrigen", "almacenOrigen",
				"El Almacen Origen es obligatorio.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productoId", "productoId", "El Producto es obligatorio.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cantidad", "cantidad", "La cantidad es obligatorio.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "almacenDestino", "almacenDestino",
				"El Almacen Destino es obligatorio.");

		TransferenciaAlmacen transferenciaAlmacen = (TransferenciaAlmacen) target;

		if (transferenciaAlmacen.getCantidad().equals(0L))
			errors.rejectValue("cantidad", "cantidad", "La cantidad no puede ser cero");

		if (transferenciaAlmacen.getCantidad() < 0)
			errors.rejectValue("cantidad", "cantidad", "La cantidad no puede ser negativo");

	}

}
