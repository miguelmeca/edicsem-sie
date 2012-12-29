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
import com.edicsem.pe.sie.entity.DetSancionCargoSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.entity.SancionSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.DetSancionCargoService;
import com.edicsem.pe.sie.service.facade.DetallePaqueteService;
import com.edicsem.pe.sie.service.facade.PaqueteService;
import com.edicsem.pe.sie.service.facade.SancionService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "MantenimientoDetallePaqueteBiblicoFormAction")
@SessionScoped
public class MantenimientoDetallePaqueteBiblicoFormAction extends BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoDetallePaqueteBiblicoFormAction.class);
	

	
	private SancionSie objSancionSie;
	private List<SancionSie> detSancionList;
	private List<DetSancionCargoSie> detSancionCargoList;
	private int idFactor,idSancion, factor;
	private int idcargo,itemSancionCargo;
	private DetSancionCargoSie objDetSancionCargo, objAuxiSancionCargo;
	
	
	//Tb_DETALLE PAQUETE
	public String mensaje;
	private boolean newRecord = false;
	private DetPaqueteSie objDetPaqueteSie;
	private List<DetPaqueteSie> detPaqueteBiblicoList;
	private int idpaquete, iddetpaquete; 
	
	//tb_PAQUETE
	
	private List<PaqueteSie> detPaqueteList;
	private PaqueteSie objPaqueteSie;
	
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
	
	
	@ManagedProperty(value = "#{SancionSearch}")
	private MantenimientoSancionSearchAction manteSancionSearch;
	
	@ManagedProperty(value = "#{MantenimientoDetallePaqueteBiblicoSearchAction}")
	private MantenimientoDetallePaqueteBiblicoSearchAction MantenimientoDetallePaqueteBiblicoSearchAction;
	
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
		
//		objSancionSie = new SancionSie();
//		detSancionList = new ArrayList<SancionSie>();
//		detSancionCargoList = new ArrayList<DetSancionCargoSie>();
//		objDetSancionCargo = new DetSancionCargoSie();
		idFactor=0;
		idSancion =0;
		
		log.info("Inicializando el Constructor de 'MantenimientoDetallePaqueteBiblicoSearchAction'");
		objDetPaqueteSie = new DetPaqueteSie();
		detPaqueteBiblicoList = new ArrayList<DetPaqueteSie>();
		//TB_PAQUETE
		objPaqueteSie = new PaqueteSie();
		detPaqueteList = new ArrayList<PaqueteSie>();
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
//	
//		objDetSancionCargo = new DetSancionCargoSie();
//		detSancionCargoList = new ArrayList<DetSancionCargoSie>();
//		objSancionSie = new SancionSie();
//		
		//DETalle paquete
		log.info("agregar()");
		setNewRecord(true);
		objDetPaqueteSie = new DetPaqueteSie();
		detPaqueteBiblicoList = new ArrayList<DetPaqueteSie>();
		objPaqueteSie = new PaqueteSie();
		
		
		return MantenimientoDetallePaqueteBiblicoSearchAction.getViewMant();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()  " );
		setNewRecord(false);
