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


		return "OrdenCompra/ConsultaOrdenCompra";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		setUsuario(model);


		return "OrdenCompra/FormularioOrdenCompra";
	}



	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		setUsuario(model);
		model.addAttribute("id",id);


		return "OrdenCompra/FormularioOrdenCompra";
	}


	@RequestMapping("view/{id}")
	public String view(@PathVariable Long id, Model model) {
		setUsuario(model);
	model.addAttribute("id",id);

		return "OrdenCompra/ViewOrdenCompra";
	}



}
