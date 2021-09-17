package com.weberp.app.domain;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Created by claudioruiz on 8/14/16.
 */
@Entity(name="comprobante_fiscal")
public class ComprobanteFiscal extends Auditable<String>  {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String letra;

    private String numeroBase;

    private String desde;

    private String hasta;

    private Long cantidad;

    private Long contador;
    @Transient
    private BigDecimal porcientoNCF;
    @Transient
    private BigDecimal porcientoContador;

    @Transient
    private String ncf;

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





    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getNumeroBase() {
        return numeroBase;
    }

    public void setNumeroBase(String numeroBase) {
        this.numeroBase = numeroBase;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Long getContador() {
        return contador;
    }

    public void setContador(Long contador) {
        this.contador = contador;
    }

    public BigDecimal getPorcientoNCF() {
        return porcientoNCF;
    }

    public void setPorcientoNCF(BigDecimal porcientoNCF) {
        this.porcientoNCF = porcientoNCF;
    }

    public BigDecimal getPorcientoContador() {
        return porcientoContador;
    }

    public void setPorcientoContador(BigDecimal porcientoContador) {
        this.porcientoContador = porcientoContador;
    }

    public String getNcf() {

        Long contador = Long.parseLong(getDesde())+getContador();

        return getLetra()+""+getNumeroBase()+""+ StringUtils.leftPad(contador.toString(),8,"0");

    }

    public void setNcf(String ncf) {
        this.ncf = ncf;
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
