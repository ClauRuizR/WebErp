package com.weberp.app.restcontroller;

import com.weberp.app.dto.TipoProductoDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.TipoProductoService;
import com.weberp.app.validator.TipoProductoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.weberp.app.common.view.BaseRestController;
import com.weberp.app.domain.TipoProducto;
import com.weberp.app.repositories.TipoProductoRepository;
import sun.security.krb5.Config;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/tipoProducto")
public class TipoProductoRestController extends ConfigMapper
{


	@Autowired
	private TipoProductoService tipoProductoService;

	@Autowired
	private TipoProductoValidator tipoProductoValidator;



	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TipoProductoDTO> getCliente() {
		//...
		List<TipoProducto> tipoProductoList = tipoProductoService.listaTipoProductoPorEmpresa();
		return tipoProductoList.stream()
				.map(tipoProducto -> convertToDtoTipoProducto(tipoProducto))
				.collect(Collectors.toList());
	}



	@RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
	public Page<TipoProductoDTO> findPaginated(
			@RequestParam("page") int page, @RequestParam("size") int size) {

		Page<TipoProductoDTO> resultPage = tipoProductoService.listaTipoProductoPorEmpresa(new PageRequest(page,size, Sort.Direction.ASC,"id"));
		if (page > resultPage.getTotalPages()) {
			throw new IllegalArgumentException("No existe productos registrados.");
		}

		return resultPage;
	}


	@RequestMapping(value="/filtrar",params = { "nombre","page", "size" },method = RequestMethod.GET)
	public Page<TipoProducto> findTipoProductoAndPaginated( @RequestParam("nombre") String nombre,
			@RequestParam("page") int page, @RequestParam("size") int size) {


		Page<TipoProducto> resultPage = tipoProductoService.findTipoProductoAndPaginated(nombre,new PageRequest(page,size, Sort.Direction.ASC,"id"));
		if (page > resultPage.getTotalPages()) {
			throw new IllegalArgumentException("No existe productos registrados.");
		}

		return resultPage;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public TipoProductoDTO getPost(@PathVariable("id") Long id) {
		return convertToDtoTipoProducto(tipoProductoService.getTipoProductoById(id));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void save(@Valid @RequestBody TipoProductoDTO tipoProductoDTO, BindingResult result, HttpServletResponse httpServletResponse) {
		TipoProducto tipoProducto = null;
		try {
			tipoProducto = convertTipoProductoDtoToEntity(tipoProductoDTO);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		StringBuilder stringBuilderError = new StringBuilder();


		tipoProductoValidator.validate(tipoProducto, result);
		if (result.hasErrors()) {

			for (ObjectError objectError : result.getAllErrors()) {
				stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
			}
			throw new IllegalArgumentException(stringBuilderError.toString());

		}


		tipoProductoService.guardar(tipoProducto);

	}



}
