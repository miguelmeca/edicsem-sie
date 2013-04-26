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

import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.service.facade.DetallePaqueteService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.PaqueteService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "mantenimientoPaqueteBiblicoFormAction")
@SessionScoped
public class MantenimientoPaqueteBiblicoFormAction extends BaseMantenimientoAbstractAction {
	
	// PAQUETE BIBLICO
	public String mensaje;
	private PaqueteSie objPaqueteSie;
	private DetPaqueteSie objDetPaqueteSie;
	private boolean newRecord = false;
	private List<PaqueteSie> lista;
	
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
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoPaqueteBiblicoFormhAction'");	
		objDetPaqueteSie = new DetPaqueteSie();		
		objPaqueteSie = new PaqueteSie();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		objDetPaqueteSie = new DetPaqueteSie();	
		objPaqueteSie = new PaqueteSie();
		setNewRecord(true);
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()  " );	
		setNewRecord(false);
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() {
		log.info("insertar()");
		String paginaRetorno="";
		mensaje =null;
		
		try {
			log.info("Validar si existe el codigo " + objPaqueteSie.getCodpaquete());
			
			lista = mantenimientoPaqueteBiblicoSearchAction.getDetPaqueteList();
			int error = 0;
			if(isNewRecord()){
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getCodpaquete().equalsIgnoreCase(objPaqueteSie.getCodpaquete()) ) {
					log.info("Error ... Ya se encuentra un paquete biblico");
					mensaje ="Ya se encuentra un paquete biblico con el mismo nombre";
					paginaRetorno =mantenimientoPaqueteBiblicoSearchAction.listar();
					error = 1;
					break;
					}				
				}
			}
			if (error == 0) {
				if(isNewRecord()){
					objPaqueteService.insertPaquete(objPaqueteSie);
					mensaje ="Se registró el paquete correctamente";
				}
				else{
					objPaqueteService.updatePaquete(objPaqueteSie);
					mensaje="Se actualizó el Paquete correctamente";
				}
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			}else {
				log.info("mensaje de error");
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			}
		}catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			
		}
		objPaqueteSie = new  PaqueteSie();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return mantenimientoPaqueteBiblicoSearchAction.listar();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String updateDeshabilitarPaquete() throws Exception {
		mensaje =null;
		objPaqueteSie = new PaqueteSie();

		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'updateDESHABILITAR()'");
			
			}
			objPaqueteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(61));
			objPaqueteService.updatePaquete(objPaqueteSie);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_DESHABILITAR_TITULO, mensaje);
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}
		objPaqueteSie = new PaqueteSie();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return mantenimientoPaqueteBiblicoSearchAction.listar();
	}
	
	private boolean verificarPaquetesicontieneProductos(int parametroObtenido) {
		return objDetallePaqueteService.verificarPaquetesicontieneProductos(parametroObtenido);
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
	
}
