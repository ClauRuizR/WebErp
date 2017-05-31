package com.weberp.app.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.weberp.app.domain.Producto;

@Component
public class ProductoValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Producto.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre", "El nombre es obligatorio.");
		ValidationUtils.rejectIfEmpty(errors, "precio", "precio", "El precio es obligatorio.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigoAlfanumerico", "codigoAlfanumerico",
				"El Codigo Alfanumerico es obligatorio.");

		Producto producto = (Producto) target;

		if (null == producto.getTipoProducto())
			errors.rejectValue("producto", "producto", "El Tipo Producto es obligatorio.");

		if (producto.getPrecio().equals(BigDecimal.ZERO))
			errors.rejectValue("precio", "precio", "El precio no puede ser cero");

		if (BigDecimal.ZERO.compareTo(producto.getPrecio()) > 0)
			errors.rejectValue("precio", "precio", "El precio no puede ser negativo");

	}

}
