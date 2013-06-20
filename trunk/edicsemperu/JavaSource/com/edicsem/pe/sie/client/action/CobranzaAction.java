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
import org.primefaces.context.RequestContext;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.edicsem.pe.sie.client.dataModel.CobranzaDataModel;
import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.CobranzaOperaService;
import com.edicsem.pe.sie.service.facade.ConfigCobranzaService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "cobranza")
@SessionScoped
public class CobranzaAction extends BaseMantenimientoAbstractAction {
	
	private Log log = LogFactory.getLog(CobranzaAction.class);
	
	private String mensaje;
	private boolean editMode;
    private List<CobranzaSie> cobranzaList;
    private List<String> empleadoList;
    //picklist  
	private DualListModel<EmpleadoSie> teleoperadoras;
    private List<EmpleadoSie> sources; 
    private List<EmpleadoSie> targets;
    private int tipoCobranza;
    //Asignar cobranzas restantes
    private CobranzaSie[] selectedCob;
	private CobranzaDataModel cobranzaModel;
	private int operadorasignado;
	private List<EmpleadoSie> lstEmpleados;
	private List<CobranzaSie> lstcob;
	List<ConfigCobranzaOperaSie> configList;
	
	public List<ConfigCobranzaOperaSie> getConfigList() {
		return configList;
	}

	public void setConfigList(List<ConfigCobranzaOperaSie> configList) {
		this.configList = configList;
	}

	@EJB
	private CobranzaOperaService objCobranzaOperaService;
	@EJB
	private EmpleadoSieService objEmpleadoService;
	@EJB
	private  ConfigCobranzaService objConfigCobranzaService;
	
	public CobranzaAction() {
		log.info("inicializando constructor 'CobranzaAction'");
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init() ");
		empleadoList = new ArrayList<String>();
		sources = new ArrayList<EmpleadoSie>();
		targets = new ArrayList<EmpleadoSie>();
		teleoperadoras = new DualListModel<EmpleadoSie>(sources, targets);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		sources = new ArrayList<EmpleadoSie>();
		targets = new ArrayList<EmpleadoSie>();
		teleoperadoras = new DualListModel<EmpleadoSie>(sources, targets);
		lstEmpleados = objEmpleadoService.listarEmpleadosXCargo(7);
		for (int i = 0; i < lstEmpleados.size(); i++) {
			log.info(" "+lstEmpleados.get(i).getNombresCompletos() );
			sources.add(lstEmpleados.get(i));
		}
		log.info("sources()"+sources.size());
		teleoperadoras = new DualListModel<EmpleadoSie>(sources, targets);  
		cobranzaModel = new CobranzaDataModel(lstcob);
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("insertar() " );
		empleadoList = new ArrayList<String>();
		mensaje=null;
		RequestContext context = RequestContext.getCurrentInstance();
		//Lista de cobranza restante, por asignar a las teleoperadoras
		lstcob =null;
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()' " );
			}
			//Validar si se registro las listas en el dia ( si es turno mañana)
			configList = objConfigCobranzaService.buscarConfigCobranza(tipoCobranza);
			log.info("tamano config --> "+configList.size() );
			if(configList.size()>0){
					
				int cantContratos =objCobranzaOperaService.verificargeneracionDiaria();
				if(cantContratos>0){
					log.info(" se genero anteriormente la lista de cobranzas" );
					mensaje="Ya se registro una lista anteriormente";
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
					
				}else{
					for (int i = 0; i < teleoperadoras.getTarget().size(); i++) {
						empleadoList.add(teleoperadoras.getTarget().get(i).getNombresCompletos());
					}
					if(empleadoList.size()==0){
						mensaje="Debe seleccionar teleoperadores para asignar listas ";
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
					}else{
						/** Insertamos las listas de cobranzas para cada teleoperadora asignada */
						lstcob = objCobranzaOperaService.insertCobranzaOpera(empleadoList,configList);
						mensaje="Se generó la lista correctamente";
						if(lstcob!=null){
							cobranzaModel = new CobranzaDataModel(lstcob);
						}
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
					}
				}
			}else{
				mensaje="El día de hoy no está configurado para generar listas de cobranza";
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.execute("statusDialogCob.hide()");
		return getViewList();
	}
	
