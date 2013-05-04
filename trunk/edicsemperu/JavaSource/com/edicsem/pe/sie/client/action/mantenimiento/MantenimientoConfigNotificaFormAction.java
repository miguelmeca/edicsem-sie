package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ConfigNotificacionSie;
import com.edicsem.pe.sie.service.facade.CalificacionEquifaxService;
import com.edicsem.pe.sie.service.facade.ConfigNotificacionService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="configNotificaFormAction")
@SessionScoped
public class MantenimientoConfigNotificaFormAction extends BaseMantenimientoAbstractAction {
	private String mensaje;
	private int idtipocliente;
	private int idCalificacion;
	private boolean newRecord =false;
	private ConfigNotificacionSie objConfig;
	
	@ManagedProperty(value = "#{configTipoRefinanSearchAction}")
	private MantenimientoConfigRefinanciaSearchAction mantenimientoConfigSearch;
	
	@EJB
	private ConfigNotificacionService objConfigService;
	@EJB
	private CalificacionEquifaxService objCalificacionService;
	@EJB
	private EstadogeneralService objEstadoService;
	
	public static Log log = LogFactory.getLog(MantenimientoConfigNotificaFormAction.class);
	
	public MantenimientoConfigNotificaFormAction() {
		log.info("inicializando mi constructor 'MantenimientoConfigNotificaFormAction'");
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init()");
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */ 
	public String agregar() {
		log.info("agregar ");
		objConfig = new ConfigNotificacionSie();
		idtipocliente=0;
		setNewRecord(true);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()");
		idtipocliente = objConfig.getTbTipoCliente().getIdtipocliente();
		setNewRecord(false);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("insertar() " );
		String paginaRetorno=null;
		mensaje=null;
		
		try {
			if (isNewRecord()){
				objConfigService.insertConfigNotificacion(objConfig);
				mensaje ="Se registró la configuración correctamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			}else {
				objConfigService.updateConfigNotificacion(objConfig);
				mensaje ="Se atualizó la configuración correctamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			}
				objConfig = new ConfigNotificacionSie();
				paginaRetorno =mantenimientoConfigSearch.listar();
			
		}catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);	
		return paginaRetorno;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#delete()
	 */
	public String delete() throws Exception {
		log.info("delete()");
		idtipocliente = objConfig.getTbTipoCliente().getIdtipocliente();
		objConfig.setTbEstadoGeneral(objEstadoService.findEstadogeneral(22));
		objConfigService.updateConfigNotificacion(objConfig);
		mensaje ="Se deshabilitó la configuración correctamente";
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_CONFIG_TIPO_REFINAN_FORM;
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
	 * @return the idtipocliente
	 */
	public int getIdtipocliente() {
		return idtipocliente;
	}
	/**
	 * @param idtipocliente the idtipocliente to set
	 */
	public void setIdtipocliente(int idtipocliente) {
		this.idtipocliente = idtipocliente;
	}

	/**
	 * @return the mantenimientoConfigSearch
	 */
	public MantenimientoConfigRefinanciaSearchAction getMantenimientoConfigSearch() {
		return mantenimientoConfigSearch;
	}

	/**
	 * @param mantenimientoConfigSearch the mantenimientoConfigSearch to set
	 */
	public void setMantenimientoConfigSearch(
			MantenimientoConfigRefinanciaSearchAction mantenimientoConfigSearch) {
		this.mantenimientoConfigSearch = mantenimientoConfigSearch;
	}

	/**
	 * @return the idCalificacion
	 */
	public int getIdCalificacion() {
		return idCalificacion;
	}

	/**
	 * @param idCalificacion the idCalificacion to set
	 */
	public void setIdCalificacion(int idCalificacion) {
		this.idCalificacion = idCalificacion;
	}

	/**
	 * @return the objConfig
	 */
	public ConfigNotificacionSie getObjConfig() {
		return objConfig;
	}

	/**
	 * @param objConfig the objConfig to set
	 */
	public void setObjConfig(ConfigNotificacionSie objConfig) {
		this.objConfig = objConfig;
	}
}
