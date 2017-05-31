package com.weberp.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

	@CreatedBy
	@Column(insertable = true, updatable = false)
	protected U creadoPor;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = true, updatable = false)
	protected Date creadoEn;

	@LastModifiedBy
	@Column(insertable = false, updatable = true)
	protected U modificadoPor;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = true)
	protected Date modificadoEn;

	public U getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(U creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(Date creadoEn) {
		this.creadoEn = creadoEn;
	}

	public U getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(U modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Date getModificadoEn() {
		return modificadoEn;
	}

	public void setModificadoEn(Date modificadoEn) {
		this.modificadoEn = modificadoEn;
	}

}
