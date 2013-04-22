package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.CobranzaOperaService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "cobranza")
@SessionScoped
public class CobranzaAction extends BaseMantenimientoAbstractAction {
	
	private Log log = LogFactory.getLog(CobranzaAction.class);
	
	private String mensaje;
	private boolean editMode;
    private List<CobranzaSie> cobranzaList;
    private List<String> empleadoList;
    private Date dhoy;
    //picklist

	private DualListModel<EmpleadoSie> teleoperadoras;
	private DualListModel<String> teleoperadorasString;
	private List<EmpleadoSie> source;  
    private List<EmpleadoSie> target;  
    private List<String> sources; 
    private List<String> targets;
    
	@EJB
	private CobranzaOperaService objCobranzaOperaService;
	@EJB
	private EmpleadoSieService objEmpleadoService;
	
	public CobranzaAction() {
		log.info("inicializando constructor cobranzaAction");
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init() ");
		empleadoList = new ArrayList<String>();
		sources = new ArrayList<String>();
		targets = new ArrayList<String>();
		teleoperadorasString= new DualListModel<String>(sources, targets);
		source = new ArrayList<EmpleadoSie>();
		target = new ArrayList<EmpleadoSie>();
		teleoperadoras = new DualListModel<EmpleadoSie>(source, target);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		sources = new ArrayList<String>();
		targets = new ArrayList<String>();
		source = new ArrayList<EmpleadoSie>();
		target = new ArrayList<EmpleadoSie>();
		teleoperadoras = new DualListModel<EmpleadoSie>(source, target);
		List<EmpleadoSie> lstEmpleados = objEmpleadoService.listarEmpleadosXCargo(7);
		for (int i = 0; i < lstEmpleados.size(); i++) {
			log.info(" "+lstEmpleados.get(i).getNombresCompletos() );
			sources.add(lstEmpleados.get(i).getNombresCompletos());
		}
		log.info("sources()"+sources.size());
		
		teleoperadorasString = new DualListModel<String>(sources, targets);
		
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		mensaje=null;
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()' " );
			}
			dhoy =  DateUtil.getToday().getTime();
			//Validar si se registro las listas en el dia ( si es turno mañana)
			
			int cantContratos =objCobranzaOperaService.verificargeneracionDiaria();
			if(cantContratos>0){
				log.info(" se genero anteriormente la lista de cobranzas" );
				mensaje="Ya se registro una lista anteriormente";
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
				
			}else{
				/** Insertamos las listas de cobranzas para cada teleoperadora asignada */
				objCobranzaOperaService.insertCobranzaOpera(teleoperadorasString.getTarget());
				mensaje="Se generó la lista correctamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			context.execute("statusDialogCob.hide()");
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.GENERAR_LISTAS_COBRANZA_FORM_PAGE;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return super.getViewMant();
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
	 * @return the teleoperadoras
	 */
	public DualListModel<EmpleadoSie> getTeleoperadoras() {
		return teleoperadoras;
	}

	/**
	 * @param teleoperadoras the teleoperadoras to set
	 */
	public void setTeleoperadoras(DualListModel<EmpleadoSie> teleoperadoras) {
		this.teleoperadoras = teleoperadoras;
	}

	/**
	 * @return the teleoperadorasString
	 */
	public DualListModel<String> getTeleoperadorasString() {
		return teleoperadorasString;
	}

	/**
	 * @param teleoperadorasString the teleoperadorasString to set
	 */
	public void setTeleoperadorasString(DualListModel<String> teleoperadorasString) {
		this.teleoperadorasString = teleoperadorasString;
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
	 * @return the cobranzaList
	 */
	public List<CobranzaSie> getCobranzaList() {
		return cobranzaList;
	}

	/**
	 * @param cobranzaList the cobranzaList to set
	 */
	public void setCobranzaList(List<CobranzaSie> cobranzaList) {
		this.cobranzaList = cobranzaList;
	}

	/**
	 * @return the empleadoList
	 */
	public List<String> getEmpleadoList() {
		return empleadoList;
	}

	/**
	 * @param empleadoList the empleadoList to set
	 */
	public void setEmpleadoList(List<String> empleadoList) {
		this.empleadoList = empleadoList;
	}

}
