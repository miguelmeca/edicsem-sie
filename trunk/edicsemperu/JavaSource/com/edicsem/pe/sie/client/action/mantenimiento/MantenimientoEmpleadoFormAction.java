package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.Date;
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
import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.ContratoEmpleadoService;
import com.edicsem.pe.sie.service.facade.DomicilioEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
import com.edicsem.pe.sie.service.facade.TipoPagoService;
import com.edicsem.pe.sie.service.facade.UbigeoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
import com.edicsem.pe.sie.util.security.SecurityLogin;

@ManagedBean(name="mantenimientoEmpleadoFormAction")
@SessionScoped
public class MantenimientoEmpleadoFormAction extends BaseMantenimientoAbstractAction {
    private String mask;
	/*Se crean los objetos de las entidades empleado, domicilio y telefono*/	
	private EmpleadoSie objEmpleado;
	private TelefonoPersonaSie objTelefono;
	private DomicilioPersonaSie objDomicilio;
	private ContratoEmpleadoSie objContratoEmpleado;
	/*variables para telefono*/
	private List<TelefonoPersonaSie> TelefonoPersonaList,TelefonoDeshabilitado;
	private TelefonoPersonaSie nuevoTelef;
	private int TipoTelef, operadorTelefonico;
	private int idc,iddom, iddomicilio;
	private String mensaje;
	private int estado,idTipoPago;
	/*variables para domicilio*/
	private String idProvincia, idDepartamento, ubigeoDefecto, selectTelef, idUbigeo;
	private String idDistrito;
	private int tipo;
	/*variables para empleado*/
	private String nombre;
	private int CargoEmpleado;
	private int DomicilioPersona;
	private int TelefonoPersona;
	private int TipoDocumento;
	private int codigoEmpleado;
	private int codigoTipoDocumento;
	private int codigoCargoEmpleado;
    /*variable para confirmar contraseña*/
	private String confcontra;
	/*variable que capta el id del empleado*/
	private int ide;
	/*variable bolean necesaria*/
	private boolean newRecord =false;
	/*variables*/
	private int idCargo;
	private int idEmpresa;
	private boolean skip;
	private Date fechaInicioContrato;
	private int idContrato;
	private String idt;
	List<String> userList ;
	List<String> dniList;
	private List<ContratoEmpleadoSie> contratoEmpleadoList, ContratoDeshabilitado;
	
	@ManagedProperty(value="#{comboAction}") 
	private ComboAction comboManager;
	@ManagedProperty(value = "#{mantenimientoEmpleadoSearchAction}")
	private MantenimientoEmpleadoSearchAction mantenimientoEmpleadoSearch;
	
	@EJB
	private EmpresaService objEmpresaService;
	@EJB
	private UbigeoService objUbigeoService;
	@EJB 
	private EmpleadoSieService objEmpleadoService;
	@EJB 
	private DomicilioEmpleadoService objDomicilioService;
	@EJB 
	private TelefonoEmpleadoService objTelefonoService;
	@EJB 
	private EstadogeneralService objEstadoService;
	@EJB 
	private CargoEmpleadoService objCargoEmpleadoService;
	@EJB 
	private TipoPagoService objTipoPagoService;
	@EJB 
	private ContratoEmpleadoService objContratoEmpleadoService;
	
	public static Log log = LogFactory.getLog(MantenimientoEmpleadoFormAction.class);
	
	public MantenimientoEmpleadoFormAction() {
		log.info("inicializando mi constructor");
		init();
	}

	/*inicializamos los  objetos utilizados*/
	public void init() {
		log.info("init()");
		objEmpleado = new EmpleadoSie();
		objEmpleado.setGenero("F");
		objTelefono = new TelefonoPersonaSie();
		objDomicilio = new DomicilioPersonaSie();
		objContratoEmpleado = new ContratoEmpleadoSie();
		contratoEmpleadoList =  new ArrayList<ContratoEmpleadoSie>();
		TipoDocumento=1;
		skip = false;
		nuevoTelef = new TelefonoPersonaSie();
		nuevoTelef.setTipoTelef("");
		selectTelef="";
		TipoTelef=1;
		TelefonoDeshabilitado = new ArrayList<TelefonoPersonaSie>();
		ContratoDeshabilitado =  new ArrayList<ContratoEmpleadoSie>();
		TelefonoPersonaList = new ArrayList<TelefonoPersonaSie>();
		operadorTelefonico=1;
	}

