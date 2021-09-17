package com.weberp.app.dto;

import com.weberp.app.domain.Contacto;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

import java.util.Date;

/**
 * Created by claudioruiz on 6/6/17.
 */
@EntityMapper
public class ContactoDTO extends ParsableObject<Contacto,ContactoDTO> {


    @Mapping
    private Long id;
    @Mapping
    private String valor;
    @Mapping
    private String tipoContacto;
    @Mapping
    private Integer estado ;


    @Mapping
    private Long version;

    @Mapping
    private Date creadoEn;

    @Mapping
    private String creadoPor;

    @Mapping
    private Date modificadoEn;

    @Mapping
    private String modificadoPor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(String tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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

    @Override
    public Class<Contacto> getDomainClass() {
        return Contacto.class;
    }
}
