package com.edicsem.pe.sie.client.action;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.service.facade.DomicilioEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
import java.security.MessageDigest;

@ManagedBean(name="mantenimientoEmpleadoFormAction")
@SessionScoped
public class MantenimientoEmpleadoFormAction extends BaseMantenimientoAbstractAction {
    /*Se crean los objetos de las entidades empleado, domicilio y telefono*/	
	private EmpleadoSie objEmpleado;
	private TelefonoPersonaSie objTelefono;
	private DomicilioPersonaSie objDomicilio;
	/*variables para telefono*/
	private String mensaje;
	private String fijo;
	private int estado;
	/*variables para domicilio*/
	private String direccion;
	private int ubigeo;
	private int estado2;
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
    /*variable bolean necesaria*/
	private boolean newRecord =false;
		
	@EJB 
	private EmpleadoSieService objEmpleadoService;
	@EJB 
	private DomicilioEmpleadoService objDomicilioService;
	@EJB 
	private TelefonoEmpleadoService objTelefonoService;
	
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
	
	/*método que compara las contraseñas.*/
	public boolean CompararContraseña(String s1, String s2) throws Exception {
	    if ( s1.equals( s2 ) )  
	        return true;
	    else
	        return false; 
	} 
		 	
	/*método que se ejecuta haciendo click en el link NUEVO de la lista*/
	public String addNewRecord() throws Exception {
		log.info("insertar");
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
        setCargoEmpleado(c.getTbCargoEmpleado().getIdcargoempleado());
        objEmpleado.setFechanacimiento(c.getFechanacimiento());
        setEstadoe(c.getTbEstadoGeneral().getIdestadogeneral());
        
        objDomicilio.setIddomiciliopersona(d.getIddomiciliopersona());
        setDireccion(d.getDomicilio());
        setTipo(d.getTbTipoCasa().getIdtipocasa());
        setUbigeo(d.getTbUbigeo().getIdubigeo());
        setEstado2(d.getTbEstadoGeneral().getIdestadogeneral());
        
        objTelefono.setIdtelefonopersona(t.getIdtelefonopersona());
        setFijo(t.getTelefono());
        setEstado(t.getTbEstadoGeneral().getIdestadogeneral());
        
        /*método bolean necesario para actualizar que retorna al form */  
		setNewRecord(false);
		return getViewMant();
	}
	
	/*método del botón GUARDAR(inserta o actualiza el empleado, domicilio y telefono)*/
	public String insertar() throws Exception {
		if(CompararContraseña(objEmpleado.getContrasena(), confcontra)){ /*probar compararcontra*/
			/*encripta la contraseña*/
		    objEmpleado.setContrasena(getMD5(objEmpleado.getContrasena()));
			log.info("probando MD5..."+objEmpleado.getContrasena());
		try {
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'insertar(registrar, actualizar)'"+ objEmpleado.getNombreemp());
				}
				/*if: inserta al empleado, domicilio y telefono
				  else: actualiza al empleado, domicilio y telefono*/
				if (isNewRecord()) {
					log.info("insertando..... ");
					objEmpleadoService.insertarEmpleado(objEmpleado,objDomicilio,objTelefono, codigoTipoDocumento,  codigoCargoEmpleado,  mensaje, fijo,  
							estado, direccion,  ubigeo,  estado2,  tipo,  nombre,  CargoEmpleado, DomicilioPersona,  TelefonoPersona,
							TipoDocumento, codigoEmpleado, estadoe);
					log.info("insertando..... ");
					setNewRecord(false);
				} else {
					log.info("actualizando..... ");
					objEmpleadoService.actualizarEmpleado(objEmpleado,objDomicilio, objTelefono, codigoTipoDocumento,  codigoCargoEmpleado,  mensaje, fijo,  
							estado, direccion,  ubigeo,  estado2,  tipo,  nombre,  CargoEmpleado, DomicilioPersona,  TelefonoPersona,
							TipoDocumento, codigoEmpleado, estadoe);
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
		return getViewList();
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

	/**
	 * @return the ubigeo
	 */
	public int getUbigeo() {
		return ubigeo;
	}

	/**
	 * @param ubigeo the ubigeo to set
	 */
	public void setUbigeo(int ubigeo) {
		this.ubigeo = ubigeo;
	}

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
		
}
