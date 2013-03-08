package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoEventoVentaSie;
import com.edicsem.pe.sie.service.facade.TipoEventoVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="eventoSearch")
@SessionScoped
public class MantenimientoEventoSearchAction extends BaseMantenimientoAbstractAction{
	
	private Log log = LogFactory.getLog(MantenimientoEventoSearchAction.class);
	private List<TipoEventoVentaSie> eventoList;
	
	@EJB
	private TipoEventoVentaService objEventoService;
	
	public MantenimientoEventoSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoEventoSearchAction'");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarProductos 'MantenimientoEventoSearchAction' ");
		eventoList = objEventoService.listarTipoEventoVenta();
		if (eventoList == null) {
			eventoList = new ArrayList<TipoEventoVentaSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_EVENTO_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_EVENTO_FORM_PAGE;
	}

	/**
	 * @return the eventoList
	 */
	public List<TipoEventoVentaSie> getEventoList() {
		return eventoList;
	}

	/**
	 * @param eventoList the eventoList to set
	 */
	public void setEventoList(List<TipoEventoVentaSie> eventoList) {
		this.eventoList = eventoList;
	}
}
