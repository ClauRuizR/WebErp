package com.weberp.app.validator;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.weberp.app.dto.ProductoDTO;
import com.weberp.app.services.TipoProductoService;

@Component
public class ProductoValidator implements Validator {

	@Autowired
	private TipoProductoService tipoProductoService;

	@Override
	public boolean supports(Class<?> arg0) {
		return ProductoDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre", "El nombre es obligatorio.");
		ValidationUtils.rejectIfEmpty(errors, "precioCompra", "precioCompra", "El precio de compra es obligatorio.");


		ValidationUtils.rejectIfEmpty(errors, "precioVenta", "precioVenta", "El precio de venta es obligatorio.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigoAlfanumerico", "codigoAlfanumerico",
				"El Codigo Alfanumerico es obligatorio.");

		ProductoDTO producto = (ProductoDTO) target;

		if (null == producto.getTipoProducto())
			errors.rejectValue("producto", "producto", "El Tipo Producto es obligatorio.");

		if (producto.getPrecioCompra().equals(BigDecimal.ZERO))
			errors.rejectValue("precioCompra", "precioCompra", "El precio de compra no puede ser cero");

		if (BigDecimal.ZERO.compareTo(producto.getPrecioCompra()) > 0)
			errors.rejectValue("precioCompra", "precioCompra", "El precio de compra no puede ser negativo");

		boolean isFacturable = tipoProductoService.getTipoProductoById(producto.getTipoProducto().getId()).isFacturable();

		if(isFacturable) {

			if (producto.getPrecioVenta().equals(BigDecimal.ZERO))
				errors.rejectValue("precioVenta", "precioVenta", "El precio de venta no puede ser cero");

			if (BigDecimal.ZERO.compareTo(producto.getPrecioVenta()) > 0)
				errors.rejectValue("precioVenta", "precioVenta", "El precio de venta no puede ser negativo");
		}
	}

}
