package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.UsuarioService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by claudioruiz on 6/13/17.
 */

@Controller
@RequestMapping("/servicio")
public class ServicioController extends BaseController

    {

        public ServicioController(UsuarioService usuarioService){
            super(usuarioService);

    }

    @Secured("ROLE_ADMIN")
    @RequestMapping()
    public String consultaServicios(Model model) {


        return "Mantenimientos/ConsultaServicios";
    }

    @RequestMapping("/editar/{id}")
    public String formularioServicio(@PathVariable Long id, Model model) {
        model.addAttribute("id",id);

        return "Mantenimientos/FormularioServicio";
    }
        @RequestMapping("crear")
        public String crear(Model model) {

            setUsuario(model);
            return "Mantenimientos/FormularioServicio";
        }
}