//		objDetSancionCargo = new DetSancionCargoSie();
		
		
//		detSancionCargoList = objDetSancionCargoService.listarDetSancionCargo(objSancionSie.getIdsancion());
		objDetPaqueteSie = new DetPaqueteSie();
		//OBSERVACION
		detPaqueteBiblicoList = objDetallePaqueteService.listarDetPaquetes(objPaqueteSie.getIdpaquete());
		
		for (int i = 0; i < detPaqueteBiblicoList.size(); i++) {
			DetPaqueteSie det =detPaqueteBiblicoList.get(i);
			det.setItem(i+1);
			detPaqueteBiblicoList.set(i, det);
		}
		
		return MantenimientoDetallePaqueteBiblicoSearchAction.getViewMant();
	}

	
	
	public String getViewMant() {
		return Constants.MANT_DETALLE_PAQUETEBIBLICO_FORM_PAGE;
	}
	/**
	 * actualizar el detCargoSancion
	 */
	public String updateDetCargoSancion(){
		boolean isadd=false;
		log.info("updateDetCargoSancion()  "+objAuxiSancionCargo.getItem() );
		for (int i = 0; i < detSancionCargoList.size(); i++) {
			log.info(" **** "+ detSancionCargoList.get(i).getItem()+"  "+ detSancionCargoList.get(i).getDescuento());
				if(detSancionCargoList.get(i).getTbCargoempleado().getIdcargoempleado()==idcargo){
					isadd=true;
					break;
				}
			}
		
		if(isadd==false){
			for (int i = 0; i < detSancionCargoList.size(); i++) {
			
				if(detSancionCargoList.get(i).getItem()==(objAuxiSancionCargo.getItem())){
				log.info("item ********* "+ objAuxiSancionCargo.getItem());
				detSancionCargoList.get(i).setDescuento(objAuxiSancionCargo.getDescuento());
				detSancionCargoList.get(i).setTbCargoempleado(objCargoService.buscarCargoEmpleado(idcargo));
				detSancionCargoList.get(i).setCantdiaSuspension(objAuxiSancionCargo.getCantdiaSuspension());
				log.info("descr   "+detSancionCargoList.get(i).getTbCargoempleado().getDescripcion());
				}
			}
		}else{
			mensaje = "ya existe una sanción para dicho cargo";
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		log.info("actualizo  ********* " );
		setNewRecord(false);
		return manteSancionSearch.getViewMant();
	}
	
	public String updateDeshabilitar() throws Exception{
		 
		if (log.isInfoEnabled()) {
			log.info("updateDeshabilitar()' " +itemSancionCargo);
		}
		for (int i = 0; i < detSancionCargoList.size(); i++) {
			if(detSancionCargoList.get(i).getItem()==(itemSancionCargo)){
				detSancionCargoList.remove(i);
			
				for (int j = i; j < detSancionCargoList.size(); j++) {
					log.info(" i " +i+"  j "+ j);
					i=i+1;
					detSancionCargoList.get(j).setItem(i);
					detSancionCargoList.set(j, detSancionCargoList.get(j));
				}
			}
		}
		log.info("actualizando..... ");
		return manteSancionSearch.getViewMant();
	}
    
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() {
		
		log.info("insertar()" + isNewRecord()  );
		try {
			if(isNewRecord()){
				log.info("tañano lista "+ detSancionCargoList.size() );	
				if(detSancionCargoList.size()==0){
					mensaje="Debe registrar la sanción para el tipo de cargo que corresponda";
				}else{
					mensaje =Constants.MESSAGE_REGISTRO_TITULO;
					objSancionService.insertSancion(objSancionSie, factor, detSancionCargoList);
					log.info("insertandio "  );	
				}
			}
			else{
				log.info("actualizado "+objSancionSie.getDescripcion());
				objSancionService.updateSancion(objSancionSie,detSancionCargoList);
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
		
		objSancionSie = new SancionSie();
		return manteSancionSearch.getViewMant();
	}
	
	public String agregarSancionCargo(){
		mensaje=null;
		log.info("agregarSancionCargo");
		boolean isadd = false;
		objDetSancionCargo.setTbCargoempleado(objCargoService.buscarCargoEmpleado(idcargo));
		int cantidad= detSancionCargoList.size();
		for (int i = 0; i < detSancionCargoList.size(); i++) {
			if(detSancionCargoList.get(i).getTbCargoempleado().getIdcargoempleado()==idcargo){
				isadd=true;
				break;
			}
		}
		if(isadd==true){
				mensaje = "Dicho cargo ya tiene una sanción registrada ";
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_INFO_TITULO, mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			
			if(cantidad==0){
				objDetSancionCargo.setItem(1);
				objDetSancionCargo.setIsnew("N");
				detSancionCargoList.add(objDetSancionCargo);
				objDetSancionCargo = new DetSancionCargoSie();
				isadd=true;
			}else{
				objDetSancionCargo.setItem(cantidad+1);
			}
		}
		log.info(" "+ mensaje);
		return manteSancionSearch.getViewMant();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_SANCION_FORM_LIST_PAGE;
	}

	/**
	 * @return the newRecord
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
	/**
	 * @return the detSancionList
	 */
	public List<SancionSie> getDetSancionList() {
		return detSancionList;
	}

	/**
	 * @param detSancionList the detSancionList to set
	 */
	public void setDetSancionList(List<SancionSie> detSancionList) {
		this.detSancionList = detSancionList;
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

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar 'MantenimientorSancionFormAction' ");
		detSancionList = objSancionService.listarSanciones(idFactor);
		objSancionSie = new SancionSie();
		if (detSancionList == null) {
			detSancionList = new ArrayList<SancionSie>();
		}
		return getViewList();
	}
    
	/**
	 * @return the objSancionSie
	 */
	public SancionSie getObjSancionSie() {
		return objSancionSie;
	}

	/**
	 * @param objSancionSie the objSancionSie to set
	 */
	public void setObjSancionSie(SancionSie objSancionSie) {
		this.objSancionSie = objSancionSie;
	}

	/**
	 * @return the idFactor
	 */
	public int getIdFactor() {
		return idFactor;
	}

	/**
	 * @param idFactor the idFactor to set
	 */
	public void setIdFactor(int idFactor) {
		this.idFactor = idFactor;
	}

	/**
	 * @return the idSancion
	 */
	public int getIdSancion() {
		return idSancion;
	}

	/**
	 * @param idSancion the idSancion to set
	 */
	public void setIdSancion(int idSancion) {
		this.idSancion = idSancion;
	}

	/**
	 * @return the factor
	 */
	public int getFactor() {
		return factor;
	}

	/**
	 * @param factor the factor to set
	 */
	public void setFactor(int factor) {
		this.factor = factor;
	}

	/**
	 * @return the idcargo
	 */
	public int getIdcargo() {
		return idcargo;
	}

	/**
	 * @param idcargo the idcargo to set
	 */
	public void setIdcargo(int idcargo) {
		this.idcargo = idcargo;
	}

	/**
	 * @return the detSancionCargoList
	 */
	public List<DetSancionCargoSie> getDetSancionCargoList() {
		return detSancionCargoList;
	}

	/**
	 * @param detSancionCargoList the detSancionCargoList to set
	 */
	public void setDetSancionCargoList(List<DetSancionCargoSie> detSancionCargoList) {
		this.detSancionCargoList = detSancionCargoList;
	}

	/**
	 * @return the objDetSancionCargo
	 */
	public DetSancionCargoSie getObjDetSancionCargo() {
		return objDetSancionCargo;
	}

	/**
	 * @param objDetSancionCargo the objDetSancionCargo to set
	 */
	public void setObjDetSancionCargo(DetSancionCargoSie objDetSancionCargo) {
		this.objDetSancionCargo = objDetSancionCargo;
	}

	/**
	 * @return the manteSancionSearch
	 */
	public MantenimientoSancionSearchAction getManteSancionSearch() {
		return manteSancionSearch;
	}

	/**
	 * @param manteSancionSearch the manteSancionSearch to set
	 */
	public void setManteSancionSearch(
			MantenimientoSancionSearchAction manteSancionSearch) {
		this.manteSancionSearch = manteSancionSearch;
	}

	/**
	 * @return the objAuxiSancionCargo
	 */
	public DetSancionCargoSie getObjAuxiSancionCargo() {
		return objAuxiSancionCargo;
	}

	/**
	 * @param objAuxiSancionCargo the objAuxiSancionCargo to set
	 */
	public void setObjAuxiSancionCargo(DetSancionCargoSie objAuxiSancionCargo) {
		this.objAuxiSancionCargo = objAuxiSancionCargo;
	}

	/**
	 * @return the itemSancionCargo
	 */
	public int getItemSancionCargo() {
		return itemSancionCargo;
	}

	/**
	 * @param itemSancionCargo the itemSancionCargo to set
	 */
	public void setItemSancionCargo(int itemSancionCargo) {
		this.itemSancionCargo = itemSancionCargo;
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
	 */
	public MantenimientoDetallePaqueteBiblicoSearchAction getMantenimientoDetallePaqueteBiblicoSearchAction() {
		return MantenimientoDetallePaqueteBiblicoSearchAction;
	}

	/**
	 * @param mantenimientoDetallePaqueteBiblicoSearchAction the mantenimientoDetallePaqueteBiblicoSearchAction to set
	 */
	public void setMantenimientoDetallePaqueteBiblicoSearchAction(
			MantenimientoDetallePaqueteBiblicoSearchAction mantenimientoDetallePaqueteBiblicoSearchAction) {
		MantenimientoDetallePaqueteBiblicoSearchAction = mantenimientoDetallePaqueteBiblicoSearchAction;
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
	
	

}
