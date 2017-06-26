package com.weberp.app.services;

import com.weberp.app.domain.*;
import com.weberp.app.repositories.ProduccionProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by claudioruiz on 6/14/17.
 */
@Service
public class ProduccionProductoServiceImpl implements ProduccionProductoService {

    @Autowired
    private TipoServicioService tipoServicioService;

    @Autowired
    private ProduccionProductoRepository produccionProductoRepository;

    @Override
    public List<ProduccionProducto> listaProduccionProductos() {
        return (List<ProduccionProducto>) produccionProductoRepository.findAll();
    }

    @Override
    public ProduccionProducto guardar(ProduccionProducto produccionProducto) {
        return produccionProductoRepository.save(produccionProducto);
    }

    @Override
    public ProduccionProducto getProduccionProductoById(Long id) {
        return produccionProductoRepository.findOne(id);
    }

    @Override
    public void borrar(Long id) {

        produccionProductoRepository.delete(id);

    }

    @Override
    public void generaProduccionProducto(List<DetalleFactura> detalleFacturaList, String numeroDocumento) {

    for(int j = 0; j < detalleFacturaList.size(); j ++) {


        ProduccionProducto produccionProducto = new ProduccionProducto();
        TipoServicio tipoServicio = tipoServicioService.findByTipoProducto_IdAndEmpresa_Id(detalleFacturaList.get(j).getProducto().getTipoProducto().getId(), detalleFacturaList.get(j).getProducto().getTipoProducto().getEmpresa().getId());

        BigDecimal precioCompra = detalleFacturaList.get(j).getProducto().getPrecioCompra().multiply(new BigDecimal(detalleFacturaList.get(j).getCantidad()));

        produccionProducto.setMontoPrecioCompra(precioCompra);

        if(!detalleFacturaList.get(j).getProducto().getTipoProducto().isFacturable())
            continue;

        BigDecimal precioVenta = detalleFacturaList.get(j).getProducto().getPrecioVenta().multiply(new BigDecimal(detalleFacturaList.get(j).getCantidad()));


        produccionProducto.setMontoPrecioVenta(precioVenta);

        BigDecimal total = precioVenta.subtract(precioCompra);

        BigDecimal costoServicio = BigDecimal.ZERO;

        produccionProducto.setProducto(detalleFacturaList.get(j).getProducto());

        produccionProducto.setNumeroDocumento(numeroDocumento);


        for (int i = 0; i < tipoServicio.getDetalleTipoServicio().size(); i++) {
            DetalleTipoServicio detalleTipoServicio = tipoServicio.getDetalleTipoServicio().get(i);
            costoServicio = costoServicio.add(detalleTipoServicio.getServicio().getCosto());

        }

         costoServicio = costoServicio.multiply(new BigDecimal(detalleFacturaList.get(j).getCantidad()));

         produccionProducto.setCostoServicio(costoServicio);

         total = total.subtract(costoServicio);
         produccionProducto.setTotal(total);
         guardar(produccionProducto);
        }
    }

    @Override
    public void saveBatch(Iterable<ProduccionProducto> produccionProductoIterator) {
        produccionProductoRepository.save(produccionProductoIterator);
    }


}
