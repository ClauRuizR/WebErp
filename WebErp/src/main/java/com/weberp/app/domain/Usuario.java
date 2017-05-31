package com.weberp.app.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity(name = "usuario")
public class Usuario extends Auditable<String> implements Serializable  {

	private static final long serialVersionUID = -486827370610335742L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String usuario;

	@Column(nullable = false)
	private String clave;

	private String nombre;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "rol_id") })
	private Set<Rol> roles = new HashSet<>();

	private Integer estado;

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



	public Usuario() {
		estado = 1;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", clave=" + clave + ", nombre=" + nombre + ", roles="
				+ roles + ", activo=" + (estado.equals(1)) + "]";
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		if (estado == null) {
			return;
		}

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
}
