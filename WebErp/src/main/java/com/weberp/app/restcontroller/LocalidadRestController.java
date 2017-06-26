package com.weberp.app.restcontroller;

import com.weberp.app.domain.Localidad;
import com.weberp.app.dto.LocalidadDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.LocalidadService;
import com.weberp.app.validator.LocalidadValidator;
import org.apache.tomcat.jni.Local;
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
 * Created by claudioruiz on 6/23/17.
 */

@RestController
@RequestMapping("/rest/localidad")
public class LocalidadRestController extends ConfigMapper{

    @Autowired
    private LocalidadService localidadService;


    @Autowired
    private LocalidadValidator localidadValidator;



    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<LocalidadDTO> getLocalidad() {
        //...
        List<Localidad> listaServicio = localidadService.listaLocalidades();
        return listaServicio.stream()
                .map(localidad -> convertLocalidadToDto(localidad))
                .collect(Collectors.toList());
    }


    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<LocalidadDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<LocalidadDTO> resultPage = localidadService.findPaginated(page,size);
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe localidades registradas.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "nombre","page", "size" },method = RequestMethod.GET)
    public Page<LocalidadDTO> findLocalidadAndPaginated( @RequestParam("nombre") String nombre,
                                                     @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<LocalidadDTO> resultPage = localidadService.findLocalidadAndPaginated(nombre,new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe localidades registrados.");
        }

        return resultPage;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LocalidadDTO getPost(@PathVariable("id") Long id) {
        return convertLocalidadToDto(localidadService.getLocalidadById(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void save(@Valid @RequestBody LocalidadDTO localidadDTO, BindingResult result, HttpServletResponse httpServletResponse) {
        Localidad localidad = null;
        try {
            localidad = convertLocalidadToEntity(localidadDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilderError = new StringBuilder();


        localidadValidator.validate(localidad, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());

        }
        try{

            localidadService.guardar(localidad);
        }catch (Exception ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }

    }





}
