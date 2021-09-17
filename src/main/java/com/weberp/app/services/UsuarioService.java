package com.weberp.app.services;

import com.weberp.app.domain.Almacen;
import com.weberp.app.domain.Usuario;
import com.weberp.app.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {
	public Usuario findByUsuario(String usuario);
	
	void cambiarClave(String usuario, String clave);

	Almacen findAlmacenAsignado(String usuario);

	Usuario guardar(Usuario usuario);

	List<Usuario> listaUsuarios();


	Usuario getUsuarioById(Long id);

	UsuarioDTO validaUsuario(String usuario, String clave);

	Page<UsuarioDTO> findPaginated(int page, int size);

	Page<UsuarioDTO> findUsuarioAndPaginated(String usuario, Pageable pageRequest);


}
