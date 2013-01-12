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
	
	/**************WIZAR**************************/	
	private boolean skip;	
	/*******************FIN WIZAR******************************/
	
	/*****************DATOS BASICOS DE CLIENTE***************************/
	public String nombrecliente;

	private int TipoDocumento;
	private int ide, idEstadoGeneral;
	
	/*****************FIN BASICOS DE CLIENTE***************************/

	
	
												/* DATOS DE TELEFONO CLIENTE */
	private TelefonoPersonaSie nuevoTelef;	
	private int TipoTelef, operadorTelefonico;
	private String selectTelef;
	private int telefonopersona;
	private int idc;
												/* DOMICILIO CLIENTE */
	
	private DomicilioPersonaSie objDomicilio;
	private String idProvincia, idDepartamento, ubigeoDefecto, idUbigeo;
	private String idDistrito;
	private int tipo;
	private boolean defectoUbigeo;
	
	
	private DomicilioPersonaSie nuevoDomicilio;
		
//	private int iddom, iddomicilio;	
//	private int  idUbigeoD,defectoUbigeo;	
	private boolean editMode;
	private boolean newRecord = false;
	public String mensaje;

	private int Iddomiciliopersona;
	private int estado;

	
	
	
	
	
	
	private ClienteSie nuevo;
	private ClienteSie objClienteSie;
	private TelefonoPersonaSie objTelefonoPersonaSie;
														/* LISTA DE TELEFONOS */
	private List<TelefonoPersonaSie> TelefonoPersonaList;
													
	
														/*LISTA DE DOMICILIO*/
	private List<DomicilioPersonaSie> DomicilioPersonaList;
	
	
	
	
	
	private Log log = LogFactory.getLog(MantenimientoClienteFormAction.class);	

	
	/*TIPO DE CASA*/
	private int Tipocasa, idcliente;
	
	/************************/
	
	/*************************/

	@ManagedProperty(value="#{comboAction}") 
	private ComboAction comboManager;
	
	


	@EJB
	private ClienteService objClienteService;
	@EJB
	private EstadogeneralService objEstadoGeneralService;
	@EJB
	private UbigeoService objUbigeoService;
	@EJB
	private TelefonoEmpleadoService objTelefonoService;
	@EJB
	private TipoDocumentoService objTipoDocService;
	@EJB
	private DomicilioEmpleadoService objDomicilioService;
	@EJB
	private TipoCasaService objTipoCasaService;
	
	public MantenimientoClienteFormAction() {
		init();
	}
	
	
	
	

	public List<TelefonoPersonaSie> getTelefonoPersonaList() {
		return TelefonoPersonaList;
	}

	public List<DomicilioPersonaSie> getDomicilioPersonaList() {
		return DomicilioPersonaList;
	}

	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoClienteFormAction'");
		objClienteSie = new ClienteSie();
		
		
		
		//lista secundaria de domicilios de la base de datos no son temporales
		
	DomicilioPersonaList = new ArrayList<DomicilioPersonaSie>();
	
	
	/***********DOMICILIO******************/
	objDomicilio = new DomicilioPersonaSie();
	defectoUbigeo=true;
	
	
	
		
	
	/**********TELEFONO*************/
	objTelefonoPersonaSie = new TelefonoPersonaSie();
	nuevoTelef = new TelefonoPersonaSie();
	nuevoTelef.setTipoTelef("");
	selectTelef="";
	TipoTelef=1;
	TelefonoPersonaList = new ArrayList<TelefonoPersonaSie>();
	operadorTelefonico=1;	
	TipoDocumento=1;	

	
	
	nuevo = new ClienteSie();
		
	}
	

	public void limpiarDatosTelefono() {
		nuevoTelef = new TelefonoPersonaSie();
	}	
	
	public String agregar() {
		log.info("agregar");
		
		objClienteSie = new ClienteSie();
		
		defectoUbigeo=true;
		if(defectoUbigeo){
			comboManager.setIdDepartamento("15");
			comboManager.setIdProvincia("01");
			}
			idUbigeo="0";
			ubigeoDefecto="";
			comboManager.setUbigeoDistriItems(null);
		setTipo(0);
		objDomicilio = new DomicilioPersonaSie();
		comboManager.setIdDepartamento("15");
		comboManager.setIdProvincia("01");
				
		/************TELEFONO***************/
		selectTelef= "";
		operadorTelefonico=1;
		TipoTelef=1;
		//para inicializar los campos de nuevo telefono
		nuevoTelef.setTelefono("");
		//fin para inicializar los campos de nuevo telefono
		objTelefonoPersonaSie = new TelefonoPersonaSie();
		
		
		/******tipo de documento*******/
		setTipoDocumento(0);
		
		
		
		
		
		
		editMode = true;		
		setNewRecord(true);
		return getViewMant();
		
	}
	
	

	
