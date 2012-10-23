package com.edicsem.pe.sie.client.action.mantenimiento;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.DetCargoEmpleadoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.DomicilioEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoEmpleadoFormAction")
@SessionScoped
public class MantenimientoEmpleadoFormAction extends BaseMantenimientoAbstractAction {
    /*Se crean los objetos de las entidades empleado, domicilio y telefono*/	
	private EmpleadoSie objEmpleado;
	private TelefonoPersonaSie objTelefono;
	private DomicilioPersonaSie objDomicilio;
	private DetCargoEmpleadoSie objDetCargo;
	/*variables para telefono*/
	private String mensaje;
	private String fijo;
	private int estado;
	/*variables para domicilio*/
	private String direccion;
	//private int ubigeo;
	private String idProvincia, idDepartamento, ubigeoDefecto;
	private String idDistrito;
	private int estado2;
	private int idUbigeo; 
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
	private int estadoe;
    /*variable para confirmar contraseña*/
	private String confcontra;
	/*variable que capta el id del empleado*/
	private int ide;
	/*variable bolean necesaria*/
	private boolean newRecord =false;
	
	private boolean defectoUbigeo;	
	
	private ArrayList<String> listacargo;
	
	/**
	 * @return the citiesSource
	 */
	//public List<CargoEmpleadoSie> getCitiesSource() {
		//return citiesSource;
	//}

	/**
	 * @param citiesSource the citiesSource to set
	 */
	//public void setCitiesSource(List<CargoEmpleadoSie> citiesSource) {
		//this.citiesSource = citiesSource;
	//}
	@ManagedProperty(value="#{comboAction}") 
	private ComboAction comboManager;
	
	@ManagedProperty(value = "#{mantenimientoEmpleadoSearchAction}")
	private MantenimientoEmpleadoSearchAction mantenimientoEmpleadoSearch;
	
	@EJB 
	private EmpleadoSieService objEmpleadoService;
	@EJB 
	private DomicilioEmpleadoService objDomicilioService;
	@EJB 
	private TelefonoEmpleadoService objTelefonoService;
	@EJB 
	private EstadogeneralService objEstadoService;
	@EJB 
	private CargoEmpleadoService objCargoService;
	
	public static Log log = LogFactory.getLog(MantenimientoEmpleadoFormAction.class);
	
	public MantenimientoEmpleadoFormAction() {
		System.out.println("ESTOY EN MI CONSNTRUCTOR");
		log.info("inicializando mi constructor");
		init();
	}

	/*inicializamos los  objetos utilizados*/
	public void init() {
		log.info("init()");
		objEmpleado = new EmpleadoSie();
		objTelefono = new TelefonoPersonaSie();
		objDomicilio = new DomicilioPersonaSie();
		objDetCargo = new DetCargoEmpleadoSie();
		defectoUbigeo=true;
		
		//prueba list
		listacargo = new ArrayList<String>();
		//List<CargoEmpleadoSie> lstEmpleadoTelefono = new ArrayList<CargoEmpleadoSie>();
		//citiesSource = objEmpleadoService.listarEmpleados();
		//List<CargoEmpleadoSie> citiesSource = new ArrayList<CargoEmpleadoSie>();
		//citiesSource = objCargoService.listarCargoEmpleado();
		//if (citiesSource == null) {
		//citiesSource = new ArrayList<CargoEmpleadoSie>();
		//}
		
		
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
	
	public String ingresarUbigeo() {
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
	
	/*método que sirve para deshabilitar al empleado*/
	public String deshabilitar() throws Exception{
		objEmpleado = new EmpleadoSie();
		int parametroObtenido;
		EmpleadoSie e = new EmpleadoSie();
		DomicilioPersonaSie d= new DomicilioPersonaSie();
		TelefonoPersonaSie t = new TelefonoPersonaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'DESHABILITAR()'");
			}
			parametroObtenido = getIde();
			log.info(" ------>>>>>>aqui captura el parametro ID "+ parametroObtenido);
			e = objEmpleadoService.buscarEmpleado(parametroObtenido);
			t = objTelefonoService.buscarTelefonoXIdempleado(parametroObtenido);
			d = objDomicilioService.buscarDomicilioXIdempleado(parametroObtenido);
		    log.info(" empleado------ID y nombre>" + e.getIdempleado() + " "+ e.getNombreemp());
		    log.info(" telefono------ID y telefono>" + t.getIdtelefonopersona() + " "+ t.getTelefono());
		    log.info(" domicilio------ID y direccion>" + d.getIddomiciliopersona() + " "+ d.getDomicilio());
			/*seteo empleado*/
		    objEmpleado.setIdempleado(e.getIdempleado());
			objEmpleado.setNombreemp(e.getNombreemp());
	        objEmpleado.setApepatemp(e.getApepatemp());
	        objEmpleado.setApematemp(e.getApematemp());
	        objEmpleado.setUsuario(e.getUsuario());
	        objEmpleado.setContrasena(e.getContrasena());
	        setTipoDocumento(e.getTbTipoDocumentoIdentidad().getIdtipodocumentoidentidad());
	        objEmpleado.setNumdocumento(e.getNumdocumento());
	        //setCargoEmpleado(e.getTbCargoEmpleado().getIdcargoempleado());
	        objEmpleado.setFechanacimiento(e.getFechanacimiento());
	        objEmpleado.setTbEstadoGeneral(objEstadoService.findEstadogeneral(4));
	        //setEstadoe(4);
	        /*seteo domicilio*/
	        objDomicilio.setIddomiciliopersona(d.getIddomiciliopersona());
	        setDireccion(d.getDomicilio());
	        setTipo(d.getTbTipoCasa().getIdtipocasa());
	        //setUbigeo(d.getTbUbigeo().getIdubigeo());
	        setIdDistrito(getIdDistrito());	        
	        objDomicilio.setTbEstadoGeneral(objEstadoService.findEstadogeneral(16));
	        //setEstado2(d.getTbEstadoGeneral().getIdestadogeneral());
	        /*seteo telefono*/
	        objTelefono.setIdtelefonopersona(t.getIdtelefonopersona());
	        setFijo(t.getTelefono());
	        objTelefono.setTbEstadoGeneral(objEstadoService.findEstadogeneral(18));
	        //setEstado(t.getTbEstadoGeneral().getIdestadogeneral());
			log.info("-----Id estado del empleado>>>"	+ getEstadoe());
			log.info("actualizando ESTADO..... ");
			objEmpleadoService.actualizarEmpleado(objEmpleado,objDomicilio, objTelefono, objDetCargo, codigoTipoDocumento,  codigoCargoEmpleado,  mensaje, fijo,  
					estado, direccion, idUbigeo, estado2,  tipo,  nombre,  CargoEmpleado, DomicilioPersona,  TelefonoPersona,
					TipoDocumento, codigoEmpleado, estadoe, listacargo);
			log.info("actualizando..... ");
			log.info("deshabilitando..... ");
		} catch (Exception e2) {
			e2.printStackTrace();
			nombre = e2.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
			log.error(e2.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objEmpleado = new EmpleadoSie();
		return mantenimientoEmpleadoSearch.listar();
	}
	
	/*método que sirve para encriptar en MD5*/
	public String getMD5(String cadena) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(cadena.getBytes());
		int size = b.length;
		StringBuilder h = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
		int u = b[i] & 255;          
	    if (u < 16){
		h.append("0").append(Integer.toHexString(u));
		}
		else{
		h.append(Integer.toHexString(u));
		}
		}
		return h.toString();
	}
			 	
