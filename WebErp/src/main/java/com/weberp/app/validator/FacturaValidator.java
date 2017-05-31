package com.weberp.app.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.weberp.app.domain.DetalleFactura;
import com.weberp.app.domain.Factura;

@Component
public class FacturaValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Factura.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Factura factura = (Factura) target;

		if (factura.getTipoDocumento().getLlaveDocumento().equals(""))
			errors.rejectValue("tipoDocumento", "tipoDocumento", "El Tipo Documento es obligatorio.");
		if (null == factura.getCliente().getId())
			errors.rejectValue("cliente", "cliente", "El cliente es obligatorio.");

		if (BigDecimal.ZERO.compareTo(factura.getDescuento()) > 0)
			errors.rejectValue("descuento", "descuento", "El descuento no puede ser negativo");

		for (int i = 0; i < factura.getDetalleFactura().size(); i++) {
			DetalleFactura detalleFactura = factura.getDetalleFactura().get(i);

			if (null == detalleFactura.getProducto().getId())
				errors.rejectValue("DetalleFactura[" + i + "].producto", "DetalleFactura[" + i + "].producto",
						"El producto es obligatorio.");

			if (null==detalleFactura.getPrecio())
				errors.rejectValue("DetalleFactura[" + i + "].precio", "DetalleFactura[" + i + "].precio",
						"El precio es obligatorio.");
			
			if (detalleFactura.getPrecio().equals(BigDecimal.ZERO))
				errors.rejectValue("DetalleFactura[" + i + "].precio", "DetalleFactura[" + i + "].precio",
						"El precio no puede ser cero.");
			
			if (BigDecimal.ZERO.compareTo(detalleFactura.getPrecio()) > 0)
				errors.rejectValue("DetalleFactura[" + i + "].precio", "DetalleFactura[" + i + "].precio",
						"El precio no puede ser negativo.");

			if (null==detalleFactura.getCantidad())
				errors.rejectValue("DetalleFactura[" + i + "].cantidad", "DetalleFactura[" + i + "].cantidad",
						"La cantidad es obligatorio.");
			
			if (detalleFactura.getCantidad().equals(0L))
				errors.rejectValue("DetalleFactura[" + i + "].cantidad", "DetalleFactura[" + i + "].cantidad",
						"La cantidad no puede ser cero.");
			if (detalleFactura.getCantidad() < 0)
				errors.rejectValue("DetalleFactura[" + i + "].cantidad", "DetalleFactura[" + i + "].cantidad",
						"La cantidad no puede ser negativo.");

		}
	}

}
