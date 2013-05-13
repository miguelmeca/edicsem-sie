package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CajaSie;
import com.edicsem.pe.sie.service.facade.CajaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="cajaSearch")
@SessionScoped
public class MantenimientoCajaSearchAction extends BaseMantenimientoAbstractAction{
	
	private Log log = LogFactory.getLog(MantenimientoCajaSearchAction.class);
	private List<CajaSie> cajaList;
	
	@EJB
	private CajaService objCajaService;
	
	public MantenimientoCajaSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoCajaSearchAction'");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarProductos 'MantenimientoLugarVentaSearchAction' ");
		cajaList = objCajaService.listarCaja();
		if (cajaList == null) {
			cajaList = new ArrayList<CajaSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_CAJA_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_CAJA_FORM_LIST_PAGE;
	}

	/**
	 * @return the cajaList
	 */
	public List<CajaSie> getCajaList() {
		return cajaList;
	}

	/**
	 * @param cajaList the cajaList to set
	 */
	public void setCajaList(List<CajaSie> cajaList) {
		this.cajaList = cajaList;
	}
	
}
