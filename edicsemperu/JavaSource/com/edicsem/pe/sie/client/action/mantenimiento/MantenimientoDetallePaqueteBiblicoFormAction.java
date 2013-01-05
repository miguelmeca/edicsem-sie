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
import com.edicsem.pe.sie.entity.DetSancionCargoSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.entity.SancionSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.DetSancionCargoService;
import com.edicsem.pe.sie.service.facade.DetallePaqueteService;
import com.edicsem.pe.sie.service.facade.PaqueteService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.SancionService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "mantenimientoDetallePaqueteBiblicoFormAction")
@SessionScoped
public class MantenimientoDetallePaqueteBiblicoFormAction extends BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoDetallePaqueteBiblicoFormAction.class);
	
	
	
	//Tb_DETALLE PAQUETE
	public String mensaje;
	private boolean newRecord = false;
	private DetPaqueteSie objDetPaqueteSie, objAuxiDetPaqueteSie;
	private List<DetPaqueteSie> detPaqueteBiblicoList;
	private int idpaquete, iddetpaquete; 
	private int idproducto, itemDetPaqBibli;
	
	//tb_PAQUETE
	
	private List<PaqueteSie> detPaqueteList;
	private PaqueteSie objPaqueteSie;
	
	
	
//	@ManagedProperty(value = "#{mantenimientoDetallePaqueteBiblicoSearchAction}")
//	private MantenimientoDetallePaqueteBiblicoSearchAction mantenimientoDetallePaqueteBiblicoSearchAction;
	
	@EJB
	private SancionService objSancionService;
	@EJB
	private CargoEmpleadoService objCargoService;
	@EJB
	private DetSancionCargoService objDetSancionCargoService;	
	@EJB
	private DetallePaqueteService objDetallePaqueteService;
	
	@EJB
	private PaqueteService objPaqueteService;
	
	@EJB
	private ProductoService objProductoService;
	
	
	
	
	
	public MantenimientoDetallePaqueteBiblicoFormAction() {
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
		

		
		log.info("Inicializando el Constructor de 'MantenimientoDetallePaqueteBiblicoSearchAction'");
		objDetPaqueteSie = new DetPaqueteSie();
		detPaqueteBiblicoList = new ArrayList<DetPaqueteSie>();
		//TB_PAQUETE
		objPaqueteSie = new PaqueteSie();
		detPaqueteList = new ArrayList<PaqueteSie>();
		iddetpaquete=0;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		
		//DETalle paquete
		log.info("agregar-jk()");
//		setNewRecord(true);
		objDetPaqueteSie = new DetPaqueteSie();
		detPaqueteBiblicoList = new ArrayList<DetPaqueteSie>();
		objPaqueteSie = new PaqueteSie();
		
		
		return getViewMant();
	}
