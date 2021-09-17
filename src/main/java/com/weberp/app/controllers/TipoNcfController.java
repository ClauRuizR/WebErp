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
@RequestMapping("/tipoNcf")
public class TipoNcfController extends BaseController

    {

        public TipoNcfController(UsuarioService usuarioService){
            super(usuarioService);

    }

    @Secured("ROLE_ADMIN")
    @RequestMapping()
    public String consultaTipoNcf(Model model) {


        return "Mantenimientos/ConsultaTipoNcf";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/editar/{id}")
    public String formularioTipoNcf(@PathVariable Long id, Model model) {
        model.addAttribute("id",id);

        return "Mantenimientos/FormularioTipoNcf";
    }
        @RequestMapping("crear")
        public String crear(Model model) {

            setUsuario(model);
            return "Mantenimientos/FormularioTipoNcf";
        }
}
