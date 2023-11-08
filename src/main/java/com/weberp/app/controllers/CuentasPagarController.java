package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/cuentaspagar")
public class CuentasPagarController extends BaseController {

	@Autowired
	public CuentasPagarController(OrdenCompraService ordenCompraService, ProductoService productoService,
								  ProveedorService proveedorService, EstatusService estatusService, UsuarioService usuarioService) {
		super(usuarioService);

	}

	@RequestMapping("")
	public String list(Model model) {

		return "CuentasPagar/ConsultaCuentasPagar";
	}

	@RequestMapping("view/{id}")
	public String view(@PathVariable Long id, Model model) {
		setUsuario(model);
		model.addAttribute("id",id);

		return "CuentasPagar/ViewCuentasPagar";
	}


}
