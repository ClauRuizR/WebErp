package com.weberp.app.api;

import com.weberp.app.domain.Usuario;
import com.weberp.app.dto.UsuarioDTO;
import com.weberp.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by claudioruiz on 6/22/17.
 */

@RestController
@RequestMapping("api/usuario")
public class ApiUsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @RequestMapping(value ="/validaUsuario",params ={"usuario","clave"},method = RequestMethod.GET)
    @ResponseBody
    public UsuarioDTO validaUsuario(@RequestParam("usuario") String usuario, @RequestParam("clave")String clave) {

        UsuarioDTO usuarioDTO = null;
        try{

            return usuarioService.validaUsuario(usuario,clave);

        }catch (Exception ex){
        throw new IllegalArgumentException(ex.getMessage());
        }





    }


}
