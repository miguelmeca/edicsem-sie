/**
 * @author FUCKING
 *
 */

package com.edicsem.pe.sie.client.action;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.StreamedContent;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.DetalleCarEmpService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.util.FaceMessage.FaceMessage;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "mantenimientoCargoEmpleadoFormAction")
@SessionScoped
public class MantenimientoCargoEmpleadoFormAction extends
		BaseMantenimientoAbstractAction {

	public String idcargoempleado;
	public String descripcion;
	private int idEstadoGeneral, idc;
	private StreamedContent image;
	private String mensaje;
	private CargoEmpleadoSie nuevo;
	private CargoEmpleadoSie objCargoEmpleadoSie;
	private CargoEmpleadoSie selectedCargoEmpleado;
	private boolean newRecord = false;
	private boolean editMode;
	
	@ManagedProperty(value = "#{mantenimientoCargoEmpleadoSearchAction}")
	private MantenimientoCargoEmpleadoSearchAction mantenimientoCargoEmpleadoSearch;

	private static Log log = LogFactory.getLog(MantenimientoCargoEmpleadoSearchAction.class);

	@EJB
	private CargoEmpleadoService objCargoEmpleadoService;

	@EJB
	private EstadogeneralService objEstadoGeneralService;
	
	@EJB
	private DetalleCarEmpService objDetalleCarEmpService;

	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		nuevo = new CargoEmpleadoSie();
	}

	public String agregar() {
		log.info("agregar()");
		editMode = true;
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		setNewRecord(true);
		return getViewList();
	}

	public String update() throws Exception {
		log.info("update()" + objCargoEmpleadoSie.getIdcargoempleado());
		CargoEmpleadoSie c = objCargoEmpleadoService.buscarCargoEmpleado(objCargoEmpleadoSie.getIdcargoempleado());
		log.info(" id cargo " + c.getIdcargoempleado() + " des "+ c.getDescripcion());		
		setIdcargoempleado(c.getIdcargoempleado().toString());
		setDescripcion(c.getDescripcion());
		setIdEstadoGeneral(c.getTbEstadoGeneral().getIdestadogeneral());
		setNewRecord(false);
		editMode = false;
		return getViewList();

	}

	public String insertar() {

		log.info("insertar() " + isNewRecord() + " desc "
				+ objCargoEmpleadoSie.getDescripcion());
		/* ---> */objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(1));
		/* Esto se setea cuando pertenece a una segunda tabla (--->) */
		try {

			if (isNewRecord()) {

				objCargoEmpleadoSie.getDescripcion();

				int error = 0;
				List<CargoEmpleadoSie> lista = mantenimientoCargoEmpleadoSearch.getCargoEmpleadomodel();
				for (int i = 0; i < lista.size(); i++) {
					CargoEmpleadoSie a = lista.get(i);
					if (a.getDescripcion().equalsIgnoreCase(objCargoEmpleadoSie.getDescripcion())) {
						log.info("Error ... Ya se encuentra un cargo igual");
						error = 1;
						break;
					}
				}

				if (error == 0) {
					objCargoEmpleadoService.insertarCargoEmpleado(objCargoEmpleadoSie);
				} else {
					// Mostrar Validacion
					log.info("mensaje de error");
				}
				log.info("aqui validadndo si existe o no");

			} else {

				objCargoEmpleadoSie.setIdcargoempleado(Integer.parseInt(getIdcargoempleado()));

				objCargoEmpleadoSie.setDescripcion(getDescripcion());
				objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(getIdEstadoGeneral()));

				log.info("----->>>"
						+ objCargoEmpleadoSie.getTbEstadoGeneral().getIdestadogeneral()+ objCargoEmpleadoSie.getDescripcion());
				log.info("actualizando..... ");

				objCargoEmpleadoService.actualizarCargoEmpleado(objCargoEmpleadoSie);				
				log.info("actualizando..... ");				
				log.info("objCargoEmpleadoSie.isNewRecord() : ");	
			}
		} catch (Exception e) {

			e.printStackTrace();
			descripcion = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		return mantenimientoCargoEmpleadoSearch.listar();
	}

	/**
	 * @return the mayuscula
	 */

	/**
	 * @param ae
	 * @return
	 * @throws Exception
	 */
	public String updateDeshabilitar() throws Exception {

		objCargoEmpleadoSie = new CargoEmpleadoSie();
		int parametroObtenido;
		CargoEmpleadoSie c = new CargoEmpleadoSie();

		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'updateDESHABILITAR()'");
			}

			parametroObtenido = getIdc();
			log.info(" ------>>>>>>aqui cactura el parametro ID "+ parametroObtenido);

			
				if(verificarEmpleadoConCargo(parametroObtenido)){//true - no hay empleado con cargo, tonce procede
					
					c = objCargoEmpleadoService.buscarCargoEmpleado(parametroObtenido);
					log.info(" ------Android>" + c.getDescripcion() + " "+ c.getIdcargoempleado());

					
					
					objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(2));
					objCargoEmpleadoSie.setIdcargoempleado(c.getIdcargoempleado());
					objCargoEmpleadoSie.setDescripcion(c.getDescripcion());

					log.info("-----ID estado general>>>"	+ objCargoEmpleadoSie.getTbEstadoGeneral().getIdestadogeneral());
					log.info("actualizando ESTADO..... ");

					objCargoEmpleadoService.actualizarCargoEmpleado(objCargoEmpleadoSie);
					log.info("actualizando..... ");	
					
					
				}	
				
				else {
					
					FaceMessage.FaceMessageError("ALERTA", "El cargo no se puede elminar ya que se encontraron usuarios con ese cargo");
				}	

		} catch (Exception e) {
			e.printStackTrace();
			descripcion = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		return mantenimientoCargoEmpleadoSearch.listar();
	}

	
	
	private boolean verificarEmpleadoConCargo(int idcargo) {
		  	
		
		return objDetalleCarEmpService.verificarEmpleadoConCargo(idcargo) ;
				
		
		
		



		
		
		
		
	}

	/*
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */


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

	/**
	 * @return
	 */
	public int getIdc() {
		log.info("IDC *** " + idc);
		return idc;
	}

	/**
	 * @param idc
	 */
	public void setIdc(int idc) {
		this.idc = idc;
	}

	/**
	 * @return the idcargoempleado
	 */
	public String getIdcargoempleado() {
		return idcargoempleado;
	}

	/**
	 * @param idcargoempleado
	 *            the idcargoempleado to set
	 */
	public void setIdcargoempleado(String idcargoempleado) {
		this.idcargoempleado = idcargoempleado;
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
	public StreamedContent getImage() {
		return image;
	}

	/**
	 * @param image
	 */
	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public CargoEmpleadoSie getObjCargoEmpleadoSie() {
		return objCargoEmpleadoSie;
	}

	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
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

	/**
	 * @return
	 */
	public boolean isEditMode() {
		return editMode;
	}

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
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return
	 */
	public CargoEmpleadoService getObjCargoEmpleadoService() {
		return objCargoEmpleadoService;
	}

	public void setObjCargoEmpleadoService(
			CargoEmpleadoService objCargoEmpleadoService) {
		this.objCargoEmpleadoService = objCargoEmpleadoService;
	}

	public MantenimientoCargoEmpleadoFormAction() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
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

}
