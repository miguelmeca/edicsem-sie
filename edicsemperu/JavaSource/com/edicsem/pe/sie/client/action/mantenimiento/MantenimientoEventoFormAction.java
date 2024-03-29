package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoEventoVentaSie;
import com.edicsem.pe.sie.service.facade.TipoEventoVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "eventoForm")
@SessionScoped
public class MantenimientoEventoFormAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private Log log = LogFactory.getLog(MantenimientoEventoFormAction.class);
	private TipoEventoVentaSie objeventoSie;
	private boolean newRecord = false;
	
	@ManagedProperty(value = "#{eventoSearch}")
	private MantenimientoEventoSearchAction eventoSearch;
	
	@EJB
	private TipoEventoVentaService objTipoEventoService;

	public MantenimientoEventoFormAction() {
		log.info("inicializando constructor MantenimientoProducto");
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
		setObjeventoSie(new TipoEventoVentaSie());
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
		return eventoSearch.getViewMant();
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
				objTipoEventoService.insertTipoEventoVenta(objeventoSie);
				setObjeventoSie(new TipoEventoVentaSie());
				paginaRetorno = eventoSearch.listar();
				mensaje =Constants.MESSAGE_REGISTRO_TITULO;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			} else {
				objTipoEventoService.updateTipoEventoVenta(objeventoSie);
				setObjeventoSie(new TipoEventoVentaSie());
				paginaRetorno = eventoSearch.listar();
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
	 * @return the objeventoSie
	 */
	public TipoEventoVentaSie getObjeventoSie() {
		return objeventoSie;
	}

	/**
	 * @param objeventoSie the objeventoSie to set
	 */
	public void setObjeventoSie(TipoEventoVentaSie objeventoSie) {
		this.objeventoSie = objeventoSie;
	}

	/**
	 * @return the eventoSearch
	 */
	public MantenimientoEventoSearchAction getEventoSearch() {
		return eventoSearch;
	}

	/**
	 * @param eventoSearch the eventoSearch to set
	 */
	public void setEventoSearch(MantenimientoEventoSearchAction eventoSearch) {
		this.eventoSearch = eventoSearch;
	}
	
}
