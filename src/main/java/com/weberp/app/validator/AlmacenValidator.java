package com.weberp.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.weberp.app.domain.Almacen;
import com.weberp.app.domain.DetalleAlmacen;

@Component
public class AlmacenValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Almacen.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre", "El nombre es obligatorio.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", "codigo", "El Codigo  es obligatorio.");


		Almacen almacen = (Almacen) target;

		if(null==almacen.getLocalidad()){
			errors.rejectValue("localidad", "localidad",
					"La localidad es obligatorio.");
		}
		for (int i = 0; i < almacen.getDetalleAlmacen().size(); i++) {
			DetalleAlmacen detalleAlmacen = almacen.getDetalleAlmacen().get(i);

			if (null == detalleAlmacen.getProducto().getId())
				errors.rejectValue("DetalleAlmacen[" + i + "].producto", "DetalleAlmacen[" + i + "].producto",
						"El producto es obligatorio.");

		}

	}

}
