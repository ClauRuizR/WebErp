package com.weberp.app.utils;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
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

	String url = "";// databaseConfig.getUrl();
	String username = "";// databaseConfig.getUsername();
	String password = "";// databaseConfig.getPassword();

	InputStream inputStream;

	public void imprimirFactura(HttpServletResponse response, Long id, String numeroDocumento)
			throws JRException, IOException, SQLException {

		InputStream jasperStream = this.getClass().getResourceAsStream(REPORTE_FACTURACION);

		Properties prop = new Properties();
		String propFileName = "application.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		url = prop.getProperty("spring.datasource.url");
		username = prop.getProperty("spring.datasource.username");
		password = prop.getProperty("spring.datasource.password");
		Connection conn = (Connection) DriverManager.getConnection(url, username, password);
		InputStream imgInputStream = this.getClass().getResourceAsStream("/logo/pinponLogo.png");
		BufferedImage imageLogo = null;
		imageLogo = ImageIO.read(imgInputStream);

		Map<String, Object> params = new HashMap<>();
		params.put("pId", id);
		params.put("pLogo", imageLogo);
		params.put("pEmpresa", "Pin Pon S.R.L");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
		jasperPrint.setOrientation(OrientationEnum.PORTRAIT);
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=" + numeroDocumento + ".pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	}

	public void imprimirCotizacion(HttpServletResponse response, Long id, String numeroDocumento)
			throws JRException, IOException, SQLException {

		InputStream jasperStream = this.getClass().getResourceAsStream(REPORTE_COTIZACION);
		
		Properties prop = new Properties();
		String propFileName = "application.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		url = prop.getProperty("spring.datasource.url");
		username = prop.getProperty("spring.datasource.username");
		password = prop.getProperty("spring.datasource.password");
		Connection conn = (Connection) DriverManager.getConnection(url, username, password);
		InputStream imgInputStream = this.getClass().getResourceAsStream("/logo/pinponLogo.png");
		BufferedImage imageLogo = null;
		imageLogo = ImageIO.read(imgInputStream);

		Map<String, Object> params = new HashMap<>();
		params.put("pId", id);
		params.put("pLogo", imageLogo);
		params.put("pEmpresa", "Pin Pon S.R.L");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
		jasperPrint.setOrientation(OrientationEnum.PORTRAIT);
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=" + numeroDocumento + ".pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	}

}
