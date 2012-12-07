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

	public String nombrecliente;
	private String operadortelefonico;
	private int TipoDocumento, telefonopersona;
	private int ide, idEstadoGeneral;
	private int idc,iddom, iddomicilio;
	
	/* DATOS DE TELEFONO CLIENTE */
	private TelefonoPersonaSie nuevoTelef;	
	private int TipoTelef, operadorTelefonico;
	
	/* COMBITO DE UBIGEO */
	
	private ClienteSie nuevo;
	private ClienteSie objClienteSie;
	private TelefonoPersonaSie objTelefonoPersonaSie;
	/* LISTA DE TELEFONOS */
	private List<TelefonoPersonaSie> TelefonoPersonaList;
	/* DOMICILIO CLIENTE */
	private DomicilioPersonaSie nuevoDomicilio;	
	
	
	private List<DomicilioPersonaSie> DomicilioPersonaList;
	private Log log = LogFactory.getLog(MantenimientoClienteFormAction.class);	

	
	/*TIPO DE CAAS*/
	private int Tipocasa, idcliente;
	
	/************************/
	private String  idUbigeo;
	private int  idUbigeoD;
	
	private boolean editMode,defectoUbigeo;
	private boolean newRecord = false;
	public String mensaje, ubigeoDefecto;
	private String idProvincia, idDepartamento, idDistrito;
	private int Iddomiciliopersona, estado;
	private DomicilioPersonaSie objDomicilioPersonaSie;
	/*************************/

	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManagerDomicilio;
	
	


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
	private DomicilioEmpleadoService objDomicilioPersonaService;
	@EJB
	private TipoCasaService objTipoCasaService;
	
	public MantenimientoClienteFormAction() {
		init();
	}
	
	
	
	public void limpiarDatosTelefono() {
		nuevoTelef = new TelefonoPersonaSie();
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
		nuevoTelef = new TelefonoPersonaSie();
		nuevoTelef.setTipoTelef("");
		objTelefonoPersonaSie = new TelefonoPersonaSie();
		TelefonoPersonaList = new ArrayList<TelefonoPersonaSie>();
		
	DomicilioPersonaList = new ArrayList<DomicilioPersonaSie>();
	
	objDomicilioPersonaSie = new DomicilioPersonaSie();
	defectoUbigeo=true;
	
	

		
		nuevo = new ClienteSie();
		
	}
	

	
	public String agregar() {
		log.info("agregar");
		
		objClienteSie = new ClienteSie();
		
		
	
		defectoUbigeo=true;
		
		comboManagerDomicilio.setIdDepartamento(null);
		comboManagerDomicilio.setUbigeoDeparItems(null);
		comboManagerDomicilio.setIdProvincia(null);
		comboManagerDomicilio.setUbigeoProvinItems(null);
		getIdUbigeo();
		ubigeoDefecto="";
		comboManagerDomicilio.setUbigeoDistriItems(null);
		editMode = true;		
		setNewRecord(true);
		objDomicilioPersonaSie = new DomicilioPersonaSie();
		
		return getViewMant();
		
	}
	
	

	public String getViewMant() {
		return Constants.MANT_CLIENTE_FORM_PAGE;
	}
	public String getViewList() {
		return Constants.MANT_CLIENTE_FORM_LIST_PAGE;
	}
