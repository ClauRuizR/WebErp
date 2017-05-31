package com.weberp.app.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.Usuario;
import com.weberp.app.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	private Logger log = Logger.getLogger(UsuarioServiceImpl.class);


	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario findByUsuario(String usuario) {
		return usuarioRepository.findByUsuario(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String usr) throws UsernameNotFoundException {
		Usuario usuario = findByUsuario(usr);

		if (usuario == null) {
			throw new UsernameNotFoundException(usr);
		}

		return new UserDetailsImpl(usuario);
	}

	@Override
	public void cambiarClave(String usuario, String clave) {

		try {
			Usuario user = usuarioRepository.findByUsuario(usuario);
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(clave);
			user.setClave(hashedPassword);
			usuarioRepository.save(user);

		}catch (Exception ex){
			log.info("metodo: cambiarClave - Error: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

}
