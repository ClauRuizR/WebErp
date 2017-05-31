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
		model.addAttribute("proveedores", proveedorService.getProveedores());
		model.addAttribute("proveedorDTO", new ProveedorDTO());
		return "Proveedores/ConsultaProveedores";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		setUsuario(model);
		model.addAttribute("proveedor", new Proveedor());
		return "Proveedores/FormularioProveedor";
	}

	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute("proveedor") Proveedor proveedor, BindingResult result, Model model) {
		setUsuario(model);
		proveedorValidator.validate(proveedor, result);
		if (result.hasErrors()) {
			return "Proveedores/FormularioProveedor";
		}

		proveedorService.guardar(proveedor);
		return "redirect:/proveedores/editar/"+proveedor.getId();
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		setUsuario(model);
		Proveedor p = proveedorService.getProveedorById(id);
		model.addAttribute("proveedor", p);
		return "Proveedores/FormularioProveedor";
	}

	@RequestMapping(value = "agregarContacto", params = { "agregarContacto" }, method = RequestMethod.POST)
	public String agregarContacto(Proveedor proveedor,Model model) {
		setUsuario(model);
		proveedor.getContactos().add(new Contacto());
		return "Proveedores/FormularioProveedor";
	}

	@RequestMapping(value = "quitarContacto", params = { "quitarContacto" }, method = RequestMethod.POST)
	public String quitarContacto(Proveedor proveedor, HttpServletRequest req,Model model) {
		setUsuario(model);
		int index = Integer.valueOf(req.getParameter("quitarContacto"));
		proveedor.getContactos().remove(index);
		return "Proveedores/FormularioProveedor";
	}

	@RequestMapping(value = "/buscarproveedor", method = RequestMethod.POST)
	public String buscarProveedor(ProveedorDTO proveedorDTO, Model model) {
		List<Proveedor> listaProveedor = new ArrayList<>();
		setUsuario(model);
		try {
			listaProveedor = proveedorService.buscarProveedor(proveedorDTO);
			model.addAttribute("listaProveedor", listaProveedor);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Proveedores/ConsultaProveedores";
	}
}
