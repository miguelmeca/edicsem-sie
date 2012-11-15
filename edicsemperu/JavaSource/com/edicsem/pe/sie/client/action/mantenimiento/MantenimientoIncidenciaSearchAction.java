package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.IncidenciaSie;
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.service.facade.IncidenciaService;
import com.edicsem.pe.sie.service.facade.ProveedorService;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoIncidenciaSearch")
@SessionScoped
public class MantenimientoIncidenciaSearchAction extends BaseMantenimientoAbstractAction {
	/*variables*/
	private IncidenciaSie objIncidencia;
	private List<IncidenciaSie> listaIncidencia;
	private boolean editMode;
	
	@EJB 
	private IncidenciaService objIncidenciaService;
	
	public static Log log = LogFactory.getLog(MantenimientoIncidenciaSearchAction.class);
	
	public MantenimientoIncidenciaSearchAction() {
		log.info("ESTOY EN MI CONSTRUCTOR");
		log.info("inicializando mi constructor");
		init();
	}

	/*inicializamos los  objetos utilizados*/
	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		objIncidencia = new IncidenciaSie();
		log.info("despues de inicializar  ");		
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	
	/*método que lista al hacer click en el menú del template*/
	public String listar() {
		log.info("listaIncidencia 'MantenimientoIncidenciaSearchAction' ");
		listaIncidencia = objIncidenciaService.listarIncidencia();
		if (listaIncidencia == null) {
			listaIncidencia = new ArrayList<IncidenciaSie>();
		}
		return getViewList();
	}
	
	/*GETs Y SETs*/
	
	public String getViewList() {
		return "mantenimientoIncidenciaList";
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
	 * @return the objIncidencia
	 */
	public IncidenciaSie getObjIncidencia() {
		return objIncidencia;
	}

	/**
	 * @param objIncidencia the objIncidencia to set
	 */
	public void setObjIncidencia(IncidenciaSie objIncidencia) {
		this.objIncidencia = objIncidencia;
	}

	/**
	 * @return the listaIncidencia
	 */
	public List<IncidenciaSie> getListaIncidencia() {
		return listaIncidencia;
	}

	/**
	 * @param listaIncidencia the listaIncidencia to set
	 */
	public void setListaIncidencia(List<IncidenciaSie> listaIncidencia) {
		this.listaIncidencia = listaIncidencia;
	}
	
	
    
}
