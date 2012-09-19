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

import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoTipoDocumentoIdentidad")
@SessionScoped
public class MantenimientoTipoDocumentoIdentidadFormAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	public static Log log = LogFactory.getLog(MantenimientoTipoDocumentoIdentidadFormAction.class);
	private List<SelectItem> selectItems;
	private TipoDocumentoIdentidadSie objTipoDocIdentSie;
	private TipoDocumentoIdentidadSie selectedTipoDocIdent;
	private boolean editMode;
	private TipoDocumentoIdentidadSie nuevo ;
	
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
	public TipoDocumentoIdentidadSie getSelectedTipoDocumento() {
		return selectedTipoDocIdent;
	}
	public void setSelectedTipoDocumento(TipoDocumentoIdentidadSie selectedTipoDocumento) {
		this.selectedTipoDocIdent = selectedTipoDocumento;
	}
	
	@EJB
	private TipoDocumentoService objTipoDocumentoService;
	
	public MantenimientoTipoDocumentoIdentidadFormAction() {
		log.info("inicializando constructor MantenimientoTipoDocumento");
		init();
	}
	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		selectItems = new ArrayList<SelectItem>();
		objTipoDocIdentSie = new TipoDocumentoIdentidadSie();
		objTipoDocIdentSie.setDescripcion("");
		nuevo = new TipoDocumentoIdentidadSie();
	}
	public void Nuevo(ActionEvent e) throws Exception {

		setObjTipoDocumentoIdentidadSie(null);
	}
	/**
	 * @return the nuevo
	 */
	public TipoDocumentoIdentidadSie getNuevo() {
		return nuevo;
	}
	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(TipoDocumentoIdentidadSie nuevo) {
		this.nuevo = nuevo;
	}
	/**
	 * @return the image
	 */
	

	/**
	 * @param image the image to set
	 */
	

	public List<SelectItem> getSelectItems() {
		List lista = new ArrayList<TipoDocumentoIdentidadSie>();
		selectItems = new ArrayList<SelectItem>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getListaTipoDocumentoIdentidad()'");
			}
			lista = objTipoDocumentoService.listarTipoDocumentos();

			for (int i = 0; i < lista.size(); i++) {
				TipoDocumentoIdentidadSie tipo = new TipoDocumentoIdentidadSie();
				if (lista.get(i) != null) {
					tipo = (TipoDocumentoIdentidadSie) lista.get(i);
					selectItems.add(new SelectItem(tipo.getIdtipodocumentoidentidad(),
							tipo.getDescripcion()));
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
	public TipoDocumentoIdentidadSie getObjTipoDocumentoIdentidadSie() {
		return objTipoDocIdentSie;
	}

	/**
	 * @param objProductoSie
	 *            the objProductoSie to set
	 */
	public void setObjTipoDocumentoIdentidadSie(TipoDocumentoIdentidadSie objTipoDocumentoIdentidadSie) {
		this.objTipoDocIdentSie = objTipoDocumentoIdentidadSie;
	}

}
