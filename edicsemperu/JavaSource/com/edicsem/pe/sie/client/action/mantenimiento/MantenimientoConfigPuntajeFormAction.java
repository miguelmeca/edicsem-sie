package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ConfigPuntajeSie;
import com.edicsem.pe.sie.service.facade.ConfigPuntajeService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="configPuntajeFormAction")
@SessionScoped
public class MantenimientoConfigPuntajeFormAction extends BaseMantenimientoAbstractAction {
	private ConfigPuntajeSie objConfig;
	private String mensaje;
	private int idparametro, idtipocliente, idcargo;
	private boolean newRecord =false;
	
	@ManagedProperty(value = "#{configPuntajeSearchAction}")
	private MantenimientoConfigPuntajeSearchAction mantenimientoConfigSearch;
	
	@EJB
	private ConfigPuntajeService objConfigService;
	
	public static Log log = LogFactory.getLog(MantenimientoConfigPuntajeFormAction.class);
	
	public MantenimientoConfigPuntajeFormAction() {
		log.info("inicializando mi constructor 'MantenimientoConfigPuntajeFormAction'");
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
		log.info("agregar() ");
		objConfig = new ConfigPuntajeSie();
		objConfig.setValor(1);
		idparametro=0;
		idtipocliente=0;
		idtipocliente=0;
		setNewRecord(true);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()");
		idtipocliente = objConfig.getTbTipoCliente().getIdtipocliente();
		idcargo =objConfig.getTbCargoempleado().getIdcargoempleado();
		idparametro=objConfig.getTbParametroActividad().getIdparametroactividad();
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
			
			if (isNewRecord()){
				objConfigService.insertConfigPuntaje(objConfig, idparametro,idtipocliente, idcargo);
				mensaje ="Se registró la configuración correctamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			}else {
				objConfigService.updateConfigPuntaje(objConfig, idparametro,idtipocliente, idcargo);
				mensaje ="Se atualizó la configuración correctamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			objConfig = new ConfigPuntajeSie();
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
		return Constants.MANT_CONFIG_PUNTAJE_FORM;
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
	public ConfigPuntajeSie getObjConfig() {
		return objConfig;
	}

	/**
	 * @param objConfig the objConfig to set
	 */
	public void setObjConfig(ConfigPuntajeSie objConfig) {
		this.objConfig = objConfig;
	}

	/**
	 * @return the idparametro
	 */
	public int getIdparametro() {
		return idparametro;
	}

	/**
	 * @param idparametro the idparametro to set
	 */
	public void setIdparametro(int idparametro) {
		this.idparametro = idparametro;
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
	 * @return the mantenimientoConfigSearch
	 */
	public MantenimientoConfigPuntajeSearchAction getMantenimientoConfigSearch() {
		return mantenimientoConfigSearch;
	}

	/**
	 * @param mantenimientoConfigSearch the mantenimientoConfigSearch to set
	 */
	public void setMantenimientoConfigSearch(
			MantenimientoConfigPuntajeSearchAction mantenimientoConfigSearch) {
		this.mantenimientoConfigSearch = mantenimientoConfigSearch;
	}
}
