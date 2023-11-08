package com.weberp.app.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weberp.app.domain.Producto;
import com.weberp.app.dto.ProductoDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.ProductoService;

/**
 * Created by claudioruiz on 6/22/17.
 */

@RestController
@RequestMapping("/api/producto")
public class ApiProductoController extends ConfigMapper {


    @Autowired
    private ProductoService productoService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ProductoDTO> getProducto() {
        //...
        List<Producto> productoList = productoService.listaProductos();
        return productoList.stream()
                .map(producto -> convertProductoToDto(producto))
                .collect(Collectors.toList());



    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ProductoDTO getPost(@PathVariable("id") Long id) {
        return convertProductoToDto(productoService.getProductoById(id));
    }

}
