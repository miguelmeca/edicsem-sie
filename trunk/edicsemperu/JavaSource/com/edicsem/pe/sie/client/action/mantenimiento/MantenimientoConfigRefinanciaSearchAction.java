package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoRefinanciaSie;
import com.edicsem.pe.sie.service.facade.TipoRefinanciaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="configTipoRefinanSearchAction")
@SessionScoped
public class MantenimientoConfigRefinanciaSearchAction extends BaseMantenimientoAbstractAction {
    
	private List<TipoRefinanciaSie> configList; 
	
	@EJB
	private TipoRefinanciaService objConfigService;
	
	public static Log log = LogFactory.getLog(MantenimientoConfigRefinanciaSearchAction.class);
	
	public MantenimientoConfigRefinanciaSearchAction() {
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
		log.info("listar  'MantenimientoConfigRefinanciaSearchAction' ");
		configList = objConfigService.listarTipoRefinancia();
		if (configList == null) {
			configList = new ArrayList<TipoRefinanciaSie>();
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
	public List<TipoRefinanciaSie> getConfigList() {
		return configList;
	}

	/**
	 * @param configList the configList to set
	 */
	public void setConfigList(List<TipoRefinanciaSie> configList) {
		this.configList = configList;
	}
}
