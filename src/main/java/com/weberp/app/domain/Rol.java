package com.weberp.app.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity(name = "rol")
public class Rol extends Auditable<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2570126774793010955L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String rol;

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

	public Rol() {
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public Date getCreadoEn() {
		return creadoEn;
	}

	@Override
	public void setCreadoEn(Date creadoEn) {
		this.creadoEn = creadoEn;
	}

	@Override
	public String getCreadoPor() {
		return creadoPor;
	}

	@Override
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	@Override
	public Date getModificadoEn() {
		return modificadoEn;
	}

	@Override
	public void setModificadoEn(Date modificadoEn) {
		this.modificadoEn = modificadoEn;
	}

	@Override
	public String getModificadoPor() {
		return modificadoPor;
	}

	@Override
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", rol=" + rol + "]";
	}
}
