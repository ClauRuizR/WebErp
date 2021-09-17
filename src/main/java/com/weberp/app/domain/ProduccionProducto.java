package com.weberp.app.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by claudioruiz on 6/14/17.
 */

@Entity(name="produccion_producto")
public class ProduccionProducto extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;

    @Column(name = "Costo_servicio")
    private BigDecimal costoServicio;

    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;

    private BigDecimal montoPrecioVenta;

    private BigDecimal montoPrecioCompra;

    private BigDecimal total;

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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }


    public BigDecimal getCostoServicio() {
        return costoServicio;
    }

    public void setCostoServicio(BigDecimal costoServicio) {
        this.costoServicio = costoServicio;
    }

    public Producto getProducto() {
        return producto;
    }


    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public BigDecimal getMontoPrecioVenta() {
        return montoPrecioVenta;
    }

    public void setMontoPrecioVenta(BigDecimal montoPrecioVenta) {
        this.montoPrecioVenta = montoPrecioVenta;
    }

    public BigDecimal getMontoPrecioCompra() {
        return montoPrecioCompra;
    }

    public void setMontoPrecioCompra(BigDecimal montoPrecioCompra) {
        this.montoPrecioCompra = montoPrecioCompra;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
