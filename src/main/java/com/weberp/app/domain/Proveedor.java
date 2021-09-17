package com.weberp.app.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity(name = "proveedor")
public class Proveedor extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nombre;
	private String dni;
	private Long tipoDni;
	private String direccion;
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
	@Transient
	private String contacto;



	@OneToOne
	@JoinColumn(name="empresa_id")
	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "contacto_proveedor", joinColumns = { @JoinColumn(name = "proveedor_id") }, inverseJoinColumns = {
			@JoinColumn(name = "contacto_id") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Contacto> contactos = new ArrayList<>();

	public List<Contacto> getContactos() {
		return contactos;
	}

	public Date getCreadoEn() {
		return creadoEn;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getDni() {
		return dni;
	}

	public int getEstado() {
		return estado;
	}

	public Long getId() {
		return this.id;
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

	public String getNombre() {
		return nombre;
	}

	public Long getTipoDni() {
		return tipoDni;
	}

	public Long getVersion() {
		return version;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	public void setCreadoEn(Date creadoEn) {
		this.creadoEn = creadoEn;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipoDni(Long tipoDni) {
		this.tipoDni = tipoDni;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getContacto() {

		for (int i = 0; i < getContactos().size(); i++) {
			if (getContactos().get(i).getTipoContacto().equals("email")) {
				return getContactos().get(i).getValor();
			}

		}
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	@Override
	public String toString() {
		return "Proveedor [id=" + id + ", nombre=" + nombre + ", dni=" + dni + ", tipoDni=" + tipoDni + ", direccion="
				+ direccion + ", estado=" + estado + ", creadoEn=" + creadoEn + ", creadoPor=" + creadoPor
				+ ", modificadoEn=" + modificadoEn + ", modificadoPor=" + modificadoPor + ", version=" + version + "]";
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
