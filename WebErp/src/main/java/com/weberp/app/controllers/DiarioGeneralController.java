package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.domain.DiarioGeneral;
import com.weberp.app.reportes.DiarioGeneralReporte;
import com.weberp.app.services.UsuarioService;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weberp.app.services.DiarioGeneralService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping(value="/diariogeneral")
@Controller
public class DiarioGeneralController extends BaseController {

	private DiarioGeneralService diarioGeneralService;

	@Autowired
	private ServletContext context;

	@Autowired
	public DiarioGeneralController(DiarioGeneralService diarioGeneralService, UsuarioService usuarioService) {
		super(usuarioService);
		this.diarioGeneralService = diarioGeneralService;

	}

	@RequestMapping("")
	public String list(Model model) {
		setUsuario(model);

		
		return "Contabilidad/ConsultaDiarioGeneral";
	}

	@RequestMapping(value = "generaExcel", method = RequestMethod.GET)
	@ResponseBody
	public String generarExcel(HttpServletRequest request, HttpServletResponse response) {
		List<DiarioGeneral> diarioGeneral = diarioGeneralService.listaDiarioGeneral();

		try {


			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			String fechaActual = dateFormat.format(date);

			ServletOutputStream os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=\"DiarioGeneral"+fechaActual+".xls\"");

			DiarioGeneralReporte diarioGeneralReporte;
			List<DiarioGeneralReporte> diarioGeneralReporteList = new ArrayList<DiarioGeneralReporte>();
			for (int i = 0; i < diarioGeneral.size(); i++) {
				diarioGeneralReporte = new DiarioGeneralReporte();
				diarioGeneralReporte.setId(diarioGeneral.get(i).getId());
				diarioGeneralReporte.setNumeroDocumento(diarioGeneral.get(i).getNumeroDocumento());

				String fechaTransaccion = dateFormat.format(diarioGeneral.get(i).getFecha());

				diarioGeneralReporte.setFecha(fechaTransaccion);
				diarioGeneralReporte.setDescripcion(diarioGeneral.get(i).getDescripcion());
				diarioGeneralReporte.setDebito(diarioGeneral.get(i).getDebito());
				diarioGeneralReporte.setCredito(diarioGeneral.get(i).getCredito());
				diarioGeneralReporte.setEstado(diarioGeneral.get(i).getEstado());
				diarioGeneralReporte.setCreadoPor(diarioGeneral.get(i).getCreadoPor());
				diarioGeneralReporteList.add(diarioGeneralReporte);
			}
			Map<String, List<DiarioGeneralReporte>> beans = new HashMap<String, List<DiarioGeneralReporte>>();
			beans.put("diarioGeneral", diarioGeneralReporteList);
			XLSTransformer transformer = new XLSTransformer();
			String reportLocation = context.getRealPath("xls");
			Workbook workbook = (Workbook) transformer
					.transformXLS(new FileInputStream(reportLocation + "/diario_general_template.xls"), beans);
			workbook.write(os);
			os.flush();

			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
