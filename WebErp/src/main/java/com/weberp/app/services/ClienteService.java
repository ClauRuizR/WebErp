package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.Cliente;
import com.weberp.app.dto.ClienteDTO;



public interface ClienteService {
	
	List<Cliente>listaClientes();

	Cliente guardar(Cliente cliente);
	
	Cliente getClienteById(Long id);
	
	void borrar(Long id);
	
	boolean existeCliente(Cliente cliente);
	
	List<Cliente> buscarCliente(ClienteDTO clienteDTO);
	//public List<Paciente> buscarPaciente(PacienteDTO pacienteDTO);
	
}
