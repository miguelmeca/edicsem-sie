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
import com.edicsem.pe.sie.util.FaceMessage.FaceMessage;
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
	private String idProvincia, idDepartamento, ubigeoDefecto,  idUbigeo1;
	
	private int tipo;
	private String idDistrito;
	private boolean defectoUbigeo;
	
	/***********************NEW DOMICILIO*******************************/
	private List<DomicilioPersonaSie> DomicilioPersonaList,DomicilioPersonaDeshabilitado ;	
	private DomicilioPersonaSie nuevoDomici;
	private int idd;
	private int idUbigeo;
	private String idProvincia24, idDepartamento24, ubigeoDefecto24,idDistrito24;
	private boolean defectoUbigeo24;
	
	private DomicilioPersonaSie objDomicilioNew;//lo cree para que no se repita con el objeto que edita llamado objDomicilio
	private String domicilio, referencia;
	private int Tipocasanuevo;
	
    /************************TELEFONO**************************************/
	private List<TelefonoPersonaSie> TelefonoPersonaList,TelefonoDeshabilitado;
	private TelefonoPersonaSie nuevoTelef;
	private String  selectTelef;
	private int TipoTelef, operadorTelefonico;
	
	private int idt;
	private TelefonoPersonaSie objTelefono;
	private int TelefonoPersona;
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
		
		/**************NUEVO DOMICLIO****************/
		objDomicilioNew = new DomicilioPersonaSie();
		nuevoDomici = new DomicilioPersonaSie();
		Tipocasanuevo=0;
		domicilio="";
		referencia="";
		defectoUbigeo24 = true;
		idUbigeo=0;	
		ubigeoDefecto24 = "";		
		/****lista donde se van almacenar al momento de eliminar o agregar un nuevo domicilio****/
		DomicilioPersonaList = new ArrayList<DomicilioPersonaSie>();
		DomicilioPersonaDeshabilitado = new ArrayList<DomicilioPersonaSie>();		
		
		/***********telefono*************/
		
		objTelefono = new TelefonoPersonaSie();
		nuevoTelef = new TelefonoPersonaSie();
		nuevoTelef.setTipoTelef("");
		selectTelef="";
		TipoTelef=1;
		TelefonoDeshabilitado = new ArrayList<TelefonoPersonaSie>();
		TelefonoPersonaList = new ArrayList<TelefonoPersonaSie>();
		operadorTelefonico=1;
	}

	public String agregar() {
		log.info("agregar");
		objClienteSie = new ClienteSie();
		setTipoDocumento(0);		
		/****************tELEFONO*****************/		
		selectTelef= "";
		operadorTelefonico=1;
		TipoTelef=1;
		//para inicializar los campos de nuevo telefono
		nuevoTelef.setTelefono("");
		//fin para inicializar los campos de nuevo telefono
		objTelefono = new TelefonoPersonaSie();
		setTipo(0);
		
		editMode = true;
		setNewRecord(true);
		return getViewMant();
	}
	
	public String agregar2() {
		log.info("agregar2()))");
		
		objDomicilioNew = new DomicilioPersonaSie(); 
		domicilio="";
		referencia="";
		Tipocasanuevo=0;
		ubigeoDefecto24 = "";
		comboManager.setUbigeoDeparItems(null);
		comboManager.setUbigeoProvinItems(null);
		comboManager.setUbigeoDistriItems(null);
		comboManager.setIdDepartamento("15");
		comboManager.setIdProvincia("01");
		idDepartamento24="15";
		idProvincia24="01";
		idUbigeo=0;
		editMode = false;
		return  getViewMant();
	}
	/*********************ELIMINAR DOMICILIO DE LA LISTA***********************************/
		
	public void domicilioElimina(){
		log.info("DomicilioElimina()");
		for (int i = 0; i < DomicilioPersonaList.size(); i++) {    		
	     	log.info(""+DomicilioPersonaList.get(i).getItem()+" ,idd "+getIdd() +"  "+DomicilioPersonaList.get(i).getDomicilio());

		if((DomicilioPersonaList.get(i).getIddomiciliopersona()== 0 || DomicilioPersonaList.get(i).getIddomiciliopersona()== null) && DomicilioPersonaList.get(i).getItem()=="Por Agregar"){
			log.info("N2----> DomicilioPersonaList.get(i).getIddomiciliopersona()" +"   " +DomicilioPersonaList.get(i).getIddomiciliopersona());
			DomicilioPersonaSie domtemp = new DomicilioPersonaSie();
			domtemp.setIddomiciliopersona(idd);
			DomicilioPersonaList.remove(domtemp);
		for (int j = i; j < DomicilioPersonaList.size(); j++) {
			log.info(" i " +i+"  j "+ j);
			i=i+1;
			DomicilioPersonaList.set(j, DomicilioPersonaList.get(j));
		}break;
		}
		else if(DomicilioPersonaList.get(i).getIddomiciliopersona() ==(getIdd()) && DomicilioPersonaList.get(i).getItem()=="Agregado"){
			log.info("ALERTA WDFFFF");
			DomicilioPersonaSie obj = new DomicilioPersonaSie();
			obj.setIddomiciliopersona(idd);
			log.info("DENTRO LISTA DESHABILITADO");
			DomicilioPersonaDeshabilitado.add(obj);	
			FaceMessage.FaceMessageError("ALERTA", "Los Cambios se realizaran despues de hacer clic en el boton Guardar");			
//			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
//			Constants.MESSAGE_ERROR_TELEFONO_CLIENTE,mensaje);
//			FacesContext.getCurrentInstance().addMessage(null, msg);				
			}
		}
	}
	
	/*********************ELIMINAR TELEFONO DE LA LISTA***********************************/
	
	public void telefonoElimina(){
    	log.info("en eliminarTelefono()");	
    	for (int i = 0; i < TelefonoPersonaList.size(); i++) {    		
    		log.info(""+TelefonoPersonaList.get(i).getItem()+" ,idt "+getIdt() +"  "+TelefonoPersonaList.get(i).getTelefono());
//     	log.info("N1--->TelefonoPersonaList.get(i).getIdtelefonopersona()"+"   --" +TelefonoPersonaList.get(i).getIdtelefonopersona());
		if((TelefonoPersonaList.get(i).getIdtelefonopersona()== 0 || TelefonoPersonaList.get(i).getIdtelefonopersona()== null) && TelefonoPersonaList.get(i).getItem()=="Por Agregar"){
			log.info("N2----> TelefonoPersonaList.get(i).getIdtelefonopersona()" +"   " +TelefonoPersonaList.get(i).getIdtelefonopersona());
			TelefonoPersonaSie teltemp = new TelefonoPersonaSie();
			teltemp.setIdtelefonopersona(idt);
			TelefonoPersonaList.remove(teltemp);
		for (int j = i; j < TelefonoPersonaList.size(); j++) {
			log.info(" i " +i+"  j "+ j);
			i=i+1;
			//TelefonoPersonaList.get(j).setItem(i);
			TelefonoPersonaList.set(j, TelefonoPersonaList.get(j));
		}break;
			}
		else if(TelefonoPersonaList.get(i).getIdtelefonopersona() ==(getIdt()) && TelefonoPersonaList.get(i).getItem()=="Agregado"){
				log.info("ALERTA WDFFFF");
				TelefonoPersonaSie obj = new TelefonoPersonaSie(); 
				obj.setIdtelefonopersona(idt);
				log.info("DENTRO LISTA DESHABILITADO");				
				TelefonoDeshabilitado.add(obj);
		
				FaceMessage.FaceMessageError("ALERTA", "Los Cambios se realizaran despues de hacer clic en el boton Guardar");					
			}
			}
		    }
	/* METODO AGREGAR DOMICILIO */

	public void domicilioAgregar() {
		log.info("Domicilio agregar " + nuevoDomici.getDomicilio());
		boolean verifica = false;
		mensaje = null;
		nuevoDomici.setTbTipoCasa(objTipoCasaService.findTipoCasa(Tipocasanuevo));
		nuevoDomici.setDomicilio(domicilio);
		nuevoDomici.setReferencia(referencia);
		nuevoDomici.setTbUbigeo(objUbigeoService.findUbigeo(idUbigeo));
		log.info("TIPO-CASA-NUEVO--->"+nuevoDomici.getTbTipoCasa().getIdtipocasa());
		nuevoDomici.setNuevoD(1);
		//aqui ponfo el ID-Domicilio a la lista Domicilio temporalmente.
		nuevoDomici.setIddomiciliopersona(0);
		nuevoDomici.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(15));
		
		for (int i = 0; i < DomicilioPersonaList.size(); i++) {
			log.info("  "+ DomicilioPersonaList.get(i).getDomicilio() + " " + nuevoDomici.getDomicilio());
			if (DomicilioPersonaList.get(i).getDomicilio().equals(nuevoDomici.getDomicilio())) {
				verifica = false;
				mensaje = "el Domicilio ya se encuentra registrado en la lista de domiclio cliente";
				break;
			}else {
				verifica = true;
				}
				}
			if (DomicilioPersonaList.size() == 0) {
				verifica = true;
				}
			if (verifica) {
				nuevoDomici.setItem("Por Agregar");
				DomicilioPersonaList.add(nuevoDomici);
				log.info("se agrego "+  nuevoDomici.getDomicilio());
				}
			if (mensaje != null) {
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
				Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			nuevoDomici = new DomicilioPersonaSie();
		}

		/* METODO AGREGAR TELEFONO */

	public void telefonoAgregar() {
			log.info("telefono agregar " + nuevoTelef.getTelefono());
		if( nuevoTelef.getTelefono()==null||nuevoTelef.getTelefono().equals("")){
			mensaje= "Debe ingresar un número telefónico";
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
			Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		else{
			log.info("telefono agregar " + nuevoTelef.getTelefono());
			boolean verifica = false;
			mensaje = null;
		if (TipoTelef == 1)
			nuevoTelef.setTipotelefono("F");
		else
			nuevoTelef.setTipotelefono("C");
		// claro
		if (operadorTelefonico == 1)
			nuevoTelef.setOperadorTelefonico("Claro");
		else if (operadorTelefonico == 2)
			nuevoTelef.setOperadorTelefonico("Movistar");
		else if (operadorTelefonico == 3)
			nuevoTelef.setOperadorTelefonico("Nextel");
			nuevoTelef.setNuevoT(1);
			nuevoTelef.setIdtelefonopersona(0);
			nuevoTelef.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(17));
		for (int i = 0; i < TelefonoPersonaList.size(); i++) {
			log.info("  " + TelefonoPersonaList.get(i).getTelefono() + " "	+ nuevoTelef.getTelefono());
			if (TelefonoPersonaList.get(i).getTelefono().equals(nuevoTelef.getTelefono())) {
				verifica = false;
				mensaje = "el telefono ya se encuentra registrado en la lista de referencias";
				break;
			}
			else {
				verifica = true;
			}
			}
			if (TelefonoPersonaList.size() == 0) {
			verifica = true;
			}
			if (verifica) {
				//int cantidad=TelefonoPersonaList.size();
				nuevoTelef.setItem("Por Agregar");	
				TelefonoPersonaList.add(nuevoTelef);
				log.info("se agrego " + nuevoTelef.getTelefono());
			 }
			if (mensaje != null) {
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
			Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			nuevoTelef = new TelefonoPersonaSie();
			}
	}
	/**********************DOMICILIO**************************/
	public void cambiar() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(null);
		idProvincia = null;
		idUbigeo1 = null;
		log.info("cambiar   :D  --- ");
	}
	
	public void cambiar2() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(getIdProvincia());
		log.info("cambiar 2  :D  --- ");
	}
	
	/**********************NEW DOMICILIO**************************/
	
	public void cambiarnew() {
		comboManager.setIdDepartamento(getIdDepartamento24());
		comboManager.setIdProvincia(null);
		idProvincia24 = null;
		idUbigeo = 0;
		log.info("cambiar   :D  --- ");
	}
	
	public void cambiar24() {
		comboManager.setIdDepartamento(getIdDepartamento24());
		comboManager.setIdProvincia(getIdProvincia24());
		log.info("cambiar 2  :D  --- ");
	}
	
	//UBIGEO....
	
		public void busquedaUbigeo(){
			log.info("busquedaUbigeo");
			UbigeoSie ubigeo = objUbigeoService.findUbigeo(Integer.parseInt(getIdUbigeo1()));
			log.info("busquedaUbigeo "+ getIdDepartamento()+" - "+getIdProvincia()+" - "+getIdDistrito()+" - "+getIdUbigeo1());
			
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
		if (e.getValue().toString().equals(idUbigeo1)) {
			ubigeoDefecto += "-" + (String) e.getKey();
			log.info("ubigeo distrito " + ubigeoDefecto);
		break;
			}
		}
			log.info("ubigeo ------> :D   " + ubigeoDefecto);
	} 
	
	public void ingresarUbigeo() {
			// enviamos el nombre completo del depa- provincia-distrito
			log.info("ingresarUbigeo :D a --- " + idUbigeo1);
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
		if (e.getValue().toString().equals(idUbigeo1)) {
			ubigeoDefecto += "-" + (String) e.getKey();
			log.info("ubigeo distrito " + ubigeoDefecto);
		break;
			}
		}
		log.info("ubigeo ------> :D   " + ubigeoDefecto);
	}
	
	public void busquedaUbigeo24(){
			log.info("busquedaUbigeo");
			UbigeoSie ubigeo = objUbigeoService.findUbigeo(getIdUbigeo());
			log.info("busquedaUbigeo "+ getIdDepartamento24()+" - "+getIdProvincia24()+" - "+getIdDistrito24()+" - "+getIdUbigeo());
			Iterator it = comboManager.getUbigeoDeparItems().entrySet().iterator();
			Iterator it2 = comboManager.getUbigeoProvinItems().entrySet().iterator();
			Iterator it3 = comboManager.getUbigeoDistriItems().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
		if (e.getValue().toString().equals(idDepartamento24)) {
			ubigeoDefecto24 = (String) e.getKey();
			log.info("ubigeo depa " + ubigeoDefecto24);
		break;
			}
			}
		while (it2.hasNext()) {
			Map.Entry e = (Map.Entry) it2.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
		if (e.getValue().toString().equals(idProvincia24)) {
			ubigeoDefecto24 += "-" + (String) e.getKey();
			log.info("ubigeo prov " + ubigeoDefecto24);
		break;
			}
			}
		while (it3.hasNext()) {
			Map.Entry e = (Map.Entry) it3.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
		if (e.getValue().toString().equals(idUbigeo)) {
			ubigeoDefecto24 += "-" + (String) e.getKey();
			log.info("ubigeo distrito " + ubigeoDefecto24);
		break;
			}
			}
			log.info("ubigeo ------> :D   " + ubigeoDefecto24);
			} 
	
		
	public String ingresarUbigeo24() {
			// enviamos el nombre completo del depa- provincia-distrito
			log.info("ingresarUbigeo :D a --- " + idUbigeo);
			Iterator it = comboManager.getUbigeoDeparItems().entrySet().iterator();
			Iterator it2 = comboManager.getUbigeoProvinItems().entrySet().iterator();
			Iterator it3 = comboManager.getUbigeoDistriItems().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			log.info("key " + e.getKey() + " value " + e.getValue());
		if (e.getValue().toString().equals(idDepartamento24)) {
			ubigeoDefecto24 = (String) e.getKey();
			log.info("ubigeo depa " + ubigeoDefecto24);
		break;
			}
			}
			
		while (it2.hasNext()) {
			Map.Entry e = (Map.Entry) it2.next();
			log.info("key " + e.getKey() + " value " + e.getValue());
		if (e.getValue().toString().equals(idProvincia24)) {
			ubigeoDefecto24 += "- " + (String) e.getKey();
			log.info("ubigeo prov " + ubigeoDefecto24);
			break;
			}
			}
		while (it3.hasNext()) {
			Map.Entry e = (Map.Entry) it3.next();
			log.info("key " + e.getKey() + " value " + e.getValue());
		if (e.getValue().toString().equals(idUbigeo+"")) {
			ubigeoDefecto24 += "- " + (String) e.getKey();
			log.info("ubigeo distrito " + ubigeoDefecto24);
			break;
			}
			}
			log.info("ubigeo ------> :D   " + ubigeoDefecto);
		return getViewMant();
		}
		
		
	public void cambioUbica() {
			log.info("Ubigeo -------->" + idUbigeo1 + " " + ubigeoDefecto);
			String dist = "";
			Iterator it = comboManager.getUbigeoDistriItems().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
		if (e.getValue().toString().equals(idUbigeo1)) {
			dist = (String) e.getKey();
			log.info("dist " + dist);
			break;
			}
			}
			ubigeoDefecto = "LIMA-LIMA-" + dist;
			}
		
	public void cambioUbica24() {
			log.info("Ubigeo -------->" + idUbigeo + " " + ubigeoDefecto24);
			String dist1 = "";
			Iterator it = comboManager.getUbigeoDistriItems().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
		if (e.getValue().toString().equals(idUbigeo)) {
			dist1 = (String) e.getKey();
			log.info("dist1 " + dist1);
			break;
			}
			}
			ubigeoDefecto24 = "LIMA-LIMA-" + dist1;
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
			}
		else {
			comboManager.setIdDepartamento(null);
			comboManager.setIdProvincia(null);
			log.info(" cambio ubigeo   false  otro");
			}
			}
		
		
	public void cambioUbigeoDefecto24() {
			log.info(" defecto  " + defectoUbigeo24);
			comboManager.setUbigeoDeparItems(null);
			comboManager.setUbigeoProvinItems(null);
			comboManager.setUbigeoDistriItems(null);
		if (defectoUbigeo24) {
			comboManager.setIdDepartamento("15");
			comboManager.setIdProvincia("01");
			log.info(" defecto lima true 1");
			ubigeoDefecto24 = "";
		}
		else {
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
		log.info("TIPO DE DOCUMENTO-DNI=1 -->"+ objClienteSie.getTbTipoDocumentoIdentidad().getIdtipodocumentoidentidad());

		/*******DOMICILIO ES PARA EDITAR PARECE***********************/
		log.info("buscar al momento que se hace editar -->");
		objDomicilio = objDomicilioService.buscarDomicilioXIdcliente(objClienteSie.getIdcliente());
		log.info(" --Id-- " + objDomicilio.getIddomiciliopersona()+ " --IdCLIENTE-- " + objDomicilio.getIdcliente()+ " --Ubicacion-- "+ objDomicilio.getUbicacion() );
		
		/**********NUEVO DOMICILIO********************************/
		log.info("Antes de llegar al objDomicilioNew--->  ");
		objDomicilioNew = objDomicilioService.buscarDomicilioXIdcliente(objClienteSie.getIdcliente());
		log.info("despues de llegar al objDomicilioNew"+ objDomicilioNew.getIdcliente());
	    
        /*seteo domicilio*/
	if(objDomicilio!=null){
		if (objDomicilio.getTbUbigeo()!=null) {
		log.info("Dentro del IF");	
        setIdDepartamento(objDomicilio.getTbUbigeo().getCoddepartamento());
        log.info("setIdDepartamento---> "+ objDomicilio.getTbUbigeo().getCoddepartamento());
        comboManager.setIdDepartamento(idDepartamento);
        setIdProvincia(objDomicilio.getTbUbigeo().getCodprovincia());
        comboManager.setIdProvincia(idProvincia);
        setIdUbigeo1(objDomicilio.getTbUbigeo().getIdubigeo().toString());
        setIdDistrito(objDomicilio.getTbUbigeo().getCoddistrito());	        
        setTipo(objDomicilio.getTbTipoCasa().getIdtipocasa());
        log.info(" tipo casa "+objDomicilio.getTbTipoCasa().getIdtipocasa());	        
        objDomicilio.setTbEstadoGeneral(objDomicilio.getTbEstadoGeneral());	        
		}
	else{
		log.info("Dentro del Else");
		 objDomicilio.setUbicacion(objDomicilio.getUbicacion());
	     log.info("--UBICACION--"+ objDomicilio.getUbicacion());
	     setTipo(objDomicilio.getTbTipoCasa().getIdtipocasa());
	     log.info(" tipo casa "+objDomicilio.getTbTipoCasa().getIdtipocasa());			        			        
	     objDomicilio.setTbEstadoGeneral(objDomicilio.getTbEstadoGeneral());
		}
       	} 
        /*****************TELEFONO************************/
        
		TelefonoPersonaSie t = objTelefonoService.buscarTelefonoXIdcliente(objClienteSie.getIdcliente());
		log.info(" id " + t.getIdtelefonopersona()+ " numero Telefonico " + t.getTelefono() );
		 /***seteo teléfono***/
        TelefonoPersonaList = objTelefonoService.listarTelefonoEmpleadosXidcliente(objClienteSie.getIdcliente());        
	for (int i = 0; i < TelefonoPersonaList.size(); i++) {
    	TelefonoPersonaList.get(i).setItem("Agregado");
        }
	
		/*******************NUEVO DOMICILIO***************************/
		DomicilioPersonaSie d = objDomicilioService.buscarDomicilioXIdcliente(objClienteSie.getIdcliente());
		log.info("ID-DOMICILIO-->  "+ d.getIddomiciliopersona()+ " DOMICILIO--->  " + d.getDomicilio());
		/*****Lista Domicilio***/
		DomicilioPersonaList = objDomicilioService.listarDomicilioCliente(objClienteSie.getIdcliente());
	for (int i = 0; i < DomicilioPersonaList.size(); i++) {
		DomicilioPersonaList.get(i).setItem("Agregado");		
		}
		
		setNewRecord(false);
		return getViewMant();
		}

	/* REGISTRAR LOS DATOS DEL CLIENTE EDITADO */

	public String insertarOficial() {
		log.info("LISTA DE DESHABILITADO-->   "+ TelefonoDeshabilitado.size()); 
		log.info("insertarOficial() " + "Id-Cliente-->"+"  "+ objClienteSie.getIdcliente() + " "+"Nombre de Cliente"+"  "+ objClienteSie.getNombrecliente());
		String paginaretorno="";
		try {
			if (log.isInfoEnabled()) {
			}
			
			log.info("busca el Id-DNI");
			objClienteSie.setTbTipoDocumentoIdentidad(objTipoDocService.buscarTipoDocumento(TipoDocumento));
			
			log.info("busca el Id-Ubigeo");
			objDomicilio.setTbUbigeo(objUbigeoService.findUbigeo(Integer.parseInt(idUbigeo1)));
			
			log.info("*******AQUI ESTA SI HAY UN VALOR DENTRO DE LA LISTA TELEFONOS PARA ELIMINAR*******");
			log.info("Tel-FISICO--------->"+ "  "+ TelefonoDeshabilitado.size());
			log.info("Tel-TEMPORAL------->"+ "	"+ TelefonoPersonaList.size());
			
			log.info("*******AQUI ESTA SI HAY UN VALOR DENTRO DE LA LISTA DOMICILIO PARA ELIMINAR*******");
			log.info("Domicilio-FISICO--------->"+ "  "+ DomicilioPersonaDeshabilitado.size());
			log.info("Domicilio-TEMPORAL------->"+ "	"+ DomicilioPersonaList.size());
			objClienteService.updateCliente(objClienteSie,objDomicilio,idUbigeo1, idUbigeo, tipo, Tipocasanuevo,TelefonoPersona,TelefonoPersonaList, TelefonoDeshabilitado, DomicilioPersonaList, DomicilioPersonaDeshabilitado);
			
			mensaje = Constants.MESSAGE_ACTUALIZO_TITULO;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
			Constants.MESSAGE_INFO_TITULO, mensaje);
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
	
	/*********************** METODO WIZAR ****************************************/
	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = true; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}
	
	public void limpiarDatosTelefono() {
		nuevoTelef = new TelefonoPersonaSie();
	}
	
	public void limpiarDatosDomicilionew() {
		
		nuevoDomici = new DomicilioPersonaSie();
	}
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
	public String getIdUbigeo1() {
		return idUbigeo1;
	}

	/**
	 * @param idUbigeo the idUbigeo to set
	 */
	public void setIdUbigeo1(String idUbigeo1) {
		this.idUbigeo1 = idUbigeo1;
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

	/**
	 * @return the defectoUbigeo
	 */
	public boolean isDefectoUbigeo() {
		return defectoUbigeo;
	}

	/**
	 * @param defectoUbigeo the defectoUbigeo to set
	 */
	public void setDefectoUbigeo(boolean defectoUbigeo) {
		this.defectoUbigeo = defectoUbigeo;
	}

	/**
	 * @return the tipoTelef
	 */
	public int getTipoTelef() {
		return TipoTelef;
	}

	/**
	 * @param tipoTelef the tipoTelef to set
	 */
	public void setTipoTelef(int tipoTelef) {
		TipoTelef = tipoTelef;
	}

	/**
	 * @return the operadorTelefonico
	 */
	public int getOperadorTelefonico() {
		return operadorTelefonico;
	}

	/**
	 * @param operadorTelefonico the operadorTelefonico to set
	 */
	public void setOperadorTelefonico(int operadorTelefonico) {
		this.operadorTelefonico = operadorTelefonico;
	}

	/**
	 * @return the nuevoTelef
	 */
	public TelefonoPersonaSie getNuevoTelef() {
		return nuevoTelef;
	}

	/**
	 * @param nuevoTelef the nuevoTelef to set
	 */
	public void setNuevoTelef(TelefonoPersonaSie nuevoTelef) {
		this.nuevoTelef = nuevoTelef;
	}

	/**
	 * @return the telefonoPersonaList
	 */
	public List<TelefonoPersonaSie> getTelefonoPersonaList() {
		return TelefonoPersonaList;
	}

	/**
	 * @param telefonoPersonaList the telefonoPersonaList to set
	 */
	public void setTelefonoPersonaList(List<TelefonoPersonaSie> telefonoPersonaList) {
		TelefonoPersonaList = telefonoPersonaList;
	}

	/**
	 * @return the telefonoDeshabilitado
	 */
	public List<TelefonoPersonaSie> getTelefonoDeshabilitado() {
		return TelefonoDeshabilitado;
	}

	/**
	 * @param telefonoDeshabilitado the telefonoDeshabilitado to set
	 */
	public void setTelefonoDeshabilitado(
			List<TelefonoPersonaSie> telefonoDeshabilitado) {
		TelefonoDeshabilitado = telefonoDeshabilitado;
	}

	/**
	 * @return the idt
	 */
	
	/**
	 * @return the objTelefono
	 */
	public TelefonoPersonaSie getObjTelefono() {
		return objTelefono;
	}

	/**
	 * @return the idt
	 */
	public int getIdt() {
		return idt;
	}

	/**
	 * @param idt the idt to set
	 */
	public void setIdt(int idt) {
		this.idt = idt;
	}

	/**
	 * @param objTelefono the objTelefono to set
	 */
	public void setObjTelefono(TelefonoPersonaSie objTelefono) {
		this.objTelefono = objTelefono;
	}

	/**
	 * @return the telefonoPersona
	 */
	public int getTelefonoPersona() {
		return TelefonoPersona;
	}

	/**
	 * @param telefonoPersona the telefonoPersona to set
	 */
	public void setTelefonoPersona(int telefonoPersona) {
		TelefonoPersona = telefonoPersona;
	}



	/****************FIntelefonos***************************/
	
/*******************NUEVO DOMICILIO **********************************/
	/**
	 * @return the 
	 */
	public DomicilioPersonaSie getObjDomicilioNew() {
		return objDomicilioNew;
	}

	/**
	 * @param objDomicilioNew the objDomicilioNew to set
	 */
	public void setObjDomicilioNew(DomicilioPersonaSie objDomicilioNew) {
		this.objDomicilioNew = objDomicilioNew;
	}

	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @return the domicilioPersonaList
	 */
	public List<DomicilioPersonaSie> getDomicilioPersonaList() {
		return DomicilioPersonaList;
	}

	/**
	 * @param domicilioPersonaList the domicilioPersonaList to set
	 */
	public void setDomicilioPersonaList(
			List<DomicilioPersonaSie> domicilioPersonaList) {
		DomicilioPersonaList = domicilioPersonaList;
	}

	/**
	 * @return the domicilioPersonaDeshabilitado
	 */
	public List<DomicilioPersonaSie> getDomicilioPersonaDeshabilitado() {
		return DomicilioPersonaDeshabilitado;
	}

	/**
	 * @param domicilioPersonaDeshabilitado the domicilioPersonaDeshabilitado to set
	 */
	public void setDomicilioPersonaDeshabilitado(
			List<DomicilioPersonaSie> domicilioPersonaDeshabilitado) {
		DomicilioPersonaDeshabilitado = domicilioPersonaDeshabilitado;
	}

	/**
	 * @return the nuevoDomici
	 */
	public DomicilioPersonaSie getNuevoDomici() {
		return nuevoDomici;
	}

	/**
	 * @param nuevoDomici the nuevoDomici to set
	 */
	public void setNuevoDomici(DomicilioPersonaSie nuevoDomici) {
		this.nuevoDomici = nuevoDomici;
	}

	/**
	 * @return the domicilioPersonaSieList
	 *//**
	 * @return the idd
	 */
	public int getIdd() {
		return idd;
	}

	/**
	 * @param idd the idd to set
	 */
	public void setIdd(int idd) {
		this.idd = idd;
	}

	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return the tipocasanuevo
	 */
	public int getTipocasanuevo() {
		return Tipocasanuevo;
	}

	/**
	 * @param tipocasanuevo the tipocasanuevo to set
	 */
	public void setTipocasanuevo(int tipocasanuevo) {
		Tipocasanuevo = tipocasanuevo;
	}

	/**
	 * @return the idUbigeo
	 */
	public int getIdUbigeo() {
		return idUbigeo;
	}

	/**
	 * @param idUbigeo the idUbigeo to set
	 */
	public void setIdUbigeo(int idUbigeo) {
		this.idUbigeo = idUbigeo;
	}

	/**
	 * @return the idProvincia24
	 */
	public String getIdProvincia24() {
		return idProvincia24;
	}

	/**
	 * @param idProvincia24 the idProvincia24 to set
	 */
	public void setIdProvincia24(String idProvincia24) {
		this.idProvincia24 = idProvincia24;
	}

	/**
	 * @return the idDepartamento24
	 */
	public String getIdDepartamento24() {
		return idDepartamento24;
	}

	/**
	 * @param idDepartamento24 the idDepartamento24 to set
	 */
	public void setIdDepartamento24(String idDepartamento24) {
		this.idDepartamento24 = idDepartamento24;
	}


	/**
	 * @return the idDistrito24
	 */
	public String getIdDistrito24() {
		return idDistrito24;
	}

	/**
	 * @param idDistrito24 the idDistrito24 to set
	 */
	public void setIdDistrito24(String idDistrito24) {
		this.idDistrito24 = idDistrito24;
	}

	/**
	 * @return the defectoUbigeo24
	 */
	public boolean isDefectoUbigeo24() {
		return defectoUbigeo24;
	}

	/**
	 * @param defectoUbigeo24 the defectoUbigeo24 to set
	 */
	public void setDefectoUbigeo24(boolean defectoUbigeo24) {
		this.defectoUbigeo24 = defectoUbigeo24;
	}

	/**
	 * @return the ubigeoDefecto24
	 */
	public String getUbigeoDefecto24() {
		return ubigeoDefecto24;
	}

	/**
	 * @param ubigeoDefecto24 the ubigeoDefecto24 to set
	 */
	public void setUbigeoDefecto24(String ubigeoDefecto24) {
		this.ubigeoDefecto24 = ubigeoDefecto24;
	}



	
}
