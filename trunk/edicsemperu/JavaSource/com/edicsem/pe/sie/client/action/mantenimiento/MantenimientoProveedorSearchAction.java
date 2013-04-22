package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.service.facade.ProveedorService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoProveedorSearchAction")
@SessionScoped
public class MantenimientoProveedorSearchAction extends BaseMantenimientoAbstractAction {
    
	/*variables*/
	private ProveedorSie objProveedor;
	private List<ProveedorSie> proveedorList;
	private boolean editMode;
	
	@EJB 
	private ProveedorService objProveedorService;
	
	public static Log log = LogFactory.getLog(MantenimientoProveedorSearchAction.class);
	
	public MantenimientoProveedorSearchAction() {
		log.info("inicializando mi constructor");
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		objProveedor = new ProveedorSie();	
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarProveedor 'MantenimientoProveedorSearchAction' ");
		proveedorList = objProveedorService.listarProveedores();
		if (proveedorList == null) {
			proveedorList = new ArrayList<ProveedorSie>();
		}
		return getViewList();
	}
	
	/*GETs Y SETs*/
	
	public String getViewList() {
		return Constants.MANT_PROVEEDOR_FORM_LIST_PAGE;
	}

	/**
	 * @return the objProveedor
	 */
	public ProveedorSie getObjProveedor() {
		return objProveedor;
	}

	/**
	 * @param objProveedor the objProveedor to set
	 */
	public void setObjProveedor(ProveedorSie objProveedor) {
		this.objProveedor = objProveedor;
	}

	/**
	 * @return the proveedorList
	 */
	public List<ProveedorSie> getProveedorList() {
		return proveedorList;
	}

	/**
	 * @param proveedorList the proveedorList to set
	 */
	public void setProveedorList(List<ProveedorSie> proveedorList) {
		this.proveedorList = proveedorList;
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
    
}