/*************************************************/
	public String update() throws Exception {
			
//		objDomicilioPersonaSie= objDomicilioPersonaService.buscarDomicilioEmpleado(objDomicilioPersonaSie.getIddomiciliopersona());		
//		log.info(" id cargo " + objDomicilioPersonaSie.getIddomiciliopersona() );		
//		setIddomiciliopersona(objDomicilioPersonaSie.getIddomiciliopersona());		
//	setEstado(objDomicilioPersonaSie.getTbEstadoGeneral().getIdestadogeneral());
//		setIdUbigeo(objDomicilioPersonaSie.getTbUbigeo().getIdubigeo()+"");	
		UbigeoSie ubigeo = objUbigeoService.findUbigeo(Integer.parseInt(getIdUbigeo()));
		setIdDepartamento(ubigeo.getCoddepartamento());
		comboManager.setIdDepartamento(idDepartamento);
		setIdProvincia(ubigeo.getCodprovincia());
		comboManager.setIdProvincia(idProvincia);
		setIdDistrito(ubigeo.getCoddistrito());
		ubigeoDefecto="";
		log.info("busquedaUbigeo "+ getIdDepartamento()+" - "+getIdProvincia()+" - "+getIdDistrito()+" - "+getIdUbigeo());
		
		setTipoDocumento(objClienteSie.getTbTipoDocumentoIdentidad().getIdtipodocumentoidentidad());
		setTipocasa(objDomicilio.getTbTipoCasa().getIdtipocasa());
		
		
		log.info("update()");
		
		
		
//		DomicilioPersonaSie d = objDomicilioService.buscarDomicilioXIdempleado(objClienteSie.getIdcliente());
		
		
		setNewRecord(false);
		editMode = false;
		return getViewMant();
	}
	
	/*************este insertar es parte dos= cuando se listen los domicilio
	public String insertar() {
		log.info("insertar()");
//		log.info("insertar() sv " + isNewRecord() 
//				+ objDomicilio.getIddomiciliopersona()  );
//		log.info("actualizando JBS..... "+objClienteSie.getIdcliente() );
//		objDomicilio.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(15));
//		objDomicilio.setTbTipoCasa(objTipoCasaService.findTipoCasa(Tipocasa));
//		
//	
		
		
		try {
			
//			objDomicilio.setTbUbigeo(objUbigeoService.findUbigeo(Integer.parseInt(idUbigeo)));
			
			if (isNewRecord()) {
				
				
				
//				objDomicilioService.insertarDomicilioEmpleado(objDomicilio);
				
				
			} else {

//				objDomicilio.setIddomiciliopersona(getIddomiciliopersona()); 
//				objDomicilio.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(15));
//				objDomicilio.setTbTipoCasa(objTipoCasaService.findTipoCasa(Tipocasa));		
//				objDomicilio.setDomicilio(objDomicilio.getDomicilio());
//				objDomicilio.setReferencia(objDomicilio.getReferencia());
//				objDomicilioService.actualizarDomicilioEmpleado(objDomicilio);
//				
//				log.info("actualizado");
			}
		} catch (Exception e) {

			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}


		
		return getViewMant();
	}
	
**********************/


	
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
	/*************************************************/


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
	
	
	
	
/*METODO UBIGEO MUESTRA EN LA LISTA DEPARTAMENTO_PROVINCIA_DISTRITO*/

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
	
	
	
