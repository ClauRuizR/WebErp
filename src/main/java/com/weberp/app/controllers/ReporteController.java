package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weberp.app.services.ReporteService;

@Controller
@RequestMapping(value = "/reportes")
public class ReporteController extends BaseController {

	private ReporteService reporteService;
	
	@Autowired
	public ReporteController(ReporteService reporteService, UsuarioService usuarioService){
		super(usuarioService);
		this.reporteService = reporteService;
	}
	
	@RequestMapping("")
	public String list(Model model) {
		model.addAttribute("listaReportes",reporteService.listaReporte());
		setUsuario(model);
		return "Reporte/ConsultaReporte";
	}
}
