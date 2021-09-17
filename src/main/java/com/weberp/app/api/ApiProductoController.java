package com.weberp.app.api;

import com.weberp.app.domain.Producto;
import com.weberp.app.dto.ProductoDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.ProductoService;
import com.weberp.app.validator.ProductoValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by claudioruiz on 6/22/17.
 */

@RestController
@RequestMapping("/api/producto")
public class ApiProductoController extends ConfigMapper {


    @Autowired
    private ProductoService productoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductoValidator productoValidator;



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
