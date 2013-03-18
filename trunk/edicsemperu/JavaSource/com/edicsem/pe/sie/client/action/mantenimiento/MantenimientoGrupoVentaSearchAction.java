package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.GrupoVentaSie;
import com.edicsem.pe.sie.service.facade.GrupoVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="grupoSearch")
@SessionScoped
public class MantenimientoGrupoVentaSearchAction extends BaseMantenimientoAbstractAction{
	
	private Log log = LogFactory.getLog(MantenimientoGrupoVentaSearchAction.class);
	private List<GrupoVentaSie> grupoList;
	
	@EJB
	private GrupoVentaService objGrupoVentaService;
	
	public MantenimientoGrupoVentaSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoGrupoVentaSearchAction'");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarProductos 'MantenimientoGrupoVentaSearchAction' ");
		grupoList = objGrupoVentaService.listarGrupoVenta();
		if (grupoList == null) {
			grupoList = new ArrayList<GrupoVentaSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_GRUPO_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_GRUPO_FORM_LIST_PAGE;
	}

	/**
	 * @return the grupoList
	 */
	public List<GrupoVentaSie> getGrupoList() {
		return grupoList;
	}

	/**
	 * @param grupoList the grupoList to set
	 */
	public void setGrupoList(List<GrupoVentaSie> grupoList) {
		this.grupoList = grupoList;
	}
	
}
