package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.weberp.app.services.ProveedorService;
import com.weberp.app.validator.ProveedorValidator;

@RequestMapping("/proveedores")
@Controller
public class ProveedorController extends BaseController {

	@Autowired
	public ProveedorController(ProveedorService proveedorService, ProveedorValidator proveedorValidator, UsuarioService usuarioService) {
		super(usuarioService);
	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);

		return "Proveedores/ConsultaProveedores";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		setUsuario(model);

		return "Proveedores/FormularioProveedor";
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		setUsuario(model);
		model.addAttribute("id",id);
		return "Proveedores/FormularioProveedor";
	}


}
