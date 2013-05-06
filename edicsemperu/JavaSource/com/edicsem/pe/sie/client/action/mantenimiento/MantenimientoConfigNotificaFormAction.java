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

import com.edicsem.pe.sie.entity.ConfigNotificacionSie;
import com.edicsem.pe.sie.entity.NotificacionSie;
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
	private int idNotificacion;
	private boolean newRecord =false;
	private ConfigNotificacionSie objConfig;
	private NotificacionSie objNotificacion;
	private List<ConfigNotificacionSie> lstConfigNotifica;
	
	@ManagedProperty(value = "#{configTipoRefinanSearchAction}")
	private MantenimientoConfigRefinanciaSearchAction mantenimientoConfigSearch;
	
	@EJB
	private ConfigNotificacionService objConfigService;
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
		objNotificacion = new NotificacionSie();
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
		if(objConfig.getTbTipoCliente()!=null){
			idtipocliente = objConfig.getTbTipoCliente().getIdtipocliente();
		}
		if(objConfig.getTbCalificacion()!=null){
			idCalificacion = objConfig.getTbCalificacion().getIdcalificacion();
		}
		if(objConfig.getTbNotifica()!=null){
			idNotificacion = objConfig.getTbNotifica().getIdnotifica();
		}
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
			// Verificar si existe una configuración con el mismo tipo de cliente y la misma calificacion equifax,
			// y el mismo tipo de notificacion
			for (int i = 0; i < lstConfigNotifica.size(); i++) {
				if( lstConfigNotifica.get(i).getTbCalificacion().getIdcalificacion()==idCalificacion &&
					lstConfigNotifica.get(i).getTbTipoCliente().getIdtipocliente()==idtipocliente &&
					lstConfigNotifica.get(i).getTbNotifica().getIdnotifica()==idNotificacion){
					mensaje="Ya existe una configuración con los mismos parámetros";
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN,Constants.MESSAGE_INFO_TITULO, mensaje);
					break;
				}
			}
			if(mensaje==null){
				if (isNewRecord()){
					objConfigService.insertConfigNotificacion(objConfig, idtipocliente, idCalificacion,idNotificacion);
					mensaje ="Se registró la configuración correctamente";
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
				}else {
					objConfigService.updateConfigNotificacion(objConfig, idtipocliente,idCalificacion,idNotificacion);
					mensaje ="Se atualizó la configuración correctamente";
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
				}
				objConfig = new ConfigNotificacionSie();
				paginaRetorno =mantenimientoConfigSearch.listar();
			}
			
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
		objConfig.setTbEstadoGeneral(objEstadoService.findEstadogeneral(22));
		objConfigService.updateConfigNotificacion(objConfig,idtipocliente, idCalificacion,idNotificacion);
		mensaje ="Se deshabilitó la configuración correctamente";
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return listar();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar() "+objNotificacion.getIdnotifica());
		lstConfigNotifica=null;
		if(objNotificacion!=null)
		lstConfigNotifica = objConfigService.listarConfigNotificacionXNotificacion(objNotificacion.getIdnotifica());
		if(lstConfigNotifica==null){
			lstConfigNotifica = new ArrayList<ConfigNotificacionSie>();
		}
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_CONFIG_NOTIFICACION_LIST;
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

	/**
	 * @return the objNotificacion
	 */
	public NotificacionSie getObjNotificacion() {
		return objNotificacion;
	}

	/**
	 * @param objNotificacion the objNotificacion to set
	 */
	public void setObjNotificacion(NotificacionSie objNotificacion) {
		this.objNotificacion = objNotificacion;
	}

	/**
	 * @return the lstConfigNotifica
	 */
	public List<ConfigNotificacionSie> getLstConfigNotifica() {
		return lstConfigNotifica;
	}

	/**
	 * @param lstConfigNotifica the lstConfigNotifica to set
	 */
	public void setLstConfigNotifica(List<ConfigNotificacionSie> lstConfigNotifica) {
		this.lstConfigNotifica = lstConfigNotifica;
	}

	/**
	 * @return the idNotificacion
	 */
	public int getIdNotificacion() {
		return idNotificacion;
	}

	/**
	 * @param idNotificacion the idNotificacion to set
	 */
	public void setIdNotificacion(int idNotificacion) {
		this.idNotificacion = idNotificacion;
	}
}
