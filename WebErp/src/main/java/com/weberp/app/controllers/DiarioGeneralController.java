package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weberp.app.services.DiarioGeneralService;

@RequestMapping(value="/diariogeneral")
@Controller
public class DiarioGeneralController extends BaseController {

	private DiarioGeneralService diarioGeneralService;

	public DiarioGeneralController(DiarioGeneralService diarioGeneralService, UsuarioService usuarioService) {
		super(usuarioService);
		this.diarioGeneralService = diarioGeneralService;

	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);
		model.addAttribute("listaDiarioGeneral", diarioGeneralService.listaDiarioGeneral());
		
		return "Contabilidad/ConsultaDiarioGeneral";
	}
}
