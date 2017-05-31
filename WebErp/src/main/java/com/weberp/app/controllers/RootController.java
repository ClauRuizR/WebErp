package com.weberp.app.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weberp.app.annotations.Layout;
import com.weberp.app.services.UserDetailsImpl;
import com.weberp.app.services.UsuarioService;

@Controller
public class RootController {

	private Logger log = Logger.getLogger(RootController.class);

	@Autowired
	private UsuarioService usuarioService;

	public RootController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@Layout(value = "layouts/main")
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {

		String message = "Hola Mundo!!";
		model.addAttribute("message", message);


		model.addAttribute("usuario", usuarioService.findByUsuario(getUser().getUsername()));

		return "home";

	}

	public UserDetailsImpl getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		log.info("Usuario " + ((UserDetailsImpl) authentication.getPrincipal()).getUsername());
		return ((UserDetailsImpl) authentication.getPrincipal());
	}

}
