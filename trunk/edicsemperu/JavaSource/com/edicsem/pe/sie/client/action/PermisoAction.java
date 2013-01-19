package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DualListModel;

import com.edicsem.pe.sie.entity.PermisoSie;
import com.edicsem.pe.sie.service.facade.DetPermisoEmpleadoService;
import com.edicsem.pe.sie.service.facade.PermisoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "permiso")
@SessionScoped
public class PermisoAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private boolean editMode;
	private DualListModel<PermisoSie> permisos;
	private DualListModel<String> permisosString;
	private Log log = LogFactory.getLog(PermisoAction.class);
	private List<PermisoSie> source;  
    private List<PermisoSie> target;  
    private List<String> sources;  
    private List<String> targets; 
    private int idempleado;
    
	@EJB
	private PermisoService objPermisoService;
	@EJB
	private DetPermisoEmpleadoService objDetPermisoService;
	
	public PermisoAction() {
		log.info("inicializando constructor PermisoAction");
		init();
	}

	public void init() {
		log.info("init()");
		sources = new ArrayList<String>();
		targets = new ArrayList<String>();
		permisosString= new DualListModel<String>(sources, targets);
		source = new ArrayList<PermisoSie>();
		target = new ArrayList<PermisoSie>();
		permisos = new DualListModel<PermisoSie>(source, target);
	}
	
	public String agregarListas() {
		log.info("agregarrr ()");
	
		permisosString = new DualListModel<String>(new ArrayList<String>(), new ArrayList<String>());
		log.info("listarPermisosXEmpleado()mmm");
		targets= objPermisoService.listarPermisosXEmpleado(idempleado);
		log.info("targets()"+targets.size());
		sources= objPermisoService.listarPermiso();
		log.info("sources()"+sources.size());
		
		for (int i = 0; i < targets.size(); i++) {
			for (int j = 0; j < sources.size(); j++) {
				log.info("aqui   "+targets.get(i)+" -  "+sources.get(j));
				if(targets.get(i).equalsIgnoreCase(sources.get(j))){
					log.info("* desc * "+targets.get(i));
					sources.remove(j);
					log.info(sources.size()+"  sources())   " ); 
				}
			}
		}
		permisosString = new DualListModel<String>(sources, targets);
		
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
			log.info(" *************** INSERTAR *********" +targets.size() );
			log.info("xxx "+permisosString.getTarget());
			 
			objDetPermisoService.insertDetPermisoEmpleado(permisosString.getTarget(),idempleado);
			log.info(" "+targets);
			
			mensaje = Constants.MESSAGE_REGISTRO_TITULO;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return listar();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.ASIGNAR_PERMISOS_FORM_PAGE;
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
	 * @return the source
	 */
	public List<PermisoSie> getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(List<PermisoSie> source) {
		this.source = source;
	}

	/**
	 * @return the target
	 */
	public List<PermisoSie> getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(List<PermisoSie> target) {
		this.target = target;
	}

	/**
	 * @return the idempleado
	 */
	public int getIdempleado() {
		return idempleado;
	}

	/**
	 * @param idempleado the idempleado to set
	 */
	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
	}

	/**
	 * @return the permisos
	 */
	public DualListModel<PermisoSie> getPermisos() {
		return permisos;
	}

	/**
	 * @param permisos the permisos to set
	 */
	public void setPermisos(DualListModel<PermisoSie> permisos) {
		this.permisos = permisos;
	}

	/**
	 * @return the permisosString
	 */
	public DualListModel<String> getPermisosString() {
		return permisosString;
	}

	/**
	 * @param permisosString the permisosString to set
	 */
	public void setPermisosString(DualListModel<String> permisosString) {
		this.permisosString = permisosString;
	}

	/**
	 * @return the sources
	 */
	public List<String> getSources() {
		return sources;
	}

	/**
	 * @param sources the sources to set
	 */
	public void setSources(List<String> sources) {
		this.sources = sources;
	}

	/**
	 * @return the targets
	 */
	public List<String> getTargets() {
		return targets;
	}

	/**
	 * @param targets the targets to set
	 */
	public void setTargets(List<String> targets) {
		this.targets = targets;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {

		sources = new ArrayList<String>();
		targets = new ArrayList<String>();
		permisosString= new DualListModel<String>(sources, targets);
		source = new ArrayList<PermisoSie>();
		target = new ArrayList<PermisoSie>();
		permisos = new DualListModel<PermisoSie>(source, target);
		return getViewMant();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.ASIGNAR_PERMISOS_FORM_PAGE;
	}

}
