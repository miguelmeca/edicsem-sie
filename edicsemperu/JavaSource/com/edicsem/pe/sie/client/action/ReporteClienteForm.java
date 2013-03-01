package com.edicsem.pe.sie.client.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.ReporteParams;
import com.edicsem.pe.sie.client.report.service.ReporteExecutionService;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.util.constants.Constants;

@ManagedBean(name = "reporteCliente")
@SessionScoped
public class ReporteClienteForm {

	private List<ClienteSie> lstClientesReporting;
	public static Log log = LogFactory.getLog(ReporteClienteForm.class);
	private int cantLista;
	private int idTipoCliente;
	private String ContentType;

	@EJB
	private ClienteService objClienteService;
	@EJB
	private ReporteExecutionService objReporteService;
	
	public ReporteClienteForm() {
		log.info("INICIALIZANDO REPORTE");
		cantLista=0;
		idTipoCliente=0;
		ContentType="";
	}
	
	public void listarCliente() {
		lstClientesReporting = new ArrayList<ClienteSie>();
		log.info("listando reporte");
		try {
			lstClientesReporting= objClienteService.listarClientesXTipo(idTipoCliente);
			cantLista=lstClientesReporting.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ReportingClientes() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		ReporteParams parametros = new ReporteParams();
		
		try {
			parametros.setJasperFileName(Constants.REPORTE_CLIENTE_JASPER);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			Map criteria = new HashMap();
			criteria.put(Constants.REPORTE_TITULO, Constants.REPORTE_CLIENTE_LIST+"_"+ sdf.format(new Date(System.currentTimeMillis())));
			criteria.put(Constants.REPORTE_TIPO_CLIENTE, idTipoCliente );
			parametros.setQueryParams(criteria);
			
			HttpServletResponse response = (HttpServletResponse)context.getResponse();
			objReporteService.executeReporte(parametros, response, ContentType);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FacesContext.getCurrentInstance().responseComplete(); 
		}
	}
	
	/**
	 * @return the lstClientesReporting
	 */
	public List<ClienteSie> getLstClientesReporting() {
		return lstClientesReporting;
	}

	/**
	 * @param lstClientesReporting the lstClientesReporting to set
	 */
	public void setLstClientesReporting(List<ClienteSie> lstClientesReporting) {
		this.lstClientesReporting = lstClientesReporting;
	}
	
	/**
	 * @return the cantLista
	 */
	public int getCantLista() {
		return cantLista;
	}

	/**
	 * @param cantLista the cantLista to set
	 */
	public void setCantLista(int cantLista) {
		this.cantLista = cantLista;
	}

	/**
	 * @return the idTipoCliente
	 */
	public int getIdTipoCliente() {
		return idTipoCliente;
	}

	/**
	 * @param idTipoCliente the idTipoCliente to set
	 */
	public void setIdTipoCliente(int idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return ContentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		ContentType = contentType;
	}
}
