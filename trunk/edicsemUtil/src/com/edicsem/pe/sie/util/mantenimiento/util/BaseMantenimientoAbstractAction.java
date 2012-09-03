package com.edicsem.pe.sie.util.mantenimiento.util;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;


/**
 * @author Jorge Velasquez
 * 
 */
public abstract class BaseMantenimientoAbstractAction  {
	
	public static Log log = LogFactory.getLog(BaseMantenimientoAbstractAction.class);
	public static FacesMessage msg = null;
	
	private final String DEFAULT_VIEW_LIST = "";
	private final String DEFAULT_VIEW_MANT = "";
	
	
	public String getViewList(){
		return DEFAULT_VIEW_LIST;
	}
	public String getViewMant(){
		return DEFAULT_VIEW_MANT;
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertarValidation(Object bean) throws Exception {
		if (log.isInfoEnabled()) {
			log.info("Entering 'insertarValidation' method");
		}
		BaseMantenimientoForm f = (BaseMantenimientoForm) bean;
		f.setEditable(true);
		f.setModified(false);
		f.setNewRecord(true);
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void editValidation(Object bean, String id) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'editValidation' method");
		}
		BaseMantenimientoForm f = (BaseMantenimientoForm) bean;
		f.setEditable(true);
		f.setModified(false);
		f.setNewRecord(false);
		if (StringUtils.isBlank(id)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO,
					Constants.MESSAGE_ERROR_ID_NOT_FOUND);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void consultarValidation(Object bean, String id) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'consultarValidation' method");
		}
		BaseMantenimientoForm f = (BaseMantenimientoForm) bean;
		f.setEditable(false);
		f.setModified(false);
		f.setNewRecord(false);
		if (StringUtils.isBlank(id)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO,
					Constants.MESSAGE_ERROR_ID_NOT_FOUND);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}


	/**
	 * @return
	 * @throws Exception
	 */
	public void deleteValidation (String id) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteValidation' method");
		}
		if (StringUtils.isBlank(id)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO,
					Constants.MESSAGE_ERROR_ID_NOT_FOUND);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	
	public String insertar()throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertar' method");
		}
		
		return getViewList();
	}
	
	public String update()throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'update' method");
		}
		
		return getViewList();
	}
	
	public String delete()throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'update' method");
		}
		
		return getViewList();
	}
	
	public String consultar()throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'update' method");
		}
		
		return getViewMant();
	}
	
	public String listar() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'update' method");
		}
		
		return getViewList();
	}
}
