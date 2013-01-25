package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.MantenimientoCargoEmpleadoSearchAction;
import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.entity.FactorSancionSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.service.facade.DetallePaqueteService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.PaqueteService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "mantenimientoPaqueteBiblicoSearchAction")
@SessionScoped
public class MantenimientoPaqueteBiblicoSearchAction extends BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoPaqueteBiblicoSearchAction.class);
	

	// PAQUETE BIBLICO
	public String mensaje;
	private List<PaqueteSie> detPaqueteList;
	private List<DetPaqueteSie> detPaqueteBiblicoList;
	private PaqueteSie objPaqueteSie;
	private DetPaqueteSie objDetPaqueteSie;
	
	private boolean editMode;
	@EJB
	private DetallePaqueteService objDetallePaqueteService;
	
	@EJB
	private PaqueteService objPaqueteService;
	
	
	@EJB
	private EstadogeneralService objEstadoGeneralService;
	
	
	public List<DetPaqueteSie> getDetPaqueteBiblicoList() {
		return detPaqueteBiblicoList;
	}

	
	public MantenimientoPaqueteBiblicoSearchAction() {
		init();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #init()
	 */
	public void init() {

		
		//PAQUETE BIBLICO
		
		log.info("Inicializando el Constructor de 'MantenimientoPaqueteBiblicoSearchAction'");
		objDetPaqueteSie = new DetPaqueteSie();
		objPaqueteSie = new PaqueteSie();
		
		
		
	}
	

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listando paquetes biblicos... ");
		detPaqueteList = objPaqueteService.listarPaquetes();
		
		return getViewList();
	}
	
	

	public String getViewList() {
		return Constants.MANT_PAQUETEBIBLICO_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	
	
	

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the detPaqueteList
	 */
	public List<PaqueteSie> getDetPaqueteList() {
		return detPaqueteList;
	}

	/**
	 * @param detPaqueteList the detPaqueteList to set
	 */
	public void setDetPaqueteList(List<PaqueteSie> detPaqueteList) {
		this.detPaqueteList = detPaqueteList;
	}

	/**
	 * @return the detPaqueteBiblicoList
	 */
	
	/**
	 * @param detPaqueteBiblicoList the detPaqueteBiblicoList to set
	 */
	public void setDetPaqueteBiblicoList(List<DetPaqueteSie> detPaqueteBiblicoList) {
		this.detPaqueteBiblicoList = detPaqueteBiblicoList;
	}

	/**
	 * @return the objPaqueteSie
	 */
	public PaqueteSie getObjPaqueteSie() {
		return objPaqueteSie;
	}

	/**
	 * @param objPaqueteSie the objPaqueteSie to set
	 */
	public void setObjPaqueteSie(PaqueteSie objPaqueteSie) {
		this.objPaqueteSie = objPaqueteSie;
	}

	/**
	 * @return the objDetPaqueteSie
	 */
	public DetPaqueteSie getObjDetPaqueteSie() {
		return objDetPaqueteSie;
	}

	/**
	 * @param objDetPaqueteSie the objDetPaqueteSie to set
	 */
	public void setObjDetPaqueteSie(DetPaqueteSie objDetPaqueteSie) {
		this.objDetPaqueteSie = objDetPaqueteSie;
	}

	/**
	 * @return the newRecord
	 */
	
	/**
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * @param editMode the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * @return the log
	 */
	public Log getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(Log log) {
		this.log = log;
	}

	
	

	
}
