/**
 * @author FUCKING
 *
 */

package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.ContratoEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.util.FaceMessage.FaceMessage;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "mantenimientoCargoEmpleadoFormAction")
@SessionScoped
public class MantenimientoCargoEmpleadoFormAction extends
		BaseMantenimientoAbstractAction {
	
	private String descripcionUpdate;
	private String mensaje;
	private CargoEmpleadoSie objCargoEmpleadoSie;
	private CargoEmpleadoSie selectedCargoEmpleado;
	private boolean newRecord = false;
	
	private List<EmpleadoSie> cargoempleadoList;
	
	@ManagedProperty(value = "#{mantenimientoCargoEmpleadoSearchAction}")
	private MantenimientoCargoEmpleadoSearchAction mantenimientoCargoEmpleadoSearch;

	private static Log log = LogFactory.getLog(MantenimientoCargoEmpleadoSearchAction.class);

	@EJB
	private CargoEmpleadoService objCargoEmpleadoService;
	@EJB
	private EmpleadoSieService objEmpleadoSieService;
	@EJB
	private EstadogeneralService objEstadoGeneralService;
	@EJB
	private ContratoEmpleadoService objContratoEmpleadoService;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init()");
		objCargoEmpleadoSie = new CargoEmpleadoSie();
	}

	public MantenimientoCargoEmpleadoFormAction() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		descripcionUpdate=null;
		setNewRecord(true);
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()");
		descripcionUpdate = objCargoEmpleadoSie.getDescripcion();
		setNewRecord(false);
		return getViewList();
	}
	
	public String derivarEmpleado() throws Exception {
		try {
			List<ContratoEmpleadoSie> lista= new ArrayList<ContratoEmpleadoSie>() ;
			for (EmpleadoSie miempleado : cargoempleadoList ) {
				lista = objContratoEmpleadoService.listarCargoXEmp(miempleado.getIdempleado());
				for (ContratoEmpleadoSie contratoEmpleadoSie : lista) {
					contratoEmpleadoSie.setTbCargoempleado(objCargoEmpleadoService.buscarCargoEmpleado(191));
					objContratoEmpleadoService.updateContratoEmpleado(contratoEmpleadoSie);
				}
			}
			FaceMessage.FaceMessageError("Se Derivó correctamente",lista.size()+"fueron derivados a 'Sin Cargo', por favor prosiga a eliminar dicho Cargo Empleado");
	 	} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
			Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return Constants.MANT_CARGO_EMPLEADO_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() {
		log.info("insertar()");
		mensaje =null;
		try {
			int error = 0;
			List<CargoEmpleadoSie> lista = mantenimientoCargoEmpleadoSearch.getCargoEmpleadomodel();
			for (int i = 0; i < lista.size(); i++) {
				if(descripcionUpdate!=null){
					if (lista.get(i).getDescripcion().equalsIgnoreCase(objCargoEmpleadoSie.getDescripcion().trim())&&
						(!descripcionUpdate.equalsIgnoreCase(objCargoEmpleadoSie.getDescripcion().trim()))) {
						log.info("Error ... Ya se encuentra un cargo igual");
						mensaje ="Existe un cargo con la misma descripción";
						error = 1;
						break;
					}
				}
				else if ( lista.get(i).getDescripcion().equalsIgnoreCase(objCargoEmpleadoSie.getDescripcion().trim())) {
					log.info("Error ... Ya se encuentra un cargo igual");
					mensaje ="Existe un cargo con la misma descripción";
					error = 1;
					break;
				}
			}
			if (error == 0) {
				if (isNewRecord()) {
					mensaje="Se registró correctamente";
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_REGISTRO_TITULO, mensaje);
					objCargoEmpleadoSie.setDescripcion(	objCargoEmpleadoSie.getDescripcion().trim());
					objCargoEmpleadoService.insertarCargoEmpleado(objCargoEmpleadoSie);
				}
				else {
					mensaje="Se actualizó correctamente";
					objCargoEmpleadoSie.setDescripcion(objCargoEmpleadoSie.getDescripcion().trim());
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_REGISTRO_TITULO, mensaje);
					objCargoEmpleadoService.actualizarCargoEmpleado(objCargoEmpleadoSie);
				}
			}else {
				log.info("mensaje de error");
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
			Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return mantenimientoCargoEmpleadoSearch.listar();
	}
	
	/**
	 * @return the objCargoEmpleadoSie
	 */
	public CargoEmpleadoSie getObjCargoEmpleadoSie() {
		return objCargoEmpleadoSie;
	}

	/**DerivarEmpleado
	 * @param ae
	 * @return
	 * @throws Exception
	 */
	public String updateDeshabilitar() throws Exception {
		mensaje =null;
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'updateDeshabilitar()'");
			}
			if(verificarEmpleadoConCargo(objCargoEmpleadoSie.getIdcargoempleado())){
				objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(2));
				log.info("actualizando ESTADO..... ");
				objCargoEmpleadoService.actualizarCargoEmpleado(objCargoEmpleadoSie);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_DESHABILITAR_TITULO, mensaje);
			}
			else{
				if (verificarEmpleadoConCargo(objCargoEmpleadoSie.getIdcargoempleado())== false) {
					listarEmpleadosXcargo(objCargoEmpleadoSie.getIdcargoempleado());
					FaceMessage.FaceMessageError("Primero derive los empleados de la lista haciendo clic en el boton 'DERIVAR' y luego prosiga eliminar el cargo","");
				return Constants.MANT_CARGOEMPLEADO_FORM_LIST_PAGE;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
			Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return mantenimientoCargoEmpleadoSearch.listar();
	}
	
	private void listarEmpleadosXcargo(int parametroObtenido) {
		log.info("entrando en el bean listarEmpleadosXcargo  "+parametroObtenido);	
		cargoempleadoList = objEmpleadoSieService.listarEmpleadosXCargo(parametroObtenido);		
	}

	private boolean verificarEmpleadoConCargo(int idcargo) {
		return objContratoEmpleadoService.verificarEmpleadoConCargo(idcargo);
	}
	
	public String getMensaje() {
	return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setObjCargoEmpleadoSie(CargoEmpleadoSie objCargoEmpleadoSie) {
		this.objCargoEmpleadoSie = objCargoEmpleadoSie;
	}

	public CargoEmpleadoSie getSelectedCargoEmpleado() {
		return selectedCargoEmpleado;
	}

	public void setSelectedCargoEmpleado(CargoEmpleadoSie selectedCargoEmpleado) {
		this.selectedCargoEmpleado = selectedCargoEmpleado;
	}

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
	 * @return the mantenimientoCargoEmpleadoSearch
	 */
	public MantenimientoCargoEmpleadoSearchAction getMantenimientoCargoEmpleadoSearch() {
		return mantenimientoCargoEmpleadoSearch;
	}

	/**
	 * @param mantenimientoCargoEmpleadoSearch
	 *            the mantenimientoCargoEmpleadoSearch to set
	 */
	public void setMantenimientoCargoEmpleadoSearch(
			MantenimientoCargoEmpleadoSearchAction mantenimientoCargoEmpleadoSearch) {
		this.mantenimientoCargoEmpleadoSearch = mantenimientoCargoEmpleadoSearch;
	}

	/**
	 * @return the descripcionUpdate
	 */
	public String getDescripcionUpdate() {
		return descripcionUpdate;
	}

	/**
	 * @param descripcionUpdate the descripcionUpdate to set
	 */
	public void setDescripcionUpdate(String descripcionUpdate) {
		this.descripcionUpdate = descripcionUpdate;
	}

	/**
	 * @return the cargoempleadoList
	 */
	public List<EmpleadoSie> getCargoempleadoList() {
		return cargoempleadoList;
	}

	/**
	 * @param cargoempleadoList the cargoempleadoList to set
	 */
	public void setCargoempleadoList(List<EmpleadoSie> cargoempleadoList) {
		this.cargoempleadoList = cargoempleadoList;
	}
}
