package com.weberp.app.restcontroller;

import com.weberp.app.domain.TipoServicio;
import com.weberp.app.dto.TipoServicioDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.TipoServicioService;
import com.weberp.app.validator.TipoServicioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by claudioruiz on 6/13/17.
 */
@RestController
@RequestMapping("/rest/tipoServicio")
public class TipoServicioRestController  extends ConfigMapper {

    @Autowired
    private TipoServicioService tipoServicioService;

    @Autowired
    private TipoServicioValidator tipoServicioValidator;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<TipoServicioDTO> getTipoServicio() {
        //...
        List<TipoServicio> listaEmpresa = tipoServicioService.listaTipoServicio();
        return listaEmpresa.stream()
                .map(tipoServicio -> convertTipoServicioToDto(tipoServicio))
                .collect(Collectors.toList());
    }

    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<TipoServicioDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<TipoServicioDTO> resultPage = tipoServicioService.findPaginated(page,size);
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe facturas registradas.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "nombre","page", "size" },method = RequestMethod.GET)
    public Page<TipoServicioDTO> findTipoProductoAndPaginated( @RequestParam("nombre") String nombre,
                                                          @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<TipoServicioDTO> resultPage = tipoServicioService.findTipoServicioAndPaginated(nombre,new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe productos registrados.");
        }

        return resultPage;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TipoServicioDTO getById(@PathVariable("id") Long id) {
        return convertTipoServicioToDto(tipoServicioService.getTipoServicioId(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TipoServicioDTO save(@Valid @RequestBody TipoServicioDTO tipoServicioDTO, BindingResult result, HttpServletResponse httpServletResponse) {
        TipoServicio tipoServicio = null;
        try {
            tipoServicio = convertTipoServicioToEntity(tipoServicioDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilderError = new StringBuilder();


        tipoServicioValidator.validate(tipoServicioDTO, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());

        }

        try {

            tipoServicioService.guardar(tipoServicio);
        }catch (Exception ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }

        return convertTipoServicioToDto(tipoServicio);
    }


}
