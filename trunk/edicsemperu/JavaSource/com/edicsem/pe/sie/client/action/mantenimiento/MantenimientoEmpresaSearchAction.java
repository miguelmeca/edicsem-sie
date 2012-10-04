package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="empresaSearch")
@SessionScoped
public class MantenimientoEmpresaSearchAction extends BaseMantenimientoAbstractAction{

	public static Log log = LogFactory.getLog(MantenimientoEmpresaSearchAction.class);	
	
	
	private List<EmpresaSie> empresaList;
	private EmpresaSie objEmpresaSie;
	private EmpresaSie nuevo;
	private int idEstadoGeneral;
	
	
	@EJB
	private EmpresaService empresaService;
	
	public List<EmpresaSie> getEmpresaList() throws Exception {
		return empresaList;
	}

	
	public void init() {
		log.info("init()");
		
	
		
		objEmpresaSie= new EmpresaSie();
		
		objEmpresaSie.setDescripcion("");
		objEmpresaSie.setRazonsocial("");
		objEmpresaSie.setNumruc("");
		objEmpresaSie.setNumtelefono("");
		objEmpresaSie.setNumtelefono("");
		objEmpresaSie.setEmail("");
		
		
		
		nuevo = new EmpresaSie();
		
		
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarEmpresas 'MantenimientoEmpresaSearchAction' ");
		empresaList = empresaService.listarEmpresas();
		
		return getViewList();
	}

	public String getViewList() {
		return Constants.MANT_EMPRESA_FORM_LIST_PAGE;
	}

	
	
	public EmpresaSie getNuevo() {
		return nuevo;
	}

	
	public void setNuevo(EmpresaSie nuevo) {
		this.nuevo = nuevo;
	}

	


	

	public void setEmpresaList(List<EmpresaSie> empresaList) {
		this.empresaList = empresaList;
	}

	/**
	 * @return the objEmpresaSie
	 */
	public EmpresaSie getObjEmpresaSie() {
		return objEmpresaSie;
	}


	/**
	 * @param objEmpresaSie the objEmpresaSie to set
	 */
	public void setObjEmpresaSie(EmpresaSie objEmpresaSie) {
		this.objEmpresaSie = objEmpresaSie;
	}


	/**
	 * @return the empresaService
	 */
	public EmpresaService getEmpresaService() {
		return empresaService;
	}


	/**
	 * @param empresaService the empresaService to set
	 */
	public void setEmpresaService(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}


	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}


	/**
	 * @param idEstadoGeneral the idEstadoGeneral to set
	 */
	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
	}
}
