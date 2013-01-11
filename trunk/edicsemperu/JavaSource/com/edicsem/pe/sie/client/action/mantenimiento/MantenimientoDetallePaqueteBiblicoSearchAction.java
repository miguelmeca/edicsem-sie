package com.edicsem.pe.sie.client.action.mantenimiento;

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

import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.entity.FactorSancionSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.service.facade.DetallePaqueteService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.PaqueteService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "mantenimientoDetallePaqueteBiblicoSearchAction")
@SessionScoped
public class MantenimientoDetallePaqueteBiblicoSearchAction extends BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoDetallePaqueteBiblicoSearchAction.class);
	

	
	// PAQUETE BIBLICO
	public String mensaje;
	private List<DetPaqueteSie> detPaqueteBiblicoList;
	private DetPaqueteSie objDetPaqueteSie;
	private int idpaquete;
	private PaqueteSie objPaqueteSie;
	

	private DetPaqueteSie nuevo;
	private int idc;
	
	
	
	
	
	
	private int idproducto;
	private int idEstadoGeneral;
	private int cantidad,idDetPaquete;
	
	
	
	private boolean newRecord = false;
	private boolean editMode;

	
	@EJB
	private DetallePaqueteService objDetallePaqueteService;
	
	@EJB
	private PaqueteService objPaqueteService;
	
	
	@EJB
	private ProductoService objProductoService;
	
	@EJB
	private EstadogeneralService objEstadoGeneralService;

	
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
//		detPaqueteBiblicoList = new ArrayList<DetPaqueteSie>();
		
		objDetPaqueteSie = new DetPaqueteSie();
		objPaqueteSie = new PaqueteSie();
		

		nuevo = new DetPaqueteSie();
		
//		objPaqueteSie = new PaqueteSie();
		
		
	}
	public List<DetPaqueteSie> getDetPaqueteBiblicoList() {
		return detPaqueteBiblicoList;
	}


	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar 'MantenimientoDetallePaqueteBiblicoSearchAction' ");
		detPaqueteBiblicoList = objDetallePaqueteService.listarDetPaquetes(idpaquete);
		
		
		return getViewList();
	}
	


	
	
	
	
	//AGREGAR Y UPDATE
public String agregar() {
	
			log.info("agregar()");
		
			editMode = true;
			objDetPaqueteSie = new DetPaqueteSie();
			

			
			setNewRecord(true);
			return getViewList();
		}
		
		
		
		
		
public String update() throws Exception {
			log.info("update()  " );

	log.info(" id_detalle_paquete" + " " + objDetPaqueteSie.getIdDetPaquete() + " id_de_producto "+ objDetPaqueteSie.getTbProducto().getIdproducto());		


	objDetPaqueteSie = objDetallePaqueteService.findDetPaquete(objDetPaqueteSie.getIdDetPaquete());
	
	setIdDetPaquete(objDetPaqueteSie.getIdDetPaquete());
	setIdpaquete(objDetPaqueteSie.getTbPaquete().getIdpaquete());
	setIdproducto(objDetPaqueteSie.getTbProducto().getIdproducto());
	setCantidad(objDetPaqueteSie.getCantidad());
	setIdEstadoGeneral(objDetPaqueteSie.getTbEstadoGeneral().getIdestadogeneral());
			
			
			setNewRecord(false);
			editMode = false;
			return getViewList();
		}


public String insertar() {
	mensaje =null;
//	log.info("insertar()");
//	
	
	
	objDetPaqueteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(62));
	objDetPaqueteSie.setTbProducto(objProductoService.findProducto(idproducto));
	objDetPaqueteSie.setTbPaquete(objPaqueteService.findPaquete(idpaquete));
	

	
	try {
		
//		log.info("aqui validadndo si existe o no" + idproducto + " "+ objDetPaqueteSie.getCantidad());
//		int error = 0;
//		List<DetPaqueteSie> lista = mantenimientoDetallePaqueteBiblicoSearchAction.getDetPaqueteBiblicoList();
				
//		for (int i = 0; i < lista.size(); i++) {
//			DetPaqueteSie a = lista.get(i);
//			if (a.getTbProducto().getIdproducto().equals(objDetPaqueteSie.getTbProducto().getIdproducto())) {
//				log.info("Error ... Ya se encuentra un Delllate Paquete Biblico");
//				mensaje ="Ya se encuentra un Producto en el paquete biblico con el mismo nombre";
//				error = 1;
//				break;
//			}
//			
//		}
//		if (error == 0) {
					
		if(isNewRecord()){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Constants.MESSAGE_REGISTRO_TITULO, mensaje);
			
			
//			objDetPaqueteSie.setIdDetPaquete(objDetPaqueteSie.getIdDetPaquete());
			objDetPaqueteSie.setCantidad(objDetPaqueteSie.getCantidad());
			
//			objDetPaqueteSie.setTbProducto(setIdproducto(objDetPaqueteSie.getTbProducto().getIdproducto()));
			
			
		objDetPaqueteSie.setTbProducto(objProductoService.findProducto(idproducto));

						
		
			
			objDetallePaqueteService.insertDetPaquete(objDetPaqueteSie);
			log.info("insertando "  );
		}
		else{
			

objDetPaqueteSie.setIdDetPaquete(getIdDetPaquete());

objDetPaqueteSie.setCantidad(objDetPaqueteSie.getCantidad());
objDetPaqueteSie.setTbPaquete(objPaqueteService.findPaquete(getIdpaquete()));
objDetPaqueteSie.setTbProducto(objProductoService.findProducto(getIdproducto()));
objDetPaqueteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(getIdEstadoGeneral()));

