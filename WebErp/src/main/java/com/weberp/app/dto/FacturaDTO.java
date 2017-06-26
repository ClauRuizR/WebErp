package com.weberp.app.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.weberp.app.domain.Factura;

import com.weberp.app.enums.EstatusEnum;
import com.weberp.app.mapperobject.EntityMapper;
import com.weberp.app.mapperobject.Mapping;
import com.weberp.app.mapperobject.ParsableObject;



@EntityMapper
public class FacturaDTO extends ParsableObject<Factura,FacturaDTO> {

    @Mapping
    private Long id;

    @Mapping
    private boolean comprobanteFiscal = false;
    @Mapping
    private String numeroComprobanteFiscal;
    @Mapping
    private String numeroDocumento;
    @Mapping
    private BigDecimal descuento = BigDecimal.ZERO;
    @Mapping
    private Date fecha;
    @Mapping
    private String estatus = EstatusEnum.PENDIENTE;
    @Mapping
    private Integer estado = 1;

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
    private TipoDocumentoDTO tipoDocumento = new TipoDocumentoDTO();

    @Mapping
    private ClienteDTO cliente = new ClienteDTO();

    @Mapping
    private String estatusNombre;

    @Mapping
    private BigDecimal montoTotal = new BigDecimal(0);

    @Mapping
    private List<DetalleFacturaDTO> detalleFactura = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isComprobanteFiscal() {
        return comprobanteFiscal;
    }

    public void setComprobanteFiscal(boolean comprobanteFiscal) {
        this.comprobanteFiscal = comprobanteFiscal;
    }

    public String getNumeroComprobanteFiscal() {
        return numeroComprobanteFiscal;
    }

    public void setNumeroComprobanteFiscal(String numeroComprobanteFiscal) {
        this.numeroComprobanteFiscal = numeroComprobanteFiscal;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getEstatusNombre() {

        switch (getEstatus()) {
            case EstatusEnum.PENDIENTE:
                return "Pendiente";

            case EstatusEnum.APROBADO:
                return "Aprobado";

            case EstatusEnum.PAGADA:
                return "Pagada";

        }
        return estatusNombre;
    }

    public void setEstatusNombre(String estatusNombre) {
        this.estatusNombre = estatusNombre;
    }

    public BigDecimal getMontoTotal() {

        BigDecimal resultado = BigDecimal.ZERO;
        for (int i = 0; i < getDetalleFactura().size(); i++) {
            if(getDetalleFactura().get(i).getEstado()==1) {
                if(null!= getDetalleFactura().get(i).getTotal())
                resultado = resultado.add(getDetalleFactura().get(i).getTotal());
            }
        }
        resultado = resultado.subtract(getDescuento());
        return resultado;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
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

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<DetalleFacturaDTO> getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(List<DetalleFacturaDTO> detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public TipoDocumentoDTO getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoDTO tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public Class<Factura> getDomainClass() {
        return Factura.class;
    }
}
