package com.weberp.app.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity(name = "detalle_factura")
public class DetalleFactura extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private BigDecimal monto = new BigDecimal(0);

	private Long cantidad = 0L;


	private BigDecimal total;

	private BigDecimal precio = new BigDecimal(0);

	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
	@ManyToOne
	@JoinColumn(name = "factura_id")
	private Factura factura;

	private BigDecimal itbis= new BigDecimal(0);
	@NotNull
	private BigDecimal importe= new BigDecimal(0);

	@NotNull
	private BigDecimal subTotal= new BigDecimal(0);

	@NotNull
	private BigDecimal descuento= new BigDecimal(0);

	private Integer estado = 1;
	@CreatedDate
	private Date creadoEn;
	@CreatedBy
	private String creadoPor;
	@LastModifiedDate
	private Date modificadoEn;
	@LastModifiedBy
	private String modificadoPor;
	@Version
	private Long version;

	public BigDecimal getTotal() {
		if (getCantidad() != null && getPrecio() != null) {
			return new BigDecimal(getCantidad()).multiply(getPrecio());	
		}
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getItbis() {
		return itbis;
	}

	public void setItbis(BigDecimal itbis) {
		this.itbis = itbis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
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

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	
}


