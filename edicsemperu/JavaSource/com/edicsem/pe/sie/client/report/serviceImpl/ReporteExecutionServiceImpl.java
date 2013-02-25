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
import javax.servlet.ServletOutputStream;
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
public class ReporteExecutionServiceImpl implements ReporteExecutionService  {
	
	protected final Log log = LogFactory.getLog(ReporteExecutionServiceImpl.class);
	private DataSource ds = null;
	private Connection conn = null;
	Context initContext;
	
	public void executeReporte(ReporteParams reportParams, HttpServletResponse response, String ContentType) {
		log.info("Entering 'executeReporte' ");
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		conn = null;
		
		String titulo=  (String) reportParams.getQueryParams().get(Constants.REPORTE_TITULO);
		try {
			initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:/edicsemJPADatasource");
			conn = ds.getConnection();
			
			FileInputStream fis = new FileInputStream(ctx.getRealPath(reportParams.getJasperFileName()));
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
			JasperReport reporte = (JasperReport) JRLoader.loadObject(bufferedInputStream);
			JasperPrint jasper = JasperFillManager.fillReport(reporte,reportParams.getQueryParams(), conn);
			
			titulo+="."+ContentType;
			log.info("Titulo-->  "+titulo);
			response.setHeader("Content-Disposition", "attachment; filename=\""+ titulo+"\";");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			ServletOutputStream out =	response.getOutputStream();
			
			if(ContentType.equals("pdf")){
				response.setContentType("application/pdf");
				ReportExporter.exportReportPDF(jasper, out);
			}else if(ContentType.equals("xls")){
				response.setContentType("application/xls");
			//	ReportExporter.exportReportXls(jasper, path);
			}
			
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
					initContext.close();
				} catch (Exception e) {
					log.info(e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
}
