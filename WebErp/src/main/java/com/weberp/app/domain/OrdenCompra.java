package com.weberp.app.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.weberp.app.enums.EstatusEnum;

@Entity(name = "orden_compra")
public class OrdenCompra extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String numeroOrdenCompra;

	private String estatus = EstatusEnum.PENDIENTE;

	private Integer estado = 1;

	@Transient
	private String estatusNombre;

	@ManyToOne
	private Proveedor proveedor;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<DetalleOrdenCompra> DetalleOrdenCompra = new ArrayList<>();

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

	@Transient
	private BigDecimal montoTotal = new BigDecimal(0);

	public BigDecimal getMontoTotal() {
		BigDecimal resultado = BigDecimal.ZERO;
		for (int i = 0; i < getDetalleOrdenCompra().size(); i++) {
			resultado = resultado.add(getDetalleOrdenCompra().get(i).getMonto());
		}

		return resultado;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroOrdenCompra() {
		return numeroOrdenCompra;
	}

	public void setNumeroOrdenCompra(String numeroOrdenCompra) {
		this.numeroOrdenCompra = numeroOrdenCompra;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public List<DetalleOrdenCompra> getDetalleOrdenCompra() {
		return DetalleOrdenCompra;
	}

	public void setDetalleOrdenCompra(List<DetalleOrdenCompra> detalleOrdenCompra) {
		DetalleOrdenCompra = detalleOrdenCompra;
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

	public String getEstatusNombre() {
		switch (getEstatus()) {
		case EstatusEnum.PENDIENTE:
			return "Pendiente";

		case EstatusEnum.APROBADO:
			return "Aprobado";

		case EstatusEnum.PAGADA:
			return "Pagada";

		}
		return estatusNombre;
	}

	public void setEstatusNombre(String estatusNombre) {
		this.estatusNombre = estatusNombre;
	}

}
