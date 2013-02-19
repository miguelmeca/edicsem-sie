package com.edicsem.pe.sie.client.report.serviceImpl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.edicsem.pe.sie.beans.ReporteParams;
import com.edicsem.pe.sie.client.report.service.ReporteExecutionService;
import com.edicsem.pe.sie.client.servlet.ReportExporter;
import com.edicsem.pe.sie.util.constants.Constants;
@Stateless
public class ReporteExecutionServiceImpl extends HttpServlet  implements ReporteExecutionService  {
	
	protected final Log log = LogFactory.getLog(ReporteExecutionServiceImpl.class);
	private DataSource ds = null;
	private Connection conn = null;
	Context initContext;
	
	public void executeReporte(ReporteParams reportParams, HttpServletResponse response) {
		log.info("Entering 'executeReporte' method");
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		conn = null;
		String path = Constants.RUTA_REPORTE_CLIENTE+ (String) reportParams.getQueryParams().get("titulo") + ".pdf";
		try {
			initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:/edicsemJPADatasource");
			conn = ds.getConnection();
			response.setHeader("Content-Disposition", "attachment; filename="+ (String) reportParams.getQueryParams().get("titulo") + ".pdf");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("application/pdf");
			
			FileInputStream fis = new FileInputStream(ctx.getRealPath(reportParams.getJasperFileName()));
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
			JasperReport reporte = (JasperReport) JRLoader.loadObject(bufferedInputStream);
			JasperPrint jasper = JasperFillManager.fillReport(reporte,reportParams.getQueryParams(), conn);
			
			ReportExporter.exportReportPDF(jasper, path, response);
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			log.error("El DataSource no pudo establecer la conexion con la base de datos");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("No se encontro el archivo jasper: "+ reportParams.getJasperFileName());
			e.printStackTrace();
		}catch (Exception e) {
			log.error("Execpcion No Capturada: "+ reportParams.getJasperFileName());
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					log.info("cerrando la conexion");
					conn.close();
					ds = null;
				} catch (Exception e) {

				}
			}
		}
	}
}