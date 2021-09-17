package com.weberp.app.services;

import com.weberp.app.domain.DetalleFactura;
import com.weberp.app.domain.ProduccionProducto;
import com.weberp.app.domain.Producto;

import java.util.Iterator;
import java.util.List;

/**
 * Created by claudioruiz on 6/14/17.
 */
public interface ProduccionProductoService {
    List<ProduccionProducto> listaProduccionProductos();

    ProduccionProducto guardar(ProduccionProducto produccionProducto);

    ProduccionProducto getProduccionProductoById(Long id);

    void borrar(Long id);

    void generaProduccionProducto(List<DetalleFactura> detalleFacturaList, String numeroDocumento);

    void saveBatch(Iterable<ProduccionProducto> produccionProductoIterator);
}
