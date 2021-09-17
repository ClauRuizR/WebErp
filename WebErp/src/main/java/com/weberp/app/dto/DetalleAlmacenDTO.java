package com.weberp.app.dto;

import com.weberp.app.domain.DetalleAlmacen;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;

import java.util.Date;

/**
 * Created by claudioruiz on 6/22/17.
 */

@EntityMapper
public class DetalleAlmacenDTO extends ParsableObject<DetalleAlmacen,DetalleAlmacenDTO> {

    @Mapping
    private Long id;

    @Mapping
    private ProductoDTO producto = new ProductoDTO();

    @Mapping
    private String nombreAlmacenPadre;

    @Mapping
    private Long cantidad;
    @Mapping
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
    private String productoDetalleNombre;

    @Mapping
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
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

    public String getNombreAlmacenPadre() {



        return nombreAlmacenPadre;
    }

    public void setNombreAlmacenPadre(String nombreAlmacenPadre) {
        this.nombreAlmacenPadre = nombreAlmacenPadre;
    }

    public String getProductoDetalleNombre() {
        return  getProducto().getNombre();
    }

    public void setProductoDetalleNombre(String productoDetalleNombre) {
        this.productoDetalleNombre = productoDetalleNombre;
    }

    @Override
    public Class<DetalleAlmacen> getDomainClass() {
        return DetalleAlmacen.class;
    }
}
