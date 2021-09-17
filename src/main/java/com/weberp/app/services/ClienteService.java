package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.Cliente;
import com.weberp.app.dto.ClienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClienteService {
	
	List<Cliente>listaClientes();

	Cliente guardar(Cliente cliente);
	
	Cliente getClienteById(Long id);
	
	void borrar(Long id);
	
	boolean existeCliente(Cliente cliente);
	
	List<Cliente> buscarCliente(ClienteDTO clienteDTO);
	Page<ClienteDTO> findPaginated(int page, int size);

	Page<ClienteDTO> findClienteAndPaginated(String nombre, Pageable pageRequest);
	
}
