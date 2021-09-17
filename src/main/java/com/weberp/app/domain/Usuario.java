package com.weberp.app.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@NamedNativeQuery(name = "Almacen.findAlmacenByEmpresa",
		query="select al.* from usuario u \n" +
				"inner join localidad lo on lo.empresa_id = u.empresa_id\n" +
				"inner join almacen al on al.localidad_id =lo.id \n" +
				"where al.principal = 1 and  u.usuario = ?1",
		resultClass = Almacen.class
)
@Table(name = "usuario")
public class 	Usuario extends Auditable<String> implements Serializable  {

	private static final long serialVersionUID = -486827370610335742L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String usuario;

	@Column(nullable = false)
	private String clave;


	private String email;




	private String nombre;


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<UsuarioRol> UsuarioRol = new ArrayList<>();

	@OneToOne
	@JoinColumn(name="empresa_id")
	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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


	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		if (estado == null) {
			return;
		}

		this.estado = estado;
	}

	public List<UsuarioRol> getUsuarioRol() {
		return UsuarioRol;
	}

	public void setUsuarioRol(List<UsuarioRol> usuarioRol) {
		UsuarioRol = usuarioRol;
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
