package com.edicsem.pe.sie.client.action.mantenimiento;

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
import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.service.facade.DetallePaqueteService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.PaqueteService;
import com.edicsem.pe.sie.util.FaceMessage.FaceMessage;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "mantenimientoPaqueteBiblicoFormAction")
@SessionScoped
public class MantenimientoPaqueteBiblicoFormAction extends 
		BaseMantenimientoAbstractAction {

	
	

	// PAQUETE BIBLICO
	public String mensaje;
	private PaqueteSie nuevo;

	private PaqueteSie objPaqueteSie;
	private DetPaqueteSie objDetPaqueteSie;
	private boolean newRecord = false;
	private boolean editMode;
	private int idc, idEstadoGeneral;
	public String idpaquete, codpaquete;
	
	
	@ManagedProperty(value = "#{mantenimientoPaqueteBiblicoSearchAction}")
	private MantenimientoPaqueteBiblicoSearchAction mantenimientoPaqueteBiblicoSearchAction;
	

	
	private Log log = LogFactory.getLog(MantenimientoPaqueteBiblicoFormAction.class);
	
	@EJB
	private PaqueteService objPaqueteService;
	
	@EJB
	private DetallePaqueteService objDetallePaqueteService;
	
	@EJB
	private EstadogeneralService objEstadoGeneralService;
	
	
	
	public MantenimientoPaqueteBiblicoFormAction() {
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

		
		//PAQUETE BIBLICO
		log.info("Inicializando el Constructor de 'MantenimientoPaqueteBiblicoFormhAction'");	
		objDetPaqueteSie = new DetPaqueteSie();		
		objPaqueteSie = new PaqueteSie();
		nuevo = new PaqueteSie();
		
		
	}


	
	//AGREGAR Y UPDATE
	public String agregar() {
		log.info("agregar()");
	
		editMode = true;
		objPaqueteSie = new PaqueteSie();
		setNewRecord(true);
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()  " );

log.info(" id cargo " +objPaqueteSie.getIdpaquete() + " codigo paquete "+ objPaqueteSie.getCodpaquete());		


objPaqueteSie = objPaqueteService.findPaquete(objPaqueteSie.getIdpaquete());
setIdpaquete(objPaqueteSie.getIdpaquete().toString());
setIdEstadoGeneral(objPaqueteSie.getTbEstadoGeneral().getIdestadogeneral());
		
		
		setNewRecord(false);
		editMode = false;
		return getViewList();
	}
	
	//insertar nuevo Paquete Biblico
	public String insertar() {
		mensaje =null;
		log.info("insertar()");
		
		objPaqueteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(60));
		try {
			
			log.info("aqui validadndo si existe o no" + objPaqueteSie.getCodpaquete());
			int error = 0;
			List<PaqueteSie> lista = mantenimientoPaqueteBiblicoSearchAction.getDetPaqueteList();
			for (int i = 0; i < lista.size(); i++) {
				PaqueteSie a = lista.get(i);
				if (a.getCodpaquete().equalsIgnoreCase(objPaqueteSie.getCodpaquete()) && a.getDescripcionpaquete().equalsIgnoreCase(objPaqueteSie.getDescripcionpaquete())) {
					log.info("Error ... Ya se encuentra un paquete biblico");
					mensaje ="Ya se encuentra un paquete biblico con el mismo nombre";
					error = 1;
					break;
				}
				
			}
			if (error == 0) {
						
			if(isNewRecord()){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						Constants.MESSAGE_REGISTRO_TITULO, mensaje);
			
				objPaqueteService.insertPaquete(objPaqueteSie);
				log.info("insertando "  );
			}
			else{
				

objPaqueteSie.setIdpaquete(Integer.parseInt(getIdpaquete()));
objPaqueteSie.setCodpaquete(objPaqueteSie.getCodpaquete());
objPaqueteSie.setDescripcionpaquete(objPaqueteSie.getDescripcionpaquete());
objPaqueteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(getIdEstadoGeneral()));
log.info("actualizando..... ");	
msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
		Constants.MESSAGE_REGISTRO_TITULO, mensaje);
	objPaqueteService.updatePaquete(objPaqueteSie);
				mensaje="Se actualizó el factor";
				log.info("actualizado");
			}
			}else {
				log.info("mensaje de error");
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
						Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
		
		} 	FacesContext.getCurrentInstance().addMessage(null, msg);

}catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	
		objPaqueteSie = new  PaqueteSie();
		return mantenimientoPaqueteBiblicoSearchAction.listar();
	}

	
	
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String updateDeshabilitarPaquete() throws Exception {
		mensaje =null;

//		objCargoEmpleadoSie = new CargoEmpleadoSie();
		objPaqueteSie = new PaqueteSie();
		int parametroObtenido;
		PaqueteSie c = new PaqueteSie();
//		CargoEmpleadoSie c = new CargoEmpleadoSie();

		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'updateDESHABILITAR()'");
			
			}	
			parametroObtenido = getIdc();
			log.info(" ------>>>>>>aqui cactura el parametro ID "+ parametroObtenido);
			
			
					
					c = objPaqueteService.findPaquete(parametroObtenido);
					log.info(" ------Android>" + c.getCodpaquete() + " "+ c.getDescripcionpaquete());
					
