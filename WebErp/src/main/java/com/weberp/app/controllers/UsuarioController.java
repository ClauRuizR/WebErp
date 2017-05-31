package com.weberp.app.controllers;

import com.weberp.app.domain.Usuario;
import com.weberp.app.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
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
