package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.StreamedContent;

import com.edicsem.pe.sie.beans.EmpresaDTO;
import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
import com.edicsem.pe.sie.util.redirections.Redirections;

@ManagedBean(name = "mantenimientoCargoEmpleadoFormAction")
@SessionScoped
public class MantenimientoCargoEmpleadoFormAction extends
		BaseMantenimientoAbstractAction {
	private String mensaje;
	public String idcargoempleado;
	public String descripcion;
	public static Log log = LogFactory.getLog(MantenimientoCargoEmpleadoSearchAction.class);
	private StreamedContent image;
	
	
	private CargoEmpleadoSie objCargoEmpleadoSie;
	private DataModel<CargoEmpleadoSie> CargoEmpleadomodel;
	private CargoEmpleadoSie selectedCargoEmpleado;
	
	private boolean editMode;
	private CargoEmpleadoSie nuevo;
	private int idEstadoGeneral, idc;
	private boolean newRecord = false;

	@EJB
	private CargoEmpleadoService objCargoEmpleadoService;

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

	@EJB
	private EstadogeneralService objEstadoGeneralService;

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

	public StreamedContent getImage() {
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public CargoEmpleadoSie getObjCargoEmpleadoSie() {
		return objCargoEmpleadoSie;
	}

	public void setObjCargoEmpleadoSie(CargoEmpleadoSie objCargoEmpleadoSie) {
		this.objCargoEmpleadoSie = objCargoEmpleadoSie;
	}

	public DataModel<CargoEmpleadoSie> getCargoEmpleadomodel() throws Exception {

		CargoEmpleadomodel = new ListDataModel<CargoEmpleadoSie>(
				objCargoEmpleadoService.listarCargoEmpleado());
		return CargoEmpleadomodel;
	}

	public void setCargoEmpleadomodel(
			DataModel<CargoEmpleadoSie> cargoEmpleadomodel) {
		CargoEmpleadomodel = cargoEmpleadomodel;
	}

	public CargoEmpleadoSie getSelectedCargoEmpleado() {
		return selectedCargoEmpleado;
	}

	public void setSelectedCargoEmpleado(CargoEmpleadoSie selectedCargoEmpleado) {
		this.selectedCargoEmpleado = selectedCargoEmpleado;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public void setNuevo(CargoEmpleadoSie nuevo) {
		this.nuevo = nuevo;
	}

	public CargoEmpleadoSie getNuevo() {
		return nuevo;
	}

	public static Log getLog() {
		return log;
	}

	public static void setLog(Log log) {
		MantenimientoCargoEmpleadoSearchAction.log = log;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

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

	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		// estadosItems = new ArrayList<SelectItem>();
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		objCargoEmpleadoSie.setDescripcion("");
		nuevo = new CargoEmpleadoSie();
	}



	/*
	 * 
	 * public String insertar() throws Exception {
	 * 
	 * try { if (log.isInfoEnabled()) {
	 * log.info("Entering my method 'insertar()'"); } log.info(" ------ "+
	 * idEstadoGeneral);
	 * objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService
	 * .findEstadogeneral(idEstadoGeneral));
	 * 
	 * if (objCargoEmpleadoSie.isNewRecord()) { log.info("insertando..... "); //
	 * insertarValidation(objCargoEmpleadoSie);
	 * objCargoEmpleadoService.insertarCargoEmpleado(objCargoEmpleadoSie);
	 * log.info("insertando..... "); objCargoEmpleadoSie.setNewRecord(false);
	 * 
	 * } else { log.info("objCargoEmpleadoSie.isNewRecord() : " +
	 * objCargoEmpleadoSie.isNewRecord()); }
	 * objCargoEmpleadoSie.setDescripcion(""); //Agregen esto a tus
	 * redirecciones parece que esta referenciando a otra cosa verifiquen a
	 * donde estan //llenando los datos
	 * 
	 * } catch (Exception e) { e.printStackTrace(); descripcion =
	 * e.getMessage(); msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
	 * Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
	 * log.error(e.getMessage());
	 * FacesContext.getCurrentInstance().addMessage(null, msg); }
	 * objCargoEmpleadoSie=new CargoEmpleadoSie(); return getViewList(); }
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #agregar()
	 */

	public String agregar(){
		log.info("agregar()");
		setNewRecord(true);
		return insertar();
	}
	
	public String update() throws Exception {
		log.info("update()");
		setNewRecord(false);
		return insertar();
	}
	
	public String insertar(){
		log.info("insertar() " + isNewRecord());

/*  --->  */objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService
				.findEstadogeneral(idEstadoGeneral));
		/*Esto se setea cuando pertenece a una segunda tabla (--->)*/
		log.info("seteo()");
		try {

			if (isNewRecord()) {/*cuando esta vacio () es true*/

				objCargoEmpleadoService
						.insertarCargoEmpleado(objCargoEmpleadoSie);
			} else {

				objCargoEmpleadoSie.setIdcargoempleado(Integer
						.parseInt(getIdcargoempleado()));
				objCargoEmpleadoSie.setDescripcion(getDescripcion());
				objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService
						.findEstadogeneral(getIdEstadoGeneral()));
				log.info("----->>>"
						+ objCargoEmpleadoSie.getTbEstadoGeneral()
								.getIdestadogeneral()
						+ objCargoEmpleadoSie.getDescripcion());

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
		return getViewList();
	}

	public String getMensaje() {
		return mensaje;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	
	public String getViewList() {
		// TODO Auto-generated method stub
		return "mantenimientoCargoEmpleadoFormList";
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
	
	
	
	
	public void buscarCargoEmpleado(ActionEvent ae) {
		// /Busca el objeto por ese codigo ID
		// objCargoEmpleadoSie=new CargoEmpleadoSie() ;
		int parametroObtenido;
		try {
			log.info("llego");

			parametroObtenido = Integer.parseInt(ae.getComponent()
					.getAttributes().get("idcargoempleado").toString());
			log.info("probarCamapana1 : " + parametroObtenido);
			// se busca el objeto campaña
			CargoEmpleadoSie c = objCargoEmpleadoService
					.buscarCargoEmpleado(parametroObtenido);
			log.info(" id cargo " + c.getIdcargoempleado() + " des "
					+ c.getDescripcion());
			setIdcargoempleado(c.getIdcargoempleado().toString());
			setDescripcion(c.getDescripcion());
			setIdEstadoGeneral(c.getTbEstadoGeneral().getIdestadogeneral());
			log.info("f****************   " + getDescripcion());

		} catch (Exception e) {
			System.out.println("error:" + e.getMessage());
		}
	}

	/*
	 * public void buscarCargoEmpleadoDESHABILITAR(ActionEvent ae){
	 * 
	 * ///Busca el objeto por ese codigo ID //objCargoEmpleadoSie=new
	 * CargoEmpleadoSie() ; int parametroObtenido ; try { log.info("llego");
	 * 
	 * parametroObtenido =
	 * Integer.parseInt(ae.getComponent().getAttributes().get
	 * ("idcargoempleado").toString()); log.info("probarCamapana1 : " +
	 * parametroObtenido); //se busca el objeto campaña CargoEmpleadoSie c=
	 * objCargoEmpleadoService.buscarCargoEmpleado(parametroObtenido);
	 * log.info(" id cargo " + c.getIdcargoempleado()+ " des " +
	 * c.getDescripcion() );
	 * setIdcargoempleado(c.getIdcargoempleado().toString());
	 * setDescripcion(c.getDescripcion());
	 * setIdEstadoGeneral(c.getTbEstadoGeneral().getIdestadogeneral());
	 * log.info("f****************   " + getDescripcion());
	 * 
	 * } catch (Exception e) { System.out.println("error:" + e.getMessage()); }
	 * }
	 */

	public String updateDeshabilitar(ActionEvent ae) throws Exception {

		objCargoEmpleadoSie = new CargoEmpleadoSie();
		int parametroObtenido;

		CargoEmpleadoSie c = new CargoEmpleadoSie();

		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'updateDESHABILITAR()'");
			}

			parametroObtenido =  getIdc();
			log.info(" ------>>>>>>aqui cactura el parametro ID " + parametroObtenido);
			
			
			c = objCargoEmpleadoService.buscarCargoEmpleado(parametroObtenido);
			log.info(" ------lechuga>" + c.getDescripcion() + " " + c.getIdcargoempleado());

//			objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(2));
//			objCargoEmpleadoSie.setIdcargoempleado(c.getIdcargoempleado());
//			objCargoEmpleadoSie.setDescripcion(c.getDescripcion());
//			log.info("-----AJI>>>"
//					+ objCargoEmpleadoSie.getTbEstadoGeneral()
//							.getIdestadogeneral());
//
//			log.info("actualizando ESTADO..... ");
//
//			objCargoEmpleadoService.actualizarCargoEmpleado(objCargoEmpleadoSie);
//			log.info("actualizando..... ");
//			

		} catch (Exception e) {
			e.printStackTrace();
			descripcion = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		
		return getViewList();
	}

	public int getIdc() {
		log.info("IDC *** " +idc ) ;
		return idc;
	}

	public void setIdc(int idc) {
		this.idc = idc;
	}

}
