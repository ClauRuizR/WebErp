package com.weberp.app.restcontroller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weberp.app.domain.OrdenCompra;
import com.weberp.app.dto.OrdenCompraDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.OrdenCompraService;
import com.weberp.app.validator.OrdenCompraValidator;

/**
 * Created by claudioruiz on 6/7/17.
 */

@RestController
@RequestMapping("/rest/ordencompra")
public class OrdenCompraRestController extends ConfigMapper {
    @Autowired
    private OrdenCompraService ordenCompraService;

    @Autowired
    private OrdenCompraValidator ordenCompraValidator;


    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<OrdenCompraDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<OrdenCompraDTO> resultPage = ordenCompraService.listaOrdenCompra(new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe Ordenes registradas.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "numeroOrdenCompra","page", "size" },method = RequestMethod.GET)
    public Page<OrdenCompraDTO> findOrdenCompraAndPaginated( @RequestParam("numeroOrdenCompra") String numeroOrdenCompra,
                                                          @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<OrdenCompraDTO> resultPage = ordenCompraService.findOrdenCompraAndPaginated(numeroOrdenCompra,new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe Ordenes registradas.");
        }

        return resultPage;
    }




    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OrdenCompraDTO getFacturaDto(@PathVariable("id") Long id) {
        return convertOrdenCompraToDto(ordenCompraService.getOrdenCompraById(id));
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public OrdenCompraDTO guardar(@Valid @RequestBody OrdenCompraDTO ordenCompraDTO, BindingResult result) {
        OrdenCompra ordenCompra = null;
        try {
            ordenCompra = convertOrdenCompraToEntity(ordenCompraDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilderError = new StringBuilder();

        ordenCompraValidator.validate(ordenCompra, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());
        }
        try{
        ordenCompra = ordenCompraService.guardar(ordenCompra);
    }catch (Exception ex)
    {
        throw new IllegalArgumentException(ex.getMessage());
    }
        return convertOrdenCompraToDto(ordenCompra);

    }
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/cambiarEstatusOrdenCompra", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public OrdenCompraDTO aprobar(@RequestBody OrdenCompra ordenCompra, Model model)  {


        ordenCompra = ordenCompraService.cambiarEstatusOrdenCompra(ordenCompra);

        return convertOrdenCompraToDto(ordenCompra);
    }


}
