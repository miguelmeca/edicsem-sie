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

import com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie;
import com.edicsem.pe.sie.service.facade.ConfigCobranzaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="configCobranzaOperaFormAction")
@SessionScoped
public class MantenimientoConfigCobranzaFormAction extends BaseMantenimientoAbstractAction {
	private ConfigCobranzaOperaSie objConfig;
	private String mensaje;
	private int idtipocobranza, idtipocliente;
	private List<String> diasList;
	private boolean newRecord =false;
	
	@ManagedProperty(value = "#{configCobranzaOperaSearchAction}")
	private MantenimientoConfigCobranzaOperaSearchAction mantenimientoConfigSearch;
	
	@EJB
	private ConfigCobranzaService objConfigService;
	
	public static Log log = LogFactory.getLog(MantenimientoConfigCobranzaFormAction.class);
	
	public MantenimientoConfigCobranzaFormAction() {
		log.info("inicializando mi constructor 'MantenimientoConfigCobranzaFormAction'");
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
		log.info("agregar ");
		objConfig = new ConfigCobranzaOperaSie();
		diasList= new ArrayList<String>();
		idtipocobranza=0;
		idtipocliente=0;
		setNewRecord(true);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()");
		diasList= new ArrayList<String>();
		String[] diass=objConfig.getDias().split(",");
		for (int i = 0; i < diass.length; i++) {
			diasList.add(diass[i]);
		}
		idtipocliente = objConfig.getTbTipoCliente().getIdtipocliente();
		idtipocobranza= objConfig.getTbTipoCobranza().getIdtipocobranza();
		setNewRecord(false);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("insertar()");
		String paginaRetorno="";
		mensaje=null;
		try {
			String dias="";
			for (int i = 0; i < diasList.size(); i++) {
				if(i==0){
					dias=""+diasList.get(i);
				}else{
					dias+=","+diasList.get(i);
				}
			}
			objConfig.setDias(dias);
			
			if (isNewRecord()){
				objConfigService.insertConfigCobranza(objConfig, idtipocobranza,idtipocliente);
				mensaje ="Se registró la configuracion correctamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			}else {
				objConfigService.updateConfigCobranza(objConfig, idtipocobranza,idtipocliente);
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
		return Constants.MANT_CONFIG_COBRANZA_OPERA_FORM;
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
	 * @return the mantenimientoConfigSearch
	 */
	public MantenimientoConfigCobranzaOperaSearchAction getMantenimientoConfigSearch() {
		return mantenimientoConfigSearch;
	}
	/**
	 * @param mantenimientoConfigSearch the mantenimientoConfigSearch to set
	 */
	public void setMantenimientoConfigSearch(MantenimientoConfigCobranzaOperaSearchAction mantenimientoConfigSearch) {
		this.mantenimientoConfigSearch = mantenimientoConfigSearch;
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
}
