package com.weberp.app.dto;

import com.weberp.app.domain.TipoDocumento;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

import java.util.Date;

/**
 * Created by claudioruiz on 6/7/17.
 */
@EntityMapper
public class TipoDocumentoDTO extends ParsableObject<TipoDocumento,TipoDocumentoDTO> {

    @Mapping
    private Long id;

    @Mapping
    private String documento;

    @Mapping
    private String llaveDocumento;

    @Mapping
    private Long numeroControl;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getLlaveDocumento() {
        return llaveDocumento;
    }

    public void setLlaveDocumento(String llaveDocumento) {
        this.llaveDocumento = llaveDocumento;
    }

    public Long getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(Long numeroControl) {
        this.numeroControl = numeroControl;
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
    public Class<TipoDocumento> getDomainClass() {
        return TipoDocumento.class;
    }
}
