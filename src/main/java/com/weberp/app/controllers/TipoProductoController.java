package com.weberp.app.controllers;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.services.UsuarioService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weberp.app.domain.TipoProducto;
import com.weberp.app.dto.TipoProductoDTO;
import com.weberp.app.reportes.TipoProductoReporte;
import com.weberp.app.services.TipoProductoService;
import com.weberp.app.validator.TipoProductoValidator;

import net.sf.jxls.transformer.XLSTransformer;

@RequestMapping("/tipoproductos")
@Controller
public class TipoProductoController extends BaseController {

	private TipoProductoService tipoProductoService;

	private TipoProductoValidator tipoProductoValidator;

	@Autowired
	private ServletContext context;

	@Autowired
	public TipoProductoController(TipoProductoService tipoProductoService,
								  TipoProductoValidator tipoProductoValidator, UsuarioService usuarioService) {
		super(usuarioService);
		this.tipoProductoService = tipoProductoService;
		this.tipoProductoValidator = tipoProductoValidator;
	}

	@RequestMapping("")
	public String list(Model model) {

		setUsuario(model);
		return "Mantenimientos/ConsultaTipoProductos";

	}

	@RequestMapping("crear")
	public String crear(Model model) {

		setUsuario(model);
		return "Mantenimientos/FormularioTipoProducto";
	}



	@RequestMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {

		model.addAttribute("id",id);
		setUsuario(model);
		return "Mantenimientos/FormularioTipoProducto";
	}

	@RequestMapping(value = "generaExcel", method = RequestMethod.GET)
	@ResponseBody
	public String generarExcel(HttpServletRequest request, HttpServletResponse response) {
		List<TipoProducto> tipoProducto = tipoProductoService.listaTipoProductoPorEmpresa();

		try {
			
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			String fechaActual = dateFormat.format(date);

			ServletOutputStream os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=\"TipoProductoReporte"+fechaActual+".xls\"");

			TipoProductoReporte tipoProductoReporte;
			List<TipoProductoReporte> tipoProductoCollection = new ArrayList<TipoProductoReporte>();
			for (int i = 0; i < tipoProducto.size(); i++) {
				tipoProductoReporte = new TipoProductoReporte();
				tipoProductoReporte.setId(tipoProducto.get(i).getId());
				tipoProductoReporte.setNombre(tipoProducto.get(i).getNombre());
				tipoProductoReporte.setEstado(tipoProducto.get(i).getEstado()==1 ? "Activo" : "Inactivo");

				tipoProductoCollection.add(tipoProductoReporte);
			}
			Map<String, List<TipoProductoReporte>> beans = new HashMap<String, List<TipoProductoReporte>>();
			beans.put("tipoProducto", tipoProductoCollection);
			XLSTransformer transformer = new XLSTransformer();
			String reportLocation = context.getRealPath("xls");
			Workbook workbook = (Workbook) transformer
					.transformXLS(new FileInputStream(reportLocation + "/tipo_producto_template.xls"), beans);
			workbook.write(os);
			os.flush();

			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