/*************************************************/
	public String update() throws Exception {
		log.info("update()" + objDomicilioPersonaSie.getIddomiciliopersona());


		
		objDomicilioPersonaSie= objDomicilioPersonaService.buscarDomicilioEmpleado(objDomicilioPersonaSie.getIddomiciliopersona());
		
		log.info(" id cargo " + objDomicilioPersonaSie.getIddomiciliopersona() );
		
		setIddomiciliopersona(objDomicilioPersonaSie.getIddomiciliopersona());
		
		setEstado(objDomicilioPersonaSie.getTbEstadoGeneral().getIdestadogeneral());
		setIdUbigeo(objDomicilioPersonaSie.getTbUbigeo().getIdubigeo()+"");
		setTipocasa(objDomicilioPersonaSie.getTbTipoCasa().getIdtipocasa());
		
	
		UbigeoSie ubigeo = objUbigeoService.findUbigeo(Integer.parseInt(getIdUbigeo()));
		setIdDepartamento(ubigeo.getCoddepartamento());
		comboManagerDomicilio.setIdDepartamento(idDepartamento);
		setIdProvincia(ubigeo.getCodprovincia());
		comboManagerDomicilio.setIdProvincia(idProvincia);
		setIdDistrito(ubigeo.getCoddistrito());
		ubigeoDefecto="";
		log.info("busquedaUbigeo "+ getIdDepartamento()+" - "+getIdProvincia()+" - "+getIdDistrito()+" - "+getIdUbigeo());
		setNewRecord(false);
		editMode = false;
		return getViewMant();
	}
	
	/***********************************/
	public String insertar() {

		log.info("insertar() sv " + isNewRecord() 
				+ objDomicilioPersonaSie.getIddomiciliopersona()  );
		log.info("actualizando JBS..... "+objClienteSie.getIdcliente() );
		objDomicilioPersonaSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(15));
		objDomicilioPersonaSie.setTbTipoCasa(objTipoCasaService.findTipoCasa(Tipocasa));
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		try {
			
objDomicilioPersonaSie.setTbUbigeo(objUbigeoService.findUbigeo(Integer.parseInt(idUbigeo)));
			
			if (isNewRecord()) {
				
				
				
	objDomicilioPersonaService.insertarDomicilioEmpleado(objDomicilioPersonaSie);
				
				
			} else {

				objDomicilioPersonaSie.setIddomiciliopersona(getIddomiciliopersona()); 
				objDomicilioPersonaSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(15));
				objDomicilioPersonaSie.setTbTipoCasa(objTipoCasaService.findTipoCasa(Tipocasa));		
				objDomicilioPersonaSie.setDomicilio(objDomicilioPersonaSie.getDomicilio());
				objDomicilioPersonaSie.setReferencia(objDomicilioPersonaSie.getReferencia());
				objDomicilioPersonaService.actualizarDomicilioEmpleado(objDomicilioPersonaSie);
				
				log.info("actualizado");
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
	
	/****************************************/

public void limpiardialog(){
		
		if (defectoUbigeo) {
			comboManagerDomicilio.setUbigeoDeparItems(null);
			comboManagerDomicilio.setUbigeoProvinItems(null);
			comboManagerDomicilio.setUbigeoDistriItems(null);
			ubigeoDefecto = "";
		} else {

			comboManagerDomicilio.setIdDepartamento(null);
			comboManagerDomicilio.setIdProvincia(null);
			log.info(" cambio ubigeo   false  otro");
		}
		
	}
	
public void cambioUbica() {
	log.info("Ubigeo -------->" + idUbigeo + " " + ubigeoDefecto);
	String dist = "";
	Iterator it = comboManagerDomicilio.getUbigeoDistriItems().entrySet().iterator();
	while (it.hasNext()) {
		Map.Entry e = (Map.Entry) it.next();
		log.info("key " + e.getKey() + " value " + e.getValue());
		if (e.getValue().toString().equals(idUbigeo)) {
			dist = (String) e.getKey();
			log.info("dist " + dist);
			break;
		}
	}
	ubigeoDefecto = "LIMA-LIMA-" + dist;
}
	
	
	
public void cambiar() {
	comboManagerDomicilio.setIdDepartamento(getIdDepartamento());
	comboManagerDomicilio.setIdProvincia(null);
	idProvincia = null;
	idUbigeo = null;
	log.info("cambiar   :D  --- ");
}
	
public void cambiar2() {
	comboManagerDomicilio.setIdDepartamento(getIdDepartamento());
	comboManagerDomicilio.setIdProvincia(getIdProvincia());
	log.info("cambiar 2  :D  --- ");
}	
	/*************************************************/



	public void busquedaUbigeo(){
	log.info("busquedaUbigeo");
	UbigeoSie ubigeo = objUbigeoService.findUbigeo(Integer.parseInt(getIdUbigeo()));
	
	log.info("busquedaUbigeo "+ getIdDepartamento()+" - "+getIdProvincia()+" - "+getIdDistrito()+" - "+getIdUbigeo());
	
	Iterator it = comboManagerDomicilio.getUbigeoDeparItems().entrySet().iterator();
	Iterator it2 = comboManagerDomicilio.getUbigeoProvinItems().entrySet().iterator();
	Iterator it3 = comboManagerDomicilio.getUbigeoDistriItems().entrySet().iterator();
	
	while (it.hasNext()) {
		Map.Entry e = (Map.Entry) it.next();
		log.info("key " + e.getKey() + " value " + e.getValue());
		if (e.getValue().toString().equals(idDepartamento)) {
			ubigeoDefecto = (String) e.getKey();
			log.info("ubigeo depa " + ubigeoDefecto);
			break;
		}
	}
	while (it2.hasNext()) {
		Map.Entry e = (Map.Entry) it2.next();
		log.info("key " + e.getKey() + " value " + e.getValue());
		if (e.getValue().toString().equals(idProvincia)) {
			ubigeoDefecto += "-" + (String) e.getKey();
			log.info("ubigeo prov " + ubigeoDefecto);
			break;
		}
	}
	while (it3.hasNext()) {
		Map.Entry e = (Map.Entry) it3.next();
		log.info("key " + e.getKey() + " value " + e.getValue());
		if (e.getValue().toString().equals(idUbigeo)) {
			ubigeoDefecto += "-" + (String) e.getKey();
			log.info("ubigeo distrito " + ubigeoDefecto);
			break;
		}
	}
	log.info("ubigeo ------> :D   " + ubigeoDefecto);
} 

	
	
	
/*METODO UBIGEO MUESTRA EN LA LISTA DEPARTAMENTO_PROVINCIA_DISTRITO*/
	
	
	public void ingresarUbigeoLista() {
		// enviamos el nombre completo del depa- provincia-distrito
		log.info("ingresarUbigeo :D a :D ");
		
		UbigeoSie objUbigeoDomicilio = new UbigeoSie();
	
		for (int i = 0; i < DomicilioPersonaList.size() ; i++) {
			objDomicilioPersonaSie = DomicilioPersonaList.get(i);
			idUbigeo= DomicilioPersonaList.get(i).getTbUbigeo().getIdubigeo()+"";
				
		log.info("ingresarUbigeo :D a --- " + idUbigeo);
		
		objUbigeoDomicilio = objUbigeoService.findUbigeo(Integer.parseInt(idUbigeo));

		log.info("ingresarUbigeo :D a --- " + objUbigeoDomicilio.getIdubigeo());
		comboManagerDomicilio.setIdDepartamento(objUbigeoDomicilio.getCoddepartamento());
		comboManagerDomicilio.setIdProvincia(objUbigeoDomicilio.getCodprovincia());

		Iterator it = comboManagerDomicilio.getUbigeoDeparItems().entrySet().iterator();
		
		Iterator it2 = comboManagerDomicilio.getUbigeoProvinItems().entrySet().iterator();
		Iterator it3 = comboManagerDomicilio.getUbigeoDistriItems().entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			log.info("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString()
					.equals(objUbigeoDomicilio.getCoddepartamento())) {
				ubigeoDefecto = (String) e.getKey();
				log.info("ubigeo depa " + ubigeoDefecto);
				break;
			}
		}
		log.info(" 2.");
		while (it2.hasNext()) {
			Map.Entry e = (Map.Entry) it2.next();
			log.info("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString()
					.equals(objUbigeoDomicilio.getCodprovincia())) {
				ubigeoDefecto += "-" + (String) e.getKey();
				log.info("ubigeo prov " + ubigeoDefecto);
				break;
			}
		}
		log.info(" 3 .");
		while (it3.hasNext()) {
			Map.Entry e = (Map.Entry) it3.next();
			log.info("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString()
					.equals(objUbigeoDomicilio.getIdubigeo() + "")) {
				ubigeoDefecto += "-" + (String) e.getKey();
				log.info("ubigeo distrito " + ubigeoDefecto);
				break;
			}
		}
		DomicilioPersonaList.get(i).setDesUbigeo(ubigeoDefecto);

		log.info("ubigeo ------> :D   " + ubigeoDefecto);
		}
	}

	
