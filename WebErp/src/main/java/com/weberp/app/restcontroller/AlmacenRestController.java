package com.weberp.app.restcontroller;

import com.weberp.app.domain.Almacen;
import com.weberp.app.dto.AlmacenDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.AlmacenService;
import com.weberp.app.validator.AlmacenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

/**
 * Created by claudioruiz on 6/23/17.
 */

@RestController
@RequestMapping("/rest/almacen")
public class AlmacenRestController extends ConfigMapper {


    @Autowired
    private AlmacenService almacenService;


    @Autowired
    private AlmacenValidator almacenValidator;

    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<AlmacenDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<AlmacenDTO> resultPage = almacenService.findPaginated(page,size);
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe facturas registradas.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "codigo","page", "size" },method = RequestMethod.GET)
    public Page<AlmacenDTO> findAlmacenAndPaginated( @RequestParam("codigo") String codigo,
                                                          @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<AlmacenDTO> resultPage = almacenService.findAlmacenAndPaginated(codigo,new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe almacenes registrados.");
        }

        return resultPage;
    }


    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlmacenDTO getAlmacenDto(@PathVariable("id") Long id) {
        return convertAlmacenToDto(almacenService.getAlmacenById(id));
    }
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public AlmacenDTO guardar(@Valid @RequestBody AlmacenDTO almacenDTO, BindingResult result)  {
        Almacen almacen = null;

        if(null==almacenDTO)
            throw new IllegalArgumentException("No puede grabar el formulario vacio");

        try {
            almacen = convertAlmacenToEntity(almacenDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilderError = new StringBuilder();



        almacenValidator.validate(almacen, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());
        }
        try{
            almacen = almacenService.guardar(almacen);
        }catch (Exception ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }
        return convertAlmacenToDto(almacen);

    }



}
