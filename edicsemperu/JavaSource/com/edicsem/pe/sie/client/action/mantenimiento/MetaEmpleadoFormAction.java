package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.MetaEmpleadoSie;
import com.edicsem.pe.sie.service.facade.MetaEmpleadoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "metaEmpleadoForm")
@SessionScoped
public class MetaEmpleadoFormAction extends BaseMantenimientoAbstractAction {
	
	private int ide,idmetames,idEmpleado,idEmpresa;
	private boolean editMode;
	private MetaEmpleadoSie objMetaEmpleado;
	private String mensaje;
	private Log log = LogFactory.getLog(MetaEmpleadoFormAction.class);
	private boolean newRecord = false;
	
	@ManagedProperty(value = "#{metaEmpleadoSearch}")
	private MetaEmpleadoSearchAction manteMetaEmpleadoSearch;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboAction;
	
	@EJB
	private MetaEmpleadoService metaEmpleadoService;

	public MetaEmpleadoFormAction() {
		init();
	}

	public void init() {
		log.info("Inicializando el Constructor de 'MetaEmpleadoFormAction'");
		objMetaEmpleado = new MetaEmpleadoSie();
		idmetames=0;
		idEmpresa=0;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()" );
		idmetames=0;
		setNewRecord(true);
		objMetaEmpleado = new MetaEmpleadoSie();
		return manteMetaEmpleadoSearch.getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()" );
		objMetaEmpleado= metaEmpleadoService.findMetaEmpleado(getIde());
		idmetames = objMetaEmpleado.getTbMetaMes().getIdmetames();
		log.info(" id   "+idmetames);
		setNewRecord(true);
		editMode = true;
		return manteMetaEmpleadoSearch.getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() {
		
		log.info("insertar()" );
		try {
			if (isNewRecord()) {
				metaEmpleadoService.insertMetaEmpleado(objMetaEmpleado, idmetames);
				mensaje="Se registro correctamente";
				
			} else {
				metaEmpleadoService.updateMetaEmpleado(objMetaEmpleado);
				mensaje="Se actualizó correctamente";
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		objMetaEmpleado = new MetaEmpleadoSie();
		return manteMetaEmpleadoSearch.getViewMant();
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
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * @return the idmetames
	 */
	public int getIdmetames() {
		return idmetames;
	}

	/**
	 * @param idmetames the idmetames to set
	 */
	public void setIdmetames(int idmetames) {
		this.idmetames = idmetames;
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
	 * @return the ide
	 */
	public int getIde() {
		return ide;
	}

	/**
	 * @param ide the ide to set
	 */
	public void setIde(int ide) {
		this.ide = ide;
	}

	/**
	 * @return the objMetaEmpleado
	 */
	public MetaEmpleadoSie getObjMetaEmpleado() {
		return objMetaEmpleado;
	}

	/**
	 * @param objMetaEmpleado the objMetaEmpleado to set
	 */
	public void setObjMetaEmpleado(MetaEmpleadoSie objMetaEmpleado) {
		this.objMetaEmpleado = objMetaEmpleado;
	}

	/**
	 * @return the idEmpleado
	 */
	public int getIdEmpleado() {
		return idEmpleado;
	}

	/**
	 * @param idEmpleado the idEmpleado to set
	 */
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	/**
	 * @return the manteMetaEmpleadoSearch
	 */
	public MetaEmpleadoSearchAction getManteMetaEmpleadoSearch() {
		return manteMetaEmpleadoSearch;
	}

	/**
	 * @param manteMetaEmpleadoSearch the manteMetaEmpleadoSearch to set
	 */
	public void setManteMetaEmpleadoSearch(
			MetaEmpleadoSearchAction manteMetaEmpleadoSearch) {
		this.manteMetaEmpleadoSearch = manteMetaEmpleadoSearch;
	}

	/**
	 * @return the idEmpresa
	 */
	public int getIdEmpresa() {
		log.info("empresa :D  "  + idEmpresa );
		return idEmpresa;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(int idEmpresa) {
		log.info("empresa :D  "  + idEmpresa );
		comboAction.setIdEmpresa(idEmpresa);
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return the comboAction
	 */
	public ComboAction getComboAction() {
		return comboAction;
	}

	/**
	 * @param comboAction the comboAction to set
	 */
	public void setComboAction(ComboAction comboAction) {
		this.comboAction = comboAction;
	}

}
