package com.weberp.app.dto;

import com.weberp.app.domain.GrupoEmpresa;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

import java.util.*;

/**
 * Created by claudioruiz on 6/9/17.
 */
@EntityMapper
public class GrupoEmpresaDTO extends ParsableObject<GrupoEmpresa,GrupoEmpresaDTO> {

    @Mapping
    private Long id;
    @Mapping
    private String nombre;
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
    private Long version;

    @Mapping
    private List<EmpresaDTO> empresa = new ArrayList<>();



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


    public List<EmpresaDTO> getEmpresa() {
        return empresa;
    }

    public void setEmpresa(List<EmpresaDTO> empresa) {
        this.empresa = empresa;
    }

    @Override
    public Class<GrupoEmpresa> getDomainClass() {
        return GrupoEmpresa.class;
    }
}
