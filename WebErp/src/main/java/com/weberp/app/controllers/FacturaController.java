package com.weberp.app.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.weberp.app.domain.DetalleFactura;
import com.weberp.app.domain.Factura;
import com.weberp.app.domain.Producto;
import com.weberp.app.dto.FacturaDTO;
import com.weberp.app.enums.TipoDocumentoEnums;
import com.weberp.app.utils.JasperReportUtil;
import com.weberp.app.validator.FacturaValidator;

import net.sf.jasperreports.engine.JRException;

/**
 * Created by claudioruiz on 7/27/16.
 */

@Controller
@RequestMapping(value = "/facturas")
public class FacturaController extends BaseController {

	JasperReportUtil jasperReportUtil;
	private FacturaService facturaService;

	private FacturaValidator facturaValidator;

	private ComprobanteFiscalService comprobanteFiscalService;

	private ProductoService productoService;

	private ClienteService clienteService;

	private TipoDocumentoService tipoDocumentoService;

	private EstatusService estatusService;

	@Autowired
	public FacturaController(FacturaService facturaService, ClienteService clienteService,
							 ComprobanteFiscalService comprobanteFiscalService, ProductoService productoService,
							 TipoDocumentoService tipoDocumentoService, EstatusService estatusService,
							 FacturaValidator facturaValidator, UsuarioService usuarioService) {
		super(usuarioService);
		this.facturaService = facturaService;
		this.clienteService = clienteService;
		this.comprobanteFiscalService = comprobanteFiscalService;
		this.tipoDocumentoService = tipoDocumentoService;
		this.productoService = productoService;
		this.estatusService = estatusService;
		this.facturaValidator = facturaValidator;
	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);
		List<Factura> listaFacturas = facturaService.listaFacturas();
		model.addAttribute("listaFacturas", listaFacturas);
		model.addAttribute("facturaDTO", new FacturaDTO());
		model.addAttribute("clienteList", clienteService.listaClientes());
		List<Producto> listaProductos = productoService.listaProductos();
		model.addAttribute("productoList", listaProductos);

		return "Factura/ConsultaFactura";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		setUsuario(model);
		model.addAttribute("factura", new Factura());
		model.addAttribute("clienteList", clienteService.listaClientes());
		model.addAttribute("tipoDocumentoList", tipoDocumentoService.listaTiposDocumentosSalida());
		model.addAttribute("productoLista", productoService.listaProductos());
		return "Factura/FormularioFactura";
	}

	@RequestMapping(value = "/agregarDetalleFactura", params = { "agregarDetalleFactura" }, method = RequestMethod.POST)
	public String agregarDetalleFactura(Factura factura, Model model) {
		setUsuario(model);
		factura.getDetalleFactura().add(new DetalleFactura());
		model.addAttribute("clienteList", clienteService.listaClientes());
		model.addAttribute("productoLista", productoService.listaProductos());
		model.addAttribute("tipoDocumentoList", tipoDocumentoService.listaTiposDocumentosSalida());

		return "Factura/FormularioFactura";
	}

	@RequestMapping(value = "/quitarDetalleFactura", params = { "quitarDetalleFactura" }, method = RequestMethod.POST)
	public String quitarDetalleFactura(Factura factura, HttpServletRequest req, Model model) {
		setUsuario(model);

		int index = Integer.valueOf(req.getParameter("quitarDetalleFactura"));
		factura.getDetalleFactura().remove(index);
		model.addAttribute("clienteList", clienteService.listaClientes());
		model.addAttribute("productoLista", productoService.listaProductos());
		model.addAttribute("tipoDocumentoList", tipoDocumentoService.listaTiposDocumentosSalida());
		return "Factura/FormularioFactura";
	}

	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute("factura") Factura factura, BindingResult result, Model model) {
		setUsuario(model);

		model.addAttribute("clienteList", clienteService.listaClientes());
		model.addAttribute("tipoDocumentoList", tipoDocumentoService.listaTiposDocumentosSalida());
		model.addAttribute("productoLista", productoService.listaProductos());
		facturaValidator.validate(factura, result);
		if (result.hasErrors()) {
			System.err.println(result);
			return "Factura/FormularioFactura";
		}

		facturaService.guardar(factura);

		return "redirect:/facturas/editar/" + factura.getId();
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		setUsuario(model);
		model.addAttribute("factura", facturaService.getFacturaById(id));
		model.addAttribute("clienteList", clienteService.listaClientes());
		model.addAttribute("tipoDocumentoList", tipoDocumentoService.listaTiposDocumentosSalida());

		model.addAttribute("productoLista", productoService.listaProductos());

		return "Factura/FormularioFactura";
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

		return "Factura/ViewFactura";
	}

	@RequestMapping(value = "cambiarEstatusFactura", method = RequestMethod.POST)
	public String aprobar(Factura factura,Model model) {

		setUsuario(model);
		facturaService.cambiarEstatusFactura(factura);

		return "redirect:/facturas/view/" + factura.getId();
	}

	@RequestMapping(value = "imprimirDocumento/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void imprimirFactura(@PathVariable Long id, HttpServletResponse response)
			throws JRException, IOException, SQLException {
		Factura factura = facturaService.getFacturaById(id);
		jasperReportUtil = new JasperReportUtil();

		if (null == factura.getTipoDocumento() && factura.getTipoDocumento().getLlaveDocumento().equals(""))
			return;

		if (factura.getTipoDocumento().getLlaveDocumento().equals(TipoDocumentoEnums.FACTURA)) {
			jasperReportUtil.imprimirFactura(response, id, factura.getNumeroDocumento());
		}

		if (factura.getTipoDocumento().getLlaveDocumento().equals(TipoDocumentoEnums.COTIZACION)) {
			jasperReportUtil.imprimirCotizacion(response, id, factura.getNumeroDocumento());
		}

	}

}
