package com.weberp.app.services;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.Almacen;
import com.weberp.app.domain.Empresa;
import com.weberp.app.domain.Rol;
import com.weberp.app.dto.UsuarioDTO;
import com.weberp.app.dto.config.ConfigMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.Usuario;
import com.weberp.app.repositories.UsuarioRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.net.PasswordAuthentication;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UsuarioServiceImpl extends ConfigMapper implements UsuarioService, UserDetailsService {

	private Logger log = Logger.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private EntityManager entityManager;

	@Autowired
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

	@Override
	public Almacen findAlmacenAsignado(String usuario) {
		Almacen almacen;
		try {
			 almacen = entityManager.createNamedQuery("Almacen.findAlmacenByEmpresa", Almacen.class).setParameter(1, usuario).getSingleResult();
		}catch (Exception ex)
		{
			throw new IllegalArgumentException("Error en buscar almacen asignado al usuario "+ usuario);
		}

		return almacen;
	}

	@Override
	public Usuario guardar(Usuario usuario) {

		Empresa empresa = UsuarioUtil.getCurrentUserEmpresa().getEmpresa();

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(usuario.getClave());

		usuario.setClave(hashedPassword);
		usuario.setEmpresa(empresa);
		for(int i= 0; i < usuario.getUsuarioRol().size();i++){
			usuario.getUsuarioRol().get(i).setUsuario(usuario);
		}

		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> listaUsuarios() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Usuario getUsuarioById(Long id) {
		return usuarioRepository.findOne(id);
	}

	@Override
	public UsuarioDTO validaUsuario(String usuario, String clave) {

		UsuarioDTO usuarioDTO=  convertUsuarioToDto(usuarioRepository.findByUsuario(usuario));

		if (BCrypt.checkpw(clave, usuarioDTO.getClave()))
			return usuarioDTO;

		return null;

	}

	@Override
	public Page<UsuarioDTO> findPaginated(int page, int size) {
		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

		Page<Usuario> usuarioPage=  (usuarioRepository.findByEmpresa_Id(empresaId,new PageRequest(page,size)));


		final Page<UsuarioDTO> contactDtoPage = usuarioPage.map(this::convertUsuarioToDto);
		return contactDtoPage;
	}

	@Override
	public Page<UsuarioDTO> findUsuarioAndPaginated(String usuario, Pageable pageRequest) {
		Page<Usuario> usuarioPage=  usuarioRepository.findByUsuario(usuario,pageRequest);

		final Page<UsuarioDTO> contactDtoPage = usuarioPage.map(this::convertUsuarioToDto);
		return contactDtoPage;
	}

}
