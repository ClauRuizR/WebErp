package com.weberp.app.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by claudioruiz on 6/9/17.
 */

@Controller
@RequestMapping("/grupoempresa")
public class GrupoEmpresaController {



    @Secured("ROLE_ADMIN")
    @RequestMapping()
    public String consultaGrupoEmpresa(Model model) {

        return "Seguridad/ConsultaGrupoEmpresa";
    }

    @RequestMapping("/editar/{id}")
    public String formularioGrupoEmpresa(@PathVariable Long id, Model model) {
        model.addAttribute("id",id);
        return "Seguridad/FormularioGrupoEmpresa";
    }

}
