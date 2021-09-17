package com.weberp.app.restcontroller;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.ComprobanteFiscal;
import com.weberp.app.dto.ComprobanteFiscalDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.UsuarioService;
import com.weberp.app.validator.ComprobanteFiscalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.weberp.app.services.ComprobanteFiscalService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by claudioruiz on 8/14/16.
 */
@RequestMapping(value="/rest/comprobanteFiscal")
@RestController
public class ComprobanteFiscalRestController extends ConfigMapper {



    @Autowired
    private ComprobanteFiscalService comprobanteFiscalService;


    @Autowired
    private UsuarioService usuarioService;



    @Autowired
    private ComprobanteFiscalValidator comprobanteFiscalValidator;


    @RequestMapping(method = RequestMethod.GET)
    public List<ComprobanteFiscalDTO> obtenerComprobanteFiscal(){


        List<ComprobanteFiscal> clientesList = comprobanteFiscalService.listaComprobanteFiscal();
        return clientesList.stream()
                .map(comprobanteFiscal -> convertComprobanteFiscalToDto(comprobanteFiscal))
                .collect(Collectors.toList());

    }

    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<ComprobanteFiscalDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<ComprobanteFiscalDTO> resultPage = comprobanteFiscalService.findPaginated(page,size);
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe tipo clientes registrados.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "letra","page", "size" },method = RequestMethod.GET)
    public Page<ComprobanteFiscalDTO> findTipoNcfAndPaginated( @RequestParam("letra") String letra,
                                                     @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<ComprobanteFiscalDTO> resultPage=null;
        try {
            resultPage= comprobanteFiscalService.findComprobanteFiscalDTOAndPaginated(letra, new PageRequest(page, size, Sort.Direction.ASC, "id"));
        }catch(ParseException pe){

        }
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe tipo cliente registrados.");
        }

        return resultPage;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ComprobanteFiscalDTO getById(@PathVariable("id") Long id) {
        return comprobanteFiscalService.getComprobanteFiscalById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ComprobanteFiscalDTO save(@Valid @RequestBody ComprobanteFiscalDTO comprobanteFiscalDTO, BindingResult result, HttpServletResponse httpServletResponse) {


        StringBuilder stringBuilderError = new StringBuilder();


        comprobanteFiscalValidator.validate(comprobanteFiscalDTO, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());

        }

        try {

            comprobanteFiscalDTO=    comprobanteFiscalService.guardar(comprobanteFiscalDTO);
        }catch (Exception ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }

        return comprobanteFiscalDTO;
    }

}
