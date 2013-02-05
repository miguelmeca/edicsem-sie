package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.service.facade.ClienteService;

@ManagedBean(name = "reporte")
@SessionScoped
public class Reporte {

	@EJB
	private ClienteService objClienteService;

	private List<ClienteSie> lstClientesReporting;
	public static Log log = LogFactory.getLog(Reporte.class);
	//cantidad de la lista
	private int cantLista;
	private final String reportingCliente = "/ReportingCliente";

	public Reporte() {
		log.info("INICIALIZANDO EL BEAN MANAGER REPORTE BM");
	}
	
	public void listarCliente(ActionEvent f) {
		lstClientesReporting = new ArrayList<ClienteSie>();
		log.info("listando reporte ..");
		try {
			lstClientesReporting= objClienteService.listarClientes();
			cantLista=lstClientesReporting.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getApplication().getNavigationHandler()
				.handleNavigation(fc, null, "reporteClienteForm");
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
	public void ReportingClientes() {
		generateReporting(reportingCliente);
	}
	
	private void generateReporting(String rutaServlet) {
		log.info(" reportando ");
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().dispatch(rutaServlet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			context.responseComplete();
		}
	}
}
