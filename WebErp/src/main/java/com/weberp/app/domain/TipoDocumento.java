package com.weberp.app.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity(name = "tipo_documento")
public class TipoDocumento extends Auditable<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "DOCUMENTO")
	private String documento;

	@Column(name = "LLAVE_DOCUMENTO")
	private String llaveDocumento;

	@Column(name = "NUMERO_CONTROL")
	private Long numeroControl;

	@Column(name = "ESTADO")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getLlaveDocumento() {
		return llaveDocumento;
	}

	public void setLlaveDocumento(String llaveDocumento) {
		this.llaveDocumento = llaveDocumento;
	}

	public Long getNumeroControl() {
		return numeroControl;
	}

	public void setNumeroControl(Long numeroControl) {
		this.numeroControl = numeroControl;
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

}
