package com.weberp.app.controllers;

import java.util.List;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weberp.app.domain.OrdenCompra;
import com.weberp.app.dto.OrdenCompraDTO;

@Controller
@RequestMapping(value = "/cuentaspagar")
public class CuentasPagarController extends BaseController {

	private OrdenCompraService ordenCompraService;

	private ProductoService productoService;

	private ProveedorService proveedorService;

	private EstatusService estatusService;

	@Autowired
	public CuentasPagarController(OrdenCompraService ordenCompraService, ProductoService productoService,
								  ProveedorService proveedorService, EstatusService estatusService, UsuarioService usuarioService) {
		super(usuarioService);
		this.ordenCompraService = ordenCompraService;
		this.productoService = productoService;
		this.proveedorService = proveedorService;
		this.estatusService = estatusService;

	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);
		List<OrdenCompra> listaOrdenCompra = ordenCompraService.ordensPorPagar();
		model.addAttribute("listaOrdenCompras", listaOrdenCompra);
		model.addAttribute("ordenCompraDTO", new OrdenCompraDTO());

		return "CuentasPagar/ConsultaCuentasPagar";
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

		return "CuentasPagar/ViewCuentasPagar";
	}

	@RequestMapping(value = "cambiarEstatusOrdenCompra", method = RequestMethod.POST)
	public String aprobar(OrdenCompra ordenCompra,Model model) {
		setUsuario(model);

		ordenCompraService.cambiarEstatusOrdenCompra(ordenCompra);

		return "redirect:/cuentaspagar/view/" + ordenCompra.getId();
	}
}
