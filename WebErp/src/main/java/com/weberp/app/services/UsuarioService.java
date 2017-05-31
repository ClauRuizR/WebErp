package com.weberp.app.services;

import com.weberp.app.domain.Usuario;

public interface UsuarioService {
	public Usuario findByUsuario(String usuario);
	
	void cambiarClave(String usuario, String clave);
	
	
}
