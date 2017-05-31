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

import com.weberp.app.domain.Cliente;
import com.weberp.app.domain.Contacto;
import com.weberp.app.dto.ClienteDTO;
import com.weberp.app.services.ClienteService;
import com.weberp.app.validator.ClienteValidator;
import com.weberp.app.validator.ContactoValidator;

@RequestMapping("/clientes")
@Controller
public class ClienteController extends BaseController {

	private ClienteService clienteService;

	private ClienteValidator clienteValidator;



	@Autowired
	public ClienteController(ClienteService clienteService, ClienteValidator clienteValidator,
							 ContactoValidator contactoValidator, UsuarioService usuarioService) {
		super(usuarioService);
		this.clienteValidator = clienteValidator;
		this.clienteService = clienteService;
		
	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);
		model.addAttribute("listaClientes", clienteService.listaClientes());
		model.addAttribute("clienteDTO", new ClienteDTO());
		return "Clientes/ConsultaClientes";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		setUsuario(model);
		model.addAttribute("cliente", new Cliente());

		return "Clientes/FormularioCliente";
	}

	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
		setUsuario(model);
		clienteValidator.validate(cliente, result);
		if (result.hasErrors()) {
			//System.err.println(result);
			return "Clientes/FormularioCliente";
		}


		if (clienteService.existeCliente(cliente) && cliente.getId() == null)
			return "Clientes/FormularioCliente";

		clienteService.guardar(cliente);

		return "redirect:/clientes";
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		setUsuario(model);
		model.addAttribute("cliente", clienteService.getClienteById(id));

		return "Clientes/FormularioCliente";
	}

	@RequestMapping(value = "/agregarContacto", params = { "agregarContacto" }, method = RequestMethod.POST)
	public String agregarContacto(Cliente cliente,Model model) {
		setUsuario(model);
		cliente.getContactos().add(new Contacto());
		return "Clientes/FormularioCliente";
	}

	@RequestMapping(value = "/quitarContacto", params = { "quitarContacto" }, method = RequestMethod.POST)
	public String quitarContacto(Cliente cliente, HttpServletRequest req,Model model) {
		setUsuario(model);
		int index = Integer.valueOf(req.getParameter("quitarContacto"));
		cliente.getContactos().remove(index);
		return "Clientes/FormularioCliente";
	}

	@RequestMapping(value = "/buscarcliente", method = RequestMethod.POST)
	public String buscarCliente(ClienteDTO clienteDTO, Model model) {
		setUsuario(model);
		List<Cliente> listaCliente = new ArrayList<>();
		try {
			listaCliente = clienteService.buscarCliente(clienteDTO);
			model.addAttribute("listaCliente", listaCliente);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Clientes/ConsultaClientes";
	}

}
