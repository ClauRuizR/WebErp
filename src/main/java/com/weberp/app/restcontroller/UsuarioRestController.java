package com.weberp.app.restcontroller;

import com.weberp.app.common.view.BaseRestController;
import com.weberp.app.domain.Usuario;
import com.weberp.app.dto.UsuarioDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.UsuarioRepository;
import com.weberp.app.services.UsuarioService;
import com.weberp.app.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ConfigurableTypeInformationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by claudioruiz on 6/9/17.
 */

@RestController
@RequestMapping("/rest/usuarios")
public class UsuarioRestController extends ConfigMapper {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioValidator usuarioValidator;

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<UsuarioDTO> getT() {
        //...
        List<Usuario> listaRol = usuarioService.listaUsuarios();
        return listaRol.stream()
                .map(usuario -> convertUsuarioToDto(usuario))
                .collect(Collectors.toList());
    }

    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<UsuarioDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<UsuarioDTO> resultPage = usuarioService.findPaginated(page,size);
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe facturas registradas.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "usuario","page", "size" },method = RequestMethod.GET)
    public Page<UsuarioDTO> findUsuarioAndPaginated( @RequestParam("usuario") String usuario,
                                                     @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<UsuarioDTO> resultPage = usuarioService.findUsuarioAndPaginated(usuario,new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe productos registrados.");
        }

        return resultPage;
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UsuarioDTO getById(@PathVariable("id") Long id) {
        return convertUsuarioToDto(usuarioService.getUsuarioById(id));
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UsuarioDTO save(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result, HttpServletResponse httpServletResponse) {
        Usuario usuario = null;
        try {
            usuario = convertUsuarioToEntity(usuarioDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilderError = new StringBuilder();


        usuarioValidator.validate(usuarioDTO, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());

        }

        try {

            usuarioService.guardar(usuario);
        }catch (Exception ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }

        return convertUsuarioToDto(usuario);
    }

    @RequestMapping(value = "cambiarContrasena", method = RequestMethod.POST)
    public String guardar(Usuario usuario) {


        try{
            usuarioService.cambiarClave(usuario.getUsuario(),usuario.getClave());
        }catch (Exception ex){
            return "false";
        }

        return "OK";
    }

}
