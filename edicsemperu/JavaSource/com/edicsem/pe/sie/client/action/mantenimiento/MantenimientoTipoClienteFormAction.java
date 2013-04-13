package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoClienteSie;
import com.edicsem.pe.sie.service.facade.TipoClienteService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "tipoClienteForm")
@SessionScoped
public class MantenimientoTipoClienteFormAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private Log log = LogFactory.getLog(MantenimientoTipoClienteFormAction.class);
	private TipoClienteSie objTipoClienteSie;
	private boolean newRecord = false;
	
	@ManagedProperty(value = "#{tipoClienteSearch}")
	private MantenimientoTipoClienteSearchAction tipoClienteSearch;
	
	@EJB
	private TipoClienteService objTipoClienteService;

	public MantenimientoTipoClienteFormAction() {
		log.info("inicializando constructor MantenimientoLugarVentaFormAction");
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
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
		objTipoClienteSie = new TipoClienteSie();
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
		return tipoClienteSearch.getViewMant();
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
				objTipoClienteService.insertTipoCliente(objTipoClienteSie);
				paginaRetorno = tipoClienteSearch.listar();
				mensaje =Constants.MESSAGE_REGISTRO_TITULO;
			} else {
				objTipoClienteService.updateTipoCliente(objTipoClienteSie);
				paginaRetorno = tipoClienteSearch.listar();
				mensaje =Constants.MESSAGE_ACTUALIZO_TITULO;
			}
			objTipoClienteSie = new TipoClienteSie();
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return paginaRetorno;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_LUGAR_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_LUGAR_FORM_LIST_PAGE;
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
	 * @return the tipoClienteSearch
	 */
	public MantenimientoTipoClienteSearchAction getTipoClienteSearch() {
		return tipoClienteSearch;
	}

	/**
	 * @param tipoClienteSearch the tipoClienteSearch to set
	 */
	public void setTipoClienteSearch(MantenimientoTipoClienteSearchAction tipoClienteSearch) {
		this.tipoClienteSearch = tipoClienteSearch;
	}

	/**
	 * @return the objTipoClienteSie
	 */
	public TipoClienteSie getObjTipoClienteSie() {
		return objTipoClienteSie;
	}

	/**
	 * @param objTipoClienteSie the objTipoClienteSie to set
	 */
	public void setObjTipoClienteSie(TipoClienteSie objTipoClienteSie) {
		this.objTipoClienteSie = objTipoClienteSie;
	}
}
