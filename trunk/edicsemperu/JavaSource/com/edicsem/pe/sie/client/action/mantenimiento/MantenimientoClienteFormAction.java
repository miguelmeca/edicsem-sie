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
import com.edicsem.pe.sie.entity.DetProductoContratoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.service.facade.DomicilioEmpleadoService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
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
	private int idc;
	private int estado;
	/* DATOS DE TELEFONO CLIENTE */
	private TelefonoPersonaSie nuevoTelef;
	
	
	private int TipoTelef, operadorTelefonico;
	private String mensaje;	
	/* COMBITO DE UBIGEO */
	private boolean newRecord = true;
	private ClienteSie nuevo;
	private ClienteSie objClienteSie;
	private TelefonoPersonaSie objTelefonoPersonaSie;
	/* LISTA DE TELEFONOS */
	private List<TelefonoPersonaSie> TelefonoPersonaList;
	/* DOMICILIO CLIENTE */
	private DomicilioPersonaSie nuevoDomicilio;
	
	
	private DomicilioPersonaSie objDomicilioPersonaSie2;
	private String domicilio,referencia;
	private DomicilioPersonaSie objDomicilioPersonaSie;
	private List<DomicilioPersonaSie> DomicilioPersonaList;
	private Log log = LogFactory.getLog(MantenimientoClienteFormAction.class);
	/*NUEVO UBIGEO*/
	private boolean defectoUbigeo;
	private String idProvincia, idDepartamento, ubigeoDefecto;
	private int idUbigeo;
	/*TIPO DE CAAS*/
	private int Tipocasa;

	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManagerDomicilio;
	
	@ManagedProperty(value="#{comboAction}") 
	private ComboAction comboManager;

	@ManagedProperty(value = "#{MantenimientoClienteSearchAction}")
	private MantenimientoClienteSearchAction mantenimientoClienteSearch;

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

	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoClienteFormAction'");
		objClienteSie = new ClienteSie();
		nuevoTelef = new TelefonoPersonaSie();
		nuevoTelef.setTipoTelef("");
		objTelefonoPersonaSie = new TelefonoPersonaSie();
		TelefonoPersonaList = new ArrayList<TelefonoPersonaSie>();
		objDomicilioPersonaSie = new DomicilioPersonaSie();
		DomicilioPersonaList = new ArrayList<DomicilioPersonaSie>();
		defectoUbigeo=true;
		objDomicilioPersonaSie = new DomicilioPersonaSie();
		objDomicilioPersonaSie2 = new DomicilioPersonaSie();
		nuevoDomicilio = new DomicilioPersonaSie();
		nuevoDomicilio.setDomicilio("");
		nuevoDomicilio.setReferencia("");
		
		
		nuevo = new ClienteSie();
	}
	
	/*METODO AGREGAR DOMICILIO A LA LISTA DOMOCILIO CLIENTES*/
	/* METODO TELEFONO */

	public void domicilioAgregar() {	
		
		mensaje=null;
		log.info("agregarProducto ");
		int cantidad = DomicilioPersonaList.size();
//		int cantidad= detPaqueteList.size();
		
		DomicilioPersonaSie det = new DomicilioPersonaSie(); 
//		DetPaqueteSie det = new DetPaqueteSie();
		
//		det.setDomicilio(objDomicilioPersonaSie.getDomicilio());
//		det.setReferencia(objDomicilioPersonaSie.getReferencia());
		det.setTbEstadoGeneral(objEstadoGeneralService
				.findEstadogeneral(24));
		log.info(""+ idUbigeo);
		det.setTbUbigeo(objUbigeoService.findUbigeo(idUbigeo));
//		
		log.info(""+ idUbigeo);
		if(cantidad==0){
			
			det.setItem(1);
			objDomicilioPersonaSie=det;
			DomicilioPersonaList.add(objDomicilioPersonaSie);
			ingresarUbigeo();
			log.info("tama�o lista de dominos "+ DomicilioPersonaList.size());
		}else{
			for (int i = 0; i < DomicilioPersonaList.size(); i++) {
				if(DomicilioPersonaList.get(i).getIddomiciliopersona()==det.getIddomiciliopersona()){
					mensaje="Dicho producto ya se encuentra registrado en la lista ";
				}
			det.setItem(cantidad+1);
			}
			if(mensaje==null){
				objDomicilioPersonaSie=det;
				DomicilioPersonaList.add(objDomicilioPersonaSie);
				ingresarUbigeo();
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			log.info("tama�o lista de domicilio "+ DomicilioPersonaList.size());
		}
	
	
	
	}
	

	
	/*METODO CREAR NUEVO UBIGEO*/
	
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
	public void cambiar() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(null);
		idProvincia = null;
		idUbigeo = 0;
		log.info("cambiar   :D  --- ");
	}
	
	public void cambiar2() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(getIdProvincia());
		log.info("cambiar 2  :D  --- ");
	}
	
	public void cambioUbica() {
		log.info("Ubigeo -------->" + idUbigeo + " " + ubigeoDefecto);
		String dist = "";
		Iterator it = comboManager.getUbigeoDistriItems().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			log.info("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idUbigeo+"")) {
				log.info("xxx -" + e.getKey() + "- value -" + idUbigeo+"-");
				dist = (String) e.getKey();
				log.info("dist " + dist);
				break;
			}
		}
		ubigeoDefecto = "LIMA-LIMA-" + dist;
	}
