package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.NotificacionSie;
import com.edicsem.pe.sie.service.facade.NotificacionService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="configTipoRefinanSearchAction")
@SessionScoped
public class MantenimientoConfigNotificacionSearchAction extends BaseMantenimientoAbstractAction {
    
	private List<NotificacionSie> configList; 
	
	@EJB
	private NotificacionService objNotificacionService;
	
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
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar  'MantenimientoConfigNotificacionSearchAction' ");
		configList = objNotificacionService.listarNotificacion();
		if (configList == null) {
			configList = new ArrayList<NotificacionSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_CONFIG_TIPO_REFINAN_LIST;
	}
	
	/*GETs Y SETs*/
	/**
	 * @return the configList
	 */
	public List<NotificacionSie> getConfigList() {
		return configList;
	}

	/**
	 * @param configList the configList to set
	 */
	public void setConfigList(List<NotificacionSie> configList) {
		this.configList = configList;
	}

	
}
