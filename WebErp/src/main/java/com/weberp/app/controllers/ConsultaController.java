package com.weberp.app.controllers;

import com.weberp.app.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weberp.app.common.view.BaseController;

@Controller
public class ConsultaController  extends BaseController{


	public ConsultaController(UsuarioService usuarioService) {
		super(usuarioService);
	}

	@RequestMapping(value = "/consulta/home",method=RequestMethod.GET)
	public String home(Model model){
		String message = "Hola Mundo!!";
		model.addAttribute("message",message);
		
		
		
		return "Consulta/home";
	}
	
	@RequestMapping(value = "/consulta/add",method=RequestMethod.GET)
	public String add(Model model){
		String message = "Hola Mundo!!";
		model.addAttribute("message",message);
		
		
		
		return "Consulta/add";
	}
}
