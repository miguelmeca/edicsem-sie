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
import com.edicsem.pe.sie.service.facade.PermisoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "permiso")
@SessionScoped
public class PermisoAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private boolean editMode;
	private DualListModel<PermisoSie> permisos;
	private Log log = LogFactory.getLog(PermisoAction.class);
	private List<PermisoSie> source;  
    private List<PermisoSie> target;  
    private int idempleado;
    
	@EJB
	private PermisoService objPermisoService;
	
	public PermisoAction() {
		log.info("inicializando constructor PermisoAction");
		init();
	}

	public void init() {
		log.info("init()");
		source = new ArrayList<PermisoSie>();
		target = new ArrayList<PermisoSie>();
		permisos = new DualListModel<PermisoSie>(source, target);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	@SuppressWarnings("unchecked")
	public String agregar() {
		log.info("agregar()");
		permisos = new DualListModel<PermisoSie>(new ArrayList<PermisoSie>(), new ArrayList<PermisoSie>());
		log.info("listarPermisosXEmpleado()");
		target= objPermisoService.listarPermisosXEmpleado(idempleado);
		log.info("target()"+target.size());
		source= objPermisoService.listarPermiso();
		log.info("source()"+source.size());
		
		for (int i = 0; i < target.size(); i++) {
				for (int j = 0; j < source.size(); j++) {
				log.info("aqui   "+target.get(i).getIdpermiso()+" -  "+source.get(j).getIdpermiso());
				if(target.get(i).getIdpermiso()==source.get(j).getIdpermiso()){
					log.info("* desc * "+target.get(i).getNombrePermiso());
					source.remove(j);
					log.info(source.size()+"  source())   " ); 
				}
			}
		}
		log.info("source())   "+source.size()); 
		permisos = new DualListModel<PermisoSie>(source, target);
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

}
