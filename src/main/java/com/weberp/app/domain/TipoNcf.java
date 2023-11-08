package com.weberp.app.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Created by claudioruiz on 7/4/17.
 */

@Entity
public class TipoNcf extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String codigo;

    @NotNull
    private String nombre;

    private Integer estado = 1;


    @OneToOne
    @JoinColumn(name="comprobante_fiscal_id")
    private ComprobanteFiscal comprobanteFiscal;


    @CreatedDate
    private Date creadoEn;

    @CreatedBy
    private String creadoPor;

    @LastModifiedDate
    private Date modificadoEn;

    @LastModifiedBy
    private String modificadoPor;

    @Version
    private Long version;


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

    @Override
    public Date getCreadoEn() {
        return creadoEn;
    }

    @Override
    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }

    @Override
    public String getCreadoPor() {
        return creadoPor;
    }

    @Override
    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    @Override
    public Date getModificadoEn() {
        return modificadoEn;
    }

    @Override
    public void setModificadoEn(Date modificadoEn) {
        this.modificadoEn = modificadoEn;
    }

    @Override
    public String getModificadoPor() {
        return modificadoPor;
    }


    public ComprobanteFiscal getComprobanteFiscal() {
        return comprobanteFiscal;
    }

    public void setComprobanteFiscal(ComprobanteFiscal comprobanteFiscal) {
        this.comprobanteFiscal = comprobanteFiscal;
    }

    @Override
    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
