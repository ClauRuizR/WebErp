package com.weberp.app.dto;

import com.weberp.app.domain.Empresa;
import com.weberp.app.domain.GrupoEmpresa;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

import java.util.Date;

/**
 * Created by claudioruiz on 6/9/17.
 */

@EntityMapper
public class EmpresaDTO extends ParsableObject<Empresa,EmpresaDTO> {

    @Mapping
    private Long id;
    @Mapping
    private String nombre;
    @Mapping
    private String logo;
    @Mapping
    private String direccion;

    @Mapping
    private String rnc;
    @Mapping
    private String website;

    @Mapping
    private String razonSocial;

    @Mapping
    private String telefono;
    @Mapping
    private String fax;
    @Mapping
    private String email;


    @Mapping
    private GrupoEmpresaDTO grupoEmpresa = new GrupoEmpresaDTO();

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public GrupoEmpresaDTO getGrupoEmpresa() {
        return grupoEmpresa;
    }

    public void setGrupoEmpresa(GrupoEmpresaDTO grupoEmpresa) {
        this.grupoEmpresa = grupoEmpresa;
    }

    @Override
    public Class<Empresa> getDomainClass() {
        return Empresa.class;
    }
}