log.info("actualizando..... ");	
msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
	Constants.MESSAGE_REGISTRO_TITULO, mensaje);


objDetallePaqueteService.updateDetPaquete(objDetPaqueteSie);
			mensaje="Se actualiz� el Producto";
			log.info("actualizado");
		}
//	}
//		else {
//			log.info("mensaje de error");
//			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
//					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
//	
//	} 
		FacesContext.getCurrentInstance().addMessage(null, msg);

}
		catch (Exception e) {
		e.printStackTrace();
		mensaje = e.getMessage();
		msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
				Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
		log.error(e.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	objDetPaqueteSie = new  DetPaqueteSie();
	return  getViewMant() ;
}




public String updateDeshabilitarDetProdPaquete() throws Exception {
	mensaje =null;

//	objCargoEmpleadoSie = new CargoEmpleadoSie();
	objDetPaqueteSie = new DetPaqueteSie();
	int parametroObtenido;
	DetPaqueteSie c = new DetPaqueteSie();
//	CargoEmpleadoSie c = new CargoEmpleadoSie();

	try {
		if (log.isInfoEnabled()) {
			log.info("Entering my method 'updateDESHABILITAR()'");
		
		}	
		parametroObtenido = getIdc();
		log.info(" ------>>>>>>aqui cactura el parametro ID "+ parametroObtenido);
		
		
				
				
				c = objDetallePaqueteService.findDetPaquete(parametroObtenido);
				log.info(" ------Android>" + c.getIdDetPaquete());
				
//				
				
			
				objDetPaqueteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(63));
				objDetPaqueteSie.setIdDetPaquete(c.getIdDetPaquete());
				objDetPaqueteSie.setCantidad(c.getCantidad());
				objDetPaqueteSie.setTbProducto(objProductoService.findProducto(c.getTbProducto().getIdproducto()));
				
				objDetPaqueteSie.setTbPaquete(objPaqueteService.findPaquete(idpaquete));


				
				log.info("-----ID estado general>>>"	+ objDetPaqueteSie.getTbEstadoGeneral().getIdestadogeneral());
				log.info("actualizando ESTADO..... ");

				objDetallePaqueteService.updateDetPaquete(objDetPaqueteSie);

				
				log.info("actualizando..... ");	
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
	Constants.MESSAGE_DESHABILITAR_TITULO, mensaje);
				mensaje ="Se deshabilito correctamente";
//			}	
	
				FacesContext.getCurrentInstance().addMessage(null, msg);

	} catch (Exception e) {
		e.printStackTrace();
		mensaje = e.getMessage();
		msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
				Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
		log.error(e.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
//	objCargoEmpleadoSie = new CargoEmpleadoSie();
	objDetPaqueteSie = new DetPaqueteSie();
	return getViewMant();
}




	
	

	public String getViewList() {
		return Constants.MANT_PAQUETEBIBLICO_FORM_LIST_PAGE;
	}


	public String getViewMant() {
		return Constants.MANT_DETALLEPAQUETEBIBLICO_FORM_PAGE;
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
	 * @return the idpaquete
	 */
	public int getIdpaquete() {
		return idpaquete;
	}

	/**
	 * @param idpaquete the idpaquete to set
	 */
	public void setIdpaquete(int idpaquete) {
		this.idpaquete = idpaquete;
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
	 * @return the nuevo
	 */
	public DetPaqueteSie getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(DetPaqueteSie nuevo) {
		this.nuevo = nuevo;
	}

	/**
	 * @return the idproducto
	 */
	public int getIdproducto() {
		return idproducto;
	}

	/**
	 * @param idproducto the idproducto to set
	 */
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
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
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the idDetPaquete
	 */
	public int getIdDetPaquete() {
		return idDetPaquete;
	}

	/**
	 * @param idDetPaquete the idDetPaquete to set
	 */
	public void setIdDetPaquete(int idDetPaquete) {
		this.idDetPaquete = idDetPaquete;
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
	 * @return the idc
	 */
	public int getIdc() {
		return idc;
	}

	/**
	 * @param idc the idc to set
	 */
	public void setIdc(int idc) {
		this.idc = idc;
	}

	/**
	 * @return the newRecord
	 */

	

	
}