	public void limpiarDatosTelefono() {
		nuevoTelef = new TelefonoPersonaSie();
	}	
	
	/**
	 * Eliminar Teléfono de la lista*/
	 public void telefonoElimina(){
	    	log.info("en telefonoElimina()");
			
	    	for (int i = 0; i < TelefonoPersonaList.size(); i++) {
         	log.info(""+TelefonoPersonaList.get(i).getItem()+"  "+idt +"  "+TelefonoPersonaList.get(i).getTelefono());
				if(TelefonoPersonaList.get(i).getTelefono()==idt && TelefonoPersonaList.get(i).getItem()=="Por Agregar"){
					TelefonoPersonaList.remove(i);
					for (int j = i; j < TelefonoPersonaList.size(); j++) {
						log.info(" i " +i+"  j "+ j);
						i=i+1;
						TelefonoPersonaList.set(j, TelefonoPersonaList.get(j));
						
					}break;
				}
				else if(TelefonoPersonaList.get(i).getTelefono()==(idt) && TelefonoPersonaList.get(i).getItem()=="Agregado"){
					TelefonoPersonaList.get(i).setTbEstadoGeneral(objEstadoService.findEstadogeneral(18));
					TelefonoDeshabilitado.add(TelefonoPersonaList.get(i));
					TelefonoPersonaList.remove(i);
					for (int j = i; j < TelefonoPersonaList.size(); j++) {
						log.info(" i " +i+"  j "+ j);
						i=i+1;
						TelefonoPersonaList.set(j, TelefonoPersonaList.get(j));
					}
					break;
				}
			}
			idt="";
	    }
	 
		/**
		 * Eliminar Contrato de la lista*/
		 public void contratoElimina(){
		    	log.info("en eliminarContrato()");
				for (int i = 0; i < contratoEmpleadoList.size(); i++) {
					if(contratoEmpleadoList.get(i).getItem()==(idContrato) && contratoEmpleadoList.get(i).getTipo()=="Por Agregar"){
						contratoEmpleadoList.remove(i);
						for (int j = i; j < contratoEmpleadoList.size(); j++) {
							log.info(" i " +i+"  j "+ j);
							i=i+1;
							//contratoEmpleadoList.get(j).setItem(i);
							contratoEmpleadoList.set(j, contratoEmpleadoList.get(j));
						}break;
					}
					else if(contratoEmpleadoList.get(i).getItem()==(idContrato) && contratoEmpleadoList.get(i).getTipo()=="Agregado"){
						contratoEmpleadoList.get(i).setTbEstadoGeneral(objEstadoService.findEstadogeneral(18));
						ContratoDeshabilitado.add(contratoEmpleadoList.get(i));
						contratoEmpleadoList.remove(i);
						for (int j = i; j < contratoEmpleadoList.size(); j++) {
							log.info(" i " +i+"  j "+ j);
							i=i+1;
							//TelefonoPersonaList.get(j).setItem(i);
							contratoEmpleadoList.set(j, contratoEmpleadoList.get(j));
						}
						break;
					}
				}
				idContrato=0;
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
		nuevoTelef.setTbEstadoGeneral(objEstadoService.findEstadogeneral(17));

		for (int i = 0; i < TelefonoPersonaList.size(); i++) {
			log.info("  " + TelefonoPersonaList.get(i).getTelefono() + " "
					+ nuevoTelef.getTelefono());
			if (TelefonoPersonaList.get(i).getTelefono().equals(nuevoTelef.getTelefono())) {
				verifica = false;
				mensaje = "el telefono "+nuevoTelef.getTelefono()+" ya se encuentra registrado en la lista de referencias";
				break;
			} else {
				verifica = true;
			}
		}
		if (TelefonoPersonaList.size() == 0) {
			verifica = true;
		}
		if (verifica) {
			
			nuevoTelef.setItem("Por Agregar");	
			
			TelefonoPersonaList.add(nuevoTelef);
			log.info("se agrego " + nuevoTelef.getTelefono());
			mensaje="Se agregó telefono";
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		if (mensaje != null &&verifica==false) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		nuevoTelef = new TelefonoPersonaSie();
	}
		
/*DESHABILITAR TELEFONOS*/
	public String updateDeshabilitar() throws Exception {
		log.info("deshabilitar telefono del empleado");
		
		objTelefono = new TelefonoPersonaSie();
		int parametroObtenido;
	try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'updateDESHABILITAR()'");
			}
			parametroObtenido = getIdc();
			log.info(" ------>>>>>>aqui captura el parametro ID "+ parametroObtenido);
			objTelefono = objTelefonoService.buscarTelefonoEmpleado(parametroObtenido);

			log.info(" ------desps de la variable l"+ objTelefono.getIdtelefonopersona());
			objTelefono.setTbEstadoGeneral(objEstadoService.findEstadogeneral(18));

			log.info("-----desps de setearlo con 18>>>"	+ objTelefono.getTbEstadoGeneral().getIdestadogeneral());
			log.info("actualizando ESTADO..... ");

			objTelefonoService.actualizarTelefonoEmpleado(objTelefono);
			log.info("actualizando..... ");

		} catch (Exception e) {
			e.printStackTrace();
			nombre = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objTelefono = new TelefonoPersonaSie();

		return getViewList();
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
	
	/*método que sirve para deshabilitar al empleado*/
	public String deshabilitar() throws Exception{
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'DESHABILITAR()'");
			}
			log.info("actualizando ESTADO..... ");
			objEmpleadoService.eliminarEmpleado(getIde());
			log.info("deshabilitando..... ");
			mensaje="Se deshabilitó al empleado ";
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e2) {
			e2.printStackTrace();
			mensaje = e2.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e2.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return mantenimientoEmpleadoSearch.listar();
	}
	
			 	
	/*método que se ejecuta haciendo click en el link NUEVO de la lista*/
	public String agregar() {
		log.info("agregar");
		TelefonoPersonaList = new ArrayList<TelefonoPersonaSie>();
		contratoEmpleadoList = new ArrayList<ContratoEmpleadoSie>();
		idUbigeo="0";
		ubigeoDefecto="";
		comboManager.setUbigeoDistriItems(null);
		selectTelef= "";
		operadorTelefonico=1;
		TipoDocumento=1;
		TipoTelef=1;
		//para inicializar los campos de nuevo telefono
		nuevoTelef.setTelefono("");
		//fin para inicializar los campos de nuevo telefono
		setTipoDocumento(0);
		setCargoEmpleado(0);
		objEmpleado = new EmpleadoSie();
		objEmpleado.setGenero("F");
		objTelefono = new TelefonoPersonaSie();
		setTipo(0);
		objDomicilio = new DomicilioPersonaSie();
		comboManager.setUbigeoDeparItems(null);
		comboManager.setUbigeoProvinItems(null);
		comboManager.setUbigeoDistriItems(null);
		comboManager.setIdDepartamento("15");
		comboManager.setIdProvincia("01");
		idDepartamento="15";
		idProvincia="01";
		ubigeoDefecto = "";
		fechaInicioContrato=null;
		setNewRecord(true);
		 //validar usuario
		userList =  objEmpleadoService.listarUsuario();
		
		//validar DNI
		dniList =  objEmpleadoService.listarDni();
		return getViewMant();
	}

