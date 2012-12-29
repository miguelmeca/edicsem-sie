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

import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.entity.FactorSancionSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.service.facade.DetallePaqueteService;
import com.edicsem.pe.sie.service.facade.PaqueteService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "MantenimientoPaqueteBiblicoSearchAction")
@SessionScoped
public class MantenimientoPaqueteBiblicoSearchAction extends BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoPaqueteBiblicoSearchAction.class);
	
//	public String mensaje;
//	private boolean newRecord = false;
//	private List<SancionSie> detSancionList;
//	private List<DetSancionCargoSie> detSancionCargoList;
//	private int idFactor,idSancion, factor;
//	private int idcargo;
//	private DetSancionCargoSie objDetSancionCargo;
	
	// PAQUETE BIBLICO
	public String mensaje;
	private List<PaqueteSie> detPaqueteList;
	private List<DetPaqueteSie> detPaqueteBiblicoList;
	private PaqueteSie objPaqueteSie;
	private DetPaqueteSie objDetPaqueteSie;
	private boolean newRecord = false;
	

	
	@EJB
	private DetallePaqueteService objDetallePaqueteService;
	
	@EJB
	private PaqueteService objPaqueteService;
	
	
	
	public MantenimientoPaqueteBiblicoSearchAction() {
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
		log.info("Inicializando el Constructor de 'MantenimientoPaqueteBiblicoSearchAction'");
		detPaqueteList = new ArrayList<PaqueteSie>();
		detPaqueteBiblicoList = new ArrayList<DetPaqueteSie>();
		objDetPaqueteSie = new DetPaqueteSie();
		
		objPaqueteSie = new PaqueteSie();
		
		
	}
	public List<DetPaqueteSie> getDetPaqueteBiblicoList() {
		return detPaqueteBiblicoList;
	}


	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar 'MantenimientorSancionFormAction' ");
		detPaqueteList = objPaqueteService.listarPaquetes();
		

		if (detPaqueteList == null) {
			detPaqueteList = new ArrayList<PaqueteSie>();
		}
		return getViewList();
	}
	
	//AGREGAR Y UPDATE
	public String agregar() {
		log.info("agregar()");
		setNewRecord(true);
		objPaqueteSie = new PaqueteSie();
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
	
	//insertar nuevo Paquete Biblico
	public String insertar() throws Exception {
		log.info("insertar()");
		try {
			if(isNewRecord()){
				mensaje =Constants.MESSAGE_REGISTRO_TITULO;
//				
				objPaqueteService.insertPaquete(objPaqueteSie);
				log.info("insertando "  );
			}
			else{
				
				objPaqueteService.updatePaquete(objPaqueteSie);
				mensaje="Se actualizó el factor";
				log.info("actualizado");
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_INFO_TITULO, mensaje);
		
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objPaqueteSie = new  PaqueteSie();
		return getViewList();
	}

	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_PAQUETEBIBLICO_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_SANCION_FORM_PAGE;
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
	public List<PaqueteSie> getDetPaqueteList() {
		return detPaqueteList;
	}

	/**
	 * @param detPaqueteList the detPaqueteList to set
	 */
	public void setDetPaqueteList(List<PaqueteSie> detPaqueteList) {
		this.detPaqueteList = detPaqueteList;
	}

	/**
	 * @return the detPaqueteBiblicoList
	 */
	
	/**
	 * @param detPaqueteBiblicoList the detPaqueteBiblicoList to set
	 */
	public void setDetPaqueteBiblicoList(List<DetPaqueteSie> detPaqueteBiblicoList) {
		this.detPaqueteBiblicoList = detPaqueteBiblicoList;
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
	
	

	
}
