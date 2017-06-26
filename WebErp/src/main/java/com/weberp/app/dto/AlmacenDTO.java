package com.weberp.app.dto;

import com.weberp.app.domain.Almacen;
import com.weberp.app.domain.DetalleAlmacen;
import com.weberp.app.domain.Localidad;
import com.weberp.app.domain.MovimientoInventario;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.core.convert.converter.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
	private Integer estado = 1;

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


	private Page<DetalleAlmacenDTO> detalleAlmacenDTOPage = new Page<DetalleAlmacenDTO>() {
		@Override
		public int getTotalPages() {
			return 0;
		}

		@Override
		public long getTotalElements() {
			return getDetalleAlmacen().size();
		}

		@Override
		public <S> Page<S> map(org.springframework.core.convert.converter.Converter<? super DetalleAlmacenDTO, ? extends S> converter) {
			return null;
		}

		@Override
		public int getNumber() {
			return getDetalleAlmacen().size();
		}

		@Override
		public int getSize() {
			return getDetalleAlmacen().size();
		}

		@Override
		public int getNumberOfElements() {
			return getDetalleAlmacen().size();
		}

		@Override
		public List<DetalleAlmacenDTO> getContent() {
			return getDetalleAlmacen();
		}

		@Override
		public boolean hasContent() {
			return getDetalleAlmacen().size() >0 ;
		}

		@Override
		public Sort getSort() {
			return null;
		}

		@Override
		public boolean isFirst() {
			return false;
		}

		@Override
		public boolean isLast() {
			return false;
		}

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public boolean hasPrevious() {
			return false;
		}

		@Override
		public Pageable nextPageable() {
			return null;
		}

		@Override
		public Pageable previousPageable() {
			return null;
		}

		@Override
		public Iterator<DetalleAlmacenDTO> iterator() {
			return null;
		}
	};

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

	public Page<DetalleAlmacenDTO> getDetalleAlmacenDTOPage() {
		return detalleAlmacenDTOPage;
	}

	public void setDetalleAlmacenDTOPage(Page<DetalleAlmacenDTO> detalleAlmacenDTOPage) {
		this.detalleAlmacenDTOPage = detalleAlmacenDTOPage;
	}

	@Override
	public Class<Almacen> getDomainClass() {
		return Almacen.class;
	}
}
