package com.weberp.app.dto;

import com.weberp.app.domain.Cliente;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EntityMapper
public class ClienteDTO extends ParsableObject<Cliente,ClienteDTO> {

	@Mapping
	private Long id;
	@Mapping
	private String nombre;
	@Mapping
	private String apellido;
	@Mapping
	private Long tipoDni;
	@Mapping
	private String dni;
	@Mapping
	private String direccion;
	@Mapping
	private String sexo;
	@Mapping
	private Integer estado = 1;

	@Mapping
	private String contacto;

	@Mapping
	private Date creadoEn;

	@Mapping
	private String creadoPor;

	@Mapping
	private Date modificadoEn;

	@Mapping
	private String modificadoPor;


	@Mapping
	private EmpresaDTO empresa = new EmpresaDTO();


	@Mapping
	private Long version;

	@Mapping
	private List<ContactoDTO> contactos = new ArrayList<>();

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getTipoDni() {
		return tipoDni;
	}

	public void setTipoDni(Long tipoDni) {
		this.tipoDni = tipoDni;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
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

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<ContactoDTO> getContactos() {
		return contactos;
	}

	public void setContactos(List<ContactoDTO> contactos) {
		this.contactos = contactos;
	}

	@Override
	public Class<Cliente> getDomainClass() {
		return Cliente.class;
	}
}
