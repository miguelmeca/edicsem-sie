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

import com.edicsem.pe.sie.entity.DetEmpresaEmpleadoSie;
import com.edicsem.pe.sie.service.facade.DetEmpresaEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "detEmpresaEmpleadoForm")
@SessionScoped
public class MantenimientoDetEmpresaEmpleadoFormAction extends
		BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoDetEmpresaEmpleadoFormAction.class);
	private int idEmpresa;
	private String mensaje;
	private boolean newRecord = false, lider =false;
	private DetEmpresaEmpleadoSie objDetEmpresaEmpleadoSie;
	private List<String> empleadosList;
	
	@EJB
	private DetEmpresaEmpleadoService objDetEmpresaEmpleadoService;
	@EJB
	private EmpresaService objEmpresaService;
	@EJB
	private EstadogeneralService objEstadoGeneralService;
	
	public MantenimientoDetEmpresaEmpleadoFormAction() {
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
		log.info("Inicializando el Constructor de 'MantenimientoDetEmpresaEmpleadoFormAction'");
		objDetEmpresaEmpleadoSie = new DetEmpresaEmpleadoSie();
		empleadosList = new ArrayList<String>();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		setNewRecord(true);
		idEmpresa=0;
		empleadosList = new ArrayList<String>();
		return getViewMant();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()" );
		setNewRecord(false);
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() {
		mensaje=null;
		log.info("insertar() ");
		boolean registrar=true;
		try {
			if(empleadosList.size()!=1 && lider==true ){
				//verificar si selecciona lider que la cantidad de empleados seleccionados sólo sea 1
				
				mensaje = "Debe seleccionar un sólo líder para dicha empresa ";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
			} else {
				if (isNewRecord()) {
					log.info("insertar() :D ");
					/** Sie es lider se verifica si existe un lider anterior de esa empresa, solo para confimrar su cambio
					 * **/
					if(lider){
						log.info("true  ");
						List<DetEmpresaEmpleadoSie> listadet = objDetEmpresaEmpleadoService.listarDetEmpresaEmpleadoSie();
						for (int i = 0; i < listadet.size(); i++) {
							if(listadet.get(i).getTbEmpresa().getIdempresa()==idEmpresa &&
								listadet.get(i).getTbEmpleado().getIdempleado()== Integer.parseInt(empleadosList.get(0)) &&
								listadet.get(i).getLider().equals("S") ){
								log.info(" existe uno = ");
								mensaje ="El empleado : "+ listadet.get(i).getTbEmpleado().getNombresCompletos() +" es lider actualmente ";
								log.info(mensaje);
								registrar=false;
								break;
							}
							
							//actualizar el anterior lider diferente al actual
							else if(listadet.get(i).getTbEmpresa().getIdempresa()==idEmpresa &&
									listadet.get(i).getTbEmpleado().getIdempleado()!= Integer.parseInt(empleadosList.get(0)) &&
									listadet.get(i).getLider().equals("S") ){
									log.info(" para actualizar su estado "+listadet.get(i).getIdDetEmpresaEmpl());
									objDetEmpresaEmpleadoSie = objDetEmpresaEmpleadoService.findDetEmpresaEmpleadoSie(listadet.get(i).getIdDetEmpresaEmpl());
									objDetEmpresaEmpleadoSie.setLider("N");
									objDetEmpresaEmpleadoService.updateDetEmpresaEmpleadoSie(objDetEmpresaEmpleadoSie);
									for (int j = 0; j < listadet.size(); j++) {
										//si el empleado se encuentra en otra empresa
										log.info(""+ empleadosList.get(0)+ "        "+listadet.get(j).getTbEmpleado().getIdempleado());
									if(listadet.get(j).getTbEmpleado().getIdempleado()== Integer.parseInt(empleadosList.get(0))){
										log.info("son igualitos :D 1" );
										objDetEmpresaEmpleadoSie = objDetEmpresaEmpleadoService.findDetEmpresaEmpleadoSie(listadet.get(j).getIdDetEmpresaEmpl());
										objDetEmpresaEmpleadoSie.setLider("S");
										objDetEmpresaEmpleadoSie.setTbEmpresa(objEmpresaService.findEmpresa(idEmpresa));
										objDetEmpresaEmpleadoService.updateDetEmpresaEmpleadoSie(objDetEmpresaEmpleadoSie);
										mensaje ="El empleado : "+ objDetEmpresaEmpleadoSie.getTbEmpleado().getNombresCompletos() +" es lider actualmente ";
										registrar=false;
										break;
									}
								}
							}
							
							// ACTUALIZAR LA EMPRESA DE UN VENDEDOR QUE ESTA EN UNA EMPRESA
							else if(listadet.get(i).getTbEmpresa().getIdempresa()!=idEmpresa &&
									listadet.get(i).getTbEmpleado().getIdempleado()== Integer.parseInt(empleadosList.get(0)) ){
								objDetEmpresaEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(34));
								objDetEmpresaEmpleadoService.updateDetEmpresaEmpleadoSie(objDetEmpresaEmpleadoSie);
								registrar=false;
								break;
							}
						}
						if(registrar){
						log.info("a registarr ");
						objDetEmpresaEmpleadoService.insertDetEmpresaEmpleadoSie(empleadosList, idEmpresa, lider);
						}
					}else{
						log.info("no ");
					}
					
				}
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		objDetEmpresaEmpleadoSie = new DetEmpresaEmpleadoSie();
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
		return Constants.MANT_DET_EMPRESA_EMPLEADO_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_DET_EMPRESA_EMPLEADO_FORM_PAGE;
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
	 * @return the idEmpresa
	 */
	public int getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
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
	 * @return the objDetEmpresaEmpleadoSie
	 */
	public DetEmpresaEmpleadoSie getObjDetEmpresaEmpleadoSie() {
		return objDetEmpresaEmpleadoSie;
	}

	/**
	 * @param objDetEmpresaEmpleadoSie the objDetEmpresaEmpleadoSie to set
	 */
	public void setObjDetEmpresaEmpleadoSie(
			DetEmpresaEmpleadoSie objDetEmpresaEmpleadoSie) {
		this.objDetEmpresaEmpleadoSie = objDetEmpresaEmpleadoSie;
	}

	/**
	 * @return the empleadosList
	 */
	public List<String> getEmpleadosList() {
		return empleadosList;
	}

	/**
	 * @param empleadosList the empleadosList to set
	 */
	public void setEmpleadosList(List<String> empleadosList) {
		this.empleadosList = empleadosList;
	}

	/**
	 * @return the lider
	 */
	public boolean isLider() {
		return lider;
	}

	/**
	 * @param lider the lider to set
	 */
	public void setLider(boolean lider) {
		this.lider = lider;
	}
	
}
