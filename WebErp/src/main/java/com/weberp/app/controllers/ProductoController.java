package com.weberp.app.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weberp.app.domain.Producto;
import com.weberp.app.dto.ProductoDTO;
import com.weberp.app.services.ProductoService;
import com.weberp.app.services.TipoProductoService;
import com.weberp.app.validator.ProductoValidator;

@RequestMapping("/productos")
@Controller
public class ProductoController extends BaseController {

	private ProductoService productoService;

	private TipoProductoService tipoProductoService;

	private ProductoValidator productoValidator;

	@Autowired
	public ProductoController(ProductoService productoService, TipoProductoService tipoProductoService,
							  ProductoValidator productoValidator, UsuarioService usuarioService) {
		super(usuarioService);
		this.productoService = productoService;
		this.tipoProductoService = tipoProductoService;
		this.productoValidator = productoValidator;
	}

	@RequestMapping("")
	public String list(Model model) {
	setUsuario(model);
		model.addAttribute("listaProductos", productoService.listaProductos());
		model.addAttribute("productoDTO", new ProductoDTO());

		return "Mantenimientos/ConsultaProductos";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		setUsuario(model);
		model.addAttribute("producto", new Producto());
		model.addAttribute("tipoProductoList", tipoProductoService.listaTipoProductos());
		return "Mantenimientos/FormularioProducto";
	}

	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute("producto") Producto producto, BindingResult result, Model model) {
		setUsuario(model);
		productoValidator.validate(producto, result);
		model.addAttribute("tipoProductoList", tipoProductoService.listaTipoProductos());
		if (result.hasErrors()) {
			return "Mantenimientos/FormularioProducto";
		}
		productoService.guardar(producto);

		return "redirect:/productos";
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		setUsuario(model);
		model.addAttribute("producto", productoService.getProductoById(id));
		model.addAttribute("tipoProductoList", tipoProductoService.listaTipoProductos());
		model.addAttribute("listaAlmacen", productoService.buscarProductosEnAlmacenes(id));
		return "Mantenimientos/FormularioProducto";
	}

	@RequestMapping(value = "/buscarProductos", method = RequestMethod.POST)
	public String buscarPor(ProductoDTO productoDTO, Model model) {
		setUsuario(model);
		List<Producto> listaProductos = new ArrayList<>();
		try {
			listaProductos = productoService.buscarProducto(productoDTO);
			model.addAttribute("listaProductos", listaProductos);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Mantenimientos/ConsultaProductos";
	}

}
