package com.edicsem.pe.sie.client.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.fill.JRFillInterruptedException;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.util.constants.Constants;


@WebServlet("/ReportingCliente")
public class ReportingCliente extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private	EmpleadoSie objUsuario = null;
	public static Log log = LogFactory.getLog(ReportingCliente.class);
	DataSource ds = null;
	@EJB
	private ClienteService objClienteService;

	private List<ClienteSie> lstClientesReporting;
	
	public ReportingCliente() {
		log.info("ReportingCliente() :D  ");
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
		Map criteria = (Map) session.getAttribute("Constante.BLABALBAL");
		
		log.info("doPost()");
		//response.setHeader("Content-Disposition",
			//	"attachment; filename=\""+(String)criteria.get("titulo") +"\";");
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
			objUsuario = (EmpleadoSie) sessionhttp.getAttribute(Constants.USER_KEY);
		}
		 
		log.info("USUARIO ==>" + objUsuario.getUsuario());
		ServletOutputStream out = response.getOutputStream();
		InetAddress ip = InetAddress.getLocalHost();
		InetAddress addr = InetAddress.getByName(ip.getHostAddress());
		try {
			ServletContext context2 = getServletContext();
			String reportLocation = context2.getRealPath("Reporte");

			FileInputStream fis = new FileInputStream(reportLocation+"/report2.jasper");
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
			JasperReport reporte = (JasperReport) JRLoader.loadObject(bufferedInputStream);
			//Map parametros = new HashMap();
			//JRBeanCollectionDataSource  jrDataSource = new JRBeanCollectionDataSource(lstClientesReporting);
			
			
			/*lstClientesReporting= objClienteService.listarClientes();
			log.info("tamañito "+lstClientesReporting.size());
			parametros.put("nombreUsuario", objUsuario.getNombreemp() + " " + objUsuario.getApepatemp()
					+ " " + objUsuario.getApematemp());
			parametros.put("nombrePC", addr.getHostName());
			parametros.put("subreport", reporte);
			parametros.put("subreportData", jrDataSource);
			log.info(" pccc  "+ addr.getHostName());
			*/
			Context initContext = new InitialContext();
			ds = (DataSource)initContext.lookup("java:/edicsemJPADatasource");
			Connection conn = ds.getConnection();
			if(ds==null){
				log.info("Es nulo el datasource" );
			}
			if(conn==null){
				log.info("Es nulo la conexion" );
			}
			JasperPrint jasper = JasperFillManager.fillReport(reporte,
					criteria, conn);
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasper);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.exportReport();
			log.info("  cerrando conexion :D" );
			conn.close();
			conn = null;
			ds=null;
			initContext.close();
		}
		catch (SQLException em) {
			log.info("Error de sql: " + em.getMessage()+" cause "+em.getCause());
			log.info("Error al conectarse a la Base de Datos");
			em.printStackTrace();
			}
		catch (JRFillInterruptedException ef) {
			log.info("mensaje jrfill  "+ef.getMessage()+" cause "+ef.getCause());
			ef.printStackTrace();
			log.info("No se pudo generar el reporte");
			}
		catch (JRException ex) {
			log.info("mensaje jrex  "+ex.getMessage()+" cause "+ex.getCause());
			ex.printStackTrace();
			log.info("No se pudo generar el reporte");
			}
		catch (Exception e) {
			log.info("mensaje Reportingcliente  "+e.getMessage()+" cause "+e.getCause());
			e.printStackTrace();
			}
	}
}