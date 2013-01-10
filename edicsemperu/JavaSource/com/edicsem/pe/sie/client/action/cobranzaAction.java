package com.edicsem.pe.sie.client.action;

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

import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.service.facade.CobranzaOperaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "cobranza")
@SessionScoped
public class cobranzaAction extends BaseMantenimientoAbstractAction {
	
	private Log log = LogFactory.getLog(cobranzaAction.class);
	
	private String mensaje;
	private boolean editMode;
    private int cantOperadora;
    private List<CobranzaSie> cobranzaList;
    private List<String> empleadoList;
    
	@EJB
	private CobranzaOperaService objCobranzaOperaService;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;
	
	public cobranzaAction() {
		log.info("inicializando constructor cobranzaAction");
		init();
	}
	
	public void init() {
		log.info("init()  -->");
		empleadoList = new ArrayList<String>();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info(" 7 :D ");
		comboManager.setCargoEmpleado(7);
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		mensaje=null;
		
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()' :D  " );
			}
			log.info(" *************** INSERTAR *********"  );
			
			/** Insertamos las listas de cobranzas para cada teleoperadora asignada */
			objCobranzaOperaService.insertCobranzaOpera(empleadoList);
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.GENERAR_LISTAS_COBRANZA_FORM_PAGE;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return super.getViewMant();
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
	 * @return the cantOperadora
	 */
	public int getCantOperadora() {
		return cantOperadora;
	}

	/**
	 * @param cantOperadora the cantOperadora to set
	 */
	public void setCantOperadora(int cantOperadora) {
		this.cantOperadora = cantOperadora;
	}

	/**
	 * @return the cobranzaList
	 */
	public List<CobranzaSie> getCobranzaList() {
		return cobranzaList;
	}

	/**
	 * @param cobranzaList the cobranzaList to set
	 */
	public void setCobranzaList(List<CobranzaSie> cobranzaList) {
		this.cobranzaList = cobranzaList;
	}

	/**
	 * @return the empleadoList
	 */
	public List<String> getEmpleadoList() {
		return empleadoList;
	}

	/**
	 * @param empleadoList the empleadoList to set
	 */
	public void setEmpleadoList(List<String> empleadoList) {
		this.empleadoList = empleadoList;
	}

	/**
	 * @return the comboManager
	 */
	public ComboAction getComboManager() {
		return comboManager;
	}

	/**
	 * @param comboManager the comboManager to set
	 */
	public void setComboManager(ComboAction comboManager) {
		comboManager.setCargoEmpleado(7);
		this.comboManager = comboManager;
	}

}
