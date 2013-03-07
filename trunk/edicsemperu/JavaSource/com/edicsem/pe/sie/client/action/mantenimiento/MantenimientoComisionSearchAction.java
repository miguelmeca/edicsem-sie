package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ComisionVentaSie;
import com.edicsem.pe.sie.service.facade.ComisionVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="comisionSearch")
@SessionScoped
public class MantenimientoComisionSearchAction extends BaseMantenimientoAbstractAction{
	
	private Log log = LogFactory.getLog(MantenimientoComisionSearchAction.class);
	private List<ComisionVentaSie> comisionList;
	
	@EJB
	private ComisionVentaService objComisionService;
	
	public MantenimientoComisionSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoComisionSearchAction'");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar() 'MantenimientoComisionSearchAction' ");
		comisionList = objComisionService.listarComisionVenta();
		if (comisionList == null) {
			comisionList = new ArrayList<ComisionVentaSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_COMISION_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_COMISION_FORM_PAGE;
	}

	/**
	 * @return the comisionList
	 */
	public List<ComisionVentaSie> getComisionList() {
		return comisionList;
	}

	/**
	 * @param comisionList the comisionList to set
	 */
	public void setComisionList(List<ComisionVentaSie> comisionList) {
		this.comisionList = comisionList;
	}
 

}
