package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
import com.edicsem.pe.sie.util.redirections.Redirections;

@ManagedBean(name="mantenimientoEmpleadoSearchAction")
@SessionScoped
public class MantenimientoEmpleadoSearchAction extends BaseMantenimientoAbstractAction {
    
	/*variables*/
	private int cargoEmpleado;
	private int DomicilioPersona;
	private int TelefonoPersona;
	private int tipoDocumento;
	private EmpleadoSie objEmpleado;
	private List<EmpleadoSie> empleadoList;
	private EmpleadoSie selectedEmpleado;
	private boolean editMode;
	private EmpleadoSie nuevo ;
	
	@EJB 
	private EmpleadoSieService objEmpleadoService;
	
	public static Log log = LogFactory.getLog(MantenimientoEmpleadoSearchAction.class);
	
	public MantenimientoEmpleadoSearchAction() {
		System.out.println("ESTOY EN MI CONSNTRUCTOR");
		log.info("inicializando mi constructor");
		init();
	}

	/*inicializamos los  objetos utilizados*/
	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		objEmpleado = new EmpleadoSie();
		objEmpleado.setNombreemp("");
		nuevo = new EmpleadoSie();
		log.info("despues de inicializar  ");		
	}
	
	/*método para listar a los empleados*/
	public void listarEmpleados() {
		log.info("listarEmpleados 'MantenimientoEmpleadoSearchAction' ");
		empleadoList = objEmpleadoService.listarEmpleados();
		if (empleadoList == null) {
			empleadoList = new ArrayList<EmpleadoSie>();
		}
		Redirections.redirectionsPage("mantenimiento", "mantenimientoEmpleadoFormList");
	}
		
	public void Nuevo(ActionEvent e) throws Exception {
		setObjEmpleado(null);
	}

	public EmpleadoSie getNuevo() {
		return nuevo;
	}
	
	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(EmpleadoSie nuevo) {
		this.nuevo = nuevo;
	}

	public int getCargoEmpleado() {
		return cargoEmpleado;
	}

	public void setCargoEmpleado(int cargoEmpleado2) {
		cargoEmpleado = cargoEmpleado2;
	}

	public int getDomicilioPersona() {
		return DomicilioPersona;
	}

	public void setDomicilioPersona(int domicilioPersona) {
		DomicilioPersona = domicilioPersona;
	}

	public int getTelefonoPersona() {
		return TelefonoPersona;
	}

	public void setTelefonoPersona(int telefonoPersona) {
		TelefonoPersona = telefonoPersona;
	}

	public int getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento1) {
		tipoDocumento = tipoDocumento1;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	public String getViewList() {
		return "index";
	}

	/**
	 * @return the objUsuario
	 */
	public EmpleadoSie getObjEmpleado() {
		return objEmpleado;
	}

	/**
	 * @param objUsuario
	 *            the objUsuario to set
	 */
	public void setObjEmpleado(EmpleadoSie objEmpleado) {
		this.objEmpleado = objEmpleado;
	}
	
	public boolean isEditMode() {
		return editMode;
	}
	
	/**
	 * @param editMode the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	public EmpleadoSie getSelectedEmpleado() {
		return selectedEmpleado;
	}
	
	public void setSelecteEmpleado(EmpleadoSie selectedEmpleado) {
		this.selectedEmpleado = selectedEmpleado;
	}

	/**
	 * @return the empleadoList
	 */
	/*GET para listar empleados*/
	public List<EmpleadoSie> getEmpleadoList() {
		return empleadoList=objEmpleadoService.listarEmpleados();
	}

	/**
	 * @param empleadoList the empleadoList to set
	 */
	public void setEmpleadoList(List<EmpleadoSie> empleadoList) {
		this.empleadoList = empleadoList;
	}	
	
}
