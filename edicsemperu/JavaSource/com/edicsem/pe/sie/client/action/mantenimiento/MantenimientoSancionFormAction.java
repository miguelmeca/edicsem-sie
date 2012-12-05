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
import org.primefaces.event.RowEditEvent;

import com.edicsem.pe.sie.entity.DetSancionCargoSie;
import com.edicsem.pe.sie.entity.SancionSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.DetSancionCargoService;
import com.edicsem.pe.sie.service.facade.SancionService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "SancionForm")
@SessionScoped
public class MantenimientoSancionFormAction extends BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoSancionFormAction.class);
	
	public String mensaje;
	private boolean newRecord = false;
	private SancionSie objSancionSie;
	private List<SancionSie> detSancionList;
	private List<DetSancionCargoSie> detSancionCargoList;
	private int idFactor,idSancion, factor;
	private int idcargo;
	private DetSancionCargoSie objDetSancionCargo;
	
	@EJB
	private SancionService objSancionService;
	@EJB
	private CargoEmpleadoService objCargoService;
	@EJB
	private DetSancionCargoService objDetSancionCargoService;
	
	@ManagedProperty(value = "#{SancionSearch}")
	private MantenimientoSancionSearchAction manteSancionSearch;
	
	public MantenimientoSancionFormAction() {
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
		log.info("Inicializando el Constructor de 'MantenimientoSancionFormAction'");
		objSancionSie = new SancionSie();
		detSancionList = new ArrayList<SancionSie>();
		detSancionCargoList = new ArrayList<DetSancionCargoSie>();
		objDetSancionCargo = new DetSancionCargoSie();
		idFactor=0;
		idSancion =0;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		setNewRecord(true);
		objDetSancionCargo = new DetSancionCargoSie();
		detSancionCargoList = new ArrayList<DetSancionCargoSie>();
		objSancionSie = new SancionSie();
		return manteSancionSearch.getViewMant();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()  " );
		setNewRecord(false);
		objDetSancionCargo = new DetSancionCargoSie();
		detSancionCargoList = objDetSancionCargoService.listarDetSancionCargo(objSancionSie.getIdsancion());
		return manteSancionSearch.getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() {
		
		log.info("insertar()" + isNewRecord()  );
		try {
			if(isNewRecord()){
				log.info("ta�ano lista "+ detSancionCargoList.size() );	
				if(detSancionCargoList.size()==0){
					mensaje="Debe registrar la sanci�n para el tipo de cargo que corresponda";
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
		
		for (int i = 0; i < detSancionCargoList.size(); i++) {
			if(detSancionCargoList.get(i).getTbCargoempleado().getIdcargoempleado()==idcargo){
				isadd=true;
				break;
			}
		}
		if(isadd==true){
				mensaje = "Dicho cargo ya tiene una sanci�n registrada ";
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_INFO_TITULO, mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			objDetSancionCargo.setIsnew("N");
			detSancionCargoList.add(objDetSancionCargo);
			objDetSancionCargo = new DetSancionCargoSie();
			isadd=true;
		}
		log.info(" "+ mensaje);
		return manteSancionSearch.getViewMant();
	}
	
	public void onEditDetSancion(RowEditEvent event) {
		log.info("en onEditDetPaquete() ");
			
		for (int i = 0; i < detSancionCargoList.size(); i++) {
			log.info(" ------- "+i+"  "+detSancionCargoList.get(i).getTbCargoempleado().getDescripcion());
		}
	}
	
	public void onCancelDetSancion (RowEditEvent event) {
		
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

}
