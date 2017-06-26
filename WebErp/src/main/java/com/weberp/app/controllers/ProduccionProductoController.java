package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.ProduccionProductoService;
import com.weberp.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;

/**
 * Created by claudioruiz on 6/18/17.
 */
@Controller
@RequestMapping("/produccionProducto")
public class ProduccionProductoController extends BaseController {


    private ProduccionProductoService produccionProductoService;


    @Autowired
    private ServletContext context;

    @Autowired
    public ProduccionProductoController(ProduccionProductoService produccionProductoService, UsuarioService usuarioService) {
        super(usuarioService);
        this.produccionProductoService = produccionProductoService;

    }

    @RequestMapping("")
    public String list(Model model) {
        setUsuario(model);


        return "ProduccionProducto/ConsultaProduccionProducto";
    }

}
