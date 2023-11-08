package com.weberp.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weberp.app.dto.UsuarioDTO;
import com.weberp.app.services.UsuarioService;

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

        try{

            return usuarioService.validaUsuario(usuario,clave);

        }catch (Exception ex){
        throw new IllegalArgumentException(ex.getMessage());
        }
    }
}
