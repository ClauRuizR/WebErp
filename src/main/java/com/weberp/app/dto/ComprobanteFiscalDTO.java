package com.weberp.app.dto;

import com.weberp.app.domain.ComprobanteFiscal;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by claudioruiz on 7/4/17.
 */
@EntityMapper
public class ComprobanteFiscalDTO extends ParsableObject<ComprobanteFiscal,ComprobanteFiscalDTO> {


    @Mapping
    private Long id;

    @Mapping
    private String letra;
    @Mapping
    private String numeroBase;
    @Mapping
    private String desde;
    @Mapping
    private String hasta;
    @Mapping
    private Long cantidad;
    @Mapping
    private Long contador;
    @Mapping
    private BigDecimal porcientoNCF;
    @Mapping
    private BigDecimal porcientoContador;

    @Mapping
    private String ncf;
    @Mapping
    private Integer estado ;

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
        return ncf;
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
    public Class<ComprobanteFiscal> getDomainClass() {
        return ComprobanteFiscal.class;
    }
}
