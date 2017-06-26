package com.weberp.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.weberp.app.domain.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	public Usuario findByUsuario(String usuario);


	Page<Usuario> findByEmpresa_Id(Long empresaId, Pageable pageRequest);

	Page<Usuario> findByUsuario(String usuario, Pageable pageCriteira);

}
