package com.weberp.app.utils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.mysql.jdbc.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.util.JRLoader;

public class JasperReportUtil {

	@Autowired
	private Environment env;

	private static final String DATASOURCE = "datasource";

	public final static String REPORTE_FACTURACION = "/reportes/facturacion.jasper";

	public final static String REPORTE_COTIZACION = "/reportes/cotizacion.jasper";

	public final static String REPORTE_ORDEN_COMPRA = "/reportes/ordencompra.jasper";


	public final static String LOGO = "/pinponLogo.png";

		public  Connection getConnection () throws SQLException, IOException{
			Properties prop = new Properties();
			String propFileName = "application.properties";

			InputStream	inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			String url = prop.getProperty("spring.datasource.url");
			String username = prop.getProperty("spring.datasource.username");
			String password = prop.getProperty("spring.datasource.password");
			Connection conn = (Connection) DriverManager.getConnection(url, username, password);

			return conn;
		}

	public void imprimirFactura(HttpServletResponse response, Long id, String numeroDocumento, ServletContext context)
			throws JRException, IOException, SQLException {

		InputStream jasperStream = this.getClass().getResourceAsStream(REPORTE_FACTURACION);


		String logoPath = context.getRealPath("logo");
		InputStream imgInputStream = new FileInputStream(logoPath+""+LOGO);
		BufferedImage imageLogo = null;
		imageLogo = ImageIO.read(imgInputStream);

		Map<String, Object> params = new HashMap<>();
		params.put("pId", id);
		params.put("pLogo", imageLogo);


		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, getConnection());
		jasperPrint.setOrientation(OrientationEnum.PORTRAIT);
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=" + numeroDocumento + ".pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	}

	public void imprimirCotizacion(HttpServletResponse response, Long id, String numeroDocumento,ServletContext context)
			throws JRException, IOException, SQLException {

		InputStream jasperStream = this.getClass().getResourceAsStream(REPORTE_COTIZACION);
		

		String logoPath = context.getRealPath("logo");

		InputStream imgInputStream = new FileInputStream(logoPath+""+LOGO);
		BufferedImage imageLogo = null;
		imageLogo = ImageIO.read(imgInputStream);

		Map<String, Object> params = new HashMap<>();
		params.put("pId", id);
		params.put("pLogo", imageLogo);


		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, getConnection());
		jasperPrint.setOrientation(OrientationEnum.PORTRAIT);
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=" + numeroDocumento + ".pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	}

	public void imprimirDocumento(HttpServletResponse response, Long id, String numeroDocumento,ServletContext context, String jasperReportName)
			throws JRException, IOException, SQLException {

		InputStream jasperStream = this.getClass().getResourceAsStream(jasperReportName);


		String logoPath = context.getRealPath("logo");

		InputStream imgInputStream = new FileInputStream(logoPath+""+LOGO);
		BufferedImage imageLogo = null;
		imageLogo = ImageIO.read(imgInputStream);

		Map<String, Object> params = new HashMap<>();
		params.put("pId", id);
		params.put("pLogo", imageLogo);


		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, getConnection());
		jasperPrint.setOrientation(OrientationEnum.PORTRAIT);
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=" + numeroDocumento + ".pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	}


}
