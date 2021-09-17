package com.weberp.app.restcontroller;

import com.weberp.app.domain.Servicio;
import com.weberp.app.dto.ServicioDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.ServicioService;
import com.weberp.app.validator.ServicioValidator;
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
@RequestMapping("/rest/servicio")
public class ServicioRestController extends ConfigMapper{


    @Autowired
    private ServicioService servicioService;


    @Autowired
    private ServicioValidator servicioValidator;



    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ServicioDTO> getTipoServicio() {
        //...
        List<Servicio> listaServicio = servicioService.listaServicio();
        return listaServicio.stream()
                .map(servicio -> convertServicioToDto(servicio))
                .collect(Collectors.toList());
    }
    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<ServicioDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<ServicioDTO> resultPage = servicioService.findPaginated(page,size);
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe facturas registradas.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "nombre","page", "size" },method = RequestMethod.GET)
    public Page<ServicioDTO> findTipoProductoAndPaginated( @RequestParam("nombre") String nombre,
                                                          @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<ServicioDTO> resultPage = servicioService.findServicioandPaginated(nombre,new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe productos registrados.");
        }

        return resultPage;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ServicioDTO getById(@PathVariable("id") Long id) {
        return convertServicioToDto(servicioService.getServicioById(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ServicioDTO save(@Valid @RequestBody ServicioDTO servicioDTO, BindingResult result, HttpServletResponse httpServletResponse) {
        Servicio servicio = null;
        try {
            servicio = convertServicioToEntity(servicioDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilderError = new StringBuilder();


        servicioValidator.validate(servicioDTO, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());

        }

        try {

            servicioService.guardar(servicio);
        }catch (Exception ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }

        return convertServicioToDto(servicio);
    }


}
