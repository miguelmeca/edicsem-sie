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
	
	
	private final String DEFAULT_VIEW_LIST = "";
    private final String DEFAULT_VIEW_MANT = "";
    
    
    
     
	public String getViewList(){
        return DEFAULT_VIEW_LIST;
	}
	
	public String getViewMant(){
        return DEFAULT_VIEW_MANT;
	}
	
	/**
	 * Insertar la Entidad
	 * */
	public String insertar() throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertar' method");
		}
		return getViewList();
	}
	
	/**
	 * Actualizar la Entidad
	 * */
	public String update()throws Exception{
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'update' method");
		}
		
		return getViewList();
	}
	/**
	 * Eliminar la Entidad
	 ***/
	public String delete()throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'update' method");
		}
		return getViewMant();
	}
	
	/**
	 * Consultar la Entidad
	 * */
	public String consultar()throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'update' method");
		}
		return getViewMant();
	}
	/**
	 * Listar la tabla de Entidad
	 * */
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
	public String agregar(){
		if(log.isInfoEnabled()){
			log.info("Entering my method 'addNewRecord()'");
		}
		return getViewMant();
	}
}
