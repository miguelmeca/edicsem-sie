package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.FactorSancionSie;
import com.edicsem.pe.sie.service.facade.FactorSancionService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="factorSancionSearch")
@SessionScoped
public class MantenimientoFactorSancionSearchAction extends BaseMantenimientoAbstractAction{

	private Log log = LogFactory.getLog(MantenimientoFactorSancionSearchAction.class);
	private List<FactorSancionSie> factorSancionList;
	private FactorSancionSie objFactorSie;
	private String mensaje;
	private boolean newRecord = false;
	
	@EJB
	private FactorSancionService objFactorSancionService;
	
	public MantenimientoFactorSancionSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoFactorSancionSearchAction'");
		}
		objFactorSie = new FactorSancionSie();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar 'MantenimientoFactorSancionSearchAction' ");
		factorSancionList = objFactorSancionService.listarFactorSancion();
		if (factorSancionList == null) {
			factorSancionList = new ArrayList<FactorSancionSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_FACTOR_SANCION_FORM_LIST_PAGE;
	}

	/**
	 * @return the objFactorSancionService
	 */
	public FactorSancionService getObjFactorSancionService() {
		return objFactorSancionService;
	}

	/**
	 * @param objFactorSancionService the objFactorSancionService to set
	 */
	public void setObjFactorSancionService(
			FactorSancionService objFactorSancionService) {
		this.objFactorSancionService = objFactorSancionService;
	}

	/**
	 * @return the factorSancionList
	 */
	public List<FactorSancionSie> getFactorSancionList() {
		return factorSancionList;
	}

	/**
	 * @param factorSancionList the factorSancionList to set
	 */
	public void setFactorSancionList(List<FactorSancionSie> factorSancionList) {
		this.factorSancionList = factorSancionList;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("insertar()");
		try {
			if(isNewRecord()){
				mensaje =Constants.MESSAGE_REGISTRO_TITULO;
				objFactorSancionService.insertFactorSancion(objFactorSie);
				log.info("insertando "  );
			}
			else{
				objFactorSancionService.updateFactorSancion(objFactorSie);
				mensaje="Se actualizó el factor";
				log.info("actualizado");
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_INFO_TITULO, mensaje);
		
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objFactorSie = new FactorSancionSie();
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		setNewRecord(true);
		objFactorSie = new FactorSancionSie();
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()  " );
		setNewRecord(false);
		return getViewList();
	}

	/**
	 * @return the objFactorSie
	 */
	public FactorSancionSie getObjFactorSie() {
		return objFactorSie;
	}

	/**
	 * @param objFactorSie the objFactorSie to set
	 */
	public void setObjFactorSie(FactorSancionSie objFactorSie) {
		this.objFactorSie = objFactorSie;
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
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
	
}
