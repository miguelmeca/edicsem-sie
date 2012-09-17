package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoCargoEmpleado")
@SessionScoped
public class MantenimientoCargoEmpleadoFormAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	public static Log log = LogFactory.getLog(MantenimientoCargoEmpleadoFormAction.class);
	private List<SelectItem> selectItems;
	private CargoEmpleadoSie objCargoEmpleadoSie;
	private CargoEmpleadoSie selectedCargoEmpleado;
	private boolean editMode;
	private CargoEmpleadoSie nuevo ;
	
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
	public CargoEmpleadoSie getSelectedCargoEmpleado() {
		return selectedCargoEmpleado;
	}
	public void setSelectedCargoEmpleado(CargoEmpleadoSie selectedCargoEmpleado) {
		this.selectedCargoEmpleado = selectedCargoEmpleado;
	}
	
	@EJB
	private CargoEmpleadoService objCargoEmpleadoService;
	
	public MantenimientoCargoEmpleadoFormAction() {
		log.info("inicializando constructor MantenimientoCargoEmpleado");
		init();
	}
	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		selectItems = new ArrayList<SelectItem>();
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		objCargoEmpleadoSie.setDescipcion("");
		nuevo = new CargoEmpleadoSie();
	}
	public void Nuevo(ActionEvent e) throws Exception {

		setObjCargoEmpleadoSie(null);
	}
	/**
	 * @return the nuevo
	 */
	public CargoEmpleadoSie getNuevo() {
		return nuevo;
	}
	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(CargoEmpleadoSie nuevo) {
		this.nuevo = nuevo;
	}
	/**
	 * @return the image
	 */
	

	/**
	 * @param image the image to set
	 */
	

	public List<SelectItem> getSelectItems() {
		List lista = new ArrayList<CargoEmpleadoSie>();
		selectItems = new ArrayList<SelectItem>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getListaCargoEmpleado()'");
			}
			lista = objCargoEmpleadoService.listarCargoEmpleado();

			for (int i = 0; i < lista.size(); i++) {
				CargoEmpleadoSie cargo = new CargoEmpleadoSie();
				if (lista.get(i) != null) {
					cargo = (CargoEmpleadoSie) lista.get(i);
					selectItems.add(new SelectItem(cargo.getIdcargoempleado(),
							cargo.getDescipcion()));
			}else {
				break;
			}
		}
		log.info("finalizacion del metodo 'getSelectItems'");
		
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return selectItems;
	}

	public String insertar() throws Exception {
		return getViewList();
	}

	

	/**
	 * @param selectItems
	 *            the selectItems to set
	 */
	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}



	/**
	 * @return the tipoProducto
	 */
	

	/**
	 * @param tipoProducto
	 *            the tipoProducto to set
	 */
	
	/**
	 * @return the objProductoSie
	 */
	public CargoEmpleadoSie getObjCargoEmpleadoSie() {
		return objCargoEmpleadoSie;
	}

	/**
	 * @param objProductoSie
	 *            the objProductoSie to set
	 */
	public void setObjCargoEmpleadoSie(CargoEmpleadoSie objCargoEmpleadoSie) {
		this.objCargoEmpleadoSie = objCargoEmpleadoSie;
	}

}