	/*método que se ejecuta al hacer click en el botón EDITAR de la lista*/
	public String update() throws Exception {
	    log.info("actualizar");
		log.info("update()" + objEmpleado.getIdempleado());
		/*busca el empleado, lista de teléfonos, domicilio, detalle de empresa de empleado y contrato de empleado*/
		EmpleadoSie c = objEmpleadoService.buscarEmpleado(objEmpleado.getIdempleado());
		TelefonoPersonaSie t = objTelefonoService.buscarTelefonoXIdempleado(objEmpleado.getIdempleado());
		objDomicilio = objDomicilioService.buscarDomicilioXIdempleado(objEmpleado.getIdempleado());
		
		/*Seteo para mostrar los datos en el form*/
		/*seteo empleado*/
		objEmpleado.setIdempleado(c.getIdempleado());
		objEmpleado.setNombreemp(c.getNombreemp());
        objEmpleado.setApepatemp(c.getApepatemp());
        objEmpleado.setApematemp(c.getApematemp());
        objEmpleado.setUsuario(c.getUsuario());
        objEmpleado.setContrasena(c.getContrasena());
        setConfcontra(c.getContrasena());
        setTipoDocumento(c.getTbTipoDocumentoIdentidad().getIdtipodocumentoidentidad());
        objEmpleado.setNumdocumento(c.getNumdocumento());
        objEmpleado.setFechanacimiento(c.getFechanacimiento());
        objEmpleado.setCorreo(c.getCorreo());
        objEmpleado.setTbEstadoGeneral(c.getTbEstadoGeneral());
        /*seteo teléfono*/
        TelefonoPersonaList = objTelefonoService.listarTelefonoEmpleadosXidempleado(c.getIdempleado());		
        /*seteo domicilio*/
        if(objDomicilio.getIddomiciliopersona()!=null){
        setIdDepartamento(objDomicilio.getTbUbigeo().getCoddepartamento());
        comboManager.setIdDepartamento(idDepartamento);
        setIdProvincia(objDomicilio.getTbUbigeo().getCodprovincia());
        comboManager.setIdProvincia(idProvincia);
        setIdUbigeo(objDomicilio.getTbUbigeo().getIdubigeo().toString());
        setIdDistrito(objDomicilio.getTbUbigeo().getCoddistrito());
        log.info(" tipo casa "+objDomicilio.getTbTipoCasa().getIdtipocasa());
        setTipo(objDomicilio.getTbTipoCasa().getIdtipocasa());
        objDomicilio.setTbEstadoGeneral(objDomicilio.getTbEstadoGeneral());
        }
        /*seteo contrato*/
        contratoEmpleadoList = objContratoEmpleadoService.listarCargoXEmp(c.getIdempleado());
        /*seteo detalle contrato*/
      // detEmpresaEmpList = objDetEmpresaEmpService.listarDetEmpresaEmpleadoXidempleado(c.getIdempleado());
        /**/
        for (int i = 0; i < TelefonoPersonaList.size(); i++) {
        	TelefonoPersonaList.get(i).setItem("Agregado");
        }
        for (int i = 0; i < contratoEmpleadoList.size(); i++) {
        	contratoEmpleadoList.get(i).setTipo("Agregado");
        	contratoEmpleadoList.get(i).setItem(i+1);
        }
        
        //validar usuario
		userList =  objEmpleadoService.listarUsuario();
		userList.remove(objEmpleado.getUsuario());
		
		//validar DNI
		dniList =  objEmpleadoService.listarDni();
		dniList.remove(objEmpleado.getNumdocumento());
		
		setNewRecord(false);
		return getViewMant();
	}
		
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		String paginaretorno="";
		log.info("Entering my method 'insertar(registrar, actualizar)'"+ objEmpleado.getNombreemp());
			/*encripta la contraseña*/
		log.info("probando  ."+confcontra);
		log.info("probando MD5..."+objEmpleado.getContrasena());
		    objEmpleado.setContrasena(SecurityLogin.getMD5(objEmpleado.getContrasena()));
			log.info("probando MD5..."+objEmpleado.getContrasena());
			try {
				 
				if(userList.contains(objEmpleado.getUsuario())){
					mensaje="Dicho usuario ya existe en el sistema";
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
							Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
				}
				else if(dniList.contains(objEmpleado.getNumdocumento())){
					mensaje="Dicho número de documento le pertenece a otro empleado ";
					msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
							Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
				}
				else if(contratoEmpleadoList.size()<0){
					mensaje="Debe registrar el contrato del empleado ";
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
							Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
				}
				else{
				objDomicilio.setTbUbigeo(objUbigeoService.findUbigeo(Integer.parseInt(idUbigeo)));
				/*if: inserta al empleado, domicilio y telefono
				  else: actualiza al empleado, domicilio y telefono*/
				if (isNewRecord()) {
					log.info("insertando..... ");
					log.info("insertar empleado  ");
					objEmpleado.setUsuario(objEmpleado.getUsuario().toLowerCase());
					objEmpleadoService.insertarEmpleado(objEmpleado,objDomicilio, codigoTipoDocumento,  codigoCargoEmpleado,  
					idUbigeo, tipo,  idCargo, DomicilioPersona, TelefonoPersona,TipoDocumento, idEmpresa, idTipoPago, codigoEmpleado, contratoEmpleadoList, TelefonoPersonaList);
					log.info("insertando..... ");
					setNewRecord(false);
					mensaje=Constants.MESSAGE_REGISTRO_TITULO;
				} else {
					log.info("actualizando..... ");
					objEmpleadoService.actualizarEmpleado(objEmpleado,objDomicilio, codigoTipoDocumento,  codigoCargoEmpleado,  
					idUbigeo, tipo,  idCargo, DomicilioPersona, TelefonoPersona,TipoDocumento, idEmpresa, idTipoPago, codigoEmpleado, contratoEmpleadoList, TelefonoPersonaList, TelefonoDeshabilitado, ContratoDeshabilitado);
					log.info("insertando..... ");
					mensaje = Constants.MESSAGE_ACTUALIZO_TITULO;
				}
				paginaretorno= mantenimientoEmpleadoSearch.listar();
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						Constants.MESSAGE_INFO_TITULO, mensaje);
				objEmpleado = new EmpleadoSie();
				}
				FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		return paginaretorno;
	}
	
	public void agregarPago(){
		log.info("agregarPago()" );
		objContratoEmpleado.setTbCargoempleado(objCargoEmpleadoService.buscarCargoEmpleado(idCargo));
		objContratoEmpleado.setFechaInicioContrato(fechaInicioContrato);
		objContratoEmpleado.setTbTipoPago(objTipoPagoService.findTipoPago(idTipoPago));

		int cantidad2=contratoEmpleadoList.size();
		objContratoEmpleado.setItem(cantidad2+1);
		objContratoEmpleado.setTipo("Por Agregar");
		objContratoEmpleado.setEmpresa(idEmpresa);
		objContratoEmpleado.setDescEmpresa(objEmpresaService.findEmpresa(idEmpresa).getRazonsocial());
		contratoEmpleadoList.add(objContratoEmpleado);
		log.info("agregado ooo " );
		objContratoEmpleado= new ContratoEmpleadoSie();
	}

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
	
    /*métodos GET y SET*/
	/**
	 * @return the codigoEmpleado
	 */
	public int getCodigoEmpleado() {
		return codigoEmpleado;
	}

	/**
	 * @param codigoEmpleado the codigoEmpleado to set
	 */
	public void setCodigoEmpleado(int codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	public String getViewList() {
		return "mantenimientoEmpleadoList";
	}
	
	public String getViewMant() {
		return "mantenimientoEmpleadoForm";
	}

	/**
	 * @return the objUsuario
	 */
	public EmpleadoSie getObjEmpleado() {
		return objEmpleado;
	}

	/**
	 * @param objUsuario
	 *            the objUsuario to set
	 */
	public void setObjEmpleado(EmpleadoSie objEmpleado) {
		this.objEmpleado = objEmpleado;
	}

	/**
	 * @return the codigoTipoDocumento
	 */
	public int getCodigoCargoEmpleado() {
		return codigoCargoEmpleado;
	}

	/**
	 * @param codigoTipoDocumento the codigoTipoDocumento to set
	 */
	public void setCodigoCargoEmpleado(int codigoCargoEmpleado) {
		this.codigoCargoEmpleado = codigoCargoEmpleado;
	}
	
	public int getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	/**
	 * @param codigoTipoDocumento the codigoTipoDocumento to set
	 */
	public void setCodigoTipoDocumento(int codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}
	
	public int getCargoEmpleado() {
		return CargoEmpleado;
	}

	public void setCargoEmpleado(int cargoEmpleado) {
		CargoEmpleado = cargoEmpleado;
	}

	public int getDomicilioPersona() {
		return DomicilioPersona;
	}

	public void setDomicilioPersona(int domicilioPersona) {
		DomicilioPersona = domicilioPersona;
	}

	public int getTelefonoPersona() {
		return TelefonoPersona;
	}

	public void setTelefonoPersona(int telefonoPersona) {
		TelefonoPersona = telefonoPersona;
	}

	public int getTipoDocumento() {
		return TipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}

	/**
	 * @return the objTelefono
	 */
	public TelefonoPersonaSie getObjTelefono() {
		return objTelefono;
	}

	/**
	 * @param objTelefono the objTelefono to set
	 */
	public void setObjTelefono(TelefonoPersonaSie objTelefono) {
		this.objTelefono = objTelefono;
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
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * @return the direccion
	 */
//	public String getDireccion() {
//		return direccion;
//	}
//
//	/**
//	 * @param direccion the direccion to set
//	 */
//	public void setDireccion(String direccion) {
//		this.direccion = direccion;
//	}
//
//	/**
//	 * @return the estado2
//	 */
//	public int getEstado2() {
//		return estado2;
//	}
//
//	/**
//	 * @param estado2 the estado2 to set
//	 */
//	public void setEstado2(int estado2) {
//		this.estado2 = estado2;
//	}

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
	 * @return the confcontra
	 */
	public String getConfcontra() {
		return confcontra;
	}

	/**
	 * @param confcontra the confcontra to set
	 */
	public void setConfcontra(String confcontra) {
		this.confcontra = confcontra;
	}
	
	/**
	 * @return the idp
	 */
	public int getIde() {
		return ide;
	}

	/**
	 * @param idp the idp to set
	 */
	public void setIde(int ide) {
		this.ide = ide;
	}

	/**
	 * @return the log
	 */
	public static Log getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public static void setLog(Log log) {
		MantenimientoEmpleadoSearchAction.log = log;
	}

	/**
	 * @return the mantenimientoEmpleadoSearch
	 */
	public MantenimientoEmpleadoSearchAction getMantenimientoEmpleadoSearch() {
		return mantenimientoEmpleadoSearch;
	}

	/**
	 * @param mantenimientoEmpleadoSearch the mantenimientoEmpleadoSearch to set
	 */
	public void setMantenimientoEmpleadoSearch(
			MantenimientoEmpleadoSearchAction mantenimientoEmpleadoSearch) {
		this.mantenimientoEmpleadoSearch = mantenimientoEmpleadoSearch;
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
	 * @return the comboManager
	 */
	public ComboAction getComboManager() {
		return comboManager;
	}

	/**
	 * @param comboManager the comboManager to set
	 */
	public void setComboManager(ComboAction comboManager) {
		comboManager.setCodigoEstado(Constants.COD_ESTADO_TB_PRODUCTO);
		this.comboManager = comboManager;
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
	 * @return the objDetCargo
	 */
//	public DetCargoEmpleadoSie getObjDetCargo() {
//		return objDetCargo;
//	}
//
//	/**
//	 * @param objDetCargo the objDetCargo to set
//	 */
//	public void setObjDetCargo(DetCargoEmpleadoSie objDetCargo) {
//		this.objDetCargo = objDetCargo;
//	}

	/**
	 * @return the objContratoEmpleado
	 */
	public ContratoEmpleadoSie getObjContratoEmpleado() {
		return objContratoEmpleado;
	}

	/**
	 * @param objContratoEmpleado the objContratoEmpleado to set
	 */
	public void setObjContratoEmpleado(ContratoEmpleadoSie objContratoEmpleado) {
		this.objContratoEmpleado = objContratoEmpleado;
	}

	/**
	 * @return the contratoEmpleadoList
	 */
	public List<ContratoEmpleadoSie> getContratoEmpleadoList() {
		return contratoEmpleadoList;
	}

	/**
	 * @param contratoEmpleadoList the contratoEmpleadoList to set
	 */
	public void setContratoEmpleadoList(
			List<ContratoEmpleadoSie> contratoEmpleadoList) {
		this.contratoEmpleadoList = contratoEmpleadoList;
	}

	/**
	 * @return the fechaInicioContrato
	 */
	public Date getFechaInicioContrato() {
		return fechaInicioContrato;
	}

	/**
	 * @param fechaInicioContrato the fechaInicioContrato to set
	 */
	public void setFechaInicioContrato(Date fechaInicioContrato) {
		this.fechaInicioContrato = fechaInicioContrato;
	}

	/**
	 * @return the idCargo
	 */
	public int getIdCargo() {
		return idCargo;
	}

	/**
	 * @param idCargo the idCargo to set
	 */
	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}

	/**
	 * @return the idTipoPago
	 */
	public int getIdTipoPago() {
		return idTipoPago;
	}

	/**
	 * @param idTipoPago the idTipoPago to set
	 */
	public void setIdTipoPago(int idTipoPago) {
		this.idTipoPago = idTipoPago;
	}

	/**
	 * @return the mask
	 */
	public String getMask() {
		return mask;
	}

	/**
	 * @param mask the mask to set
	 */
	public void setMask(String mask) {
		this.mask = mask;
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
	 * @return the idc
	 */
	public int getIdc() {
		return idc;
	}

	/**
	 * @param idc the idc to set
	 */
	public void setIdc(int idc) {
		this.idc = idc;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the idEmpresa
	 */
	public int getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return the idContrato
	 */
	public int getIdContrato() {
		return idContrato;
	}

	/**
	 * @param idContrato the idContrato to set
	 */
	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	/**
	 * @return the idt
	 */
	public String getIdt() {
		return idt;
	}

	/**
	 * @param idt the idt to set
	 */
	public void setIdt(String idt) {
		this.idt = idt;
	}

	/**
	 * @return the contratoDeshabilitado
	 */
	public List<ContratoEmpleadoSie> getContratoDeshabilitado() {
		return ContratoDeshabilitado;
	}

	/**
	 * @param contratoDeshabilitado the contratoDeshabilitado to set
	 */
	public void setContratoDeshabilitado(
			List<ContratoEmpleadoSie> contratoDeshabilitado) {
		ContratoDeshabilitado = contratoDeshabilitado;
	}
}


