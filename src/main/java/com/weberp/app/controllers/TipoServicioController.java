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
@RequestMapping("/tiposervicio")
public class TipoServicioController extends BaseController {


    public TipoServicioController(UsuarioService usuarioService){
        super(usuarioService);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping()
    public String consultaTipoServicio(Model model) {

        return "Mantenimientos/ConsultaTipoServicios";
    }

    @RequestMapping("/editar/{id}")
    public String formularioTipoServicio(@PathVariable Long id, Model model) {
        model.addAttribute("id",id);

        return "Mantenimientos/FormularioTipoServicio";
    }
    @RequestMapping("crear")
    public String crear(Model model) {

        setUsuario(model);
        return "Mantenimientos/FormularioTipoServicio";
    }
}