	/*método que se ejecuta haciendo click en el link NUEVO de la lista*/
	public String agregar() {
		log.info("agregar");
		setTipoDocumento(0);
		setCargoEmpleado(0);
		//setEstadoe(0);
		objEmpleado = new EmpleadoSie();
		setFijo("");
		//setEstado(0);
		objTelefono = new TelefonoPersonaSie();
		setDireccion("");
		//setUbigeo(0);
		//setIdDistrito(-1);
		setTipo(0);
		//setEstado2(0);
		objDomicilio = new DomicilioPersonaSie();
		comboManager.setIdDepartamento("15");
		comboManager.setIdProvincia("01");
		listacargo = new ArrayList<String>();
		
		setNewRecord(true);
		return getViewMant();
	}

	/*método que se ejecuta al hacer click en el botón EDITAR de la lista*/
	public String update() throws Exception {
	    log.info("actualizar");
		log.info("update()" + objEmpleado.getIdempleado());
		/*busca el empleado por medio del id del ¿datatable?*/
		EmpleadoSie c = objEmpleadoService.buscarEmpleado(objEmpleado.getIdempleado());
		/*código aún por verse*/
		DomicilioPersonaSie d = objDomicilioService.buscarDomicilioXIdempleado(objEmpleado.getIdempleado());
		TelefonoPersonaSie t = objTelefonoService.buscarTelefonoXIdempleado(objEmpleado.getIdempleado());
		log.info(" id " + c.getIdempleado()+ " nombre " + c.getNombreemp() ); 
		log.info(" id " + d.getIddomiciliopersona()+ " nombre " + d.getDomicilio() );
		log.info(" id " + t.getIdtelefonopersona()+ " nombre " + t.getTelefono() );
		/*Seteo para mostrar los datos en el form*/
		objEmpleado.setIdempleado(c.getIdempleado());
		objEmpleado.setNombreemp(c.getNombreemp());
        objEmpleado.setApepatemp(c.getApepatemp());
        objEmpleado.setApematemp(c.getApematemp());
        objEmpleado.setUsuario(c.getUsuario());
        objEmpleado.setContrasena(c.getContrasena());
        setTipoDocumento(c.getTbTipoDocumentoIdentidad().getIdtipodocumentoidentidad());
        objEmpleado.setNumdocumento(c.getNumdocumento());
        //setCargoEmpleado(c.getTbCargoEmpleado().getIdcargoempleado());
        objEmpleado.setFechanacimiento(c.getFechanacimiento());
        objEmpleado.setTbEstadoGeneral(c.getTbEstadoGeneral());
        //setEstadoe(c.getTbEstadoGeneral().getIdestadogeneral());
        
        objDomicilio.setIddomiciliopersona(d.getIddomiciliopersona());
        setDireccion(d.getDomicilio());
        setTipo(d.getTbTipoCasa().getIdtipocasa());
        //setUbigeo(d.getTbUbigeo().getIdubigeo());
        setIdDistrito(getIdDistrito());
        objDomicilio.setTbEstadoGeneral(d.getTbEstadoGeneral());
        //setEstado2(d.getTbEstadoGeneral().getIdestadogeneral());
        
        objTelefono.setIdtelefonopersona(t.getIdtelefonopersona());
        setFijo(t.getTelefono());
        objTelefono.setTbEstadoGeneral(t.getTbEstadoGeneral());
        //setEstado(t.getTbEstadoGeneral().getIdestadogeneral());
        
        /*método bolean necesario para actualizar que retorna al form */  
		setNewRecord(false);
		return getViewMant();
	}
	
