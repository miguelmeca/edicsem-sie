package com.edicsem.pe.sie.client.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.util.constants.Constants;


@WebServlet("/ReportingCliente")
public class ReportingCliente extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private	EmpleadoSie objUsuario = null;
	public static Log log = LogFactory.getLog(ReportingCliente.class);
	@EJB
	private Reporting re;
	 
	public ReportingCliente() {
		log.info("ReportingCliente()**");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		log.info("doGet()");
	}

	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("doPost()");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"Reporte_Cliente.pdf\";");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/pdf");
		
		HttpSession sessionhttp = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if (sessionhttp.getAttribute(Constants.USER_KEY) == null) {
			objUsuario = new EmpleadoSie();
		} else {
			objUsuario = (EmpleadoSie) sessionhttp
					.getAttribute(Constants.USER_KEY);
		}
		
		log.info("MI USUARIO ES ***********************:D ***********");
		log.info("USUARIO ==>" + objUsuario.getUsuario());
		// String nombrePC=InetAddress.getLocalHost().getCanonicalHostName();
		ServletOutputStream out = response.getOutputStream();
		InetAddress ip = InetAddress.getLocalHost();
		InetAddress addr = InetAddress.getByName(ip.getHostAddress());
		try {
			   ServletContext context2 = getServletContext();
			  String reportLocation = context2.getRealPath("Reporte");

			FileInputStream fis = new FileInputStream(reportLocation+"/report4.jasper");
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
			JasperReport reporte = (JasperReport) JRLoader.loadObject(bufferedInputStream);
			Map parametros = new HashMap();
			
			parametros.put("nombreUsuario", objUsuario.getNombreemp() + " " + objUsuario.getApepatemp()
					+ " " + objUsuario.getApematemp());
			parametros.put("nombrePC", addr.getHostName());
			log.info(" pccc  "+ addr.getHostName());
			
			JasperPrint jasper = JasperFillManager.fillReport(reporte,
					parametros,re.getMap() );
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasper);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.exportReport();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}