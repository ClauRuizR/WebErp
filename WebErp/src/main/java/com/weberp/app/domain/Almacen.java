package com.weberp.app.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@NamedNativeQuery(name = "Almacen.findAlmacenByProducto",
		query="SELECT count(*) FROM almacen al inner join detalle_almacen da on al.id= da.almacen_id where da.estado=1 and da.producto_id = ?1 and al.id= ?2 "
)
@Table(name = "almacen")
public class Almacen extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String nombre;

	@NotNull
	private boolean principal;

	@ManyToOne
	@JoinColumn(name="localidad_id")
	private Localidad localidad;

	private String descripcion;

	@NotNull
	private String codigo;

	private Integer estado = 1;

	@CreatedDate
	private Date creadoEn;

	@CreatedBy
	private String creadoPor;

	@LastModifiedDate
	private Date modificadoEn;

	@LastModifiedBy
	private String modificadoPor;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "movimiento_inventario", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = {
			@JoinColumn(name = "almacen_id") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<MovimientoInventario> MovimientoInventario = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<DetalleAlmacen> DetalleAlmacen = new ArrayList<>();

	@Version
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

	public List<MovimientoInventario> getMovimientoInventario() {
		return MovimientoInventario;
	}

	public void setMovimientoInventario(List<MovimientoInventario> movimientoInventario) {
		MovimientoInventario = movimientoInventario;
	}

	public List<DetalleAlmacen> getDetalleAlmacen() {
		return DetalleAlmacen;
	}

	public void setDetalleAlmacen(List<DetalleAlmacen> detalleAlmacen) {
		DetalleAlmacen = detalleAlmacen;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}


	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
}
