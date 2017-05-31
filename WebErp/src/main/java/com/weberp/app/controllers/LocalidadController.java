package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.domain.Localidad;
import com.weberp.app.domain.TipoProducto;
import com.weberp.app.dto.LocalidadDTO;
import com.weberp.app.dto.TipoProductoDTO;
import com.weberp.app.reportes.TipoProductoReporte;
import com.weberp.app.services.LocalidadService;
import com.weberp.app.services.TipoProductoService;
import com.weberp.app.services.UsuarioService;
import com.weberp.app.validator.LocalidadValidator;
import com.weberp.app.validator.TipoProductoValidator;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/localidad")
@Controller
public class LocalidadController extends BaseController {

	private LocalidadService localidadService;

	private LocalidadValidator localidadValidator;

	@Autowired
	private ServletContext context;

	@Autowired
	public LocalidadController(LocalidadService localidadService, LocalidadValidator localidadValidator, UsuarioService usuarioService) {
		super(usuarioService);
		this.localidadService = localidadService;
		this.localidadValidator = localidadValidator;
	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);
		model.addAttribute("listaLocalidades", localidadService.listaLocalidades());
		model.addAttribute("localidadDTO", new LocalidadDTO());
		return "Mantenimientos/ConsultaLocalidad";
	}

	@RequestMapping("crear")
	public String crear(Model model) {
		setUsuario(model);
		model.addAttribute("localidad", new Localidad());

		return "Mantenimientos/FormularioLocalidad";
	}

	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute("localidad") Localidad localidad, BindingResult result,
			Model model) {
		setUsuario(model);
		localidadValidator.validate(localidad, result);
		if (result.hasErrors()) {
			System.err.println(result.getFieldError());
			return "Mantenimientos/FormularioLocalidad";
		}
		localidadService.guardar(localidad);

		return "redirect:/localidad";
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		setUsuario(model);
		model.addAttribute("localidad", localidadService.getLocalidadById(id));

		return "Mantenimientos/FormularioLocalidad";
	}




}
