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
	
	private Log log = LogFactory.getLog(BaseMantenimientoAbstractAction.class);
	public static FacesMessage msg = null;
	
	public String getViewList(){
        return "";
}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected void insertarValidation(Object bean) throws Exception {
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
	protected void editValidation(Object bean, String id) throws Exception {
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
	protected void consultarValidation(Object bean, String id) throws Exception {
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
	protected void deleteValidation (String id) throws Exception {
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
	
	
	public void insertar()throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertar' method");
		}
	}
	
	public void update()throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'update' method");
		}
	}
	
	public void delete()throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'update' method");
		}
	}
	
	public void consultar()throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'update' method");
		}
	}
	
	public String listar() {
		if (log.isInfoEnabled()) {
			log.info("Entering 'update' method");
		}
		return getViewList();
	}
	
	/**
	 * Metodo para inicializar los objetos
	 * **/
	public void init(){
		
	}
	
	/**
	 * Metodo que realiza el seteo Nuevo registro
	 * **/
	public void addNewRecord(){
		if(log.isInfoEnabled()){
			log.info("Entering my method 'addNewRecord()'");
		}
	}
}
