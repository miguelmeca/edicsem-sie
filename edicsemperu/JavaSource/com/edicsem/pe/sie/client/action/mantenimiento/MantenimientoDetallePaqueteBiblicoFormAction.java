//package com.edicsem.pe.sie.client.action.mantenimiento;
//
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.edicsem.pe.sie.entity.DetPaqueteSie;
//import com.edicsem.pe.sie.service.facade.DetallePaqueteService;
//import com.edicsem.pe.sie.service.facade.EstadogeneralService;
//import com.edicsem.pe.sie.service.facade.PaqueteService;
//import com.edicsem.pe.sie.service.facade.ProductoService;
//import com.edicsem.pe.sie.util.constants.Constants;
//import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
//
//@ManagedBean(name = "detallePaqueteBiblicoFormAction")
//@SessionScoped
//public class MantenimientoDetallePaqueteBiblicoFormAction extends BaseMantenimientoAbstractAction {
//
//	private Log log = LogFactory.getLog(MantenimientoDetallePaqueteBiblicoFormAction.class);
//	
//	
//	
//
//	
//	public String mensaje;
//	private DetPaqueteSie nuevo;
//	private DetPaqueteSie objDetPaqueteSie;
//	
//	
//	
//	
//	
//	private int idproducto;
//	private int idEstadoGeneral;
//	private int cantidad,idDetPaquete;
//	private int idpaquete;
//	
//	
//	private boolean newRecord = false;
//	private boolean editMode;
//
//	@ManagedProperty(value = "#{mantenimientoDetallePaqueteBiblicoSearchAction}")
//	private MantenimientoDetallePaqueteBiblicoSearchAction mantenimientoDetallePaqueteBiblicoSearchAction;
//	@EJB
//	private DetallePaqueteService objDetallePaqueteService;
//	
//	@EJB
//	private PaqueteService objPaqueteService;
//	
//	@EJB
//	private ProductoService objProductoService;
//	
//	@EJB
//	private EstadogeneralService objEstadoGeneralService;
//
//	public MantenimientoDetallePaqueteBiblicoFormAction() {
//		init();
//	}
////	
////	/*
////	 * (non-Javadoc)
////	 * 
////	 * @see
////	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
////	 * #init()
////	 */
//	public void init() {
//		
//
//		
//		log.info("Inicializando el Constructor de 'detallePaqueteBiblicoFormAction'");
//		objDetPaqueteSie = new DetPaqueteSie();
//
//		nuevo = new DetPaqueteSie();
//		
//		
//		
//		
//		
//	}
//	
//	
//	//AGREGAR Y UPDATE
//public String agregar() {
//	
//			log.info("agregar()");
//		
//			editMode = true;
//			objDetPaqueteSie = new DetPaqueteSie();
//			
//
//			
//			setNewRecord(true);
//			return getViewList();
//		}
//		
//		
//		
//		
//		
//public String update() throws Exception {
//			log.info("update()  " );
//
//	log.info(" id_detalle_paquete" + " " + objDetPaqueteSie.getIdDetPaquete() + " id_de_producto "+ objDetPaqueteSie.getTbProducto().getIdproducto());		
//
//
//	objDetPaqueteSie = objDetallePaqueteService.findDetPaquete(objDetPaqueteSie.getIdDetPaquete());
//	
//	setIdDetPaquete(objDetPaqueteSie.getIdDetPaquete());
//	setIdpaquete(objDetPaqueteSie.getTbPaquete().getIdpaquete());
//	setIdproducto(objDetPaqueteSie.getTbProducto().getIdproducto());
//	setCantidad(objDetPaqueteSie.getCantidad());
//	setIdEstadoGeneral(objDetPaqueteSie.getTbEstadoGeneral().getIdestadogeneral());
//			
//			
//			setNewRecord(false);
//			editMode = false;
//			return getViewList();
//		}
//		
//
////insertar nuevo y Edita Detalle Paquete Biblico
//
//
//
//	public String insertar() {
//		mensaje =null;
////		log.info("insertar()");
////		
//		
//		
//		objDetPaqueteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(62));
//		objDetPaqueteSie.setTbProducto(objProductoService.findProducto(idproducto));
//		objDetPaqueteSie.setTbPaquete(objPaqueteService.findPaquete(idpaquete));
//		
//
//		
//		try {
//			
////			log.info("aqui validadndo si existe o no" + idproducto + " "+ objDetPaqueteSie.getCantidad());
////			int error = 0;
////			List<DetPaqueteSie> lista = mantenimientoDetallePaqueteBiblicoSearchAction.getDetPaqueteBiblicoList();
//					
////			for (int i = 0; i < lista.size(); i++) {
////				DetPaqueteSie a = lista.get(i);
////				if (a.getTbProducto().getIdproducto().equals(objDetPaqueteSie.getTbProducto().getIdproducto())) {
////					log.info("Error ... Ya se encuentra un Delllate Paquete Biblico");
////					mensaje ="Ya se encuentra un Producto en el paquete biblico con el mismo nombre";
////					error = 1;
////					break;
////				}
////				
////			}
////			if (error == 0) {
//						
//			if(isNewRecord()){
//				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
//						Constants.MESSAGE_REGISTRO_TITULO, mensaje);
//				
//				
////				objDetPaqueteSie.setIdDetPaquete(objDetPaqueteSie.getIdDetPaquete());
//				objDetPaqueteSie.setCantidad(objDetPaqueteSie.getCantidad());
//				
////				objDetPaqueteSie.setTbProducto(setIdproducto(objDetPaqueteSie.getTbProducto().getIdproducto()));
//				
//				
//			objDetPaqueteSie.setTbProducto(objProductoService.findProducto(idproducto));
//
//							
//			
//				
//				objDetallePaqueteService.insertDetPaquete(objDetPaqueteSie);
//				log.info("insertando "  );
//			}
//			else{
//				
//
//objDetPaqueteSie.setIdDetPaquete(getIdDetPaquete());
//
//objDetPaqueteSie.setCantidad(objDetPaqueteSie.getCantidad());
//objDetPaqueteSie.setTbPaquete(objPaqueteService.findPaquete(getIdpaquete()));
//objDetPaqueteSie.setTbProducto(objProductoService.findProducto(getIdproducto()));
//objDetPaqueteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(getIdEstadoGeneral()));
//
//log.info("actualizando..... ");	
//msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
//		Constants.MESSAGE_REGISTRO_TITULO, mensaje);
//	
//	
//	objDetallePaqueteService.updateDetPaquete(objDetPaqueteSie);
//				mensaje="Se actualizó el Producto";
//				log.info("actualizado");
//			}
////		}
////			else {
////				log.info("mensaje de error");
////				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
////						Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
////		
////		} 
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//
//}
//			catch (Exception e) {
//			e.printStackTrace();
//			mensaje = e.getMessage();
//			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
//					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
//			log.error(e.getMessage());
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//		}
//	
//		objDetPaqueteSie = new  DetPaqueteSie();
//		return  mantenimientoDetallePaqueteBiblicoSearchAction.listar();
//	}
//
//	
////	public String getViewList() {
////		return Constants.MANT_PAQUETEBIBLICO_FORM_LIST_PAGE;
////	}
//	/**
//	 * @return the objDetPaqueteSie
//	 */
//	public DetPaqueteSie getObjDetPaqueteSie() {
//		return objDetPaqueteSie;
//	}
//
//	/**
//	 * @param objDetPaqueteSie the objDetPaqueteSie to set
//	 */
//	public void setObjDetPaqueteSie(DetPaqueteSie objDetPaqueteSie) {
//		this.objDetPaqueteSie = objDetPaqueteSie;
//	}
//	/**
//	 * @return the log
//	 */
//	public Log getLog() {
//		return log;
//	}
//	/**
//	 * @param log the log to set
//	 */
//	public void setLog(Log log) {
//		this.log = log;
//	}
//	/**
//	 * @return the mensaje
//	 */
//	public String getMensaje() {
//		return mensaje;
//	}
//	/**
//	 * @param mensaje the mensaje to set
//	 */
//	public void setMensaje(String mensaje) {
//		this.mensaje = mensaje;
//	}
//	/**
//	 * @return the nuevo
//	 */
//	public DetPaqueteSie getNuevo() {
//		return nuevo;
//	}
//	/**
//	 * @param nuevo the nuevo to set
//	 */
//	public void setNuevo(DetPaqueteSie nuevo) {
//		this.nuevo = nuevo;
//	}
//	/**
//	 * @return the objPaqueteSie
//	 */
//
//	/**
//	 * @return the newRecord
//	 */
//	public boolean isNewRecord() {
//		return newRecord;
//	}
//	/**
//	 * @param newRecord the newRecord to set
//	 */
//	public void setNewRecord(boolean newRecord) {
//		this.newRecord = newRecord;
//	}
//	/**
//	 * @return the editMode
//	 */
//	public boolean isEditMode() {
//		return editMode;
//	}
//	/**
//	 * @param editMode the editMode to set
//	 */
//	public void setEditMode(boolean editMode) {
//		this.editMode = editMode;
//	}
//	/**
//	
//	/**
//	 * @return the mantenimientoPaqueteBiblicoSearchAction
//	 */
//	
//	/**
//	 * @return the idEstadoGeneral
//	 */
//	public int getIdEstadoGeneral() {
//		return idEstadoGeneral;
//	}
//	/**
//	 * @param idEstadoGeneral the idEstadoGeneral to set
//	 */
//	public void setIdEstadoGeneral(int idEstadoGeneral) {
//		this.idEstadoGeneral = idEstadoGeneral;
//	}
//	/**
//	 * @return the cantidad
//	 */
//	public int getCantidad() {
//		return cantidad;
//	}
//	/**
//	 * @param cantidad the cantidad to set
//	 */
//	public void setCantidad(int cantidad) {
//		this.cantidad = cantidad;
//	}
//	/**
//	 * @return the idproducto
//	 */
//	public int getIdproducto() {
//		return idproducto;
//	}
//	/**
//	 * @param idproducto the idproducto to set
//	 */
//	public void setIdproducto(int idproducto) {
//		this.idproducto = idproducto;
//	}
//
//	public int getIdpaquete() {
//		return idpaquete;
//	}
//	/**
//	 * @param idpaquete the idpaquete to set
//	 */
//	public void setIdpaquete(int idpaquete) {
//		this.idpaquete = idpaquete;
//	}
//	/**
//	 * @return the mantenimientoDetallePaqueteBiblicoSearchAction
//	 */
//	public MantenimientoDetallePaqueteBiblicoSearchAction getMantenimientoDetallePaqueteBiblicoSearchAction() {
//		return mantenimientoDetallePaqueteBiblicoSearchAction;
//	}
//	/**
//	 * @param mantenimientoDetallePaqueteBiblicoSearchAction the mantenimientoDetallePaqueteBiblicoSearchAction to set
//	 */
//	public void setMantenimientoDetallePaqueteBiblicoSearchAction(
//			MantenimientoDetallePaqueteBiblicoSearchAction mantenimientoDetallePaqueteBiblicoSearchAction) {
//		this.mantenimientoDetallePaqueteBiblicoSearchAction = mantenimientoDetallePaqueteBiblicoSearchAction;
//	}
//	/**
//	 * @return the idDetPaquete
//	 */
//	public int getIdDetPaquete() {
//		return idDetPaquete;
//	}
//	/**
//	 * @param idDetPaquete the idDetPaquete to set
//	 */
//	public void setIdDetPaquete(int idDetPaquete) {
//		this.idDetPaquete = idDetPaquete;
//	}
//
//	
//	
//}
//	
