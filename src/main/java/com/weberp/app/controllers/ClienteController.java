package com.weberp.app.controllers;

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

import com.weberp.app.domain.Cliente;
import com.weberp.app.services.ClienteService;
import com.weberp.app.validator.ClienteValidator;
import com.weberp.app.validator.ContactoValidator;

@RequestMapping("/clientes")
@Controller
public class ClienteController extends BaseController {

	@Autowired
	public ClienteController(ClienteService clienteService, ClienteValidator clienteValidator,
							 ContactoValidator contactoValidator, UsuarioService usuarioService) {
		super(usuarioService);
		
	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);

		return "Clientes/ConsultaClientes";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		setUsuario(model);


		return "Clientes/FormularioCliente";
	}

	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
		setUsuario(model);


		return "redirect:/clientes";
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		setUsuario(model);
		model.addAttribute("id", id);

		return "Clientes/FormularioCliente";
	}

	@RequestMapping(value = "/agregarContacto", params = { "agregarContacto" }, method = RequestMethod.POST)
	public String agregarContacto(Cliente cliente,Model model) {
		setUsuario(model);

		return "Clientes/FormularioCliente";
	}

	@RequestMapping(value = "/quitarContacto", params = { "quitarContacto" }, method = RequestMethod.POST)
	public String quitarContacto(Cliente cliente, HttpServletRequest req,Model model) {
		setUsuario(model);

		return "Clientes/FormularioCliente";
	}



}
