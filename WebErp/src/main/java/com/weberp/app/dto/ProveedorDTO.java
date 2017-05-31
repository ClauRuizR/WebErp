package com.weberp.app.dto;

public class ProveedorDTO {
	private Long id;

	private String nombre;
	private String dni;
	private Long tipoDni;
	private String direccion;
	private int estado = 1;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Long getTipoDni() {
		return tipoDni;
	}
	public void setTipoDni(Long tipoDni) {
		this.tipoDni = tipoDni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
