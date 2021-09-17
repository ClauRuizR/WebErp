package com.weberp.app.dto;

import com.weberp.app.domain.DetalleTipoServicio;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

import java.util.Date;

/**
 * Created by claudioruiz on 6/13/17.
 */
@EntityMapper
public class DetalleTipoServicioDTO extends ParsableObject<DetalleTipoServicio,DetalleTipoServicioDTO> {

   @Mapping
    private Long id;



    @Mapping
    private ServicioDTO servicio = new ServicioDTO();


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

    public ServicioDTO getServicio() {
        return servicio;
    }

    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
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
    public Class<DetalleTipoServicio> getDomainClass() {
        return DetalleTipoServicio.class;
    }
}
