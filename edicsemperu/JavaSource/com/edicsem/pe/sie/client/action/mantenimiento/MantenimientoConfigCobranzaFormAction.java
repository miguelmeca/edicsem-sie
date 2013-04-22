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

import com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie;
import com.edicsem.pe.sie.service.facade.ConfigCobranzaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="configCobranzaOperaFormAction")
@SessionScoped
public class MantenimientoConfigCobranzaFormAction extends BaseMantenimientoAbstractAction {
    /*Se crean los objetos de las entidades proveedor*/	
	private ConfigCobranzaOperaSie objConfig;
	/*variables*/
	private String mensaje;
	private int idtipocobranza, idtipocliente;
	private List<String> diasList;
	/*variable boolean necesaria*/
	private boolean newRecord =false;
	
	/**
	 * @return the idtipocobranza
	 */
	public int getIdtipocobranza() {
		return idtipocobranza;
	}
	/**
	 * @param idtipocobranza the idtipocobranza to set
	 */
	public void setIdtipocobranza(int idtipocobranza) {
		this.idtipocobranza = idtipocobranza;
	}
	/**
	 * @return the idtipocliente
	 */
	public int getIdtipocliente() {
		return idtipocliente;
	}
	/**
	 * @param idtipocliente the idtipocliente to set
	 */
	public void setIdtipocliente(int idtipocliente) {
		this.idtipocliente = idtipocliente;
	}
	/**
	 * @return the diasList
	 */
	public List<String> getDiasList() {
		return diasList;
	}
	/**
	 * @param diasList the diasList to set
	 */
	public void setDiasList(List<String> diasList) {
		this.diasList = diasList;
	}
	@ManagedProperty(value = "#{mantenimientoProveedorSearchAction}")
	private MantenimientoConfigCobranzaOperaSearchAction mantenimientoConfigSearch;
	
	@EJB
	private ConfigCobranzaService objConfigService;
	
	public static Log log = LogFactory.getLog(MantenimientoProveedorFormAction.class);
	
	public MantenimientoConfigCobranzaFormAction() {
		log.info("inicializando mi constructor");
		init();
	}
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init()");
	} 
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */ 
	public String agregar() {
		log.info("agregar");
		objConfig = new ConfigCobranzaOperaSie();
		setNewRecord(true);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()");
		setNewRecord(false);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		String paginaRetorno="";
		mensaje=null;
		try {
			if (isNewRecord()) {
				objConfigService.insertConfigCobranza(objConfig);
				mensaje ="Se registró la configuracion correctamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			}else {
				objConfigService.updateConfigCobranza(objConfig);
				mensaje ="Se atualizó la configuracion correctamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			objConfig = new ConfigCobranzaOperaSie();
			paginaRetorno =mantenimientoConfigSearch.listar();
		}catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);	
		return paginaRetorno;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_PROVEEDOR_FORM_PAGE;
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
	 * @return the objConfig
	 */
	public ConfigCobranzaOperaSie getObjConfig() {
		return objConfig;
	}
	/**
	 * @param objConfig the objConfig to set
	 */
	public void setObjConfig(ConfigCobranzaOperaSie objConfig) {
		this.objConfig = objConfig;
	}
}
