package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.GrupoVentaSie;
import com.edicsem.pe.sie.service.facade.GrupoVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "grupoForm")
@SessionScoped
public class MantenimientoGrupoVentaFormAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private Log log = LogFactory.getLog(MantenimientoGrupoVentaFormAction.class);
	private GrupoVentaSie objgrupoSie;
	private int idTipoEvento;
	private boolean newRecord = false;
	
	@ManagedProperty(value = "#{grupoSearch}")
	private MantenimientoGrupoVentaSearchAction grupoSearch;
	
	@EJB
	private GrupoVentaService objGrupoVentaService;
	
	public MantenimientoGrupoVentaFormAction() {
		log.info("inicializando constructor MantenimientoGrupoVentaFormAction");
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
		objgrupoSie = new GrupoVentaSie();
		setNewRecord(true);
		return grupoSearch.getViewMant();
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
		idTipoEvento= objgrupoSie.getTbTipoEventoVenta().getIdtipoevento();
		return grupoSearch.getViewMant();
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
				objGrupoVentaService.insertGrupo(objgrupoSie,idTipoEvento);
				objgrupoSie = new GrupoVentaSie();
				paginaRetorno = grupoSearch.listar();
				mensaje =Constants.MESSAGE_REGISTRO_TITULO;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			} else {
				objGrupoVentaService.updateGrupo(objgrupoSie,idTipoEvento);
				objgrupoSie = new GrupoVentaSie();
				paginaRetorno = grupoSearch.listar();
				mensaje =Constants.MESSAGE_ACTUALIZO_TITULO;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		log.info("pagina retorno " +paginaRetorno);
		return paginaRetorno;
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
	 * @return the objgrupoSie
	 */
	public GrupoVentaSie getObjgrupoSie() {
		return objgrupoSie;
	}

	/**
	 * @param objgrupoSie the objgrupoSie to set
	 */
	public void setObjgrupoSie(GrupoVentaSie objgrupoSie) {
		this.objgrupoSie = objgrupoSie;
	}

	/**
	 * @return the grupoSearch
	 */
	public MantenimientoGrupoVentaSearchAction getGrupoSearch() {
		return grupoSearch;
	}

	/**
	 * @param grupoSearch the grupoSearch to set
	 */
	public void setGrupoSearch(MantenimientoGrupoVentaSearchAction grupoSearch) {
		this.grupoSearch = grupoSearch;
	}

	/**
	 * @return the idTipoEvento
	 */
	public int getIdTipoEvento() {
		return idTipoEvento;
	}

	/**
	 * @param idTipoEvento the idTipoEvento to set
	 */
	public void setIdTipoEvento(int idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}

}