/*DESHABILITAR DOMICILIO*/
	public String updateDeshabilitarDomicilio() throws Exception {

		
			objDomicilioPersonaSie = new DomicilioPersonaSie();
			int parametroObtenido2;
	try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'updateDESHABILITARDOMICILIO()'");
			}
			parametroObtenido2 = getIddom();
			log.info(" ------>>>>>>aqui cactura el parametro ID "+ parametroObtenido2);			
			objDomicilioPersonaSie = objDomicilioPersonaService.buscarDomicilioEmpleado(parametroObtenido2);
			
			log.info(" ------desps de la variable l"+ objDomicilioPersonaSie.getIddomiciliopersona());	
			objDomicilioPersonaSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(16));

			log.info("-----desps de setearlo con 16>>>"	+ objDomicilioPersonaSie.getTbEstadoGeneral().getIdestadogeneral());
			log.info("actualizando ESTADO..... ");
			
			objDomicilioPersonaService.actualizarDomicilioEmpleado(objDomicilioPersonaSie);
			log.info("actualizando.Domicilio.... ");

		} catch (Exception e) {
			e.printStackTrace();	
			nombrecliente = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombrecliente);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		objDomicilioPersonaSie = new DomicilioPersonaSie();

		return getViewList();
	}


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
		nuevoTelef.setTbEstadoGeneral(objEstadoGeneralService
				.findEstadogeneral(17));

		for (int i = 0; i < TelefonoPersonaList.size(); i++) {
			log.info("  " + TelefonoPersonaList.get(i).getTelefono() + " "
					+ nuevoTelef.getTelefono());
			if (TelefonoPersonaList.get(i).getTelefono()
					.equals(nuevoTelef.getTelefono())) {
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
	

	
/*DESHABILITAR TELEFONOS*/
	public String updateDeshabilitar() throws Exception {

		objTelefonoPersonaSie = new TelefonoPersonaSie();
		int parametroObtenido;
	try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'updateDESHABILITAR()'");
			}
			parametroObtenido = getIdc();
			log.info(" ------>>>>>>aqui cactura el parametro ID "+ parametroObtenido);
			objTelefonoPersonaSie = objTelefonoService.buscarTelefonoCliente(parametroObtenido);

			log.info(" ------desps de la variable l"+ objTelefonoPersonaSie.getIdtelefonopersona());
			objTelefonoPersonaSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(18));

			log.info("-----desps de setearlo con 18>>>"	+ objTelefonoPersonaSie.getTbEstadoGeneral().getIdestadogeneral());
			log.info("actualizando ESTADO..... ");

			objTelefonoService.actualizarTelefonoCliente(objTelefonoPersonaSie);
			log.info("actualizando..... ");

		} catch (Exception e) {
			e.printStackTrace();
			nombrecliente = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombrecliente);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objTelefonoPersonaSie = new TelefonoPersonaSie();

		return getViewList();
	}
