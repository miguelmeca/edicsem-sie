package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="empresaEmpleProducSearchAction")
@SessionScoped
public class MantenimientoEmpresaEmpleadoProductoSearchAction extends BaseMantenimientoAbstractAction{

	public static Log log = LogFactory.getLog(MantenimientoEmpresaEmpleadoProductoSearchAction.class);	
	
	
//	private List<EmpresaSie> empresaList;
//	private EmpresaSie objEmpresaSie;
//	private EmpresaSie nuevo;
//	private int idEstadoGeneral;
//aqui estan los Datos de Lista Empleados POr Id de Empresa
	private List<EmpleadoSie> empleadoList;
	private EmpleadoSie objEmpleadoSie;
	private EmpleadoSie nuevoempleado;
	private int idCargo;	
	
	@EJB
	private EmpresaService empresaService;	
	@EJB
	private EmpleadoSieService objEmpleadoSieService;
	
	// aqui listare los empleados
	public List<EmpleadoSie> getEmpleadoList() throws Exception{
		return empleadoList;
	}
		
	public void init() {
		log.info("init()");
		objEmpleadoSie= new EmpleadoSie();
		objEmpleadoSie.setNombresCompletos("");
		objEmpleadoSie.setNumdocumento("");
		objEmpleadoSie.setNombreemp("");
		nuevoempleado = new EmpleadoSie();
			}

	/**
	 * @return the empleadoList
	 */
	
	/**
	 * @param empleadoList the empleadoList to set
	 */
	public void setEmpleadoList(List<EmpleadoSie> empleadoList) {
		this.empleadoList = empleadoList;
	}
//	Lista de Empleados con Relacion a la Empresa que pertenece
	
	public String listarEmpleadosXempresa(){
		log.info("listarEmpresas con relacion a EMpleados ");
		log.info("ID JOSELO"+idCargo);
		
	
		empleadoList = objEmpleadoSieService.listarEmpleadoxEmpresas(idCargo);
		
		return getViewList();
	}
	

	public String getViewList() {
		
		
		return Constants.MANT_EMPRESA_EMPLEADO_PRODUCTO_FORM_LIST_PAGE;
	}
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
//	Lista de Empresas
//	public String listar() {
//		log.info("listarEmpresas 'MantenimientoEmpresaSearchAction' ");
//		empresaList = empresaService.listarEmpresas();
//		
//		return getViewList();
//	}
//
//	public String getViewList() {
//		
//		
//		return Constants.MANT_EMPRESA_FORM_LIST_PAGE;
//	}
//

	
	
	
//	public EmpresaSie getNuevo() {
//		return nuevo;
//	}
//
//	
//	public void setNuevo(EmpresaSie nuevo) {
//		this.nuevo = nuevo;
//	}
//
//	


	
//
//	public void setEmpresaList(List<EmpresaSie> empresaList) {
//		this.empresaList = empresaList;
//	}
//
//	/**
//	 * @return the objEmpresaSie
//	 */
//	public EmpresaSie getObjEmpresaSie() {
//		return objEmpresaSie;
//	}
//

//	/**
//	 * @param objEmpresaSie the objEmpresaSie to set
//	 */
//	public void setObjEmpresaSie(EmpresaSie objEmpresaSie) {
//		this.objEmpresaSie = objEmpresaSie;
//	}


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

//
//	/**
//	 * @return the idEstadoGeneral
//	 */
//	public int getIdEstadoGeneral() {
//		return idEstadoGeneral;
//	}
//
//
//	/**
//	 * @param idEstadoGeneral the idEstadoGeneral to set
//	 */
//	public void setIdEstadoGeneral(int idEstadoGeneral) {
//		this.idEstadoGeneral = idEstadoGeneral;
//	}

	/**
	 * @return the objEmpleadoSie
	 */
	public EmpleadoSie getObjEmpleadoSie() {
		return objEmpleadoSie;
	}

	/**
	 * @param objEmpleadoSie the objEmpleadoSie to set
	 */
	public void setObjEmpleadoSie(EmpleadoSie objEmpleadoSie) {
		this.objEmpleadoSie = objEmpleadoSie;
	}

	/**
	 * @return the nuevoempleado
	 */
	public EmpleadoSie getNuevoempleado() {
		return nuevoempleado;
	}

	/**
	 * @param nuevoempleado the nuevoempleado to set
	 */
	public void setNuevoempleado(EmpleadoSie nuevoempleado) {
		this.nuevoempleado = nuevoempleado;
	}



	/**
	 * @return the idCargo
	 */
	public int getIdCargo() {
		return idCargo;
	}



	/**
	 * @param idCargo the idCargo to set
	 */
	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}
	
	
	
}
