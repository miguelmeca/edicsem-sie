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

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.IncidenciaSie;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.IncidenciaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.StringUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "configcorreos")
@SessionScoped
public class CorreosIncidenteAction extends BaseMantenimientoAbstractAction {
	
	private String mensaje;
	private boolean editMode;
	private DualListModel<EmpleadoSie> empleados;
	private DualListModel<String> empleadosString;
	private Log log = LogFactory.getLog(CorreosIncidenteAction.class);
	private List<EmpleadoSie> source;
    private List<EmpleadoSie> target;  
    private List<String> sources;
    private List<String> targets; 
    private int idincidente;
    private String incidenteElegidos;
    private IncidenciaSie objIncidencia;
    @EJB
   	private IncidenciaService objIncidenteService;
    @EJB
	private EmpleadoSieService objEmpleadoService;
	
	public CorreosIncidenteAction() {
		log.info("inicializando constructor 'CorreosIncidenteAction'");
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init()");
		sources = new ArrayList<String>();
		targets = new ArrayList<String>();
		empleadosString= new DualListModel<String>(sources, targets);
		source = new ArrayList<EmpleadoSie>();
		target = new ArrayList<EmpleadoSie>();
		empleados = new DualListModel<EmpleadoSie>(source, target);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		sources = new ArrayList<String>();
		targets = new ArrayList<String>();
		empleadosString= new DualListModel<String>(sources, targets);
		source = new ArrayList<EmpleadoSie>();
		target = new ArrayList<EmpleadoSie>();
		setEmpleados(new DualListModel<EmpleadoSie>(source, target));
		objIncidencia = new IncidenciaSie();
		return getViewMant();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.ASIGNAR_CORREOS_INCIDENTE_FORM_PAGE;
	}
	
	public String agregarListas() {
		log.info("agregarListas()");
		String[] arregloUsuario = null;
		empleadosString = new DualListModel<String>(new ArrayList<String>(), new ArrayList<String>());
		sources = new ArrayList<String>();
		targets = new ArrayList<String>();
		objIncidencia = objIncidenteService.findIncidencia(idincidente);
		log.info("incidente "+objIncidencia.getUsuariosenviomsj());
		if(objIncidencia.getUsuariosenviomsj()!=null){
			arregloUsuario = StringUtil.obtenerArreglo(objIncidencia.getUsuariosenviomsj(), ",");
			
			//buscar empleados por usuario
			for (int i = 0; i < arregloUsuario.length; i++) {
				EmpleadoSie em = objEmpleadoService.buscarEmpleadosPorUsuario(arregloUsuario[i]);
				targets.add(em.getNombresCompletos());
			}
		}
		
		log.info("targets()"+targets.size());
		List<EmpleadoSie> lstEmpleados = objEmpleadoService.listarEmpleados();
		for (int i = 0; i < lstEmpleados.size(); i++) {
			log.info(" "+lstEmpleados.get(i).getNombresCompletos() );
			sources.add(lstEmpleados.get(i).getNombresCompletos());
		}
		log.info("sources()"+sources.size());
		
		for (int i = 0; i < targets.size(); i++) {
			for (int j = 0; j < sources.size(); j++) {
				if(targets.get(i).equalsIgnoreCase(sources.get(j))){
					log.info("* desc * "+targets.get(i));
					sources.remove(j);
					log.info(sources.size()+"  sources())   " ); 
				}
			}
		}
		empleadosString = new DualListModel<String>(sources, targets);
		
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
			log.info("xxx "+empleadosString.getTarget());
			//buscar todos los empleados seleccionador (targets)
			String usuariomsj=null;
			for (int i = 0; i < empleadosString.getTarget().size(); i++) {
				EmpleadoSie em = objEmpleadoService.buscarEmpleadoPorNombreCompleto(empleadosString.getTarget().get(i));
				if(i ==0){
					usuariomsj= em.getUsuario();
				}else{
					usuariomsj+=","+em.getUsuario();
				}
			}
			objIncidencia.setUsuariosenviomsj(usuariomsj);
			objIncidenteService.updateIncidencia(objIncidencia);
			
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
		return Constants.ASIGNAR_CORREOS_INCIDENTE_FORM_PAGE;
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

	/**
	 * @return the empleados
	 */
	public DualListModel<EmpleadoSie> getEmpleados() {
		return empleados;
	}

	/**
	 * @param empleados the empleados to set
	 */
	public void setEmpleados(DualListModel<EmpleadoSie> empleados) {
		this.empleados = empleados;
	}

	/**
	 * @return the source
	 */
	public List<EmpleadoSie> getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(List<EmpleadoSie> source) {
		this.source = source;
	}

	/**
	 * @return the target
	 */
	public List<EmpleadoSie> getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(List<EmpleadoSie> target) {
		this.target = target;
	}

	/**
	 * @return the empleadosString
	 */
	public DualListModel<String> getEmpleadosString() {
		return empleadosString;
	}

	/**
	 * @param empleadosString the empleadosString to set
	 */
	public void setEmpleadosString(DualListModel<String> empleadosString) {
		this.empleadosString = empleadosString;
	}

	/**
	 * @return the idincidente
	 */
	public int getIdincidente() {
		return idincidente;
	}

	/**
	 * @param idincidente the idincidente to set
	 */
	public void setIdincidente(int idincidente) {
		this.idincidente = idincidente;
	}

	/**
	 * @return the incidenteElegidos
	 */
	public String getIncidenteElegidos() {
		return incidenteElegidos;
	}

	/**
	 * @param incidenteElegidos the incidenteElegidos to set
	 */
	public void setIncidenteElegidos(String incidenteElegidos) {
		this.incidenteElegidos = incidenteElegidos;
	}

}
