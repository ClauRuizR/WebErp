package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.UsuarioService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by claudioruiz on 6/9/17.
 */

@Controller
@RequestMapping("/rol")
public class RolController extends BaseController{


    public RolController(UsuarioService usuarioService) {
        super(usuarioService);
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping()
    public String consultaRol(Model model) {

        return "Seguridad/ConsultaRol";
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping("/editar/{id}")
    public String formularioServicio(@PathVariable Long id, Model model) {
        setUsuario(model);
        model.addAttribute("id",id);

        return "Seguridad/FormularioRol";
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping("crear")
    public String crear(Model model) {

        setUsuario(model);
        return "Seguridad/FormularioRol";
    }




}
