package com.weberp.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.Empresa;
import com.weberp.app.dto.config.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.Cliente;
import com.weberp.app.dto.ClienteDTO;
import com.weberp.app.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl extends ConfigMapper implements ClienteService {

	Cliente cliente;
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> listaClientes() {
		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

		List<Cliente> listaCliente =  (List<Cliente>) clienteRepository.findByEmpresa_Id(empresaId);
	
		return listaCliente;
	}

	@Override
	public Cliente guardar(Cliente cliente) {

		Empresa empresa = usuarioService.findByUsuario(UsuarioUtil.getCurrentUser()).getEmpresa();

		cliente.setEmpresa(empresa);
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente getClienteById(Long id) {
		return clienteRepository.findOne(id);

	}

	@Override
	public void borrar(Long id) {
		clienteRepository.delete(id);
	}

	@Override
	public boolean existeCliente(Cliente cliente) {
		
		 CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
	        Root<Cliente> rootCliente = cq.from(Cliente.class);

	        List<Predicate> predicates = new ArrayList<Predicate>();
	        if(null != cliente.getTipoDni() && !cliente.getTipoDni().equals("") && !cliente.getDni().equals("")){
	        	predicates.add(cb.equal(rootCliente.get("tipoDni"),cliente.getTipoDni()));
	        	predicates.add(cb.equal(rootCliente.get("dni"),cliente.getDni()));
				
	        }
	        cq.select(rootCliente).where(predicates.toArray(new Predicate[]{}));
	        
	        List<Cliente> clienteList = new ArrayList<>();
	        clienteList = entityManager.createQuery(cq).getResultList();
	        
			return clienteList.size() > 0;
		
		
	}

	@Override
	public List<Cliente> buscarCliente(ClienteDTO clienteDTO) {
		 CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
	        Root<Cliente> rootCliente = cq.from(Cliente.class);

	        List<Predicate> predicates = new ArrayList<Predicate>();
	        if(!clienteDTO.getNombre().equals("")){
	        	predicates.add(cb.like(rootCliente.get("nombre"),"%"+clienteDTO.getNombre()+"%"));		
	        }
	        cq.select(rootCliente).where(predicates.toArray(new Predicate[]{}));
	        
	        List<Cliente> clienteList = new ArrayList<>();
	        clienteList = entityManager.createQuery(cq).getResultList();
			return clienteList;
	}

	@Override
	public Page<ClienteDTO> findPaginated(int page, int size) {
		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();
		Page<Cliente> clientePage=  (clienteRepository.findByEmpresa_Id(empresaId,new PageRequest(page,size, Sort.Direction.ASC,"id")));

		final Page<ClienteDTO> contactDtoPage = clientePage.map(this::convertClienteToDto);
		return contactDtoPage;
	}

	@Override
	public Page<ClienteDTO> findClienteAndPaginated(String nombre, Pageable pageRequest) {
		Page<Cliente> facturaPage=  clienteRepository.findByNombre(nombre,pageRequest);

		final Page<ClienteDTO> contactDtoPage = facturaPage.map(this::convertClienteToDto);
		return contactDtoPage;
	}


}
