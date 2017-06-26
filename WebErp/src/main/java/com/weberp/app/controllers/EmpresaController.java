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
@RequestMapping("/empresa")
public class EmpresaController {



    @Secured("ROLE_ADMIN")
    @RequestMapping()
    public String consultaEmpresa(Model model) {

        return "Seguridad/ConsultaEmpresa";
    }

    @RequestMapping("/editar/{id}")
    public String formularioEmpresa(@PathVariable Long id, Model model) {
        model.addAttribute("id",id);
        return "Seguridad/FormularioEmpresa";
    }



}
