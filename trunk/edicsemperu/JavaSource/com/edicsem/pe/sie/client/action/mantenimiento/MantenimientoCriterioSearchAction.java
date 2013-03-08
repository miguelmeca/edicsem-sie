package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CriterioComisionSie;
import com.edicsem.pe.sie.service.facade.CriterioComisionService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="criterioSearch")
@SessionScoped
public class MantenimientoCriterioSearchAction extends BaseMantenimientoAbstractAction{
	
	private Log log = LogFactory.getLog(MantenimientoCriterioSearchAction.class);
	private List<CriterioComisionSie> criterioList;
	
	@EJB
	private CriterioComisionService objCriterioService;
	
	public MantenimientoCriterioSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoCriterioSearchAction'");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarProductos 'MantenimientoCriterioSearchAction' ");
		criterioList = objCriterioService.listarCriterioComision();
		if (criterioList == null) {
			criterioList = new ArrayList<CriterioComisionSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_CRITERIO_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_CRITERIO_FORM_LIST_PAGE;
	}
	
	/**
	 * @return the criterioList
	 */
	public List<CriterioComisionSie> getCriterioList() {
		return criterioList;
	}

	/**
	 * @param criterioList the criterioList to set
	 */
	public void setCriterioList(List<CriterioComisionSie> criterioList) {
		this.criterioList = criterioList;
	}
}
