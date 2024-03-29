package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CriterioComisionSie;
import com.edicsem.pe.sie.service.facade.CriterioComisionService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "criterioForm")
@SessionScoped
public class MantenimientoCriterioFormAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private Log log = LogFactory.getLog(MantenimientoCriterioFormAction.class);
	private CriterioComisionSie objcriterioSie;
	private boolean newRecord = false;
	
	@ManagedProperty(value = "#{criterioSearch}")
	private MantenimientoCriterioSearchAction criterioSearch;
	
	@EJB
	private CriterioComisionService objcriterioService;

	public MantenimientoCriterioFormAction() {
		log.info("inicializando constructor MantenimientoCriterio");
		init();
	}

	public void init() {
		log.info("init()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		objcriterioSie = new CriterioComisionSie();
		setNewRecord(true);
		return getViewMant();
	}
 
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #update()
	 */
	public String update() throws Exception {
		log.info("update()" );
		setNewRecord(false);
		return criterioSearch.getViewMant();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #insertar()
	 */
	public String insertar() {
		log.info("Entering my method 'insertar()' " );
		mensaje=null;
		String paginaRetorno="";
		try {
			if (isNewRecord()) {
				objcriterioService.insertCriterioComision(objcriterioSie);
				objcriterioSie = new CriterioComisionSie();
				paginaRetorno = criterioSearch.listar();
				mensaje =Constants.MESSAGE_REGISTRO_TITULO;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			} else {
				objcriterioService.updateCriterioComision(objcriterioSie);
				objcriterioSie = new CriterioComisionSie();
				paginaRetorno = criterioSearch.listar();
				mensaje =Constants.MESSAGE_ACTUALIZO_TITULO;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		log.info("pagina retorno " +paginaRetorno);
		return paginaRetorno;
	}
	

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_EVENTO_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_EVENTO_FORM_PAGE;
	}

	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord
	 *            the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

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
	 * @return the objcriterioSie
	 */
	public CriterioComisionSie getObjcriterioSie() {
		return objcriterioSie;
	}

	/**
	 * @param objcriterioSie the objcriterioSie to set
	 */
	public void setObjcriterioSie(CriterioComisionSie objcriterioSie) {
		this.objcriterioSie = objcriterioSie;
	}

	/**
	 * @return the criterioSearch
	 */
	public MantenimientoCriterioSearchAction getCriterioSearch() {
		return criterioSearch;
	}

	/**
	 * @param criterioSearch the criterioSearch to set
	 */
	public void setCriterioSearch(MantenimientoCriterioSearchAction criterioSearch) {
		this.criterioSearch = criterioSearch;
	}

}
