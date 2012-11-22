package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.IncidenciaSie;
import com.edicsem.pe.sie.entity.ObservacionIncidenciaSie;
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.service.facade.IncidenciaService;
import com.edicsem.pe.sie.service.facade.ObsIncidenciaService;
import com.edicsem.pe.sie.service.facade.ProveedorService;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoIncidenciaSearch")
@SessionScoped
public class MantenimientoIncidenciaSearchAction extends BaseMantenimientoAbstractAction {
	/*variables*/
	private IncidenciaSie objIncidencia;
	private List<IncidenciaSie> listaIncidencia;
	private ObservacionIncidenciaSie objObsIncidencia;
	private List<ObservacionIncidenciaSie> listaObsIncidencia;
	private boolean editMode;
	
	@EJB 
	private IncidenciaService objIncidenciaService;
	@EJB 
	private ObsIncidenciaService objObsIncidenciaService;
	
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
		objObsIncidencia = new ObservacionIncidenciaSie();
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
	
	/*método que lista*/
	/*public String listarObsInc() {
		listaObsIncidencia = objObsIncidenciaService.listarObsIncidencia(id);
		if (listaObsIncidencia == null) {
			listaObsIncidencia = new ArrayList<ObservacionIncidenciaSie>();
		}
		return "";
	}*/
	
	/*GETs Y SETs*/
	
	public String getViewList() {
		return "mantenimientoIncidenciaList";
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	@Override
	public String getViewMant() {
		return "mantenimientoIncidenciaForm";
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

	/**
	 * @return the objObsIncidencia
	 */
	public ObservacionIncidenciaSie getObjObsIncidencia() {
		return objObsIncidencia;
	}

	/**
	 * @param objObsIncidencia the objObsIncidencia to set
	 */
	public void setObjObsIncidencia(ObservacionIncidenciaSie objObsIncidencia) {
		this.objObsIncidencia = objObsIncidencia;
	}

	/**
	 * @return the listaObsIncidencia
	 */
	public List<ObservacionIncidenciaSie> getListaObsIncidencia() {
		return listaObsIncidencia;
	}

	/**
	 * @param listaObsIncidencia the listaObsIncidencia to set
	 */
	public void setListaObsIncidencia(
			List<ObservacionIncidenciaSie> listaObsIncidencia) {
		this.listaObsIncidencia = listaObsIncidencia;
	}
	
	
    
}
