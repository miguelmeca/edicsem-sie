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

import com.edicsem.pe.sie.entity.TipoRefinanciaSie;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.TipoRefinanciaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="configRefinanciaFormAction")
@SessionScoped
public class MantenimientoConfigRefinanciaFormAction extends BaseMantenimientoAbstractAction {
	private TipoRefinanciaSie objConfig;
	private String mensaje, descripcionUpdate;
	private int idtipocliente;
	private boolean newRecord =false;
	List<String> lista ;
	@ManagedProperty(value = "#{configTipoRefinanSearchAction}")
	private MantenimientoConfigRefinanciaSearchAction mantenimientoConfigSearch;
	
	@EJB
	private TipoRefinanciaService objConfigService;
	@EJB
	private EstadogeneralService objEstadoService;
	
	public static Log log = LogFactory.getLog(MantenimientoConfigRefinanciaFormAction.class);
	
	public MantenimientoConfigRefinanciaFormAction() {
		log.info("inicializando mi constructor 'MantenimientoConfigRefinanciaFormAction'");
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
		objConfig = new TipoRefinanciaSie();
		idtipocliente=0;
		descripcionUpdate="";
		lista  = new ArrayList<String>();
		setNewRecord(true);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()");
		descripcionUpdate = objConfig.getDescripcion();
		idtipocliente = objConfig.getTbTipoCliente().getIdtipocliente();
		setNewRecord(false);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("insertar() " +mantenimientoConfigSearch.getConfigList().size());
		String paginaRetorno=null;
		mensaje=null;
		
		for (int i = 0; i < mantenimientoConfigSearch.getConfigList().size(); i++) {
			log.info("add " +mantenimientoConfigSearch.getConfigList().get(i).getDescripcion());
			lista.add(mantenimientoConfigSearch.getConfigList().get(i).getDescripcion());
		}
		if(!isNewRecord()){
			for (int i = 0; i < mantenimientoConfigSearch.getConfigList().size(); i++) {
				log.info("up "+descripcionUpdate+" des "+mantenimientoConfigSearch.getConfigList().get(i).getDescripcion());
				if(descripcionUpdate.equalsIgnoreCase(mantenimientoConfigSearch.getConfigList().get(i).getDescripcion())){
				lista.remove(i);
				break;
				}
			}
		}
		try {
			if(objConfig.getFechainicio().after(objConfig.getFechafin())){
				mensaje="La fecha de Inicio no puede ser mayor a la fecha fin";
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			else if(lista.contains(objConfig.getDescripcion())){
				log.info(" contain ");
				mensaje="Dicha descripción ya se encuentra registrada con otra configuración";
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			else{
				if (isNewRecord()){
					objConfig.setDescripcion(objConfig.getDescripcion().trim());
					objConfigService.insertTipoRefinancia(objConfig, idtipocliente);
					mensaje ="Se registró la configuración correctamente";
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
				}else {
					objConfigService.updateTipoRefinancia(objConfig,idtipocliente);
					mensaje ="Se atualizó la configuración correctamente";
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
				}
				objConfig = new TipoRefinanciaSie();
				paginaRetorno =mantenimientoConfigSearch.listar();
			}
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
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#delete()
	 */
	public String delete() throws Exception {
		log.info("delete()");
		idtipocliente = objConfig.getTbTipoCliente().getIdtipocliente();
		objConfig.setTbEstadoGeneral(objEstadoService.findEstadogeneral(22));
		objConfigService.updateTipoRefinancia(objConfig,idtipocliente);
		mensaje ="Se deshabilitó la configuración correctamente";
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_CONFIG_TIPO_REFINAN_FORM;
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
	 * @return the objConfig
	 */
	public TipoRefinanciaSie getObjConfig() {
		return objConfig;
	}

	/**
	 * @param objConfig the objConfig to set
	 */
	public void setObjConfig(TipoRefinanciaSie objConfig) {
		this.objConfig = objConfig;
	}

	/**
	 * @return the mantenimientoConfigSearch
	 */
	public MantenimientoConfigRefinanciaSearchAction getMantenimientoConfigSearch() {
		return mantenimientoConfigSearch;
	}

	/**
	 * @param mantenimientoConfigSearch the mantenimientoConfigSearch to set
	 */
	public void setMantenimientoConfigSearch(
			MantenimientoConfigRefinanciaSearchAction mantenimientoConfigSearch) {
		this.mantenimientoConfigSearch = mantenimientoConfigSearch;
	}

	public String getDescripcionUpdate() {
		return descripcionUpdate;
	}

	public void setDescripcionUpdate(String descripcionUpdate) {
		this.descripcionUpdate = descripcionUpdate;
	}
}
