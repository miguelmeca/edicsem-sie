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
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="clienteSearchAction")
@SessionScoped
public class MantenimientoClienteSearchAction extends BaseMantenimientoAbstractAction {
    
	private ClienteSie objCliente;
	private List<ClienteSie> clienteList;
	private EmpleadoSie selectedEmpleado;
	private boolean editMode;
	
	@EJB 
	private ClienteService objclienteService;
	
	private Log log = LogFactory.getLog(MantenimientoClienteSearchAction.class);
	
	public MantenimientoClienteSearchAction() {
		log.info("inicializando mi constructor");
		init();
	}

	/*inicializamos los  objetos utilizados*/
	public void init() {
		log.info("init()");
		objCliente = new ClienteSie();
		log.info("despues de inicializar  ");		
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarEmpleados 'MantenimientoClienteSearchAction' ");
		clienteList = objclienteService.listarClientes();
				
		if (clienteList == null) {
			clienteList = new ArrayList<ClienteSie>();
		}
		return getViewList();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	public String getViewList() {
		return "mantenimientoEmpleadoList";
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
	 * @return the objclienteService
	 */
	public ClienteService getObjclienteService() {
		return objclienteService;
	}

	/**
	 * @param objclienteService the objclienteService to set
	 */
	public void setObjclienteService(ClienteService objclienteService) {
		this.objclienteService = objclienteService;
	}

	/**
	 * @return the clienteList
	 */
	public List<ClienteSie> getClienteList() {
		return clienteList;
	}

	/**
	 * @param clienteList the clienteList to set
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
	 * @param objCliente the objCliente to set
	 */
	public void setObjCliente(ClienteSie objCliente) {
		this.objCliente = objCliente;
	}

	
	
}
