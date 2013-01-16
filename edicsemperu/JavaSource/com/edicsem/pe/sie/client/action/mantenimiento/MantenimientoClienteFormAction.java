package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FlowEvent;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.service.facade.DomicilioEmpleadoService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
import com.edicsem.pe.sie.service.facade.TipoCasaService;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
import com.edicsem.pe.sie.service.facade.UbigeoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "MantenimientoClienteFormAction")
@SessionScoped
public class MantenimientoClienteFormAction extends
		BaseMantenimientoAbstractAction {

	/************** WIZAR **************************/
	private boolean skip;
	/******************* FIN WIZAR ******************************/

	/***************** DATOS BASICOS DE CLIENTE ***************************/
	private ClienteSie objClienteSie;
	public String nombrecliente;
	private int TipoDocumento;
	private int idEstadoGeneral;

	/***************** FIN BASICOS DE CLIENTE ***************************/

	private boolean editMode;
	private boolean newRecord = false;
	public String mensaje;

	private int estado;

	

	private Log log = LogFactory.getLog(MantenimientoClienteFormAction.class);



	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;

	@EJB
	private ClienteService objClienteService;
	@EJB
	private EstadogeneralService objEstadoGeneralService;
	@EJB
	private TipoDocumentoService objTipoDocService;	
	@EJB
	private UbigeoService objUbigeoService;
	@EJB
	private TelefonoEmpleadoService objTelefonoService;	
	@EJB
	private DomicilioEmpleadoService objDomicilioService;
	@EJB
	private TipoCasaService objTipoCasaService;

	public MantenimientoClienteFormAction() {
		init();
	}

	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoClienteFormAction'");
		objClienteSie = new ClienteSie();
		TipoDocumento = 1;
	}

	public String agregar() {
		log.info("agregar");

		objClienteSie = new ClienteSie();
		setTipoDocumento(0);
		editMode = true;
		setNewRecord(true);
		return getViewMant();

	}

	/* METODO EDITAR DATOS DE CLIENTE CUANDO SE SELECCIONA EL BOTON EDITAR */

	public String update() throws Exception {
		log.info("updateGeneral()");
		log.info(" id cliente " + objClienteSie.getIdcliente() + " nombre "
				+ objClienteSie.getNombrecliente());

		setTipoDocumento(objClienteSie.getTbTipoDocumentoIdentidad().getIdtipodocumentoidentidad());

		setNewRecord(false);
		editMode = false;
		return getViewMant();
	}

	/* REGISTRAR LOS DATOS DEL CLIENTE EDITADO */

	public String insertarOficial() {
		log.info("insertarOficial() " + objClienteSie.getIdcliente() + " "+ objClienteSie.getNombrecliente());

		try {
			if (log.isInfoEnabled()) {

			}

			objClienteSie.setTbTipoDocumentoIdentidad(objTipoDocService.buscarTipoDocumento(TipoDocumento));
			objClienteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(23));

			objClienteService.updateCliente(objClienteSie);

			mensaje = "";
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Constants.MESSAGE_REGISTRO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {

			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		return getViewList();
	}

	/*********************** METODO WIZAR ****************************************/
	public String onFlowProcess(FlowEvent event) {
		log.info("Current wizard step:" + event.getOldStep());
		log.info("Next step:" + event.getNewStep());
		log.info("skip :" + skip);
		if (skip) {
			skip = true; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	/************************** FIN WIZAR *********************************/

	public String getViewMant() {
		return Constants.MANT_CLIENTE_FORM_PAGE;
	}

	public String getViewList() {
		return Constants.MANT_CLIENTE_FORM_LIST_PAGE;
	}

	/* METODOS GET Y SET */

	public ClienteSie getObjClienteSie() {
		return objClienteSie;
	}

	public void setObjClienteSie(ClienteSie objClienteSie) {
		this.objClienteSie = objClienteSie;
	}

	public String getNombrecliente() {
		return nombrecliente;
	}

	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}

	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getTipoDocumento() {
		return TipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}




	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

		
	public ComboAction getComboManager() {
		return comboManager;
	}

	public void setComboManager(ComboAction comboManager) {
		this.comboManager = comboManager;
	}

	

}
