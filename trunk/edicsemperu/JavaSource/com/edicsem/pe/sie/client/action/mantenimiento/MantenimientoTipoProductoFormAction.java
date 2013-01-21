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
import org.primefaces.model.StreamedContent;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.ContratoEmpleadoService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.util.FaceMessage.FaceMessage;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "mantenimientoTipoProductoFormAction")
@SessionScoped
public class MantenimientoTipoProductoFormAction extends
		BaseMantenimientoAbstractAction {

	public String idtipoproducto;
	public String descripcion;
	private int idEstadoGeneral, idc;
	private String mensaje;
	private TipoProductoSie nuevo;
	private TipoProductoSie objTipoProductoSie;
	private TipoProductoSie selectedTipoProducto;
	private boolean newRecord = false;
	private boolean editMode;
	
	@ManagedProperty(value = "#{tipoProductoSearch}")
	private MantenimientoTipoProductoSearchAction mantenimientoTipoProductoSearch;

	private static Log log = LogFactory.getLog(MantenimientoTipoProductoFormAction.class);

	@EJB
	private TipoProductoService objTipoProductoService;

	@EJB
	private EstadogeneralService objEstadoGeneralService;

	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		objTipoProductoSie = new TipoProductoSie();
		nuevo = new TipoProductoSie();
	}

	public String agregar() {
		log.info("agregar()");
		editMode = true;
		objTipoProductoSie = new TipoProductoSie();
		setNewRecord(true);
		return getViewList();
	}

	public String update() throws Exception {
		log.info("update()" + objTipoProductoSie.getIdtipoproducto());
		TipoProductoSie tp = objTipoProductoService.findTipoProducto(objTipoProductoSie.getIdtipoproducto());
		log.info(" id tipoproducto " + tp.getIdtipoproducto() + " cod "+ tp.getCodtipoproducto());		
		setIdtipoproducto(tp.getIdtipoproducto().toString());
		objTipoProductoSie.setCodtipoproducto(tp.getCodtipoproducto());
		objTipoProductoSie.setNombretipoproducto(tp.getNombretipoproducto());
		//setIdEstadoGeneral(c.getTbEstadoGeneral().getIdestadogeneral());
		setNewRecord(false);
		editMode = false;
		return getViewList();

	}

	public String insertar() {
		try {

			if (isNewRecord()) {
					objTipoProductoService.insertTipoProducto(objTipoProductoSie);
				setNewRecord(false);
			} else {
				objTipoProductoService.updateTipoProducto(objTipoProductoSie);				
				log.info("actualizando..... ");					
			}
			//mensaje
			mensaje ="";
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Constants.MESSAGE_REGISTRO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {

			e.printStackTrace();
			descripcion = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objTipoProductoSie = new TipoProductoSie();
		return mantenimientoTipoProductoSearch.listar();
	}

	public String updateDeshabilitar() throws Exception {

		objTipoProductoSie = new TipoProductoSie();
		int parametroObtenido;
		TipoProductoSie tp = new TipoProductoSie();

		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'updateDESHABILITAR()'");
			}

			parametroObtenido = getIdc();
			log.info(" ------>>>>>>aqui cactura el parametro ID "+ parametroObtenido);
			
				//if(verificar(parametroObtenido)){//true - no hay empleado con cargo, tonce procede
					
					tp = objTipoProductoService.findTipoProducto(parametroObtenido);
					
					//objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(2));
					//objCargoEmpleadoSie.setIdcargoempleado(c.getIdcargoempleado());
					//objCargoEmpleadoSie.setDescripcion(c.getDescripcion());

					objTipoProductoService.updateTipoProducto(objTipoProductoSie);
					log.info("actualizando..... ");	
				//}	
				
				//else {
					
					//FaceMessage.FaceMessageError("ALERTA", "El cargo no se puede elminar ya que se encontraron usuarios con ese cargo");
				//}	

		} catch (Exception e) {
			e.printStackTrace();
			descripcion = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objTipoProductoSie = new TipoProductoSie();
		return mantenimientoTipoProductoSearch.listar();
	}

	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}

	/**
	 * @param idEstadoGeneral the idEstadoGeneral to set
	 */
	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
	}

	/**
	 * @return the idc
	 */
	public int getIdc() {
		return idc;
	}

	/**
	 * @param idc the idc to set
	 */
	public void setIdc(int idc) {
		this.idc = idc;
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
	 * @return the nuevo
	 */
	public TipoProductoSie getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(TipoProductoSie nuevo) {
		this.nuevo = nuevo;
	}

	/**
	 * @return the objTipoProductoSie
	 */
	public TipoProductoSie getObjTipoProductoSie() {
		return objTipoProductoSie;
	}

	/**
	 * @param objTipoProductoSie the objTipoProductoSie to set
	 */
	public void setObjTipoProductoSie(TipoProductoSie objTipoProductoSie) {
		this.objTipoProductoSie = objTipoProductoSie;
	}

	/**
	 * @return the selectedTipoProducto
	 */
	public TipoProductoSie getSelectedTipoProducto() {
		return selectedTipoProducto;
	}

	/**
	 * @param selectedTipoProducto the selectedTipoProducto to set
	 */
	public void setSelectedTipoProducto(TipoProductoSie selectedTipoProducto) {
		this.selectedTipoProducto = selectedTipoProducto;
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
	 * @return the mantenimientoTipoProductoSearch
	 */
	public MantenimientoTipoProductoSearchAction getMantenimientoTipoProductoSearch() {
		return mantenimientoTipoProductoSearch;
	}

	/**
	 * @param mantenimientoTipoProductoSearch the mantenimientoTipoProductoSearch to set
	 */
	public void setMantenimientoTipoProductoSearch(
			MantenimientoTipoProductoSearchAction mantenimientoTipoProductoSearch) {
		this.mantenimientoTipoProductoSearch = mantenimientoTipoProductoSearch;
	}

	/**
	 * @return the idtipoproducto
	 */
	public String getIdtipoproducto() {
		return idtipoproducto;
	}

	/**
	 * @param idtipoproducto the idtipoproducto to set
	 */
	public void setIdtipoproducto(String idtipoproducto) {
		this.idtipoproducto = idtipoproducto;
	}
	
	public String getViewList() {
		return Constants.MANT_TIPO_PRODUCTO_FORM_LIST_PAGE;
	}
	
}
