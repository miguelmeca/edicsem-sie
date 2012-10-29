package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ComprobanteSie;
import com.edicsem.pe.sie.service.facade.ComprobanteService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="comprobanteSearch")
@SessionScoped
public class MantenimientoComprobanteSearchAction extends BaseMantenimientoAbstractAction{

	private Log log = LogFactory.getLog(MantenimientoComprobanteSearchAction.class);
	private List<ComprobanteSie> comprobanteList;
	 
	@EJB
	private ComprobanteService objComprobanteService;
	
	public MantenimientoComprobanteSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoComprobanteSearchAction'");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarProductos 'MantenimientoPuntoAlmacenSearchAction' ");
		comprobanteList = objComprobanteService.listarComprobantes();
		if (comprobanteList == null) {
			comprobanteList = new ArrayList<ComprobanteSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_COMPROBANTE_FORM_LIST_PAGE;
	}
	
	/**
	 * @return the comprobanteList
	 */
	public List<ComprobanteSie> getComprobanteList() {
		return comprobanteList;
	}

	/**
	 * @param comprobanteList the comprobanteList to set
	 */
	public void setComprobanteList(List<ComprobanteSie> comprobanteList) {
		this.comprobanteList = comprobanteList;
	}
	
}
