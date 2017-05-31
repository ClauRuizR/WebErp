package com.weberp.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weberp.app.domain.DetalleOrdenCompra;
import com.weberp.app.domain.OrdenCompra;
import com.weberp.app.domain.Producto;
import com.weberp.app.dto.OrdenCompraDTO;
import com.weberp.app.validator.OrdenCompraValidator;

@RequestMapping(value = "/ordencompras")
@Controller
public class OrdenCompraController extends BaseController{

	private OrdenCompraService ordenCompraService;

	private ProductoService productoService;

	private ProveedorService proveedorService;

	private EstatusService estatusService;

	private OrdenCompraValidator ordenCompraValidator;

	@Autowired
	public OrdenCompraController(OrdenCompraService ordenCompraService, ProveedorService proveedorService,
								 ProductoService productoService, EstatusService estatusService, OrdenCompraValidator ordenCompraValidator,
								 UsuarioService usuarioService) {
		super(usuarioService);
		this.ordenCompraService = ordenCompraService;
		this.proveedorService = proveedorService;
		this.estatusService = estatusService;
		this.ordenCompraValidator = ordenCompraValidator;
		this.productoService = productoService;
	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);
		List<OrdenCompra> listaOrdenCompra = ordenCompraService.listaOrdenCompra();
		model.addAttribute("listaOrdenCompras", listaOrdenCompra);
		model.addAttribute("ordenCompraDTO", new OrdenCompraDTO());
		model.addAttribute("proveedorList", proveedorService.getProveedores());
		List<Producto> listaProductos = productoService.listaProductos();
		model.addAttribute("productoList", listaProductos);

		return "OrdenCompra/ConsultaOrdenCompra";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		setUsuario(model);
		model.addAttribute("ordenCompra", new OrdenCompra());
		model.addAttribute("proveedorList", proveedorService.getProveedores());

		return "OrdenCompra/FormularioOrdenCompra";
	}

	@RequestMapping(value = "/agregarDetalleOrdenCompra", params = {
			"agregarDetalleOrdenCompra" }, method = RequestMethod.POST)
	public String agregarDetalleOrdenCompra(OrdenCompra ordenCompra, Model model) {
		setUsuario(model);
		ordenCompra.getDetalleOrdenCompra().add(new DetalleOrdenCompra());
		model.addAttribute("proveedorList", proveedorService.getProveedores());
		model.addAttribute("productoList", productoService.listaProductos());
		return "OrdenCompra/FormularioOrdenCompra";
	}

	@RequestMapping(value = "/quitarDetalleOrdenCompra", params = {
			"quitarDetalleOrdenCompra" }, method = RequestMethod.POST)
	public String quitarDetalleOrdenCompra(OrdenCompra ordenCompra, HttpServletRequest req, Model model) {
		setUsuario(model);

		int index = Integer.valueOf(req.getParameter("quitarDetalleOrdenCompra"));
		ordenCompra.getDetalleOrdenCompra().remove(index);
		model.addAttribute("proveedorList", proveedorService.getProveedores());
		model.addAttribute("productoList", productoService.listaProductos());
		return "OrdenCompra/FormularioOrdenCompra";
	}

	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute("ordenCompra") OrdenCompra ordenCompra, BindingResult result, Model model) {
		setUsuario(model);

		model.addAttribute("proveedorList", proveedorService.getProveedores());
		model.addAttribute("productoList", productoService.listaProductos());
		ordenCompraValidator.validate(ordenCompra, result);
		if (result.hasErrors()) {
			return "OrdenCompra/FormularioOrdenCompra";
		}
		ordenCompraService.guardar(ordenCompra);

		return "redirect:/ordencompras/editar/" + ordenCompra.getId();
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		setUsuario(model);
		model.addAttribute("ordenCompra", ordenCompraService.getOrdenCompraById(id));
		model.addAttribute("proveedorList", proveedorService.getProveedores());

		model.addAttribute("productoList", productoService.listaProductos());

		return "OrdenCompra/FormularioOrdenCompra";
	}

	@RequestMapping(value = "/aprobar/{id}", method = RequestMethod.POST)
	public String aprobar(@PathVariable Long id, Model model) {
		setUsuario(model);

		ordenCompraService.generaEntradaProductos(id);
		model.addAttribute("ordenCompra", ordenCompraService.getOrdenCompraById(id));
		model.addAttribute("proveedorList", proveedorService.getProveedores());

		// model.addAttribute("ordenCompraDTO", new OrdenCompraDTO());
		model.addAttribute("productoList", productoService.listaProductos());

		return "OrdenCompra/FormularioOrdenCompra";
	}

	@RequestMapping(value = "/pagar/{id}", method = RequestMethod.POST)
	public String pagar(@PathVariable Long id, Model model) {
		setUsuario(model);
		ordenCompraService.pagarOrdenComprar(id);

		model.addAttribute("ordenCompra", ordenCompraService.getOrdenCompraById(id));
		model.addAttribute("proveedorList", proveedorService.getProveedores());

		// model.addAttribute("ordenCompraDTO", new OrdenCompraDTO());
		model.addAttribute("productoList", productoService.listaProductos());

		return "OrdenCompra/FormularioOrdenCompra";
	}

	@RequestMapping("view/{id}")
	public String view(@PathVariable Long id, Model model) {
		setUsuario(model);
		OrdenCompra ordenCompra = ordenCompraService.getOrdenCompraById(id);
		model.addAttribute("ordenCompra", ordenCompra);
		model.addAttribute("proveedorList", proveedorService.getProveedores());
		model.addAttribute("estatusList", estatusService.findByEstatus(ordenCompra.getEstatus()));
		model.addAttribute("noDocumento", "Orden Compra: " + ordenCompra.getNumeroOrdenCompra());
		model.addAttribute("productoList", productoService.listaProductos());

		return "OrdenCompra/ViewOrdenCompra";
	}

	@RequestMapping(value = "cambiarEstatusOrdenCompra", method = RequestMethod.POST)
	public String aprobar(OrdenCompra ordenCompra, Model model) {
		setUsuario(model);

		ordenCompraService.cambiarEstatusOrdenCompra(ordenCompra);

		return "redirect:/ordencompras/view/" + ordenCompra.getId();
	}

}
