package com.edicsem.pe.sie.client.action.mantenimiento;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ComisionVentaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.ComisionVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "comisionForm")
@SessionScoped
public class MantenimientoComisionFormAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private Log log = LogFactory.getLog(MantenimientoComisionFormAction.class);
	private int idcargo, idcriterio, option, idevento;
	private ComisionVentaSie objcomisionSie;
	private boolean newRecord = false, rangomayor;
	
	@ManagedProperty(value = "#{comisionSearch}")
	private MantenimientoComisionSearchAction comisionSearch;
	
	@EJB
	private ComisionVentaService objComisionService;

	public MantenimientoComisionFormAction() {
		log.info("inicializando constructor MantenimientoComisionFormAction");
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init()");
		objcomisionSie = new ComisionVentaSie();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		objcomisionSie = new ComisionVentaSie();
		option=1;
		idcargo=0;
		idcriterio=0;
		idevento=0;
		setNewRecord(true);
		return getViewMant();
	}
 
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #update()
	 */
	public String update() throws Exception {
		log.info("update()");
		idcargo = objcomisionSie.getTbCargoempleado().getIdcargoempleado();
		if(objcomisionSie.getTbTipoEventoVenta()!=null)
		idevento = objcomisionSie.getTbTipoEventoVenta().getIdtipoevento();
		if(objcomisionSie.getRangoinicial()!=null){
			option=1;
		}else 
			option=2;
		setNewRecord(false);
		return comisionSearch.getViewMant();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #insertar()
	 */
	public String insertar() {
		log.info("Entering my method 'insertar()' " );
		mensaje=null;
		String paginaRetorno="";
		//Capturando el empleado en session
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		
		try {
			if(objcomisionSie.getRangofinal()< objcomisionSie.getRangoinicial()){
				mensaje = "El rango incial ebe ser menor al rango final de ventas";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			else{
				if(isNewRecord()){
				objcomisionSie.setUsuariocreacion(sessionUsuario.getUsuario());
				objComisionService.insertComisionVenta(objcomisionSie, idcargo,idcriterio,idevento);
				objcomisionSie = new ComisionVentaSie();
				paginaRetorno = comisionSearch.listar();
				mensaje=Constants.MESSAGE_REGISTRO_TITULO;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
				}
				else{
					objcomisionSie.setUsuariomodifica(sessionUsuario.getUsuario());
					objComisionService.updateComisionVenta(objcomisionSie, idcargo,idcriterio,idevento);
					mensaje=Constants.MESSAGE_ACTUALIZO_TITULO;
					paginaRetorno = comisionSearch.listar();
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
				}
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return paginaRetorno;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	
	public String consultar() throws Exception {
		log.info("consultar() --> "+ objcomisionSie.getBase());
		//cambiar el porcentaje
		BigDecimal porc ;
		if(!objcomisionSie.getBase().toString().equalsIgnoreCase("0")){
			porc =  objcomisionSie.getBase().divide(new BigDecimal(690.0), 16, RoundingMode.HALF_UP);
		}else{
			porc= new BigDecimal(0);
		}
		objcomisionSie.setPorcentaje(porc);
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_COMISION_FORM_LIST_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_COMISION_FORM_PAGE;
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

	public ComisionVentaSie getObjcomisionSie() {
		return objcomisionSie;
	}

	public void setObjcomisionSie(ComisionVentaSie objcomisionSie) {
		this.objcomisionSie = objcomisionSie;
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
	 * @return the idcriterio
	 */
	public int getIdcriterio() {
		return idcriterio;
	}

	/**
	 * @param idcriterio the idcriterio to set
	 */
	public void setIdcriterio(int idcriterio) {
		this.idcriterio = idcriterio;
	}

	/**
	 * @return the comisionSearch
	 */
	public MantenimientoComisionSearchAction getComisionSearch() {
		return comisionSearch;
	}

	/**
	 * @param comisionSearch the comisionSearch to set
	 */
	public void setComisionSearch(MantenimientoComisionSearchAction comisionSearch) {
		this.comisionSearch = comisionSearch;
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
	 * @return the option
	 */
	public int getOption() {
		if(option==2){
			objcomisionSie.setRangoinicial(0);
		}else{
			idcriterio=0;
		}
		return option;
	}

	/**
	 * @param option the option to set
	 */
	public void setOption(int option) {
		if(option==1){
			objcomisionSie.setRangoinicial(0);
		}else{

			idcriterio=0;
		}
		this.option = option;
	}

	/**
	 * @return the idevento
	 */
	public int getIdevento() {
		return idevento;
	}

	/**
	 * @param idevento the idevento to set
	 */
	public void setIdevento(int idevento) {
		this.idevento = idevento;
	}

	/**
	 * @return the rangomayor
	 */
	public boolean isRangomayor() {
		if(rangomayor){
			objcomisionSie.setRangofinal(null);
		}
		return rangomayor;
	}

	/**
	 * @param rangomayor the rangomayor to set
	 */
	public void setRangomayor(boolean rangomayor) {
		if(rangomayor){
			objcomisionSie.setRangofinal(null);
		}
		this.rangomayor = rangomayor;
	}
	
}
