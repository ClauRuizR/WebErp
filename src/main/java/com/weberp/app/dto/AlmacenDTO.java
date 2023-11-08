package com.weberp.app.dto;

import com.weberp.app.domain.Almacen;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@EntityMapper
public class AlmacenDTO extends ParsableObject<Almacen,AlmacenDTO> {

	@Mapping
	private Long id;

	@Mapping
	private String nombre;

	@Mapping
	private boolean principal;

	@Mapping
	private LocalidadDTO localidad = new LocalidadDTO();


	@Mapping
	private String descripcion;

	@Mapping
	private String codigo;
	@Mapping
	private Integer estado;

	@Mapping
	private Date creadoEn;

	@Mapping
	private String creadoPor;

	@Mapping
	private Date modificadoEn;

	@Mapping
	private String modificadoPor;

	@Mapping
	private List<DetalleAlmacenDTO> DetalleAlmacen = new ArrayList<>();



	@Mapping
	private Long version;

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

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getEstado() {
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

	public List<DetalleAlmacenDTO> getDetalleAlmacen() {
		return DetalleAlmacen;
	}

	public void setDetalleAlmacen(List<DetalleAlmacenDTO> detalleAlmacen) {
		DetalleAlmacen = detalleAlmacen;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}


	@Override
	public Class<Almacen> getDomainClass() {
		return Almacen.class;
	}
}
