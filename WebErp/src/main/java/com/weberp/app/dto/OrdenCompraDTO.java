package com.weberp.app.dto;

import com.weberp.app.domain.OrdenCompra;
import com.weberp.app.enums.EstatusEnum;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EntityMapper
public class OrdenCompraDTO extends ParsableObject<OrdenCompra,OrdenCompraDTO> {



	@Mapping
	private Long id;
	@Mapping
	private String numeroOrdenCompra;
	@Mapping
	private String estatus = EstatusEnum.PENDIENTE;
	@Mapping
	private Integer estado = 1;

	@Mapping
	private Date fecha;

	@Mapping
	private String estatusNombre;

	@Mapping
	private ProveedorDTO proveedor  = new ProveedorDTO();

	@Mapping
	private List<DetalleOrdenCompraDTO> DetalleOrdenCompra = new ArrayList<>();

	@Mapping
	private Date creadoEn;

	@Mapping
	private String creadoPor;

	@Mapping
	private Date modificadoEn;

	@Mapping
	private String modificadoPor;

	@Mapping
	private Long version;

	@Mapping
	private BigDecimal montoTotal = new BigDecimal(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroOrdenCompra() {
		return numeroOrdenCompra;
	}

	public void setNumeroOrdenCompra(String numeroOrdenCompra) {
		this.numeroOrdenCompra = numeroOrdenCompra;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getEstatusNombre() {

		switch (getEstatus()) {
			case EstatusEnum.PENDIENTE:
				return "Pendiente";

			case EstatusEnum.APROBADO:
				return "Aprobado";

			case EstatusEnum.PAGADA:
				return "Pagada";

		}
		return estatusNombre;
	}

	public void setEstatusNombre(String estatusNombre) {
		this.estatusNombre = estatusNombre;
	}

	public ProveedorDTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}

	public List<DetalleOrdenCompraDTO> getDetalleOrdenCompra() {
		return DetalleOrdenCompra;
	}

	public void setDetalleOrdenCompra(List<DetalleOrdenCompraDTO> detalleOrdenCompra) {
		DetalleOrdenCompra = detalleOrdenCompra;
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

	public BigDecimal getMontoTotal() {
		BigDecimal resultado = BigDecimal.ZERO;
		for (int i = 0; i < getDetalleOrdenCompra().size(); i++) {
			if(getDetalleOrdenCompra().get(i).getEstado()==1) {

				resultado = resultado.add(getDetalleOrdenCompra().get(i).getMonto());
			}
		}

		return resultado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	@Override
	public Class<OrdenCompra> getDomainClass() {
		return OrdenCompra.class;
	}
}
