package com.weberp.app.services;

import java.util.ArrayList;
import java.util.List;

import com.weberp.app.utils.LocalDateAttributeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.Almacen;
import com.weberp.app.domain.DetalleAlmacen;
import com.weberp.app.domain.Producto;
import com.weberp.app.repositories.AlmacenRepository;

@Service
public class AlmacenServiceImpl implements AlmacenService {

	Almacen almacen;

	Producto producto;
	@Autowired
	private ProductoService productoService;

	@Autowired
	private AlmacenRepository almacenRepository;

	@Autowired
	private LocalidadService localidadService;

	@Override
	public List<Almacen> listaAlmacen() {
		// TODO Auto-generated method stub
		return (List<Almacen>) almacenRepository.findAll();
	}

	@Override
	public Almacen guardar(Almacen almacen) {
        if(null!= almacen.getLocalidad().getId()) {
            almacen.setLocalidad(localidadService.getLocalidadById(almacen.getLocalidad().getId()));
        }
            for (int i = 0; i < almacen.getDetalleAlmacen().size(); i++) {
                DetalleAlmacen detalleAlmacen = almacen.getDetalleAlmacen().get(i);
                detalleAlmacen.setAlmacen(almacen);
                producto = productoService.getProductoById(detalleAlmacen.getProducto().getId());

                detalleAlmacen.setProducto(producto);

            }

		return almacenRepository.save(almacen);
	}

	@Override
	public Almacen getAlmacenById(Long id) {
		// TODO Auto-generated method stub
		return almacenRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		almacenRepository.delete(id);
	}

	@Override
	public Almacen findByPrincipalAndEstado(boolean principal, int estado) {
		// TODO Auto-generated method stub
		return almacenRepository.findByPrincipalAndEstado(principal, estado);
	}

	@Override
	public void afectaCantidadEntradaProductos(Long productoId, Long cantidad) {

		Almacen almacen = findByPrincipalAndEstado(true, 1);

		for (int i = 0; i < almacen.getDetalleAlmacen().size(); i++) {
			if (almacen.getDetalleAlmacen().get(i).getProducto().getId().equals(productoId)) {

				almacen.getDetalleAlmacen().get(i)
						.setCantidad(almacen.getDetalleAlmacen().get(i).getCantidad() + cantidad);
			}

		}
		almacenRepository.save(almacen);
	}

	@Override
	public void afectaCantidadSalidaProductos(Long productoId, Long cantidad) {
		Almacen almacen = findByPrincipalAndEstado(true, 1);

		for (int i = 0; i < almacen.getDetalleAlmacen().size(); i++) {
			if (almacen.getDetalleAlmacen().get(i).getProducto().getId().equals(productoId)) {

				almacen.getDetalleAlmacen().get(i)
						.setCantidad(almacen.getDetalleAlmacen().get(i).getCantidad() - cantidad);
			}

		}
		almacenRepository.save(almacen);

	}



}
