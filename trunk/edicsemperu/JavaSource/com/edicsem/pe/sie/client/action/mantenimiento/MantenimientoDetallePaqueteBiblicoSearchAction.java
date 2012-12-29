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

@ManagedBean(name = "MantenimientoDetallePaqueteBiblicoSearchAction")
@SessionScoped
public class MantenimientoDetallePaqueteBiblicoSearchAction extends BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoDetallePaqueteBiblicoSearchAction.class);
	
//	public String mensaje;
//	private boolean newRecord = false;
//	private List<SancionSie> detSancionList;
//	private List<DetSancionCargoSie> detSancionCargoList;
//	private int idFactor,idSancion, factor;
//	private int idcargo;
//	private DetSancionCargoSie objDetSancionCargo;
	
	// PAQUETE BIBLICO
	public String mensaje;
//	private List<PaqueteSie> detPaqueteList;
	private List<DetPaqueteSie> detPaqueteBiblicoList;
//	private PaqueteSie objPaqueteSie;
	private DetPaqueteSie objDetPaqueteSie;
//	private boolean newRecord = false;
	private int paquete;
	

	
	@EJB
	private DetallePaqueteService objDetallePaqueteService;
	
	@EJB
	private PaqueteService objPaqueteService;
	
	
	
	public MantenimientoDetallePaqueteBiblicoSearchAction() {
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
		log.info("Inicializando el Constructor de 'MantenimientoDetallePaqueteBiblicoSearchAction'");
//		detPaqueteList = new ArrayList<PaqueteSie>();
		detPaqueteBiblicoList = new ArrayList<DetPaqueteSie>();
		
		objDetPaqueteSie = new DetPaqueteSie();
		
//		objPaqueteSie = new PaqueteSie();
		
		
	}
	public List<DetPaqueteSie> getDetPaqueteBiblicoList() {
		return detPaqueteBiblicoList;
	}


	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar 'MantenimientorSancionFormAction' ");
		detPaqueteBiblicoList = objDetallePaqueteService.listarDetPaquetes(paquete);
		
		if (detPaqueteBiblicoList == null) {
			detPaqueteBiblicoList = new ArrayList<DetPaqueteSie>();
		}
		return getViewList();
	}
	



	
	

	public String getViewList() {
		return Constants.MANT_DETALLEPAQUETEBIBLICO_FORM_LIST_PAGE;
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
	 * @return the paquete
	 */
	public int getPaquete() {
		return paquete;
	}

	/**
	 * @param paquete the paquete to set
	 */
	public void setPaquete(int paquete) {
		this.paquete = paquete;
	}

	/**
	 * @return the newRecord
	 */

	

	
}
