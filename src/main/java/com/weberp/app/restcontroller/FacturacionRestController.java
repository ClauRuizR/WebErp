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

import com.weberp.app.domain.Factura;
import com.weberp.app.dto.FacturaDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.exception.FacturaException;
import com.weberp.app.services.FacturaService;
import com.weberp.app.validator.FacturaValidator;

/**
 * Created by claudioruiz on 6/6/17.
 */

@RestController
@RequestMapping("/rest/facturacion")
public class FacturacionRestController extends ConfigMapper {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private FacturaValidator facturaValidator;

    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<FacturaDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<FacturaDTO> resultPage = facturaService.listaFacturas(new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe facturas registradas.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "numeroDocumento","page", "size" },method = RequestMethod.GET)
    public Page<FacturaDTO> findTipoProductoAndPaginated( @RequestParam("numeroDocumento") String numeroDocumento,
                                                            @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<FacturaDTO> resultPage = facturaService.findFacturaAndPaginated(numeroDocumento,new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe productos registrados.");
        }

        return resultPage;
    }


    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public FacturaDTO getFacturaDto(@PathVariable("id") Long id) {
        return convertFacturaToDto(facturaService.getFacturaById(id));
    }
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public FacturaDTO guardar(@Valid @RequestBody FacturaDTO facturaDTO, BindingResult result) throws FacturaException {
        Factura factura = null;

        if(null==facturaDTO)
            throw new IllegalArgumentException("No puede grabar el formulario vacio");

        try {
            factura = convertFacturaToEntity(facturaDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilderError = new StringBuilder();



        facturaValidator.validate(factura, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());
        }
    try{
        factura = facturaService.guardar(factura);
    }catch (Exception ex)
    {
        throw new IllegalArgumentException(ex.getMessage());
    }
        return convertFacturaToDto(factura);

    }
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/cambiarEstatusFactura", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public FacturaDTO aprobar(@RequestBody Factura factura, Model model) throws FacturaException {

       factura = facturaService.cambiarEstatusFactura(factura);

        return convertFacturaToDto(factura);
    }



}



