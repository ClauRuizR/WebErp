package com.weberp.app.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by claudioruiz on 5/29/17.
 */

@Entity(name="pago")
public class Pago  extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MONTO")
    private BigDecimal monto;


    @ManyToOne
    private TipoDocumento tipoDocumento;

    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;

    @Column(name = "ESTADO")
    private Integer estado = 1;

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

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getEstado() {
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
