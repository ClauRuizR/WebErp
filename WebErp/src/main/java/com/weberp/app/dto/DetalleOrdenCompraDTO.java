package com.weberp.app.dto;

import com.weberp.app.domain.DetalleOrdenCompra;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by claudioruiz on 6/7/17.
 */
@EntityMapper
public class DetalleOrdenCompraDTO extends ParsableObject<DetalleOrdenCompra,DetalleOrdenCompraDTO> {

   @Mapping
    private Long id;
    @Mapping
    private Long cantidad;
    @Mapping
    private BigDecimal precio = BigDecimal.ZERO;
    @Mapping
    private BigDecimal monto = BigDecimal.ZERO;

    @Mapping
    private ProductoDTO producto = new ProductoDTO();

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

    @Mapping
    private String productoDetalleNombre;

    public String getProductoDetalleNombre() {
        return getProducto().getNombre();
    }

    public void setProductoDetalleNombre(String productoDetalleNombre) {
        this.productoDetalleNombre = productoDetalleNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
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
    public Class<DetalleOrdenCompra> getDomainClass() {
        return DetalleOrdenCompra.class;
    }
}
