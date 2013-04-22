package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie;
import com.edicsem.pe.sie.service.facade.ConfigCobranzaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="configCobranzaOperaSearchAction")
@SessionScoped
public class MantenimientoConfigCobranzaOperaSearchAction extends BaseMantenimientoAbstractAction {
    
	private List<ConfigCobranzaOperaSie> configList; 
	
	@EJB 
	private ConfigCobranzaService objConfigService;
	
	public static Log log = LogFactory.getLog(MantenimientoConfigCobranzaOperaSearchAction.class);
	
	public MantenimientoConfigCobranzaOperaSearchAction() {
		log.info("inicializando mi constructor");
		init();
	}

	/*inicializamos los  objetos utilizados*/
	public void init() {
		log.info("init()");		
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar  'MantenimientoConfigCobranzaOperaSearchAction' ");
		configList = objConfigService.listarConfigCobranza();
		if (configList == null) {
			configList = new ArrayList<ConfigCobranzaOperaSie>();
		}
		return getViewList();
	}
	
	/*GETs Y SETs*/
	
	public String getViewList() {
		return Constants.MANT_CONFIG_COBRANZA_OPERA;
	}

	/**
	 * @return the configList
	 */
	public List<ConfigCobranzaOperaSie> getConfigList() {
		return configList;
	}

	/**
	 * @param configList the configList to set
	 */
	public void setConfigList(List<ConfigCobranzaOperaSie> configList) {
		this.configList = configList;
	}

}
