package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.FactorSancionSie;
import com.edicsem.pe.sie.service.facade.FactorSancionService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="factorSancionSearch")
@SessionScoped
public class MantenimientoFactorSancionSearchAction extends BaseMantenimientoAbstractAction{

	private Log log = LogFactory.getLog(MantenimientoFactorSancionSearchAction.class);
	private List<FactorSancionSie> factorSancionList;
	
	@EJB
	private FactorSancionService objFactorSancionService;
	
	public MantenimientoFactorSancionSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoFactorSancionSearchAction'");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar 'MantenimientoFactorSancionSearchAction' ");
		factorSancionList = objFactorSancionService.listarFactorSancion();
		if (factorSancionList == null) {
			factorSancionList = new ArrayList<FactorSancionSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_FACTOR_SANCION_FORM_LIST_PAGE;
	}

	/**
	 * @return the objFactorSancionService
	 */
	public FactorSancionService getObjFactorSancionService() {
		return objFactorSancionService;
	}

	/**
	 * @param objFactorSancionService the objFactorSancionService to set
	 */
	public void setObjFactorSancionService(
			FactorSancionService objFactorSancionService) {
		this.objFactorSancionService = objFactorSancionService;
	}

	/**
	 * @return the factorSancionList
	 */
	public List<FactorSancionSie> getFactorSancionList() {
		return factorSancionList;
	}

	/**
	 * @param factorSancionList the factorSancionList to set
	 */
	public void setFactorSancionList(List<FactorSancionSie> factorSancionList) {
		this.factorSancionList = factorSancionList;
	}
	
}