	/*método del botón GUARDAR(inserta o actualiza el empleado, domicilio y telefono)*/
	public String insertar() throws Exception {
		if(StringUtils.equals(objEmpleado.getContrasena(), confcontra)){ /*probar compararcontra*/
			/*encripta la contraseña*/
		    objEmpleado.setContrasena(getMD5(objEmpleado.getContrasena()));
			log.info("probando MD5..."+objEmpleado.getContrasena());
		try {
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'insertar(registrar, actualizar)'"+ objEmpleado.getNombreemp());
				}
				/*if: inserta al empleado, domicilio y telefono
				  else: actualiza al empleado, domicilio y telefono*/
				
				//List<TelefonoPersonaSie> lstEmpleadoTelefono = new ArrayList<TelefonoPersonaSie>();
				
				if (isNewRecord()) {
					log.info("insertando..... ");
					log.info("insertando..... "+listacargo.size());
				
					log.info("insertar empleado  ");
					 
					for (int i = 0; i < listacargo.size(); i++) {
						log.info(" --  XD ");
						log.info("  p "+listacargo.get(i));
					}
					log.info("insertar empleado  xd ");
					objEmpleadoService.insertarEmpleado(objEmpleado,objDomicilio,objTelefono, objDetCargo, codigoTipoDocumento,  codigoCargoEmpleado,  mensaje, fijo,  
							estado, direccion,  idUbigeo,  estado2,  tipo,  nombre,  CargoEmpleado, DomicilioPersona,  TelefonoPersona,
							TipoDocumento, codigoEmpleado, estadoe, listacargo);
					log.info("insertando..... ");
					setNewRecord(false);
				} else {
					log.info("actualizando..... ");
					objEmpleadoService.actualizarEmpleado(objEmpleado,objDomicilio, objTelefono, objDetCargo, codigoTipoDocumento,  codigoCargoEmpleado,  mensaje, fijo,  
							estado, direccion,  idUbigeo,  estado2,  tipo,  nombre,  CargoEmpleado, DomicilioPersona,  TelefonoPersona,
							TipoDocumento, codigoEmpleado, estadoe, listacargo);
					log.info("insertando..... ");
				}
		} catch (Exception e) {
			e.printStackTrace();
			nombre = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objEmpleado = new EmpleadoSie();
		return mantenimientoEmpleadoSearch.listar();
	   }
		else{
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_PASSWORDS_DESIGUALES, "algo");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return getViewMant();
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
	 * @return the fijo
	 */
	public String getFijo() {
		return fijo;
	}

	/**
	 * @param fijo the fijo to set
	 */
	public void setFijo(String fijo) {
		this.fijo = fijo;
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
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/*
	/**
	 * @return the ubigeo
	 
	public int getUbigeo() {
		return ubigeo;
	}

	/**
	 * @param ubigeo the ubigeo to set
	 
	public void setUbigeo(int ubigeo) {
		this.ubigeo = ubigeo;
	}*/

	/**
	 * @return the estado2
	 */
	public int getEstado2() {
		return estado2;
	}

	/**
	 * @param estado2 the estado2 to set
	 */
	public void setEstado2(int estado2) {
		this.estado2 = estado2;
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
	 * @return the estadoe
	 */
	public int getEstadoe() {
		return estadoe;
	}

	/**
	 * @param estadoe the estadoe to set
	 */
	public void setEstadoe(int estadoe) {
		this.estadoe = estadoe;
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
	public DetCargoEmpleadoSie getObjDetCargo() {
		return objDetCargo;
	}

	/**
	 * @param objDetCargo the objDetCargo to set
	 */
	public void setObjDetCargo(DetCargoEmpleadoSie objDetCargo) {
		this.objDetCargo = objDetCargo;
	}

	/**
	 * @return the listacargo
	 */
	public ArrayList<String> getListacargo() {
		return listacargo;
	}

	/**
	 * @param listacargo the listacargo to set
	 */
	public void setListacargo(ArrayList<String> listacargo) {
		this.listacargo = listacargo;
	}



		
}
