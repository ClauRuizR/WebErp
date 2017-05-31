package com.weberp.app.services;

import com.weberp.app.domain.Almacen;
import com.weberp.app.domain.DetalleAlmacen;
import com.weberp.app.domain.Producto;
import com.weberp.app.dto.ProductoDTO;
import com.weberp.app.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    Producto producto;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TipoProductoService tipoProductoService;

    @Autowired
    private AlmacenService almacenService;

    @Override
    public List<Producto> listaProductos() {
        // TODO Auto-generated method stub
        List<Producto> listaProducto = (List<Producto>) productoRepository.findAll();
        for (Producto item : listaProducto) {
            item.setCantidad(buscarCantidadEnAlmacen(item.getId()));
        }

        return listaProducto;
    }

    @Override
    public Producto guardar(Producto producto) {

        producto.setTipoProducto(tipoProductoService.getTipoProductoById(producto.getTipoProducto().getId()));


        return productoRepository.save(producto);
    }

    @Override
    public Producto getProductoById(Long id) {
        // TODO Auto-generated method stub
        Producto producto = productoRepository.findOne(id);
        producto.setCantidad(buscarCantidadEnAlmacen(id));
        return producto;
    }

    @Override
    public void borrar(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean existeProducto(Producto producto) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Producto> buscarProducto(ProductoDTO productoDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DetalleAlmacen> buscarProductosEnAlmacenes(Long productoId) {
        List<Almacen> listaAlmacen = almacenService.listaAlmacen();
        List<DetalleAlmacen> listaDetalleAlmacen = null;
        for (int i = 0; i < listaAlmacen.size(); i++) {

            List<DetalleAlmacen> listadetAlmacen = listaAlmacen.get(i).getDetalleAlmacen();

            for (int j = 0; j < listadetAlmacen.size(); j++) {

                if (null != listadetAlmacen.get(j).getProducto().getId()
                        && listadetAlmacen.get(j).getProducto().getId().equals(productoId)) {
                    listaDetalleAlmacen = new ArrayList<DetalleAlmacen>();
                    listaDetalleAlmacen.add(listadetAlmacen.get(j));
                }

            }

        }

        return listaDetalleAlmacen;
    }

    @Override
    public Long buscarCantidadEnAlmacen(Long productoId) {


        List<DetalleAlmacen> listaDetalleAlmacen = entityManager.createNamedQuery("DetalleAlmacen.findByProductoId", DetalleAlmacen.class).setParameter(1, productoId).getResultList();
        Long cantidad = 0L;
        for (int i = 0; i < listaDetalleAlmacen.size(); i++) {
            // System.err.print(listaDetalleAlmacen.get(i).getCantidad());
            cantidad = cantidad + listaDetalleAlmacen.get(i).getCantidad();
        }

        return cantidad;
    }


}
