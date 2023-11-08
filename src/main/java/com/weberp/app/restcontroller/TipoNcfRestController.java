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

import com.weberp.app.dto.TipoNcfDTO;
import com.weberp.app.services.TipoNcfService;
import com.weberp.app.validator.TipoNcfValidator;

/**
 * Created by claudioruiz on 7/7/17.
 */

@RestController
@RequestMapping("/rest/tipoNcf")
public class TipoNcfRestController {

    @Autowired
    private TipoNcfService tipoNcfService;

    @Autowired
    private TipoNcfValidator tipoNcfValidator;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<TipoNcfDTO> getTipoClienteDTO() {


        return tipoNcfService.listaTipoDTONcf();
    }
    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<TipoNcfDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<TipoNcfDTO> resultPage = tipoNcfService.findPaginated(page,size);
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe tipo clientes registrados.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "codigo","page", "size" },method = RequestMethod.GET)
    public Page<TipoNcfDTO> findTipoNcfAndPaginated( @RequestParam("codigo") String codigo,
                                                             @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<TipoNcfDTO> resultPage=null;
        try {
            resultPage= tipoNcfService.findTipoNcfDTOAndPaginated(codigo, new PageRequest(page, size, Sort.Direction.ASC, "id"));
        }catch(ParseException pe){

        }
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe tipo cliente registrados.");
        }

        return resultPage;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TipoNcfDTO getById(@PathVariable("id") Long id) {
        return tipoNcfService.getTipoNcfById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TipoNcfDTO save(@Valid @RequestBody TipoNcfDTO tipoNcfDTO, BindingResult result, HttpServletResponse httpServletResponse) {


        StringBuilder stringBuilderError = new StringBuilder();


        tipoNcfValidator.validate(tipoNcfDTO, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());

        }

        try {

            tipoNcfDTO=    tipoNcfService.guardar(tipoNcfDTO);
        }catch (Exception ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }

        return tipoNcfDTO;
    }
}
