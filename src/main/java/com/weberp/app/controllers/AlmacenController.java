package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.LocalidadService;
import com.weberp.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weberp.app.domain.Almacen;
import com.weberp.app.dto.TransferenciaAlmacen;
import com.weberp.app.services.AlmacenService;
import com.weberp.app.services.ProductoService;
import com.weberp.app.validator.AlmacenValidator;
import com.weberp.app.validator.TransferenciaAlmacenValidator;

@RequestMapping("/almacen")
@Controller
public class AlmacenController extends BaseController {

	@Autowired
	public AlmacenController(AlmacenService almacenService, AlmacenValidator almacenValidator,
							 ProductoService productoService,
							 TransferenciaAlmacenValidator transferenciaAlmacenValidator,
							 LocalidadService localidadService,
							 UsuarioService usuarioService) {
		super(usuarioService);
	}
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("transferenciaAlmacen")
	public String transferenciaAlmacen(Model model) {
		setUsuario(model);

		return "Almacen/TransferenciaAlmacen";
	}
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("aplicarTransferencia")
	public String aplicarTransferencia(@ModelAttribute("almacen") TransferenciaAlmacen transferenciaAlmacen,
			BindingResult result, Model model) {
		setUsuario(model);

		return "Almacen/TransferenciaAlmacen";
	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);


		return "Almacen/ConsultaAlmacen";
	}
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("crear")
	public String crear(Model model) {
		setUsuario(model);

		return "Almacen/FormularioAlmacen";
	}
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute("almacen") Almacen almacen, BindingResult result, Model model) {
		setUsuario(model);


		return "redirect:/almacen/editar/" + almacen.getId();
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		setUsuario(model);

		return "Almacen/FormularioAlmacen";
	}

}
