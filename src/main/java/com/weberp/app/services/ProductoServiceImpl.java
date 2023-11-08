package com.weberp.app.services;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.Almacen;
import com.weberp.app.domain.DetalleAlmacen;
import com.weberp.app.domain.Producto;
import com.weberp.app.domain.TipoProducto;
import com.weberp.app.dto.DetalleAlmacenDTO;
import com.weberp.app.dto.ProductoDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl extends ConfigMapper implements ProductoService {

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

        List<Producto> listaProducto = (List<Producto>) productoRepository.findAll();
        for (Producto item : listaProducto) {
            item.setCantidad(buscarCantidadEnAlmacen(item.getId()));
        }

        return listaProducto;
    }

    @Override
    public Producto guardar(Producto producto) {

        TipoProducto tipoProducto = tipoProductoService.getTipoProductoById(producto.getTipoProducto().getId());


        producto.setTipoProducto(tipoProducto);


        return productoRepository.save(producto);
    }

    @Override
    public Producto getProductoById(Long id) {

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
    public List<DetalleAlmacenDTO> buscarProductosEnAlmacenes(Long productoId) {
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
    return listaDetalleAlmacen==null ? null : convertDetalleAlmacenToDto(listaDetalleAlmacen);
    }

    @Override
    public Long buscarCantidadEnAlmacen(Long productoId) {


        List<DetalleAlmacen> listaDetalleAlmacen = entityManager.createNamedQuery("DetalleAlmacen.findByProductoId", DetalleAlmacen.class).setParameter(1, productoId).getResultList();
        Long cantidad = 0L;
        for (int i = 0; i < listaDetalleAlmacen.size(); i++) {
            if(listaDetalleAlmacen.get(i).getCantidad() != null) {
                cantidad = cantidad + listaDetalleAlmacen.get(i).getCantidad();
            }
        }

        return cantidad;
    }

    @Override
    public List<Producto> listaProductosPorEmpresa() {
        Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

        return productoRepository.findByTipoProducto_Empresa_Id(empresaId);

    }
    @Override
    public Page<ProductoDTO> findPaginated(Pageable pageRequest) {
        Page<Producto> productoPage=  (productoRepository.findAll(pageRequest));

        final Page<ProductoDTO> contactDtoPage = productoPage.map(this::convertProductoToDto);
        return contactDtoPage;
    }

    @Override
    public Page<ProductoDTO> findProductoAndPaginated(String codigoAlfaNumerico, Pageable pageRequest) {
        Page<Producto> ordenPage=  productoRepository.findByCodigoAlfanumerico(codigoAlfaNumerico,pageRequest);

        final Page<ProductoDTO> contactDtoPage = ordenPage.map(this::convertProductoToDto);
        return contactDtoPage;
    }

    @Override
    public void afectaEntradaProducto(Producto producto	, Long cantidad) {
        producto.setCantidad(producto.getCantidad()+cantidad);
        productoRepository.save(producto);
    }

    @Override
    public void afectaSalidaProducto(Producto producto,	 Long cantidad) {
        producto.setCantidad(producto.getCantidad()- cantidad);
        productoRepository.save(producto);
    }

    @Override
    public  List<Long> getStockProductos() throws ParseException {

        Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();


        List<Long> productoLista = new ArrayList<>();
        List<Producto> productoList = productoRepository.findAll().stream().filter(o-> o.getCantidad() > 0 && o.getTipoProducto().getEmpresa().getId().equals(empresaId)).collect(Collectors.toList());



        for (Producto prod: productoList) {
            productoLista.add(prod.getCantidad());



        }


        return productoLista;
    }

    @Override
    public List<String> getStockProductoLabel() throws ParseException {
        Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();


        List<String> productoLista = new ArrayList<>();
        List<Producto> productoList = productoRepository.findAll().stream().filter(o-> o.getCantidad() > 0 && o.getTipoProducto().getEmpresa().getId().equals(empresaId)).collect(Collectors.toList());


        for (Producto prod: productoList) {
            productoLista.add(prod.getNombre());

        }


        return productoLista;
    }


}
