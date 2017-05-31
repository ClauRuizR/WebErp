package com.weberp.app.dto;

import java.time.LocalDate;

public class PacienteDTO {

	
	
	private Long id;
	private String nombres;
	private String apellidos;
	private String tipoDni;
	private String Dni;
	private LocalDate fechaNacimiento;
	private String estadoCivil;
	private String tipoSangre;
	private String genero;
	private Long paisId;
	private Long ciudadId;
	private String lugarNacimiento;
	private Long ocupacionId;
	private String direccion;
	private String foto;
	private int estado;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTipoDni() {
		return tipoDni;
	}
	public void setTipoDni(String tipoDni) {
		this.tipoDni = tipoDni;
	}
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) {
		Dni = dni;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getTipoSangre() {
		return tipoSangre;
	}
	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Long getPaisId() {
		return paisId;
	}
	public void setPaisId(Long paisId) {
		this.paisId = paisId;
	}
	public Long getCiudadId() {
		return ciudadId;
	}
	public void setCiudadId(Long ciudadId) {
		this.ciudadId = ciudadId;
	}
	public String getLugarNacimiento() {
		return lugarNacimiento;
	}
	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}
	public Long getOcupacionId() {
		return ocupacionId;
	}
	public void setOcupacionId(Long ocupacionId) {
		this.ocupacionId = ocupacionId;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
	
	
}
