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

import com.weberp.app.domain.Factura;
import com.weberp.app.dto.FacturaDTO;
import com.weberp.app.utils.JasperReportUtil;

@Controller
@RequestMapping(value = "/cuentascobrar")
public class CuentasCobrarController extends BaseController {

	JasperReportUtil jasperReportUtil;
	private FacturaService facturaService;

	private ProductoService productoService;

	private ClienteService clienteService;

	private TipoDocumentoService tipoDocumentoService;

	private EstatusService estatusService;

	@Autowired
	public CuentasCobrarController(FacturaService facturaService,
								   ProductoService productoService,
								   TipoDocumentoService tipoDocumentoService,
								   EstatusService estatusService,
								   ClienteService clienteService,
								   UsuarioService usuarioService) {
		super(usuarioService);
		this.facturaService = facturaService;
		this.productoService = productoService;
		this.tipoDocumentoService = tipoDocumentoService;
		this.estatusService= estatusService;
		this.clienteService=clienteService;

	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);
		List<Factura> listaFacturas = facturaService.facturasPorCobrar();
		model.addAttribute("listaFacturas", listaFacturas);
		model.addAttribute("facturaDTO", new FacturaDTO());
		return "CuentasCobrar/ConsultaCuentasCobrar";
	}

	@RequestMapping("view/{id}")
	public String view(@PathVariable Long id, Model model) {
		setUsuario(model);
		Factura factura = facturaService.getFacturaById(id);
		model.addAttribute("factura", factura);
		model.addAttribute("clienteList", clienteService.listaClientes());
		model.addAttribute("tipoDocumentoList", tipoDocumentoService.listaTiposDocumentosSalida());
		model.addAttribute("noDocumento", "Factura: " + factura.getNumeroDocumento());
		model.addAttribute("productoList", productoService.listaProductos());
		model.addAttribute("estatusList", estatusService.findByEstatus(factura.getEstatus()));

		return "CuentasCobrar/ViewCuentasCobrar";
	}

	@RequestMapping(value = "cambiarEstatusFactura", method = RequestMethod.POST)
	public String aprobar(Factura factura,Model model) {
		setUsuario(model);
		facturaService.cambiarEstatusFactura(factura);

		return "redirect:/cuentascobrar/view/" + factura.getId();
	}
}
