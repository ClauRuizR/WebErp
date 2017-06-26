package com.weberp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weberp.app.domain.Contacto;
import com.weberp.app.domain.Proveedor;
import com.weberp.app.dto.ProveedorDTO;
import com.weberp.app.services.ProveedorService;
import com.weberp.app.validator.ProveedorValidator;

@RequestMapping("/proveedores")
@Controller
public class ProveedorController extends BaseController {

	private ProveedorService proveedorService;

	private ProveedorValidator proveedorValidator;

	@Autowired
	public ProveedorController(ProveedorService proveedorService, ProveedorValidator proveedorValidator, UsuarioService usuarioService) {
		super(usuarioService);
		this.proveedorService = proveedorService;
		this.proveedorValidator = proveedorValidator;
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
