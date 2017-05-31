package com.weberp.app.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.weberp.app.domain.DetalleOrdenCompra;
import com.weberp.app.domain.OrdenCompra;

@Component
public class OrdenCompraValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {

		return OrdenCompra.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		
		OrdenCompra ordenCompra = (OrdenCompra) target;


		if (null == ordenCompra.getProveedor().getId())
			errors.rejectValue("proveedor", "proveedor", "El proveedor es obligatorio.");

		

		for (int i = 0; i < ordenCompra.getDetalleOrdenCompra().size(); i++) {
			DetalleOrdenCompra detalleOrdenCompra = ordenCompra.getDetalleOrdenCompra().get(i);

			if (null == detalleOrdenCompra.getProducto().getId())
				errors.rejectValue("DetalleOrdenCompra[" + i + "].producto", "DetalleOrdenCompra[" + i + "].producto",
						"El producto es obligatorio.");

			if (null==detalleOrdenCompra.getCantidad())
				errors.rejectValue("DetalleOrdenCompra[" + i + "].cantidad", "DetalleOrdenCompra[" + i + "].cantidad",
						"La cantidad es obligatorio.");
			
			if (detalleOrdenCompra.getCantidad().equals(0L))
				errors.rejectValue("DetalleOrdenCompra[" + i + "].cantidad", "DetalleOrdenCompra[" + i + "].cantidad",
						"La cantidad no puede estar e cero.");
			
			if (detalleOrdenCompra.getCantidad() < 0L)
				errors.rejectValue("DetalleOrdenCompra[" + i + "].cantidad", "DetalleOrdenCompra[" + i + "].cantidad",
						"La cantidad no puede ser negativo.");

			if (null==detalleOrdenCompra.getMonto())
				errors.rejectValue("DetalleOrdenCompra[" + i + "].monto", "DetalleOrdenCompra[" + i + "].monto",
						"El monto es obligatorio.");
			
			if (detalleOrdenCompra.getMonto().equals(BigDecimal.ZERO))
				errors.rejectValue("DetalleOrdenCompra[" + i + "].monto", "DetalleOrdenCompra[" + i + "].monto",
						"El monto no puede estar en cero.");
			if (BigDecimal.ZERO.compareTo(detalleOrdenCompra.getMonto()) > 0)
				errors.rejectValue("DetalleOrdenCompra[" + i + "].monto", "DetalleOrdenCompra[" + i + "].monto",
						"El monto no puede ser negativo.");

		}

	}

}
