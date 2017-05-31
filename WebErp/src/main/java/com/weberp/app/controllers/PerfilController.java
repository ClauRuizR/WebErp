package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weberp.app.domain.Usuario;
import com.weberp.app.services.UserDetailsImpl;
import com.weberp.app.services.UsuarioService;

@Controller
@RequestMapping(value = "/perfil")
public class PerfilController  extends BaseController{

	private UsuarioService usuarioService;

	@Autowired
	public PerfilController(UsuarioService usuarioService) {
		super(usuarioService);
		this.usuarioService = usuarioService;

	}

	@RequestMapping("")
	public String list(Model model) {
		String username = getUserLogued();

		Usuario usuario = usuarioService.findByUsuario(username);
		
		model.addAttribute("usuario",usuario);

		return "Perfil/PerfilUsuario";
	}

	public String getUserLogued() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		return ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
	}
}