//	public void ingresarUbigeoLista() {
//		// enviamos el nombre completo del depa- provincia-distrito
//		log.info("ingresarUbigeo :D a :D ");
//		
//		UbigeoSie objUbigeoDomicilio = new UbigeoSie();
//	
//		for (int i = 0; i < DomicilioPersonaList.size() ; i++) {
//			objDomicilioPersonaSie = DomicilioPersonaList.get(i);
//			idUbigeo= DomicilioPersonaList.get(i).getTbUbigeo().getIdubigeo()+"";
//				
//		log.info("ingresarUbigeo :D a --- " + idUbigeo);
//		
//		objUbigeoDomicilio = objUbigeoService.findUbigeo(Integer.parseInt(idUbigeo));
//
//		log.info("ingresarUbigeo :D a --- " + objUbigeoDomicilio.getIdubigeo());
//		comboManagerDomicilio.setIdDepartamento(objUbigeoDomicilio.getCoddepartamento());
//		comboManagerDomicilio.setIdProvincia(objUbigeoDomicilio.getCodprovincia());
//
//		Iterator it = comboManagerDomicilio.getUbigeoDeparItems().entrySet().iterator();
//		
//		Iterator it2 = comboManagerDomicilio.getUbigeoProvinItems().entrySet().iterator();
//		Iterator it3 = comboManagerDomicilio.getUbigeoDistriItems().entrySet().iterator();
//
//		while (it.hasNext()) {
//			Map.Entry e = (Map.Entry) it.next();
//			log.info("key " + e.getKey() + " value " + e.getValue());
//			if (e.getValue().toString()
//					.equals(objUbigeoDomicilio.getCoddepartamento())) {
//				ubigeoDefecto = (String) e.getKey();
//				log.info("ubigeo depa " + ubigeoDefecto);
//				break;
//			}
//		}
//		log.info(" 2.");
//		while (it2.hasNext()) {
//			Map.Entry e = (Map.Entry) it2.next();
//			log.info("key " + e.getKey() + " value " + e.getValue());
//			if (e.getValue().toString()
//					.equals(objUbigeoDomicilio.getCodprovincia())) {
//				ubigeoDefecto += "-" + (String) e.getKey();
//				log.info("ubigeo prov " + ubigeoDefecto);
//				break;
//			}
//		}
//		log.info(" 3 .");
//		while (it3.hasNext()) {
//			Map.Entry e = (Map.Entry) it3.next();
//			log.info("key " + e.getKey() + " value " + e.getValue());
//			if (e.getValue().toString()
//					.equals(objUbigeoDomicilio.getIdubigeo() + "")) {
//				ubigeoDefecto += "-" + (String) e.getKey();
//				log.info("ubigeo distrito " + ubigeoDefecto);
//				break;
//			}
//		}
//		DomicilioPersonaList.get(i).setDesUbigeo(ubigeoDefecto);
//
//		log.info("ubigeo ------> :D   " + ubigeoDefecto);
//		}
//	}


	/* METODO AGREGAR TELEFONO */

	public void telefonoAgregar() {
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
		nuevoTelef.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(17));

		for (int i = 0; i < TelefonoPersonaList.size(); i++) {
			log.info("  " + TelefonoPersonaList.get(i).getTelefono() + " "
					+ nuevoTelef.getTelefono());
			if (TelefonoPersonaList.get(i).getTelefono().equals(nuevoTelef.getTelefono())) {
				verifica = false;
				mensaje = "el telefono ya se encuentra registrado en la lista de referencias";
				break;
			} else {
				verifica = true;
			}
		}
		if (TelefonoPersonaList.size() == 0) {
			verifica = true;
		}
		if (verifica) {
		  
			int cantidad=TelefonoPersonaList.size();
			
			nuevoTelef.setItem(cantidad+1);	
			
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

	
	/*****************Eliminar telefono del template al wizar ********************************/
	
	
	
	 public void telefonoElimina(){
	    	log.info("en eliminarProducto()");
			for (int i = 0; i < TelefonoPersonaList.size(); i++) {
				if(TelefonoPersonaList.get(i).getItem()==(ide)){
					TelefonoPersonaList.remove(i);
					for (int j = i; j < TelefonoPersonaList.size(); j++) {
						log.info(" i " +i+"  j "+ j);
						i=i+1;
						TelefonoPersonaList.get(j).setItem(i);
						TelefonoPersonaList.set(j, TelefonoPersonaList.get(j));
					}
				}
			}
			ide=0;
	    }
	 
	
	/***************************FIN*********************************************/
/*REGISTRAR LOS DATOS DEL CLIENTE EDITADO*/
	

public String insertarOficial() {
	log.info("insertarOficial() "+objClienteSie.getIdcliente() + objClienteSie.getNombrecliente());
		

		try {
			if (log.isInfoEnabled()) {


			}
			
objClienteSie.setTbTipoDocumentoIdentidad(objTipoDocService.buscarTipoDocumento(TipoDocumento));
		
objClienteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(23));	

objDomicilio.setTbUbigeo(objUbigeoService.findUbigeo(Integer.parseInt(idUbigeo)));


			
log.info("actualizando..... "+objClienteSie.getIdcliente() );
			
objClienteService.updateCliente(objClienteSie, TelefonoPersonaList,	
		tipo, objDomicilio,idUbigeo);//aqui eh puesto Ubigeo
			
			
mensaje ="";
msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
		Constants.MESSAGE_REGISTRO_TITULO, mensaje);
FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info("depues de haber insertado bean");
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

	/* METODO EDITAR DATOS DE CLIENTE CUANDO SE SELECCIONA EL BOTON EDITAR */

	public String updateGeneral() throws Exception {
		log.info("updateGeneral()");
 
		log.info(" id cliente " + objClienteSie.getIdcliente() + " nombre "	+ objClienteSie.getNombrecliente());
		
		

		TelefonoPersonaList = objTelefonoService.listarTelefonoCliente(objClienteSie.getIdcliente());		
	DomicilioPersonaList = objDomicilioService.listarDomicilioCliente(objClienteSie.getIdcliente());


		
		if (TelefonoPersonaList == null)
			TelefonoPersonaList = new ArrayList<TelefonoPersonaSie>();

		TipoDocumento = objClienteSie.getTbTipoDocumentoIdentidad().getIdtipodocumentoidentidad();

		if (DomicilioPersonaList == null)
			DomicilioPersonaList = new ArrayList<DomicilioPersonaSie>();
		
		
	setTipoDocumento(objClienteSie.getTbTipoDocumentoIdentidad().getIdtipodocumentoidentidad());
 
	
	
		return getViewMant();

	}	
	
	
	
	
	/***********************METODO WIZAR****************************************/
	public String onFlowProcess(FlowEvent event) {  
	    log.info("Current wizard step:" + event.getOldStep());  
	    log.info("Next step:" + event.getNewStep());  
	    log.info("skip :"+skip);
	     if(skip) {  
	            skip = true;   //reset in case user goes back  
	            return "confirm";  
	        }  
	     else {  
	            return event.getNewStep();  
	      }
	}	
	
	/**************************FIN WIZAR*********************************/
	
	
	public String getViewMant() {
		return Constants.MANT_CLIENTE_FORM_PAGE;
	}
	public String getViewList() {
		return Constants.MANT_CLIENTE_FORM_LIST_PAGE;
	}
	/*METODOS GET Y SET*/

	/**
	 * @return the objClienteSie
	 */
	public ClienteSie getObjClienteSie() {
		return objClienteSie;
	}

	/**
	 * @param objClienteSie
	 *            the objClienteSie to set
	 */
	public void setObjClienteSie(ClienteSie objClienteSie) {
		this.objClienteSie = objClienteSie;
	}


	/**
	 * @return the nuevo
	 */
	public ClienteSie getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo
	 *            the nuevo to set
	 */
	public void setNuevo(ClienteSie nuevo) {
		this.nuevo = nuevo;
	}



	/**
	 * @return the nombrecliente
	 */
	public String getNombrecliente() {
		return nombrecliente;
	}

	/**
	 * @param nombrecliente
	 *            the nombrecliente to set
	 */
	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	/**
	 * @return the ide
	 */
	public int getIde() {
		return ide;
	}

	/**
	 * @param ide
	 *            the ide to set
	 */
	public void setIde(int ide) {
		this.ide = ide;
	}

	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}

	/**
	 * @param idEstadoGeneral
	 *            the idEstadoGeneral to set
	 */
	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
	}

	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * @return the tipoDocumento
	 */
	public int getTipoDocumento() {
		return TipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(int tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}

	public int getTelefonopersona() {
		return telefonopersona;
	}

	/**
	 * @param telefonopersona
	 *            the telefonopersona to set
	 */
	public void setTelefonopersona(int telefonopersona) {
		this.telefonopersona = telefonopersona;
	}

	/**
	 * @return the objTelefonoPersonaSie
	 */
	public TelefonoPersonaSie getObjTelefonoPersonaSie() {
		return objTelefonoPersonaSie;
	}

	/**
	 * @param objTelefonoPersonaSie
	 *            the objTelefonoPersonaSie to set
	 */
	public void setObjTelefonoPersonaSie(
			TelefonoPersonaSie objTelefonoPersonaSie) {
		this.objTelefonoPersonaSie = objTelefonoPersonaSie;
	}

	/**
	 * @return the telefonoPersonaList
	 */

	/**
	 * @param telefonoPersonaList
	 *            the telefonoPersonaList to set
	 */
	public void setTelefonoPersonaList(
			List<TelefonoPersonaSie> telefonoPersonaList) {
		TelefonoPersonaList = telefonoPersonaList;
	}

	/**
	 * @return the idc
	 */
	public int getIdc() {
		log.info("IDC *get** " + idc);
		return idc;
	}

	/**
	 * @param idc
	 *            the idc to set
	 */
	public void setIdc(int idc) {
		this.idc = idc;
	}

	/**
	 * @return the nuevoTelef
	 */
	public TelefonoPersonaSie getNuevoTelef() {
		return nuevoTelef;
	}

	/**
	 * @param nuevoTelef
	 *            the nuevoTelef to set
	 */
	public void setNuevoTelef(TelefonoPersonaSie nuevoTelef) {
		this.nuevoTelef = nuevoTelef;
	}

	/**
	 * @return the tipoTelef
	 */
	public int getTipoTelef() {
		return TipoTelef;
	}

	/**
	 * @param tipoTelef
	 *            the tipoTelef to set
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
	 * @param operadorTelefonico
	 *            the operadorTelefonico to set
	 */
	public void setOperadorTelefonico(int operadorTelefonico) {
		this.operadorTelefonico = operadorTelefonico;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the domicilioPersonaList
	 */

	/**
	 * @param domicilioPersonaList
	 *            the domicilioPersonaList to set
	 */
	public void setDomicilioPersonaList(
			List<DomicilioPersonaSie> domicilioPersonaList) {
		DomicilioPersonaList = domicilioPersonaList;
	}
 

	/**
	 * @return the objDomicilioPersonaSie
	 */


	/**
	 * @return the comboManagerDomicilio
	 */
	
	/**
	 * @return the objDomicilioPersonaSie
	 */

	/**
	 * @return the ubigeoDefecto
	 */
	public String getUbigeoDefecto() {
		return ubigeoDefecto;
	}

	/**
	 * @param ubigeoDefecto
	 *            the ubigeoDefecto to set
	 */
	public void setUbigeoDefecto(String ubigeoDefecto) {
		this.ubigeoDefecto = ubigeoDefecto;
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
	 * @return the tipocasa
	 */
	public int getTipocasa() {
		return Tipocasa;
	}




	/**
	 * @param tipocasa the tipocasa to set
	 */
	public void setTipocasa(int tipocasa) {
		Tipocasa = tipocasa;
	}
	
	/**
	 * @return the nuevoDomicilio
	 */
	public DomicilioPersonaSie getNuevoDomicilio() {
		return nuevoDomicilio;
	}




	/**
	 * @param nuevoDomicilio the nuevoDomicilio to set
	 */
	public void setNuevoDomicilio(DomicilioPersonaSie nuevoDomicilio) {
		this.nuevoDomicilio = nuevoDomicilio;
	}

	/**
	 * @return the iddom
	 */
	

	/**
	 * @param idDistrito the idDistrito to set
	 */
	public void setIdDistrito(String idDistrito) {
		this.idDistrito = idDistrito;
	}

	/**
	 * @return the log
	 */
	public Log getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(Log log) {
		this.log = log;
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
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	/**
	 * @return the iddomiciliopersona
	 */
	public int getIddomiciliopersona() {
		return Iddomiciliopersona;
	}

	/**
	 * @param iddomiciliopersona the iddomiciliopersona to set
	 */
	public void setIddomiciliopersona(int iddomiciliopersona) {
		Iddomiciliopersona = iddomiciliopersona;
	}



	/**
	 * @param idUbigeo the idUbigeo to set
	 */
	public void setIdUbigeo(String idUbigeo) {
		this.idUbigeo = idUbigeo;
	}
	
	



	/**
	 * @return the idUbigeo
	 */
	public String getIdUbigeo() {
		return idUbigeo;
	}




	/**
	 * @return the idDistrito
	 */
	public String getIdDistrito() {
		return idDistrito;
	}



	/**
	 * @return the idUbigeoD
	 */



	/**
	 * @return the idcliente
	 */
	public int getIdcliente() {
		return idcliente;
	}



	/**
	 * @param idcliente the idcliente to set
	 */
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}



	/**
	 * @return the skip
	 */
	public boolean isSkip() {
		return skip;
	}



	/**
	 * @param skip the skip to set
	 */
	public void setSkip(boolean skip) {
		this.skip = skip;
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
	 * @return the comboManager
	 */
	public ComboAction getComboManager() {
		return comboManager;
	}



	/**
	 * @param comboManager the comboManager to set
	 */
	public void setComboManager(ComboAction comboManager) {
		this.comboManager = comboManager;
	}




	
}