//lista donde se van agregar de acuerdo
	public String agregarProdPaquete(){
		mensaje=null;
		log.info("agregarProdPaquete");
		boolean isadd = false;
		//isadd es agregar
		
		log.info("antes de buscar el producto ");
		objDetPaqueteSie.setTbProducto(objProductoService.findProducto(idproducto));
		log.info("despues de buscar el producto"+ idproducto );

		int cantidad= detPaqueteBiblicoList.size();
		for (int i = 0; i < detPaqueteBiblicoList.size(); i++) {
			if(detPaqueteBiblicoList.get(i).getTbProducto().getIdproducto()==idproducto){
				isadd=true;
				break;
				
			}
			
		}
		if(isadd==true){
				mensaje = "Dicho producto ya tiene una paquete bla bla bla bla... ";
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_INFO_TITULO, mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			
			if(cantidad==0){
				
				objDetPaqueteSie.setItem(1);
				objDetPaqueteSie.setIsnew("N");
				detPaqueteBiblicoList.add(objDetPaqueteSie);
				objDetPaqueteSie  = new DetPaqueteSie();
				isadd=true;
				log.info("dentro del if cantidad==0");
				
			}else{
				objDetPaqueteSie.setItem(cantidad+1);
				log.info("dentro del else cantidad+1");
			}
		}
		log.info(" si me muestra esto esta mal "+" "+ mensaje);
		return getViewMant();
	}

	
	//lista donde se van a editar de acuerdo
	public String updateDetPaqueBiblico() {
		boolean isadd=false;
		log.info("updateDetPaqueBiblico()  "+objAuxiDetPaqueteSie.getItem() );
		for (int i = 0; i < detPaqueteBiblicoList.size(); i++) {
			log.info(" **** "+ detPaqueteBiblicoList.get(i).getItem()+"  "+  detPaqueteBiblicoList.get(i).getCantidad());
				if(detPaqueteBiblicoList.get(i).getTbProducto().getIdproducto()==idproducto){
					isadd=true;
					break;
				}
			}
		
		if(isadd==false){
			for (int i = 0; i < detPaqueteBiblicoList.size(); i++) {
			
				if(detPaqueteBiblicoList.get(i).getItem()==(objAuxiDetPaqueteSie.getItem())){
				log.info("item ********* "+ objAuxiDetPaqueteSie.getItem());
				
							
				detPaqueteBiblicoList.get(i).setTbProducto(objProductoService.findProducto(idproducto));
				detPaqueteBiblicoList.get(i).setCantidad(objAuxiDetPaqueteSie.getCantidad());	
				
				log.info("descripcion de producto   "+detPaqueteBiblicoList.get(i).getTbProducto().getDescripcionproducto());
				}
			}
		}else{
			mensaje = "ya existe un producto para dicho cargo";
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		log.info("actualizo  ********* " );
		setNewRecord(false);
		return getViewMant();
	}
    
	public String updateDeshabilitarDetPaqueBiblico() throws Exception{
		 
		if (log.isInfoEnabled()) {
			log.info("updateDeshabilitar()' " +itemDetPaqBibli);
		}
		for (int i = 0; i < detPaqueteBiblicoList.size(); i++) {
			if(detPaqueteBiblicoList.get(i).getItem()==(itemDetPaqBibli)){
				detPaqueteBiblicoList.remove(i);
			
				for (int j = i; j < detPaqueteBiblicoList.size(); j++) {
					log.info(" i " +i+"  j "+ j);
					i=i+1;
					detPaqueteBiblicoList.get(j).setItem(i);
					detPaqueteBiblicoList.set(j, detPaqueteBiblicoList.get(j));
				}
			}
		}
		log.info("actualizando..... ");
		return getViewMant();
	}
	
	//Insertar el la tabla temporal
	public String insertar() {
		
		log.info("insertar()" + isNewRecord()  );
		try {
			if(isNewRecord()){
				log.info("tañano lista "+ detPaqueteBiblicoList.size() );	
				if(detPaqueteBiblicoList.size()==0){
					mensaje="Debe registrar el producto para el tipo de paquete biblico que corresponda";
				}else{
					mensaje =Constants.MESSAGE_REGISTRO_TITULO;

					
					
					objDetallePaqueteService.insertDetPaquete(objDetPaqueteSie);
					log.info("insertandio "  );	
				}
			}
			else{
				log.info("actualizado "+objDetPaqueteSie.getCantidad());
				
				objDetallePaqueteService.updateDetPaquete(objDetPaqueteSie);
				log.info("actualizado");
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		log.info("mensaje -->* "+mensaje );
		
		objDetPaqueteSie = new DetPaqueteSie();
		return getViewMant();
	}
	
	
	
	public String update() throws Exception {
		log.info("update()-JK  "+objDetPaqueteSie.getTbPaquete().getIdpaquete() );
//		setNewRecord(false);
//		objDetPaqueteSie = new DetPaqueteSie();
		detPaqueteBiblicoList = objDetallePaqueteService.listarDetPaquetes(objDetPaqueteSie.getTbPaquete().getIdpaquete());
//		objPaqueteSie = new PaqueteSie();
//		
//		for (int i = 0; i < detPaqueteBiblicoList.size(); i++) {
//			DetPaqueteSie det =detPaqueteBiblicoList.get(i);
//			det.setItem(i+1);
//			detPaqueteBiblicoList.set(i, det);
//		}
		
		return getViewMant();
	}

	
	public String getViewMant() {
		return Constants.MANT_DETALLEPAQUETEBIBLICO_FORM_PAGE;
	}	
		
	public String getViewList() {
		return Constants.MANT_DETALLEPAQUETEBIBLICO_FORM_LIST_PAGE;
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
	 * @return the detSancionList
	 */
//	public List<SancionSie> getDetSancionList() {
//		return detSancionList;
//	}
//
//	/**
//	 * @param detSancionList the detSancionList to set
//	 */
//	public void setDetSancionList(List<SancionSie> detSancionList) {
//		this.detSancionList = detSancionList;
//	}
//
//	/**
//	 * @return the mensaje

	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
//	public String listar() {
//		log.info("listar 'MantenimientorSancionFormAction' ");
//		detSancionList = objSancionService.listarSanciones(idFactor);
//		objSancionSie = new SancionSie();
//		if (detSancionList == null) {
//			detSancionList = new ArrayList<SancionSie>();
//		}
//		return getViewList();
//	}
//    
//	/**
//	 * @return the objSancionSie
//	 */
//	public SancionSie getObjSancionSie() {
//		return objSancionSie;
//	}
//
//	/**
//	 * @param objSancionSie the objSancionSie to set
//	 */
//	public void setObjSancionSie(SancionSie objSancionSie) {
//		this.objSancionSie = objSancionSie;
//	}
//
//	/**
//	 * @return the idFactor
//	 */
//	public int getIdFactor() {
//		return idFactor;
//	}
//
//	/**
//	 * @param idFactor the idFactor to set
//	 */
//	public void setIdFactor(int idFactor) {
//		this.idFactor = idFactor;
//	}
//
//	/**
//	 * @return the idSancion
//	 */
//	public int getIdSancion() {
//		return idSancion;
//	}
//
//	/**
//	 * @param idSancion the idSancion to set
//	 */
//	public void setIdSancion(int idSancion) {
//		this.idSancion = idSancion;
//	}
//
//	/**
//	 * @return the factor
//	 */
//	public int getFactor() {
//		return factor;
//	}
//
//	/**
//	 * @param factor the factor to set
//	 */
//	public void setFactor(int factor) {
//		this.factor = factor;
//	}
//
//	/**
//	 * @return the idcargo
//	 */
//	public int getIdcargo() {
//		return idcargo;
//	}
//
//	/**
//	 * @param idcargo the idcargo to set
//	 */
//	public void setIdcargo(int idcargo) {
//		this.idcargo = idcargo;
//	}
//
//	/**
//	 * @return the detSancionCargoList
//	 */
//	public List<DetSancionCargoSie> getDetSancionCargoList() {
//		return detSancionCargoList;
//	}
//
//	/**
//	 * @param detSancionCargoList the detSancionCargoList to set
//	 */
//	public void setDetSancionCargoList(List<DetSancionCargoSie> detSancionCargoList) {
//		this.detSancionCargoList = detSancionCargoList;
//	}
//
//	/**
//	 * @return the objDetSancionCargo
//	 */
//	public DetSancionCargoSie getObjDetSancionCargo() {
//		return objDetSancionCargo;
//	}
//
//	/**
//	 * @param objDetSancionCargo the objDetSancionCargo to set
//	 */
//	public void setObjDetSancionCargo(DetSancionCargoSie objDetSancionCargo) {
//		this.objDetSancionCargo = objDetSancionCargo;
//	}

	/**
	 * @return the manteSancionSearch
	 */
//	public MantenimientoSancionSearchAction getManteSancionSearch() {
//		return manteSancionSearch;
//	}
//
//	/**
//	 * @param manteSancionSearch the manteSancionSearch to set
//	 */
//	public void setManteSancionSearch(
//			MantenimientoSancionSearchAction manteSancionSearch) {
//		this.manteSancionSearch = manteSancionSearch;
//	}

	/**
	 * @return the objAuxiSancionCargo
	 */
//	public DetSancionCargoSie getObjAuxiSancionCargo() {
//		return objAuxiSancionCargo;
//	}
//
//	/**
//	 * @param objAuxiSancionCargo the objAuxiSancionCargo to set
//	 */
//	public void setObjAuxiSancionCargo(DetSancionCargoSie objAuxiSancionCargo) {
//		this.objAuxiSancionCargo = objAuxiSancionCargo;
//	}
//
//	/**
//	 * @return the itemSancionCargo
//	 */
//	public int getItemSancionCargo() {
//		return itemSancionCargo;
//	}
//
//	/**
//	 * @param itemSancionCargo the itemSancionCargo to set
//	 */
//	public void setItemSancionCargo(int itemSancionCargo) {
//		this.itemSancionCargo = itemSancionCargo;
//	}

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
	 * @return the detPaqueteBiblicoList
	 */
	public List<DetPaqueteSie> getDetPaqueteBiblicoList() {
		return detPaqueteBiblicoList;
	}

	/**
	 * @param detPaqueteBiblicoList the detPaqueteBiblicoList to set
	 */
	public void setDetPaqueteBiblicoList(List<DetPaqueteSie> detPaqueteBiblicoList) {
		this.detPaqueteBiblicoList = detPaqueteBiblicoList;
	}

	/**
	 * @return the mantenimientoDetallePaqueteBiblicoSearchAction
//	 */
//	public MantenimientoDetallePaqueteBiblicoSearchAction getMantenimientoDetallePaqueteBiblicoSearchAction() {
//		return MantenimientoDetallePaqueteBiblicoSearchAction;
//	}

	/**
	 * @param mantenimientoDetallePaqueteBiblicoSearchAction the mantenimientoDetallePaqueteBiblicoSearchAction to set
	 */
//	public void setMantenimientoDetallePaqueteBiblicoSearchAction(
//			MantenimientoDetallePaqueteBiblicoSearchAction mantenimientoDetallePaqueteBiblicoSearchAction) {
//		MantenimientoDetallePaqueteBiblicoSearchAction = mantenimientoDetallePaqueteBiblicoSearchAction;
//	}

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
	 * @return the iddetpaquete
	 */
	public int getIddetpaquete() {
		return iddetpaquete;
	}

	/**
	 * @param iddetpaquete the iddetpaquete to set
	 */
	public void setIddetpaquete(int iddetpaquete) {
		this.iddetpaquete = iddetpaquete;
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
	 * @return the objAuxiDetPaqueteSie
	 */
	public DetPaqueteSie getObjAuxiDetPaqueteSie() {
		return objAuxiDetPaqueteSie;
	}

	/**
	 * @param objAuxiDetPaqueteSie the objAuxiDetPaqueteSie to set
	 */
	public void setObjAuxiDetPaqueteSie(DetPaqueteSie objAuxiDetPaqueteSie) {
		this.objAuxiDetPaqueteSie = objAuxiDetPaqueteSie;
	}

	/**
	 * @return the itemDetPaqBibli
	 */
	public int getItemDetPaqBibli() {
		return itemDetPaqBibli;
	}

	/**
	 * @param itemDetPaqBibli the itemDetPaqBibli to set
	 */
	public void setItemDetPaqBibli(int itemDetPaqBibli) {
		this.itemDetPaqBibli = itemDetPaqBibli;
	}

	/**
	 * @return the objProducto
	 */

	



}