/*REGISTRAR LOS DATOS DEL CLIENTE EDITADO*/
	

	public String insertarOficial() {
		log.info("insertar() "+objClienteSie.getIdcliente());
		log.info("insertar() "  + " inicio del insert "
				+ objClienteSie.getNombrecliente());

		try {
			if (log.isInfoEnabled()) {

			}
			objClienteSie.setTbTipoDocumentoIdentidad(objTipoDocService.buscarTipoDocumento(TipoDocumento));
			objClienteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(23));		
			
			log.info("actualizando..... "+objClienteSie.getIdcliente() );
			objClienteService.updateCliente(objClienteSie, TelefonoPersonaList, DomicilioPersonaList);//aqui eh puesto Ubigeo

			log.info("insertando..... ");
		} catch (Exception e) {

			e.printStackTrace();
			nombrecliente = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombrecliente);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		return getViewList();
	}

	/* METODO EDITAR DATOS DE CLIENTE */

	public String updateGeneral() throws Exception {
		log.info("update() --> objDomicilioPersonaSie");
 
		log.info(" id cliente " + objClienteSie.getIdcliente() + " nombre "	+ objClienteSie.getNombrecliente());

		TelefonoPersonaList = objTelefonoService.listarTelefonoCliente(objClienteSie.getIdcliente());		
		DomicilioPersonaList = objDomicilioPersonaService.listarDomicilioCliente(objClienteSie.getIdcliente());

		ingresarUbigeoLista();

		log.info("antes de telefono 2");
		if (TelefonoPersonaList == null)
			TelefonoPersonaList = new ArrayList<TelefonoPersonaSie>();

		TipoDocumento = objClienteSie.getTbTipoDocumentoIdentidad().getIdtipodocumentoidentidad();

		if (DomicilioPersonaList == null)
			DomicilioPersonaList = new ArrayList<DomicilioPersonaSie>();
		
 
	
		return getViewMant();

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

	/**
	 * @return the operadortelefonico
	 */
	public String getOperadortelefonico() {
		return operadortelefonico;
	}

	/**
	 * @param operadortelefonico
	 *            the operadortelefonico to set
	 */
	public void setOperadortelefonico(String operadortelefonico) {
		this.operadortelefonico = operadortelefonico;
	}

	/**
	 * @return the telefonopersona
	 */
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
	public DomicilioPersonaSie getObjDomicilioPersonaSie() {
		return objDomicilioPersonaSie;
	}

	/**
	 * @param objDomicilioPersonaSie
	 *            the objDomicilioPersonaSie to set
	 */
	public void setObjDomicilioPersonaSie(
			DomicilioPersonaSie objDomicilioPersonaSie) {
		this.objDomicilioPersonaSie = objDomicilioPersonaSie;
	}

	/**
	 * @return the comboManagerDomicilio
	 */
	public ComboAction getComboManagerDomicilio() {
		return comboManagerDomicilio;
	}

	/**
	 * @param comboManagerDomicilio
	 *            the comboManagerDomicilio to set
	 */
	public void setComboManagerDomicilio(ComboAction comboManagerDomicilio) {
		this.comboManagerDomicilio = comboManagerDomicilio;
	}

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
	public int getIddom() {
		return iddom;
	}

	/**
	 * @param iddom the iddom to set
	 */
	public void setIddom(int iddom) {
		this.iddom = iddom;
	}

	/**
	 * @return the iddomicilio
	 */
	public int getIddomicilio() {
		return iddomicilio;
	}

	/**
	 * @param iddomicilio the iddomicilio to set
	 */
	public void setIddomicilio(int iddomicilio) {
		this.iddomicilio = iddomicilio;
	}


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
	 * @return the idDistrito
	 */
	public String getIdDistrito() {
		return idDistrito;
	}



	/**
	 * @return the idUbigeoD
	 */
	public int getIdUbigeoD() {
		return idUbigeoD;
	}



	/**
	 * @param idUbigeoD the idUbigeoD to set
	 */
	public void setIdUbigeoD(int idUbigeoD) {
		this.idUbigeoD = idUbigeoD;
	}



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




	
}
