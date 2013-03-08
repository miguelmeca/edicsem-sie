package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.LugarVentaSie;
import com.edicsem.pe.sie.service.facade.LugarVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="lugarSearch")
@SessionScoped
public class MantenimientoLugarVentaSearchAction extends BaseMantenimientoAbstractAction{
	
	private Log log = LogFactory.getLog(MantenimientoLugarVentaSearchAction.class);
	private List<LugarVentaSie> lugarList;
	
	@EJB
	private LugarVentaService objLugarVentaService;
	
	public MantenimientoLugarVentaSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoLugarVentaSearchAction'");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarProductos 'MantenimientoLugarVentaSearchAction' ");
		lugarList = objLugarVentaService.listarLugarVenta();
		if (lugarList == null) {
			lugarList = new ArrayList<LugarVentaSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_LUGAR_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_LUGAR_FORM_LIST_PAGE;
	}

	/**
	 * @return the lugarList
	 */
	public List<LugarVentaSie> getLugarList() {
		return lugarList;
	}

	/**
	 * @param lugarList the lugarList to set
	 */
	public void setLugarList(List<LugarVentaSie> lugarList) {
		this.lugarList = lugarList;
	}
	
}
