/**
 * @author FUCKING
 *
 */
package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "mantenimientoCargoEmpleadoSearchAction")
@SessionScoped
public class MantenimientoCargoEmpleadoSearchAction extends	BaseMantenimientoAbstractAction {

	public static Log log = LogFactory.getLog(MantenimientoCargoEmpleadoSearchAction.class);

	private List<SelectItem> estadosItems;
	private CargoEmpleadoSie objCargoEmpleadoSie;
	private List<CargoEmpleadoSie> CargoEmpleadomodel;
	private CargoEmpleadoSie selectedCargoEmpleado;
	private boolean editMode;
	private CargoEmpleadoSie nuevo;
	private int idEstadoGeneral;

	@EJB
	private CargoEmpleadoService objCargoEmpleadoService;

	public List<CargoEmpleadoSie> getCargoEmpleadomodel() throws Exception {
		return CargoEmpleadomodel;
    }

	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		estadosItems = new ArrayList<SelectItem>();
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		objCargoEmpleadoSie.setDescripcion("");
		nuevo = new CargoEmpleadoSie();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listando Cargo Emplado ...");
		CargoEmpleadomodel = objCargoEmpleadoService.listarCargoEmpleado();
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_CARGO_EMPLEADO_FORM_LIST_PAGE;
	}

	/**
	 * @param objCargoEmpleadoService
	 */
	public void setObjCargoEmpleadoService(
			CargoEmpleadoService objCargoEmpleadoService) {
		this.objCargoEmpleadoService = objCargoEmpleadoService;
	}

	public MantenimientoCargoEmpleadoSearchAction() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
	}

	/**
	 * @param cargoEmpleadomodel
	 */
	public void setCargoEmpleadomodel(
			List<CargoEmpleadoSie> cargoEmpleadomodel) {
		CargoEmpleadomodel = cargoEmpleadomodel;
	}

	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}

	/**
	 * @param idEstadoGeneral
	 *            the idEstadoGeneral to set
	 */
	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
	}

	/**
	 * @return
	 */
	public CargoEmpleadoSie getObjCargoEmpleadoSie() {
		return objCargoEmpleadoSie;
	}

	/**
	 * @param objCargoEmpleadoSie
	 */
	public void setObjCargoEmpleadoSie(CargoEmpleadoSie objCargoEmpleadoSie) {
		this.objCargoEmpleadoSie = objCargoEmpleadoSie;
	}

	/**
	 * @return
	 */
	public CargoEmpleadoSie getSelectedCargoEmpleado() {
		return selectedCargoEmpleado;
	}

	/**
	 * @param selectedCargoEmpleado
	 */
	public void setSelectedCargoEmpleado(CargoEmpleadoSie selectedCargoEmpleado) {
		this.selectedCargoEmpleado = selectedCargoEmpleado;
	}

	/**
	 * @return
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * @param editMode
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * @param nuevo
	 */
	public void setNuevo(CargoEmpleadoSie nuevo) {
		this.nuevo = nuevo;
	}

	/**
	 * @return
	 */
	public CargoEmpleadoSie getNuevo() {
		return nuevo;
	}

	/**
	 * @return
	 */
	public static Log getLog() {
		return log;
	}

	/**
	 * @param log
	 */
	public static void setLog(Log log) {
		MantenimientoCargoEmpleadoSearchAction.log = log;
	}

	/**
	 * @return
	 */
	public CargoEmpleadoService getObjCargoEmpleadoService() {
		return objCargoEmpleadoService;
	}

	/**
	 * @param estadosItems
	 *            the estadosItems to set
	 */
	public void setEstadosItems(List<SelectItem> estadosItems) {
		this.estadosItems = estadosItems;
	}
	
	
	
	
}
