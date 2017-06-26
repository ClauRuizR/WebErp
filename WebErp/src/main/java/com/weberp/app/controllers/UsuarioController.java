package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.domain.Usuario;
import com.weberp.app.services.UsuarioService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/usuarios")
public class UsuarioController extends BaseController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        super(usuarioService);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping()
    public String consultaUsuarios(Model model) {

        return "Seguridad/ConsultaUsuarios";
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping("/editar/{id}")
    public String formularioUsuarios(@PathVariable Long id, Model model) {
        model.addAttribute("id",id);
        return "Seguridad/FormularioUsuario";
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping("crear")
    public String crear(Model model) {

        setUsuario(model);
        return "Seguridad/FormularioUsuario";
    }


}
