package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.EmpresaService;
import com.weberp.app.services.LocalidadService;
import com.weberp.app.services.UsuarioService;
import com.weberp.app.validator.LocalidadValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/localidad")
@Controller
public class LocalidadController extends BaseController {

	@Autowired
	public LocalidadController(LocalidadService localidadService, LocalidadValidator localidadValidator, UsuarioService usuarioService,
							   EmpresaService empresaService) {
		super(usuarioService);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);

		return "Mantenimientos/ConsultaLocalidad";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("crear")
	public String crear(Model model) {
		setUsuario(model);

		return "Mantenimientos/FormularioLocalidad";
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		setUsuario(model);
		model.addAttribute("id",id);
		return "Mantenimientos/FormularioLocalidad";
	}




}
