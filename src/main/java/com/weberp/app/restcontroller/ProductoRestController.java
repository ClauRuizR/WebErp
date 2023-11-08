package com.weberp.app.restcontroller;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weberp.app.domain.Producto;
import com.weberp.app.dto.DetalleAlmacenDTO;
import com.weberp.app.dto.ProductoDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.ProductoService;
import com.weberp.app.validator.ProductoValidator;

/**
 * Created by claudioruiz on 6/6/17.
 */
@RestController
@RequestMapping("/rest/producto")
public class ProductoRestController extends ConfigMapper{



    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoValidator productoValidator;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ProductoDTO> getProducto() {
        //...
        List<Producto> productoList = productoService.listaProductosPorEmpresa();
        return productoList.stream()
                .map(producto -> convertProductoToDto(producto))
                .collect(Collectors.toList());



    }

    @RequestMapping(value="/getDetalleAlmacen/{id}",method = RequestMethod.GET)
    public List<DetalleAlmacenDTO> findDetalleAlmacen(@PathVariable Long id) {

    return productoService.buscarProductosEnAlmacenes(id);

    }


    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<ProductoDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<ProductoDTO> resultPage = productoService.findPaginated(new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe productos registrados.");
        }

        return resultPage;
    }


    @RequestMapping(value="/filtrar",params = { "codigoAlfaNumerico","page", "size" },method = RequestMethod.GET)
    public Page<ProductoDTO> findProductoAndPaginated( @RequestParam("codigoAlfaNumerico") String codigoAlfaNumerico,
                                                            @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<ProductoDTO> resultPage = productoService.findProductoAndPaginated(codigoAlfaNumerico,new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe productos registrados.");
        }

        return resultPage;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ProductoDTO getPost(@PathVariable("id") Long id) {
        return convertProductoToDto(productoService.getProductoById(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void save(@Valid @RequestBody ProductoDTO productoDTO, BindingResult result, HttpServletResponse httpServletResponse) {
        Producto producto = null;
        try {
            producto = convertProductoToEntity(productoDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilderError = new StringBuilder();


        productoValidator.validate(productoDTO, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());

        }

        try {
            productoService.guardar(producto);
        }catch (Exception ex) {
           throw new IllegalArgumentException(ex.getMessage());
        }
    }

}
