package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetSancionCargoSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "PagoVentaSearch")
@SessionScoped
public class MantenimientoPagoVentaSearchAction extends BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoPagoVentaSearchAction.class);
	
	public String mensaje;
	private boolean newRecord = false;
	private List<EmpleadoSie> detEmpleadoList;
	private DetSancionCargoSie objDetSancionCargo;
	
	@EJB
	private EmpleadoSieService objEmpleadoService;
	
	public MantenimientoPagoVentaSearchAction() {
		init();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #init()
	 */
	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoSancionFormAction'");
		detEmpleadoList = new ArrayList<EmpleadoSie>();
		objDetSancionCargo = new DetSancionCargoSie();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar 'MantenimientorSancionFormAction' ");
		detEmpleadoList = objEmpleadoService.listarEmpleados();
		if (detEmpleadoList == null) {
			detEmpleadoList = new ArrayList<EmpleadoSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_PAGO_VENDEDOR_LIST_PAGE;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_PAGO_VENDEDOR_FORM_PAGE;
	}
	
	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord
	 *            the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	/**
	 * @return the objDetSancionCargo
	 */
	public DetSancionCargoSie getObjDetSancionCargo() {
		return objDetSancionCargo;
	}

	/**
	 * @param objDetSancionCargo the objDetSancionCargo to set
	 */
	public void setObjDetSancionCargo(DetSancionCargoSie objDetSancionCargo) {
		this.objDetSancionCargo = objDetSancionCargo;
	}

	/**
	 * @return the detEmpleadoList
	 */
	public List<EmpleadoSie> getDetEmpleadoList() {
		return detEmpleadoList;
	}

	/**
	 * @param detEmpleadoList the detEmpleadoList to set
	 */
	public void setDetEmpleadoList(List<EmpleadoSie> detEmpleadoList) {
		this.detEmpleadoList = detEmpleadoList;
	}

}
