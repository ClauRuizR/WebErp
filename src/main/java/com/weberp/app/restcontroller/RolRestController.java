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
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weberp.app.domain.Rol;
import com.weberp.app.dto.RolDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.RolService;
import com.weberp.app.validator.RolValidator;

/**
 * Created by claudioruiz on 6/9/17.
 */

@RestController
@RequestMapping("/rest/rol")
public class RolRestController extends ConfigMapper {


    @Autowired
    private RolService rolService;

    @Autowired
    private RolValidator rolValidator;

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<RolDTO> getT() {
        //...
        List<Rol> listaRol = rolService.listaRol();
        return listaRol.stream()
                .map(rol -> convertRolToDto(rol))
                .collect(Collectors.toList());
    }
    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<RolDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<RolDTO> resultPage = rolService.findPaginated(page,size);
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe facturas registradas.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "rol","page", "size" },method = RequestMethod.GET)
    public Page<RolDTO> findRolAndPaginated( @RequestParam("rol") String rol,
                                                     @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<RolDTO> resultPage = rolService.findRolAndPaginated(rol,new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe productos registrados.");
        }

        return resultPage;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RolDTO getById(@PathVariable("id") Long id) {
        return convertRolToDto(rolService.getRolById(id));
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RolDTO save(@Valid @RequestBody RolDTO rolDTO, BindingResult result, HttpServletResponse httpServletResponse) {
        Rol rol = null;
        try {
            rol = convertRolToEntity(rolDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilderError = new StringBuilder();


        rolValidator.validate(rolDTO, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());

        }

        try {

            rolService.guardar(rol);
        }catch (Exception ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }

        return convertRolToDto(rol);
    }


}
