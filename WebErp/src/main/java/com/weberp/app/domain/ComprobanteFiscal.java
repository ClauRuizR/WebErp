package com.weberp.app.domain;


import java.util.Date;

import javax.persistence.*;

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
    private String descripcion;
    private String codigo;
    private Long desde;
    private Long hasta;
    private int secuencia;
    private String secuenciaCaracteres;
    private Date fechaEfectividad;
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

    @OneToOne
    @JoinColumn(name="empresa_id")
    private Empresa empresa;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getDesde() {
        return desde;
    }

    public void setDesde(Long desde) {
        this.desde = desde;
    }

    public Long getHasta() {
        return hasta;
    }

    public void setHasta(Long hasta) {
        this.hasta = hasta;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getSecuenciaCaracteres() {
        return secuenciaCaracteres;
    }

    public void setSecuenciaCaracteres(String secuenciaCaracteres) {
        this.secuenciaCaracteres = secuenciaCaracteres;
    }

    public Date getFechaEfectividad() {
        return fechaEfectividad;
    }

    public void setFechaEfectividad(Date fechaEfectividad) {
        this.fechaEfectividad = fechaEfectividad;
    }

    public int getEstado() {
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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
}
