package com.weberp.app.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by claudioruiz on 7/21/17.
 */
@Entity(name="cuentas_cobrar")
public class CuentasCobrar extends Auditable<String>  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3960591550370876355L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String comentario;

    @NotNull
    private String numeroDocumento;

    @NotNull
    private BigDecimal descuento;

    @NotNull
    private BigDecimal itbis;

    @NotNull
    private BigDecimal importe;

    @NotNull
    private BigDecimal subTotal;

    @NotNull
    private BigDecimal total;

    private Integer estado = 1;

    @CreatedDate
    private Date creadoEn;

    @CreatedBy
    private String creadoPor;

    @LastModifiedDate
    private Date modificadoEn;

    @LastModifiedBy
    private String modificadoPor;


    @ManyToOne
    @JoinColumn(name="empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getItbis() {
        return itbis;
    }

    public void setItbis(BigDecimal itbis) {
        this.itbis = itbis;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
