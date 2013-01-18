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
	
	/*****************Domicilio;*************************/
	private DomicilioPersonaSie objDomicilio;
	private String idProvincia, idDepartamento, ubigeoDefecto, selectTelef, idUbigeo;
	private int tipo;
	private String idDistrito;
	private boolean defectoUbigeo;

	

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
		objDomicilio = new DomicilioPersonaSie();
	}

	public String agregar() {
		log.info("agregar");

		objClienteSie = new ClienteSie();
		setTipoDocumento(0);
		editMode = true;
		setNewRecord(true);
		return getViewMant();

	}
	
	
	/**********************DOMICILIO**************************/
	public void cambiar() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(null);
		idProvincia = null;
		idUbigeo = null;
		log.info("cambiar   :D  --- ");
	}
	
	public void cambiar2() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(getIdProvincia());
		log.info("cambiar 2  :D  --- ");
	}

	//UBIGEO....
	
		public void busquedaUbigeo(){
			log.info("busquedaUbigeo");
			UbigeoSie ubigeo = objUbigeoService.findUbigeo(Integer.parseInt(getIdUbigeo()));
			
			log.info("busquedaUbigeo "+ getIdDepartamento()+" - "+getIdProvincia()+" - "+getIdDistrito()+" - "+getIdUbigeo());
			
			Iterator it = comboManager.getUbigeoDeparItems().entrySet().iterator();
			Iterator it2 = comboManager.getUbigeoProvinItems().entrySet().iterator();
			Iterator it3 = comboManager.getUbigeoDistriItems().entrySet().iterator();
			
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				System.out.println("key " + e.getKey() + " value " + e.getValue());
				if (e.getValue().toString().equals(idDepartamento)) {
					ubigeoDefecto = (String) e.getKey();
					log.info("ubigeo depa " + ubigeoDefecto);
					break;
				}
			}
			while (it2.hasNext()) {
				Map.Entry e = (Map.Entry) it2.next();
				System.out.println("key " + e.getKey() + " value " + e.getValue());
				if (e.getValue().toString().equals(idProvincia)) {
					ubigeoDefecto += "-" + (String) e.getKey();
					log.info("ubigeo prov " + ubigeoDefecto);
					break;
				}
			}
			while (it3.hasNext()) {
				Map.Entry e = (Map.Entry) it3.next();
				System.out.println("key " + e.getKey() + " value " + e.getValue());
				if (e.getValue().toString().equals(idUbigeo)) {
					ubigeoDefecto += "-" + (String) e.getKey();
					log.info("ubigeo distrito " + ubigeoDefecto);
					break;
				}
			}
			log.info("ubigeo ------> :D   " + ubigeoDefecto);
		} 
	
	
	
	
		public void ingresarUbigeo() {
			// enviamos el nombre completo del depa- provincia-distrito

					log.info("ingresarUbigeo :D a --- " + idUbigeo);
					ubigeoDefecto = "otro ubigeo";

					Iterator it = comboManager.getUbigeoDeparItems().entrySet().iterator();
					Iterator it2 = comboManager.getUbigeoProvinItems().entrySet().iterator();
					Iterator it3 = comboManager.getUbigeoDistriItems().entrySet().iterator();
					
					while (it.hasNext()) {
						Map.Entry e = (Map.Entry) it.next();
						System.out.println("key " + e.getKey() + " value " + e.getValue());
						if (e.getValue().toString().equals(idDepartamento)) {
							ubigeoDefecto = (String) e.getKey();
							log.info("ubigeo depa " + ubigeoDefecto);
							break;
						}
					}
					while (it2.hasNext()) {
						Map.Entry e = (Map.Entry) it2.next();
						System.out.println("key " + e.getKey() + " value " + e.getValue());
						if (e.getValue().toString().equals(idProvincia)) {
							ubigeoDefecto += "-" + (String) e.getKey();
							log.info("ubigeo prov " + ubigeoDefecto);
							break;
						}
					}
					while (it3.hasNext()) {
						Map.Entry e = (Map.Entry) it3.next();
						System.out.println("key " + e.getKey() + " value " + e.getValue());
						if (e.getValue().toString().equals(idUbigeo)) {
							ubigeoDefecto += "-" + (String) e.getKey();
							log.info("ubigeo distrito " + ubigeoDefecto);
							break;
						}
					}
					log.info("ubigeo ------> :D   " + ubigeoDefecto);
		}
	
	
		public void cambioUbica() {
			log.info("Ubigeo -------->" + idUbigeo + " " + ubigeoDefecto);
			String dist = "";
			Iterator it = comboManager.getUbigeoDistriItems().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				System.out.println("key " + e.getKey() + " value " + e.getValue());
				if (e.getValue().toString().equals(idUbigeo)) {
					dist = (String) e.getKey();
					log.info("dist " + dist);
					break;
				}
			}
			ubigeoDefecto = "LIMA-LIMA-" + dist;
		}
	
		public void cambioUbigeoDefecto() {
			log.info(" defecto  " + defectoUbigeo);
			comboManager.setUbigeoDeparItems(null);
			comboManager.setUbigeoProvinItems(null);
			comboManager.setUbigeoDistriItems(null);
			if (defectoUbigeo) {
				comboManager.setIdDepartamento("15");
				comboManager.setIdProvincia("01");
				log.info(" defecto lima true 1");
				ubigeoDefecto = "";
			} else {
				comboManager.setIdDepartamento(null);
				comboManager.setIdProvincia(null);
				log.info(" cambio ubigeo   false  otro");
			}
		}
	
	
	/* METODO EDITAR DATOS DE CLIENTE CUANDO SE SELECCIONA EL BOTON EDITAR */

	public String update() throws Exception {
		log.info("updateGeneral()");
		log.info(" id cliente " + objClienteSie.getIdcliente() + " nombre "	+ objClienteSie.getNombrecliente());

		setTipoDocumento(objClienteSie.getTbTipoDocumentoIdentidad().getIdtipodocumentoidentidad());

		DomicilioPersonaSie d = objDomicilioService.buscarDomicilioXIdcliente(objClienteSie.getIdcliente());
		log.info(" id " + d.getIddomiciliopersona()+ " nombre " + d.getDomicilio() );
		 /*seteo domicilio*/                                               
        objDomicilio.setIddomiciliopersona(d.getIddomiciliopersona());
        objDomicilio.setDomicilio(d.getDomicilio());
        setIdDepartamento(d.getTbUbigeo().getCoddepartamento());
        comboManager.setIdDepartamento(idDepartamento);
        setIdProvincia(d.getTbUbigeo().getCodprovincia());
        comboManager.setIdProvincia(idProvincia);
        setIdDistrito(d.getTbUbigeo().getIdubigeo().toString());
        setTipo(d.getTbTipoCasa().getIdtipocasa());
        objDomicilio.setTbEstadoGeneral(d.getTbEstadoGeneral());
		
		setNewRecord(false);
	
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
			
			objDomicilio.setTbUbigeo(objUbigeoService.findUbigeo(Integer.parseInt(idUbigeo)));
			
			
			
			
			
			
			
			
			
			
			
			objClienteService.updateCliente(objClienteSie,objDomicilio,idUbigeo,tipo);

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

	/**
	 * @return the objDomicilio
	 */
	public DomicilioPersonaSie getObjDomicilio() {
		return objDomicilio;
	}

	/**
	 * @param objDomicilio the objDomicilio to set
	 */
	public void setObjDomicilio(DomicilioPersonaSie objDomicilio) {
		this.objDomicilio = objDomicilio;
	}

	/**
	 * @return the idProvincia
	 */
	public String getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @param idProvincia the idProvincia to set
	 */
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}

	/**
	 * @return the idDepartamento
	 */
	public String getIdDepartamento() {
		return idDepartamento;
	}

	/**
	 * @param idDepartamento the idDepartamento to set
	 */
	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	/**
	 * @return the ubigeoDefecto
	 */
	public String getUbigeoDefecto() {
		return ubigeoDefecto;
	}

	/**
	 * @param ubigeoDefecto the ubigeoDefecto to set
	 */
	public void setUbigeoDefecto(String ubigeoDefecto) {
		this.ubigeoDefecto = ubigeoDefecto;
	}

	/**
	 * @return the selectTelef
	 */
	public String getSelectTelef() {
		return selectTelef;
	}

	/**
	 * @param selectTelef the selectTelef to set
	 */
	public void setSelectTelef(String selectTelef) {
		this.selectTelef = selectTelef;
	}

	/**
	 * @return the idUbigeo
	 */
	public String getIdUbigeo() {
		return idUbigeo;
	}

	/**
	 * @param idUbigeo the idUbigeo to set
	 */
	public void setIdUbigeo(String idUbigeo) {
		this.idUbigeo = idUbigeo;
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the idDistrito
	 */
	public String getIdDistrito() {
		return idDistrito;
	}

	/**
	 * @param idDistrito the idDistrito to set
	 */
	public void setIdDistrito(String idDistrito) {
		this.idDistrito = idDistrito;
	}

	

}
