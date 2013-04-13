package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoClienteSie;
import com.edicsem.pe.sie.service.facade.TipoClienteService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="tipoClienteSearch")
@SessionScoped
public class MantenimientoTipoClienteSearchAction extends BaseMantenimientoAbstractAction{
	
	private Log log = LogFactory.getLog(MantenimientoTipoClienteSearchAction.class);
	private List<TipoClienteSie> tipoClienteList;
	
	@EJB
	private TipoClienteService objTipoClienteService;
	
	public MantenimientoTipoClienteSearchAction() {
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoTipoClienteSearchAction'");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarProductos 'MantenimientoLugarVentaSearchAction' ");
		tipoClienteList = objTipoClienteService.listarTipoCliente();
		if (tipoClienteList == null) {
			tipoClienteList = new ArrayList<TipoClienteSie>();
			
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_TIPO_CLIENTE_FORM_LIST_PAGE;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_TIPO_CLIENTE_FORM_LIST_PAGE;
	}

	/**
	 * @return the tipoClienteList
	 */
	public List<TipoClienteSie> getTipoClienteList() {
		return tipoClienteList;
	}

	/**
	 * @param tipoClienteList the tipoClienteList to set
	 */
	public void setTipoClienteList(List<TipoClienteSie> tipoClienteList) {
		this.tipoClienteList = tipoClienteList;
	}
	
}
