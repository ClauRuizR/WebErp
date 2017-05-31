package com.weberp.app.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

@Entity(name = "factura")
public class Factura extends Auditable<String> {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private boolean comprobanteFiscal = false;

	private String numeroComprobanteFiscal;

	private String numeroDocumento;

	private BigDecimal descuento = BigDecimal.ZERO;

	private Date fecha;

	private String estatus = EstatusEnum.PENDIENTE;

	private Integer estado = 1;

	@Transient
	private String estatusNombre;
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

	@ManyToOne
	private Cliente cliente;
	@OneToOne()
	private TipoDocumento tipoDocumento;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<DetalleFactura> DetalleFactura = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public List<DetalleFactura> getDetalleFactura() {
		return DetalleFactura;
	}

	public void setDetalleFactura(List<DetalleFactura> detalleFactura) {
		DetalleFactura = detalleFactura;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getNumeroComprobanteFiscal() {
		return numeroComprobanteFiscal;
	}

	public void setNumeroComprobanteFiscal(String numeroComprobanteFiscal) {
		this.numeroComprobanteFiscal = numeroComprobanteFiscal;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isComprobanteFiscal() {
		return comprobanteFiscal;
	}

	public void setComprobanteFiscal(boolean comprobanteFiscal) {
		this.comprobanteFiscal = comprobanteFiscal;
	}

	public BigDecimal getMontoTotal() {
		BigDecimal resultado = BigDecimal.ZERO;
		for (int i = 0; i < getDetalleFactura().size(); i++) {
			resultado = resultado.add(getDetalleFactura().get(i).getTotal());
		}
		resultado = resultado.subtract(getDescuento());
		return resultado;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
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

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}