/*METODO UBIGEO*/
	
	
	public void ingresarUbigeo() {
		// enviamos el nombre completo del depa- provincia-distrito
		log.info("ingresarUbigeo :D a");
		UbigeoSie objUbigeoDomicilio = new UbigeoSie();
		
		for (int i = 0; i < DomicilioPersonaList.size() ; i++) {
			if(objDomicilioPersonaService.buscarDomicilioEmpleado(DomicilioPersonaList.get(i).getIddomiciliopersona())==null){
				objDomicilioPersonaSie = objDomicilioPersonaService.buscarDomicilioEmpleado(DomicilioPersonaList.get(i).getIddomiciliopersona());
				idUbigeo= objDomicilioPersonaSie.getTbUbigeo().getIdubigeo();
			}
			else{				
				idUbigeo=DomicilioPersonaList.get(i).getTbUbigeo().getIdubigeo();
			}
		
		log.info("ingresarUbigeo :D a --- " + idUbigeo);
		
		objUbigeoDomicilio = objUbigeoService.findUbigeo(idUbigeo);

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
	
	public String agregar() {
		log.info("agregar");
		
		
		objClienteSie = new ClienteSie();
		
		
		//setUbigeo(0);
		//setIdDistrito(-1);
	
		objDomicilioPersonaSie = new DomicilioPersonaSie();
		comboManager.setIdDepartamento("15");
		comboManager.setIdProvincia("01");
		
		
		setNewRecord(true);
		return getViewMant();
	}
	/*INGRESAR UBIGEO AL FORMACTION*/
	public String ingresarUbigeoOficial() {
		// enviamos el nombre completo del depa- provincia-distrito

		log.info("ingresarUbigeo :D a --- " + idUbigeo);

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
			if (e.getValue().toString().equals(idUbigeo+"")) {
				ubigeoDefecto += "-" + (String) e.getKey();
				log.info("ubigeo distrito " + ubigeoDefecto);
				break;
			}
		}
		log.info("ubigeo ------> :D   " + ubigeoDefecto);
		
		return getViewMant();
	}

	/* METODO TELEFONO */

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
	
	

	
	/* TMETODO EDITAR CLIENTE */

	public String update() throws Exception {
		log.info("update()");

		ClienteSie c = objClienteService.findCliente(objClienteSie
				.getIdcliente());

		log.info(" id cliente " + objClienteSie.getIdcliente() + " nombre "
				+ objClienteSie.getNombrecliente());

		TelefonoPersonaList = objTelefonoService
				.listarTelefonoCliente(objClienteSie.getIdcliente());

		log.info("update()  2");

		DomicilioPersonaList = objDomicilioPersonaService
				.listarDomicilioCliente(objClienteSie.getIdcliente());
		log.info("update()  3");

		ingresarUbigeo();

		log.info("update()  4");
		log.info("antes de telefono 2");
		if (TelefonoPersonaList == null)
			TelefonoPersonaList = new ArrayList<TelefonoPersonaSie>();

		TipoDocumento = objClienteSie.getTbTipoDocumentoIdentidad()
				.getIdtipodocumentoidentidad();

		if (DomicilioPersonaList == null)
			DomicilioPersonaList = new ArrayList<DomicilioPersonaSie>();

		objClienteSie.setIdcliente(c.getIdcliente());
		objClienteSie.setNombrecliente(c.getNombrecliente());
		objClienteSie.setApepatcliente(c.getApepatcliente());
		objClienteSie.setApematcliente(c.getApematcliente());
		objClienteSie.setEmpresatrabajo(c.getEmpresatrabajo());
		objClienteSie.setCargotrabajo(c.getCargotrabajo());
		objClienteSie.setDirectrabajo(c.getDirectrabajo());
		objClienteSie.setCorreo(c.getCorreo());
		objClienteSie.setFecnacimiento(c.getFecnacimiento());
		objClienteSie.setNumdocumento(c.getNumdocumento());
		objClienteSie.setTelftrabajo(c.getTelftrabajo());

		setNewRecord(true);

		return getViewMant();

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

		return getViewMant();
	}

/*REGISTRAR LO EDITADO*/
	

	public String insertar() {
		log.info("insertar() ");
		log.info("insertar() " + isNewRecord() + " inicio del insert "
				+ objClienteSie.getNombrecliente());

		try {
			if (log.isInfoEnabled()) {

			}
			objClienteSie.setTbTipoDocumentoIdentidad(objTipoDocService
					.buscarTipoDocumento(TipoDocumento));
			objClienteSie.setTbEstadoGeneral(objEstadoGeneralService
					.findEstadogeneral(23));
			log.info("actualizando..... ");

			objClienteService.updateCliente(objClienteSie, TelefonoPersonaList);

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

	
	
	
	
	
	public void limpiarDatosTelefono() {
		nuevoTelef = new TelefonoPersonaSie();
	}

	public List<TelefonoPersonaSie> getTelefonoPersonaList() {
		return TelefonoPersonaList;
	}

	public List<DomicilioPersonaSie> getDomicilioPersonaList() {
		return DomicilioPersonaList;
	}
/*METODOS GET Y SET*/
	

	public String getViewList() {
		return Constants.MANT_CLIENTE_FORM_LIST_PAGE;
	}

	public String getViewMant() {
		return Constants.MANT_CLIENTE_FORM_PAGE;
	}

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
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord
	 *            the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
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
	 * @return the mantenimientoClienteSearch
	 */
	public MantenimientoClienteSearchAction getMantenimientoClienteSearch() {
		return mantenimientoClienteSearch;
	}

	/**
	 * @param mantenimientoClienteSearch
	 *            the mantenimientoClienteSearch to set
	 */
	public void setMantenimientoClienteSearch(
			MantenimientoClienteSearchAction mantenimientoClienteSearch) {
		this.mantenimientoClienteSearch = mantenimientoClienteSearch;
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
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * @param domicilio
	 *            the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
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
	 * @return the objDomicilioPersonaSie2
	 */
	public DomicilioPersonaSie getObjDomicilioPersonaSie2() {
		return objDomicilioPersonaSie2;
	}




	/**
	 * @param objDomicilioPersonaSie2 the objDomicilioPersonaSie2 to set
	 */
	public void setObjDomicilioPersonaSie2(
			DomicilioPersonaSie objDomicilioPersonaSie2) {
		this.objDomicilioPersonaSie2 = objDomicilioPersonaSie2;
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

	
	
}
