package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ConfigPuntajeSie;
import com.edicsem.pe.sie.service.facade.ConfigPuntajeService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="configPuntajeSearchAction")
@SessionScoped
public class MantenimientoConfigPuntajeSearchAction extends BaseMantenimientoAbstractAction {
    
	private List<ConfigPuntajeSie> configList; 
	
	@EJB 
	private ConfigPuntajeService objConfigService;
	
	public static Log log = LogFactory.getLog(MantenimientoConfigPuntajeSearchAction.class);
	
	public MantenimientoConfigPuntajeSearchAction() {
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
		log.info("listar  'MantenimientoConfigCobranzaOperaSearchAction' ");
		configList = objConfigService.listarConfigPuntaje();
		if (configList == null) {
			configList = new ArrayList<ConfigPuntajeSie>();
		}
		return getViewList();
	}
	
	/*GETs Y SETs*/
	
	public String getViewList() {
		return Constants.MANT_CONFIG_PUNTAJE_LIST;
	}
	
	/**
	 * @return the configList
	 */
	public List<ConfigPuntajeSie> getConfigList() {
		return configList;
	}
	
	/**
	 * @param configList the configList to set
	 */
	public void setConfigList(List<ConfigPuntajeSie> configList) {
		this.configList = configList;
	}
	
}
