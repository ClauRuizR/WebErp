package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.weberp.app.utils.JasperReportUtil;

@Controller
@RequestMapping(value = "/cuentascobrar")
public class CuentasCobrarController extends BaseController {

	JasperReportUtil jasperReportUtil;
	@Autowired
	public CuentasCobrarController(FacturaService facturaService,
								   ProductoService productoService,
								   TipoDocumentoService tipoDocumentoService,
								   EstatusService estatusService,
								   ClienteService clienteService,
								   UsuarioService usuarioService) {
		super(usuarioService);

	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);

		return "CuentasCobrar/ConsultaCuentasCobrar";
	}

	@RequestMapping("view/{id}")
	public String view(@PathVariable Long id, Model model) {
		setUsuario(model);
		model.addAttribute("id",id);

		return "CuentasCobrar/ViewCuentasCobrar";
	}


}