//					
					
				
					objPaqueteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(61));
					objPaqueteSie.setIdpaquete(c.getIdpaquete());
					objPaqueteSie.setCodpaquete(c.getCodpaquete());
					objPaqueteSie.setDescripcionpaquete(c.getDescripcionpaquete());

					
					log.info("-----ID estado general>>>"	+ objPaqueteSie.getTbEstadoGeneral().getIdestadogeneral());
					log.info("actualizando ESTADO..... ");


					objPaqueteService.updatePaquete(objPaqueteSie);
					log.info("actualizando..... ");	
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
		Constants.MESSAGE_DESHABILITAR_TITULO, mensaje);
					mensaje ="Se deshabilito correctamente";
//				}	
		
					FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
//		objCargoEmpleadoSie = new CargoEmpleadoSie();
		objPaqueteSie = new PaqueteSie();
		return mantenimientoPaqueteBiblicoSearchAction.listar();
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
	 * @return the detPaqueteList
	 */

	

	/**
	 * @return the objPaqueteSie
	 */
	public PaqueteSie getObjPaqueteSie() {
		return objPaqueteSie;
	}

	/**
	 * @param objPaqueteSie the objPaqueteSie to set
	 */
	public void setObjPaqueteSie(PaqueteSie objPaqueteSie) {
		this.objPaqueteSie = objPaqueteSie;
	}

	/**
	 * @return the objDetPaqueteSie
	 */
	public DetPaqueteSie getObjDetPaqueteSie() {
		return objDetPaqueteSie;
	}

	/**
	 * @param objDetPaqueteSie the objDetPaqueteSie to set
	 */
	public void setObjDetPaqueteSie(DetPaqueteSie objDetPaqueteSie) {
		this.objDetPaqueteSie = objDetPaqueteSie;
	}

	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	/**
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * @param editMode the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * @return the log
	 */
	public Log getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(Log log) {
		this.log = log;
	}

	/**
	 * @return the mantenimientoPagoVentaSearchAction
	 */
	
	
	/**
	 * @return
	 */
	public int getIdc() {
		log.info("IDC *** " + idc);
		return idc;
	}

	/**
	 * @return the mantenimientoPaqueteBiblicoSearchAction
	 */
	public MantenimientoPaqueteBiblicoSearchAction getMantenimientoPaqueteBiblicoSearchAction() {
		return mantenimientoPaqueteBiblicoSearchAction;
	}

	/**
	 * @param mantenimientoPaqueteBiblicoSearchAction the mantenimientoPaqueteBiblicoSearchAction to set
	 */
	public void setMantenimientoPaqueteBiblicoSearchAction(
			MantenimientoPaqueteBiblicoSearchAction mantenimientoPaqueteBiblicoSearchAction) {
		this.mantenimientoPaqueteBiblicoSearchAction = mantenimientoPaqueteBiblicoSearchAction;
	}

	/**
	 * @param idc
	 */
	public void setIdc(int idc) {
		this.idc = idc;
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

	/**
	 * @return the idpaquete
	 */
	public String getIdpaquete() {
		return idpaquete;
	}

	/**
	 * @param idpaquete the idpaquete to set
	 */
	public void setIdpaquete(String idpaquete) {
		this.idpaquete = idpaquete;
	}

	/**
	 * @return the codpaquete
	 */
	public String getCodpaquete() {
		return codpaquete;
	}

	/**
	 * @param codpaquete the codpaquete to set
	 */
	public void setCodpaquete(String codpaquete) {
		this.codpaquete = codpaquete;
	}

	/**
	 * @return the nuevo
	 */
	public PaqueteSie getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(PaqueteSie nuevo) {
		this.nuevo = nuevo;
	}



	
}