	/**
	 * Metodo para asignar las Cobranzas restantes 
	 */
	public String asignarcobranzarestante(){
		log.info("asignarcobranzarestante()  "+selectedCob.length);
		RequestContext context = RequestContext.getCurrentInstance();
		for (int i = 0; i < selectedCob.length; i++) {
			log.info(""+selectedCob[i].getTbCliente().getNombresCompletos());
			CobranzaOperadoraSie cobranzaopera = new CobranzaOperadoraSie();
			cobranzaopera.setTbCobranza(selectedCob[i]);
			cobranzaopera.setTbEmpleado(objEmpleadoService.buscarEmpleado(operadorasignado));
			objCobranzaOperaService.insertCobranzaOpera(cobranzaopera,configList );
		}
		log.info("a remover ");
		for (int i = 0; i < lstcob.size(); i++) {
			for (int j = 0; j < selectedCob.length; j++) {
				if(lstcob.get(i).getIdcobranza()==selectedCob[j].getIdcobranza()){
					lstcob.remove(i);
				}
			}
		}
		mensaje="Se asignó correctamente los clientes";
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		cobranzaModel = new CobranzaDataModel(lstcob);
		context.execute("cobDialog.hide()");
		return null;
	}

	public void onTransfer(TransferEvent event) {  
        StringBuilder builder = new StringBuilder();  
        for(Object item : event.getItems()) {  
            builder.append(((EmpleadoSie) item).getNombresCompletos()).append("<br />");  
        }
          
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);  
        msg.setSummary("Items Transferred");  
        msg.setDetail(builder.toString());  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
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

	/**
	 * @return the tipoCobranza
	 */
	public int getTipoCobranza() {
		return tipoCobranza;
	}

	/**
	 * @param tipoCobranza the tipoCobranza to set
	 */
	public void setTipoCobranza(int tipoCobranza) {
		this.tipoCobranza = tipoCobranza;
	}

	/**
	 * @return the sources
	 */
	public List<EmpleadoSie> getSources() {
		return sources;
	}

	/**
	 * @param sources the sources to set
	 */
	public void setSources(List<EmpleadoSie> sources) {
		this.sources = sources;
	}

	/**
	 * @return the targets
	 */
	public List<EmpleadoSie> getTargets() {
		return targets;
	}

	/**
	 * @param targets the targets to set
	 */
	public void setTargets(List<EmpleadoSie> targets) {
		this.targets = targets;
	}

	public CobranzaDataModel getCobranzaModel() {
		return cobranzaModel;
	}

	public void setCobranzaModel(CobranzaDataModel cobranzaModel) {
		this.cobranzaModel = cobranzaModel;
	}

	public CobranzaSie[] getSelectedCob() {
		return selectedCob;
	}

	public void setSelectedCob(CobranzaSie[] selectedCob) {
		log.info("tamañito "+selectedCob.length);
		this.selectedCob = selectedCob;
	}

	public int getOperadorasignado() {
		return operadorasignado;
	}

	public void setOperadorasignado(int operadorasignado) {
		this.operadorasignado = operadorasignado;
	}

	/**
	 * @return the lstEmpleados
	 */
	public List<EmpleadoSie> getLstEmpleados() {
		return lstEmpleados;
	}

	/**
	 * @param lstEmpleados the lstEmpleados to set
	 */
	public void setLstEmpleados(List<EmpleadoSie> lstEmpleados) {
		this.lstEmpleados = lstEmpleados;
	}

	/**
	 * @return the lstcob
	 */
	public List<CobranzaSie> getLstcob() {
		return lstcob;
	}

	/**
	 * @param lstcob the lstcob to set
	 */
	public void setLstcob(List<CobranzaSie> lstcob) {
		this.lstcob = lstcob;
	}

}
