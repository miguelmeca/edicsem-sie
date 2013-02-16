package serviceImpl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fill.JRAbstractLRUVirtualizer;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSwapFile;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import service.ReporteExecutionService;
import clases.ReporteParams;
import clases.ReporteResult;

import com.edicsem.pe.sie.util.constants.Constants;

public class ReporteExecutionServiceImpl  extends HttpServlet implements ReporteExecutionService  {
	
	protected final Log log = LogFactory.getLog(getClass());
	private DataSource dataSource;
	
	public ReporteResult executeReporte(ReporteParams reportParams) {
		log.debug("Entering 'executeReporte' method");
		ReporteResult reporteResult = new ReporteResult();
		Connection connection = null;
		try {
			ServletContext context2 = getServletContext();
			String reportLocation = context2.getRealPath(reportParams.getJasperFileName());
			FileInputStream fis = new FileInputStream(reportLocation);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
			log.debug("Se genero el JasperReport");

			connection = dataSource.getConnection();
		
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, reportParams.getQueryParams(), connection);
			connection.close();
			log.debug("Se genero el JasperPrint");
			reporteResult.setJasperPrint(jasperPrint);
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
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}
		return reporteResult;
	}

	/**
	 * Metodo que permite ejecutar reportes de manera virtualizada, es decir la
	 * impresion se hara en disco.
	 * 
	 * @param reportParams -
	 *            parametros a utlizar
	 */
	public ReporteResult executeReporteVirtualizer(ReporteParams reportParams) {

		log.debug("Entering 'executeReporteVirtualizer' method");
		ReporteResult reporteResult = new ReporteResult();
		Connection connection = null;

		Map params = (Map) reportParams.getQueryParams().get("parameterMap");
		String directorioTemporal = (String) params.get("directorioTemporal");
		JRFileVirtualizer fileVirtualizer = new JRFileVirtualizer(3, directorioTemporal);
		try {
			ServletContext context2 = getServletContext();
			String reportLocation = context2.getRealPath(reportParams.getJasperFileName());
			FileInputStream fis = new FileInputStream(reportLocation);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
			log.debug("Se genero el JasperReport");
			
			connection = dataSource.getConnection();
			JRAbstractLRUVirtualizer virtualizer = null;
			JRSwapFile swapFile = new JRSwapFile(directorioTemporal, 1024, 1024);
			virtualizer = new JRSwapFileVirtualizer(3, swapFile, true);			
			reportParams.getQueryParams().put(JRParameter.REPORT_VIRTUALIZER,virtualizer);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams.getQueryParams(), connection);
			if (fileVirtualizer != null) {
				fileVirtualizer.setReadOnly(true);
			}
			connection.close();
			log.debug("Se genero el JasperPrint");
			reporteResult.setJasperPrint(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			log.error("El DataSource no pudo establecer la conexion con la base de datos");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("No se encontro el archivo jasper: "+ reportParams.getJasperFileName());
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}
		return reporteResult;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ReporteResult executeReporteMultiple(ReporteParams reportParams) {
		log.debug("Entering 'executeReporteMultiple' method");
		ReporteResult reporteResult = new ReporteResult();
		Connection connection = null;
		try {
			ServletContext context2 = getServletContext();
			String reportLocation = context2.getRealPath(reportParams.getJasperFileName());
			FileInputStream fis = new FileInputStream(reportLocation);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
			
			connection = dataSource.getConnection();
		
			//Seteamos los parametros que van a variar
			Map queryParams = reportParams.getQueryParams();
			Map parameterMap = (Map)queryParams.get("parameterMap");
			
			List listaRegionesZonas = (ArrayList)MapUtils.getObject(parameterMap, Constants.REPORTE_CLIENTE_LIST);
			
			//Creamos una carpeta temporal para los reportes
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			String carpetaTemporal = sdf.format(new Date(System.currentTimeMillis()));
			String rutaCarpetaTemporal = MapUtils.getString(parameterMap, "directorioTemporal") + carpetaTemporal + File.separator;
			File fileCarpetaTemporal = new File(rutaCarpetaTemporal);
			fileCarpetaTemporal.mkdir();
			
			parameterMap.put("rutaTemporalReportesGenerados", rutaCarpetaTemporal);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, queryParams, connection);
				
				try {
					String nombreArchivoConsolidado = String.format(Constants.FORMATO_NOMBRE_REPORTE_CONSOLIDADO, carpetaTemporal);
					String rutaArchivoConsolidado = rutaCarpetaTemporal + nombreArchivoConsolidado;
					
					JasperExportManager.exportReportToPdfFile(jasperPrint, rutaArchivoConsolidado);
					
					if(log.isDebugEnabled())
						this.log.debug("Reporte Consolidado generado");

				} catch (JRException e) {
					log.error("Error al generar reporte Consolidado", e);
				}
			
			connection.close();
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			log.error("El DataSource no pudo establecer la conexion con la base de datos");
			e.printStackTrace();

		} catch (IOException e) {
			log.error("No se encontro el archivo jasper: "+ reportParams.getJasperFileName());
			e.printStackTrace();

		}catch (Exception e) {
			log.error("Execpcion No Capturada: " + reportParams.getJasperFileName());
			e.printStackTrace();
		}
		
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}
		return reporteResult;
	}
}
