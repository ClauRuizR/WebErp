package com.weberp.app.dto;

import com.weberp.app.domain.ProduccionProducto;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by claudioruiz on 6/14/17.
 */
@EntityMapper
public class ProduccionProductoDTO extends ParsableObject<ProduccionProducto,ProduccionProductoDTO> {

    @Mapping
    private Long id;

    @Mapping
    private String numeroDocumento;

    @Mapping
    private BigDecimal costoServicio;

    @Mapping
    private ProductoDTO producto  = new ProductoDTO();

    @Mapping
    private BigDecimal montoPrecioVenta;

    @Mapping
    private BigDecimal montoPrecioCompra;

    @Mapping
    private BigDecimal total;

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

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
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
    public Class<ProduccionProducto> getDomainClass() {
        return ProduccionProducto.class;
    }
}
