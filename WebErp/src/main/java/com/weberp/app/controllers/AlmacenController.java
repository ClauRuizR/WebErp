package com.weberp.app.controllers;

import javax.servlet.http.HttpServletRequest;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.LocalidadService;
import com.weberp.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weberp.app.domain.Almacen;
import com.weberp.app.domain.DetalleAlmacen;
import com.weberp.app.dto.AlmacenDTO;
import com.weberp.app.dto.TransferenciaAlmacen;
import com.weberp.app.services.AlmacenService;
import com.weberp.app.services.ProductoService;
import com.weberp.app.validator.AlmacenValidator;
import com.weberp.app.validator.TransferenciaAlmacenValidator;

@RequestMapping("/almacen")
@Controller
public class AlmacenController extends BaseController {

	private AlmacenService almacenService;

	private AlmacenValidator almacenValidator;

	private ProductoService productoService;

	private LocalidadService localidadService;

	private TransferenciaAlmacenValidator transferenciaAlmacenValidator;

	@Autowired
	public AlmacenController(AlmacenService almacenService, AlmacenValidator almacenValidator,
							 ProductoService productoService,
							 TransferenciaAlmacenValidator transferenciaAlmacenValidator,
							 LocalidadService localidadService,
							 UsuarioService usuarioService) {
		super(usuarioService);
		this.almacenService = almacenService;
		this.almacenValidator = almacenValidator;
		this.productoService = productoService;
		this.transferenciaAlmacenValidator = transferenciaAlmacenValidator;
		this.localidadService = localidadService;
	}

	@RequestMapping("transferenciaAlmacen")
	public String transferenciaAlmacen(Model model) {
		setUsuario(model);
		model.addAttribute("transferenciaAlmacen", new TransferenciaAlmacen());
		model.addAttribute("almacenOrigenList", almacenService.listaAlmacen());

		model.addAttribute("productoList", productoService.listaProductos());

		model.addAttribute("almacenDestinoList", almacenService.listaAlmacen());

		return "Almacen/TransferenciaAlmacen";
	}

	@RequestMapping("aplicarTransferencia")
	public String aplicarTransferencia(@ModelAttribute("almacen") TransferenciaAlmacen transferenciaAlmacen,
			BindingResult result, Model model) {
		setUsuario(model);
		model.addAttribute("transferenciaAlmacen", new TransferenciaAlmacen());
		model.addAttribute("almacenOrigenList", almacenService.listaAlmacen());

		model.addAttribute("productoList", productoService.listaProductos());

		model.addAttribute("almacenDestinoList", almacenService.listaAlmacen());
		transferenciaAlmacenValidator.validate(transferenciaAlmacen, result);
		if (result.hasErrors()) {
			return "Almacen/TransferenciaAlmacen";
		}
		return "Almacen/TransferenciaAlmacen";
	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);
		model.addAttribute("listaAlmacen", almacenService.listaAlmacen());
		model.addAttribute("almacenDTO", new AlmacenDTO());

		return "Almacen/ConsultaAlmacen";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		setUsuario(model);
		model.addAttribute("almacen", new Almacen());
		model.addAttribute("productoList", productoService.listaProductos());
		model.addAttribute("localidadList", localidadService.listaLocalidades());
		return "Almacen/FormularioAlmacen";
	}

	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute("almacen") Almacen almacen, BindingResult result, Model model) {
		setUsuario(model);
		almacenValidator.validate(almacen, result);
		model.addAttribute("localidadList", localidadService.listaLocalidades());
		if (result.hasErrors()) {
			return "Mantenimientos/FormularioProducto";
		}
		almacenService.guardar(almacen);

		return "redirect:/almacen/editar/" + almacen.getId();
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		setUsuario(model);
		model.addAttribute("almacen", almacenService.getAlmacenById(id));
		model.addAttribute("productoList", productoService.listaProductos());
		model.addAttribute("localidadList", localidadService.listaLocalidades());
		return "Almacen/FormularioAlmacen";
	}

	@RequestMapping(value = "/agregarDetalleAlmacen", params = { "agregarDetalleAlmacen" }, method = RequestMethod.POST)
	public String agregarDetalleAlmacen(Almacen almacen, Model model) {
		setUsuario(model);
		almacen.getDetalleAlmacen().add(new DetalleAlmacen());
		model.addAttribute("productoList", productoService.listaProductos());
		model.addAttribute("localidadList", localidadService.listaLocalidades());

		return "Almacen/FormularioAlmacen";
	}

	@RequestMapping(value = "/quitarDetalleAlmacen", params = { "quitarDetalleAlmacen" }, method = RequestMethod.POST)
	public String quitarDetalleAlmacen(Almacen almacen, HttpServletRequest req, Model model) {
		setUsuario(model);
		model.addAttribute("localidadList", localidadService.listaLocalidades());
		int index = Integer.valueOf(req.getParameter("quitarDetalleAlmacen"));

		almacen.getDetalleAlmacen().remove(index);
		model.addAttribute("productoList", productoService.listaProductos());

		return "Almacen/FormularioAlmacen";
	}

}
