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

import com.edicsem.pe.sie.entity.NotificacionSie;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.NotificacionService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="configNotificaSearchAction")
@SessionScoped
public class MantenimientoConfigNotificacionSearchAction extends BaseMantenimientoAbstractAction {
    
	private List<NotificacionSie> notificaList;
	private NotificacionSie objNotifica;
	private boolean newRecord;
	private List<String> lista;
	private String mensaje;
	private String descripcionUpdate;
	
	@EJB
	private NotificacionService objNotificacionService;
	@EJB
	private EstadogeneralService objEstadoService;
	
	public static Log log = LogFactory.getLog(MantenimientoConfigNotificacionSearchAction.class);
	
	public MantenimientoConfigNotificacionSearchAction() {
		log.info("inicializando mi constructor");
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
		log.info("agregar()");
		objNotifica = new NotificacionSie();
		lista = new ArrayList<String>();
		newRecord =true;
		descripcionUpdate=null;
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()");
		descripcionUpdate= objNotifica.getDescripcion();
		newRecord =false;
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("insertar() " +getNotificaList().size());
		String paginaRetorno=null;
		mensaje=null;
		
		for (int i = 0; i < getNotificaList().size(); i++) {
			log.info("add " +getNotificaList().get(i).getDescripcion());
			lista.add(getNotificaList().get(i).getDescripcion());
		}
		if(!isNewRecord()){
			for (int i = 0; i < getNotificaList().size(); i++) {
				if(descripcionUpdate.equalsIgnoreCase(getNotificaList().get(i).getDescripcion())){
				lista.remove(i);
				break;
				}
			}
		}
		try {
			if(lista.contains(objNotifica.getDescripcion())){
				log.info(" contain ");
				mensaje="Dicha descripción ya se encuentra registrada con otra configuración";
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			else{
				if (isNewRecord()){
					objNotifica.setDescripcion(objNotifica.getDescripcion().trim());
					objNotificacionService.insertNotificacion(objNotifica);
					mensaje ="Se registró la notificación correctamente";
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
				}else {
					objNotificacionService.updateNotificacion(objNotifica);
					mensaje ="Se atualizó la notificación correctamente";
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
				}
				objNotifica = new NotificacionSie();
				paginaRetorno = listar();
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
		objNotifica.setTbEstadoGeneral(objEstadoService.findEstadogeneral(112));
		objNotificacionService.updateNotificacion(objNotifica);
		mensaje ="Se deshabilitó la notificación correctamente";
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar  'MantenimientoConfigNotificacionSearchAction' ");
		notificaList = objNotificacionService.listarNotificacion();
		if (notificaList == null) {
			notificaList = new ArrayList<NotificacionSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_CONFIG_NOTIFICACION_LIST;
	}
	
	/*GETs Y SETs*/
	public List<NotificacionSie> getNotificaList() {
		return notificaList;
	}

	public void setNotificaList(List<NotificacionSie> notificaList) {
		this.notificaList = notificaList;
	}

	public NotificacionSie getObjNotifica() {
		return objNotifica;
	}

	public void setObjNotifica(NotificacionSie objNotifica) {
		this.objNotifica = objNotifica;
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
