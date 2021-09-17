package com.weberp.app.dto;

import com.weberp.app.domain.Producto;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;
import com.weberp.app.services.AlmacenService;
import com.weberp.app.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

@EntityMapper
public class ProductoDTO extends ParsableObject<Producto,ProductoDTO> {

	@Autowired
	private ProductoService productoService;

	@Mapping
	private Long id;

	@Mapping
	private String nombre;
	@Mapping
	private String descripcion;

	@Mapping
	private BigDecimal precioVenta = BigDecimal.ZERO;


	@Mapping
	private BigDecimal precioCompra = BigDecimal.ZERO;

	@Mapping
	private TipoProductoDTO tipoProducto = new TipoProductoDTO();
	@Mapping
	private String codigoAlfanumerico;
	@Mapping
	private Integer estado;

	@Mapping
	private Long cantidad;

	@Mapping
	private Date creadoEn;

	@Mapping
	private String creadoPor;

	@Mapping
	private Date modificadoEn;

	@Mapping
	private String modificadoPor;

	@Mapping
	private Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}

	public TipoProductoDTO getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProductoDTO tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getCodigoAlfanumerico() {
		return codigoAlfanumerico;
	}

	public void setCodigoAlfanumerico(String codigoAlfanumerico) {
		this.codigoAlfanumerico = codigoAlfanumerico;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Long getCantidad() {
	 return cantidad;

	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Date getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(Date creadoEn) {
		this.creadoEn = creadoEn;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getModificadoEn() {
		return modificadoEn;
	}

	public void setModificadoEn(Date modificadoEn) {
		this.modificadoEn = modificadoEn;
	}

	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public Class<Producto> getDomainClass() {
		return Producto.class;
	}
}
