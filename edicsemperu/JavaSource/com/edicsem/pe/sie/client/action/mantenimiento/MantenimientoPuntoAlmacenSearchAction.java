package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="almacenSearch")
@SessionScoped
public class MantenimientoPuntoAlmacenSearchAction extends BaseMantenimientoAbstractAction{

	private PuntoVentaSie almacen;
	private Log log = LogFactory.getLog(MantenimientoPuntoAlmacenSearchAction.class);
	private List<PuntoVentaSie> almacenList;
	 
	@EJB
	private AlmacenService objAlmacenService;
	
	public MantenimientoPuntoAlmacenSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoPuntoAlmacenSearchAction'");
		}
		almacen = new PuntoVentaSie();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarProductos 'MantenimientoPuntoAlmacenSearchAction' ");
		almacenList = objAlmacenService.listarAlmacenes();
		if (almacenList == null) {
			almacenList = new ArrayList<PuntoVentaSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_ALMACEN_FORM_LIST_PAGE;
	}
	
	public PuntoVentaSie getAlmacen() {
		return almacen;
	}

	public void setAlmacen(PuntoVentaSie almacen) {
		this.almacen = almacen;
	}

	public List<PuntoVentaSie> getAlmacenList() {
		return almacenList;
	}

	public void setAlmacenList(List<PuntoVentaSie> almacenList) {
		this.almacenList = almacenList;
	}
}
