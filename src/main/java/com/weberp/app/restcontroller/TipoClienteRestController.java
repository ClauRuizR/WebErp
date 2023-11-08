package com.weberp.app.restcontroller;

import java.text.ParseException;
import java.util.List;

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

import com.weberp.app.dto.TipoClienteDTO;
import com.weberp.app.services.TipoClienteService;
import com.weberp.app.validator.TipoClienteValidator;

/**
 * Created by claudioruiz on 7/6/17.
 */
@RestController
@RequestMapping("/rest/tipoCliente")
public class TipoClienteRestController {


    @Autowired
    private TipoClienteService tipoClienteService;


    @Autowired
    private TipoClienteValidator tipoClienteValidator;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<TipoClienteDTO> getTipoClienteDTO() {


        return tipoClienteService.listaTipoCliente();
    }
    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<TipoClienteDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<TipoClienteDTO> resultPage = tipoClienteService.findPaginated(page,size);
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe tipo clientes registrados.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "codigo","page", "size" },method = RequestMethod.GET)
    public Page<TipoClienteDTO> findTipoClienteAndPaginated( @RequestParam("codigo") String codigo,
                                                           @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<TipoClienteDTO> resultPage=null;
        try {
            resultPage= tipoClienteService.findTipoClienteAndPaginated(codigo, new PageRequest(page, size, Sort.Direction.ASC, "id"));
            }catch(ParseException pe){

            }
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe tipo cliente registrados.");
        }

        return resultPage;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TipoClienteDTO getById(@PathVariable("id") Long id) {
        return tipoClienteService.getTipoClienteDTOById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TipoClienteDTO save(@Valid @RequestBody TipoClienteDTO tipoClienteDTO, BindingResult result, HttpServletResponse httpServletResponse) {
        StringBuilder stringBuilderError = new StringBuilder();


        tipoClienteValidator.validate(tipoClienteDTO, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());

        }

        try {

         tipoClienteDTO=    tipoClienteService.guardar(tipoClienteDTO);
        }catch (Exception ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }

        return tipoClienteDTO;
    }



}
