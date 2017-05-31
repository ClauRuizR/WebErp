package com.weberp.app.services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.weberp.app.domain.Usuario;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -1723437571559797459L;
	private Usuario usuario;

	public UserDetailsImpl(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<>();
		usuario.getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRol())));
		return authorities;
	}

	@Override
	public String getPassword() {
		return usuario.getClave();
	}

	@Override
	public String getUsername() {
		return usuario.getUsuario();
	}
	


	public String getNombre() {
		return usuario.getNombre();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return usuario.getEstado().equals(1);
	}

}
