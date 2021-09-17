package com.weberp.app.dto;

import com.weberp.app.domain.DetalleAlmacen;
import com.weberp.app.domain.DetalleFactura;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by claudioruiz on 6/7/17.
 */
@EntityMapper
public class DetalleFacturaDTO extends ParsableObject<DetalleFactura,DetalleFacturaDTO> {

    @Mapping
    private Long id;
    @Mapping
    private BigDecimal monto = new BigDecimal(0);
    @Mapping
    private Long cantidad;

    @Mapping
    private BigDecimal precio = new BigDecimal(0);
    @Mapping
    private ProductoDTO producto = new ProductoDTO();

    @Mapping
    private BigDecimal itbis;
    @Mapping
    private BigDecimal importe;

    @Mapping
    private BigDecimal subTotal;

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

    @Mapping
    private BigDecimal descuento;

    @Mapping
    private BigDecimal total;

    @Mapping
    private String productoDetalleNombre;

    public String getProductoDetalleNombre() {
        return getProducto().getNombre();
    }

    public void setProductoDetalleNombre(String productoDetalleNombre) {
        this.productoDetalleNombre = productoDetalleNombre;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

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

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }


    public BigDecimal getItbis() {
        return itbis;
    }

    public void setItbis(BigDecimal itbis) {
        this.itbis = itbis;
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

    public BigDecimal getImporte() {
        return importe;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
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

    @Override
    public Class<DetalleFactura> getDomainClass() {
        return DetalleFactura.class;
    }
}
