package com.weberp.app.dto;

import com.weberp.app.domain.TipoNcf;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

import java.util.Date;

/**
 * Created by claudioruiz on 7/4/17.
 */
@EntityMapper
public class TipoNcfDTO extends ParsableObject<TipoNcf,TipoNcfDTO> {

    @Mapping
    private Long id;
    @Mapping
    private String codigo;

    @Mapping
    private String nombre;
    @Mapping
    private Integer estado;

    @Mapping
    private ComprobanteFiscalDTO comprobanteFiscal = new ComprobanteFiscalDTO();

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

    public ComprobanteFiscalDTO getComprobanteFiscal() {
        return comprobanteFiscal;
    }

    public void setComprobanteFiscal(ComprobanteFiscalDTO comprobanteFiscal) {
        this.comprobanteFiscal = comprobanteFiscal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    @Override
    public Class<TipoNcf> getDomainClass() {
        return TipoNcf.class;
    }
}
