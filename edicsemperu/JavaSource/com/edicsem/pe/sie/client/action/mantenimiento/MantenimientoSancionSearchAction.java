package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetSancionCargoSie;
import com.edicsem.pe.sie.entity.SancionSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.DetSancionCargoService;
import com.edicsem.pe.sie.service.facade.SancionService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "SancionSearch")
@SessionScoped
public class MantenimientoSancionSearchAction extends BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoSancionSearchAction.class);
	
	public String mensaje;
	private boolean newRecord = false;
	private List<SancionSie> detSancionList;
	private List<DetSancionCargoSie> detSancionCargoList;
	private int idFactor,idSancion, factor;
	private int idcargo;
	private DetSancionCargoSie objDetSancionCargo;
	
	@EJB
	private SancionService objSancionService;
	@EJB
	private CargoEmpleadoService objCargoService;
	@EJB
	private DetSancionCargoService objDetSancionCargoService;
	
	public MantenimientoSancionSearchAction() {
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
		detSancionList = new ArrayList<SancionSie>();
		detSancionCargoList = new ArrayList<DetSancionCargoSie>();
		objDetSancionCargo = new DetSancionCargoSie();
		idFactor=0;
		idSancion =0;
	}


	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar 'MantenimientorSancionFormAction' ");
		detSancionList = objSancionService.listarSanciones(idFactor);
		if (detSancionList == null) {
			detSancionList = new ArrayList<SancionSie>();
		}
		return getViewList();
	}
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_SANCION_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_SANCION_FORM_PAGE;
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
	 * @return the detSancionList
	 */
	public List<SancionSie> getDetSancionList() {
		return detSancionList;
	}

	/**
	 * @param detSancionList the detSancionList to set
	 */
	public void setDetSancionList(List<SancionSie> detSancionList) {
		this.detSancionList = detSancionList;
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
	 * @return the idFactor
	 */
	public int getIdFactor() {
		return idFactor;
	}

	/**
	 * @param idFactor the idFactor to set
	 */
	public void setIdFactor(int idFactor) {
		this.idFactor = idFactor;
	}

	/**
	 * @return the idSancion
	 */
	public int getIdSancion() {
		return idSancion;
	}

	/**
	 * @param idSancion the idSancion to set
	 */
	public void setIdSancion(int idSancion) {
		this.idSancion = idSancion;
	}

	/**
	 * @return the factor
	 */
	public int getFactor() {
		return factor;
	}

	/**
	 * @param factor the factor to set
	 */
	public void setFactor(int factor) {
		this.factor = factor;
	}

	/**
	 * @return the idcargo
	 */
	public int getIdcargo() {
		return idcargo;
	}

	/**
	 * @param idcargo the idcargo to set
	 */
	public void setIdcargo(int idcargo) {
		this.idcargo = idcargo;
	}

	/**
	 * @return the detSancionCargoList
	 */
	public List<DetSancionCargoSie> getDetSancionCargoList() {
		return detSancionCargoList;
	}

	/**
	 * @param detSancionCargoList the detSancionCargoList to set
	 */
	public void setDetSancionCargoList(List<DetSancionCargoSie> detSancionCargoList) {
		this.detSancionCargoList = detSancionCargoList;
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

}
