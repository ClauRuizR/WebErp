package com.weberp.app.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.weberp.app.model.TipoFactura;


/**
 * Created by claudioruiz on 7/27/16.
 */
public class FacturaDTO {

    private Long id;
    private Long clienteId;
    private String destino;
    private Long cotizacion;
    private Long tipoFacturaId;
    private String comprobanteFiscal;
    private BigDecimal descuento;
    private Date fecha;
    private int estado = 1;

    private List<TipoFactura> listaTipoFacturas;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Long getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Long cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Long getTipoFacturaId() {
        return tipoFacturaId;
    }

    public void setTipoFacturaId(Long tipoFacturaId) {
        this.tipoFacturaId = tipoFacturaId;
    }

    public String getComprobanteFiscal() {
        return comprobanteFiscal;
    }

    public void setComprobanteFiscal(String comprobanteFiscal) {
        this.comprobanteFiscal = comprobanteFiscal;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<TipoFactura> getListaTipoFacturas() {

        return listaTipoFacturas;
    }

    public void setListaTipoFacturas(List<TipoFactura> listaTipoFacturas) {

        this.listaTipoFacturas = listaTipoFacturas;
    }
}
