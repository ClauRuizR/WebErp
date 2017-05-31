package com.weberp.app.bootstrap;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.weberp.app.domain.Producto;
import com.weberp.app.domain.TipoProducto;
import com.weberp.app.repositories.ProductoRepository;
import com.weberp.app.repositories.TipoProductoRepository;

@Component
public class ProductoLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private TipoProductoRepository tipoProductoRepository;

	@Override

	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		TipoProducto tipoProducto = new TipoProducto();

		tipoProducto.setNombre("Taza");
		//tipoProductoRepository.save(tipoProducto);

		Producto producto = new Producto();

		producto.setNombre("Taza Verde");
		producto.setDescripcion("Magica");

		producto.setCodigoAlfanumerico(StringUtils.leftPad("12345", 5, "0"));
		producto.setTipoProducto(tipoProducto);
		producto.setPrecio(new BigDecimal(250));
		// productoRepository.save(producto);
	}

	public ProductoRepository getProductoRepository() {
		return productoRepository;
	}

	public void setProductoRepository(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	public TipoProductoRepository getTipoProductoRepository() {
		return tipoProductoRepository;
	}

	public void setTipoProductoRepository(TipoProductoRepository tipoProductoRepository) {
		this.tipoProductoRepository = tipoProductoRepository;
	}

}
