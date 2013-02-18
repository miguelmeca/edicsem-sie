package com.edicsem.pe.sie.client.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fill.JRFillInterruptedException;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.util.constants.Constants;


@WebServlet("/ReportingCliente")
public class ReportingCliente extends HttpServlet {
	
	public static Log log = LogFactory.getLog(ReportingCliente.class);
	private DataSource ds = null;
	private Connection conn = null;
	Context initContext;
	public ReportingCliente() {
		log.info("ReportingCliente()");
		conn = null;
		
		try {
			initContext = new InitialContext();
			ds = (DataSource)initContext.lookup("java:/edicsemJPADatasource");
			conn = ds.getConnection();
			if(ds==null){
				log.info("Es nulo el datasource" );
			}
			if(conn==null){
				log.info("Es nulo la conexion" );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		HttpSession session = request.getSession();
		//Captura de parametros
		Map criteria = new HashMap();
		criteria.put("titulo",Constants.REPORTE_CLIENTE_LIST);
		// response.setHeader("Content-Disposition","attachment; filename=\"Reporte_Cliente.pdf\";");
		log.info("-->> jaasss "+criteria.get("titulo"));
		response.setHeader("Content-Disposition","attachment; filename=" +(String)criteria.get("titulo")+".pdf");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/pdf");
		String path="C:\\Users\\karen\\Reportes\\Report\\"+(String)criteria.get("titulo")+".pdf" ;
		//path = response.getOutputStream().toString();
		log.info(" "+path);
		// ServletOutputStream out = response.getOutputStream();
		
		try {
			ServletContext context2 = getServletContext();
			String reportLocation = context2.getRealPath("Reporte");
			FileInputStream fis = new FileInputStream(reportLocation+"/report6.jasper");
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
			JasperReport reporte = (JasperReport) JRLoader.loadObject(bufferedInputStream);
			JasperPrint jasper = JasperFillManager.fillReport(reporte,criteria, conn);
			ReportExporter.exportReportPDF(jasper, path);
		}
		catch (JRFillInterruptedException ef) {
			log.info("mensaje jrfill  "+ef.getMessage()+" cause "+ef.getCause());
			ef.printStackTrace();
			log.info("No se pudo generar el reporte");
			}
		catch (Exception e) {
			log.info("mensaje Reportingcliente  "+e.getMessage()+" cause "+e.getCause());
			e.printStackTrace();
			}
		finally {
			if (conn != null){
				try {
					log.info("cerrando la conexion");
					conn.close();
					ds=null;
					initContext.close();
				} catch (Exception e) {
					
				}
			}
		}
	}
}