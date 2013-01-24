package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "MantenimientoClienteSearchAction")
@SessionScoped
public class MantenimientoClienteSearchAction extends
		BaseMantenimientoAbstractAction {

	private ClienteSie objCliente;
	private List<ClienteSie> clienteList;
	int idEstadoGeneral;
	private TelefonoPersonaSie objTelefonoPersonaSie;
	private List<TelefonoPersonaSie> TelefonoPersonaList;
	private EmpleadoSie selectedEmpleado;
	private boolean editMode;
	private List<ClienteSie> filteredClienteSie;

	@EJB
	private ClienteService objclienteService;

	public Log log = LogFactory.getLog(MantenimientoClienteSearchAction.class);

	public List<ClienteSie> getClienteList() throws Exception {
		return clienteList;
	}

	public MantenimientoClienteSearchAction() {
		log.info("inicializando mi constructor");
		init();
	}

	/* inicializamos los objetos utilizados */
	public void init() {
		log.info("init()");
		log.info("dentro del init");
		objCliente = new ClienteSie();

		log.info("despues de inicializar  ");
			log.info("init()");

	}

	public String listar() {
		log.info("listarEmpleados 'MantenimientoClienteSearchAction' ");
		clienteList = objclienteService.listarClientes();

		if (clienteList == null) {
			clienteList = new ArrayList<ClienteSie>();
		}
		return getViewList();
	}

	public String getViewList() {
		return Constants.MANT_CLIENTE_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_CLIENTE_FORM_PAGE;
	}

	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * @param editMode
	 *            the editMode to set
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
	 * @return the objclienteService
	 */
	public ClienteService getObjclienteService() {
		return objclienteService;
	}

	/**
	 * @param objclienteService
	 *            the objclienteService to set
	 */
	public void setObjclienteService(ClienteService objclienteService) {
		this.objclienteService = objclienteService;
	}

	/**
	 * @return the clienteList
	 */

	/**
	 * @param clienteList
	 *            the clienteList to set
	 */
	public void setClienteList(List<ClienteSie> clienteList) {
		this.clienteList = clienteList;
	}

	/**
	 * @return the objCliente
	 */
	public ClienteSie getObjCliente() {
		return objCliente;
	}

	/**
	 * @param objCliente
	 *            the objCliente to set
	 */
	public void setObjCliente(ClienteSie objCliente) {
		this.objCliente = objCliente;
	}

	/**
	 * @param selectedEmpleado
	 *            the selectedEmpleado to set
	 */
	public void setSelectedEmpleado(EmpleadoSie selectedEmpleado) {
		this.selectedEmpleado = selectedEmpleado;
	}

	/**
	 * @return the objTelefonoPersonaSie
	 */
	public TelefonoPersonaSie getObjTelefonoPersonaSie() {
		return objTelefonoPersonaSie;
	}

	/**
	 * @param objTelefonoPersonaSie
	 *            the objTelefonoPersonaSie to set
	 */
	public void setObjTelefonoPersonaSie(
			TelefonoPersonaSie objTelefonoPersonaSie) {
		this.objTelefonoPersonaSie = objTelefonoPersonaSie;
	}

	/**
	 * @return the telefonoPersonaList
	 */
	public List<TelefonoPersonaSie> getTelefonoPersonaList1() {
		return TelefonoPersonaList;
	}

	/**
	 * @param telefonoPersonaList
	 *            the telefonoPersonaList to set
	 */
	public void setTelefonoPersonaList(
			List<TelefonoPersonaSie> telefonoPersonaList) {
		TelefonoPersonaList = telefonoPersonaList;
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
	 * @return the filteredClienteSie
	 */
	public List<ClienteSie> getFilteredClienteSie() {
		return filteredClienteSie;
	}

	/**
	 * @param filteredClienteSie the filteredClienteSie to set
	 */
	public void setFilteredClienteSie(List<ClienteSie> filteredClienteSie) {
		this.filteredClienteSie = filteredClienteSie;
	}

	/**
	 * @return the telefonoPersonaList
	 */
	public List<TelefonoPersonaSie> getTelefonoPersonaList() {
		return TelefonoPersonaList;
	}

}
