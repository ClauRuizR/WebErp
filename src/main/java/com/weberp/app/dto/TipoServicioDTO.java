package com.weberp.app.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.weberp.app.domain.TipoServicio;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

/**
 * Created by claudioruiz on 6/13/17.
 */
@EntityMapper
public class TipoServicioDTO  extends ParsableObject<TipoServicio,TipoServicioDTO> {

 @Mapping
    private Long id;
    @Mapping
    private String nombre;
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
    private Long version;


    @Mapping
    private TipoProductoDTO tipoProducto = new TipoProductoDTO();


    @Mapping
    private EmpresaDTO empresa = new EmpresaDTO();

    @Mapping
    private List<DetalleTipoServicioDTO> DetalleTipoServicio = new ArrayList<>();


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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public TipoProductoDTO getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProductoDTO tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public EmpresaDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDTO empresa) {
        this.empresa = empresa;
    }

    public List<DetalleTipoServicioDTO> getDetalleTipoServicio() {
        return DetalleTipoServicio;
    }

    public void setDetalleTipoServicio(List<DetalleTipoServicioDTO> detalleTipoServicio) {
        DetalleTipoServicio = detalleTipoServicio;
    }

    @Override
    public Class<TipoServicio> getDomainClass() {
        return TipoServicio.class;
    }
}
