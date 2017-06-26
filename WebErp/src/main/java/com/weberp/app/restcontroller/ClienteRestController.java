package com.weberp.app.restcontroller;


import com.weberp.app.dto.ClienteDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.ClienteService;
import com.weberp.app.validator.ClienteValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.weberp.app.domain.Cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static scala.Console.out;

@RestController
@RequestMapping("/rest/clientes")
public class ClienteRestController extends ConfigMapper{


	@Autowired
	private ClienteService clienteService;



	@Autowired
	private ClienteValidator clienteValidator;





	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ClienteDTO> getCliente() {
		//...
		List<Cliente> clientesList = clienteService.listaClientes();
		return clientesList.stream()
				.map(cliente -> convertClienteToDto(cliente))
				.collect(Collectors.toList());
	}


	@RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
	public Page<ClienteDTO> findPaginated(
			@RequestParam("page") int page, @RequestParam("size") int size) {

		Page<ClienteDTO> resultPage = clienteService.findPaginated(page,size);
		if (page > resultPage.getTotalPages()) {
			throw new IllegalArgumentException("No existe facturas registradas.");
		}

		return resultPage;
	}
	@RequestMapping(value="/filtrar",params = { "busqueda","page", "size" },method = RequestMethod.GET)
	public Page<ClienteDTO> findClienteAndPaginated( @RequestParam("busqueda") String busqueda,
														  @RequestParam("page") int page, @RequestParam("size") int size) {


		Page<ClienteDTO> resultPage = clienteService.findClienteAndPaginated(busqueda,new PageRequest(page,size, Sort.Direction.ASC,"id"));
		if (page > resultPage.getTotalPages()) {
			throw new IllegalArgumentException("No existe productos registrados.");
		}

		return resultPage;
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ClienteDTO getPost(@PathVariable("id") Long id) {
		return convertClienteToDto(clienteService.getClienteById(id));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void save(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult result, HttpServletResponse httpServletResponse) {
		Cliente cliente = null;
		try {
			cliente = convertClienteDtoToEntity(clienteDTO);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		StringBuilder stringBuilderError = new StringBuilder();


		clienteValidator.validate(cliente, result);
		if (result.hasErrors()) {

			for (ObjectError objectError : result.getAllErrors()) {
				stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
			}
            throw new IllegalArgumentException(stringBuilderError.toString());

		}
try{

		clienteService.guardar(cliente);
	}catch (Exception ex)
	{
		throw new IllegalArgumentException(ex.getMessage());
	}

	}



}
