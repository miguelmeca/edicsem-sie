package com.edicsem.pe.sie.client.action.mantenimiento;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;

import com.edicsem.pe.sie.client.action.SMTPConfig;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.service.facade.CobranzaOperaService;
import com.edicsem.pe.sie.service.facade.CobranzaService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
import com.edicsem.pe.sie.service.facade.TipoLLamadaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoCobranzaOperaSearchAction")
@SessionScoped
public class MantenimientoCobranzaOperaSearchAction extends BaseMantenimientoAbstractAction {
    /*variables*/
	private CobranzaOperadoraSie objCobranzaOpera;
	private CobranzaSie objCobranza;
	private ClienteSie objcliente;
	private TelefonoPersonaSie objtelefono;
	private List<CobranzaOperadoraSie> cobranzaOperaList;
	private List<CobranzaSie> detallePagos;
	private List<TelefonoPersonaSie> listatelefono;
	private Date fecPagoNull;
	private int tipollamada; 
	private boolean editMode;
	private boolean enviarMensaje;
	private String nombre;
	private boolean newRecord =false;
	private int ide;
	private int idcontrato;
	
	@EJB 
	private CobranzaOperaService objCobranzaOperaService;
	@EJB 
	private CobranzaService objCobranzaService;
	@EJB 
	private ClienteService objClienteService;
	@EJB 
	private TelefonoEmpleadoService objTelefonoService;
	@EJB 
	private TipoLLamadaService objTipoLLamadaService;
	
	public static Log log = LogFactory.getLog(MantenimientoCobranzaOperaSearchAction.class);
	
	public MantenimientoCobranzaOperaSearchAction() {
		log.info("ESTOY EN MI CONSTRUCTOR");
		log.info("inicializando mi constructor");
		init();
	}

	/*inicializamos los  objetos utilizados*/
	public void init() {
		log.info("init()");
		objCobranza = new CobranzaSie();
		objCobranzaOpera = new CobranzaOperadoraSie();
		objcliente = new ClienteSie();
		objtelefono = new TelefonoPersonaSie();
		fecPagoNull=null;
		enviarMensaje=false;
		idcontrato=0;
		log.info("despues de inicializar  ");
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	/*método que lista al hacer click en el menú del template*/
	public String listar() {
		log.info("listarcobranzaopera 'MantenimientoCobranzaOperadoraSieSearchAction' ");
		cobranzaOperaList = objCobranzaOperaService.listarCobranzasOpera();
		if (cobranzaOperaList == null) {
			cobranzaOperaList = new ArrayList<CobranzaOperadoraSie>();
		}
		return getViewList();
	}
	
	public String mostrar() throws Exception {
		//int idcontrato;
		log.info("mostrar detalle de pagos");
		idcontrato = objCobranzaOpera.getTbCobranza().getTbContrato().getIdcontrato();
		// comenzamos a mostrar el detalles
				log.info("listarDetalleDePagos 'MantenimientoCobranzasPorCuotasSearchAction' ");
				detallePagos = objCobranzaService.listarCobranzasXidcontrato(idcontrato);
				if (detallePagos == null) {
					detallePagos = new ArrayList<CobranzaSie>();
				}
	    // mostramos los datos del cliente
		ClienteSie c = objClienteService.findCliente(objCobranzaOpera.getTbCobranza().getIdcliente()); 		
		objcliente.setIdcliente(c.getIdcliente());
		objcliente.setNombrecliente(c.getNombrecliente());
		objcliente.setCorreo(c.getCorreo());
		objcliente.setDirectrabajo(c.getDirectrabajo());
		objcliente.setEmpresatrabajo(c.getEmpresatrabajo());
		objcliente.setCargotrabajo(c.getCargotrabajo());
		objcliente.setTelftrabajo(c.getTelftrabajo());
		log.info("listartelefonos x idcliente");
		listatelefono = objTelefonoService.listarTelefonoEmpleadosXidcliente(objcliente.getIdcliente());
		if (listatelefono == null) {
			listatelefono = new ArrayList<TelefonoPersonaSie>();
		}
		return getViewList();
	}
	
	public String insertar() throws Exception {
		try {
			 if(idcontrato!=0){ 
			
			if(enviarMensaje==true){
			
			if(SMTPConfig.sendMail("Seguimiento del cliente",objCobranzaOpera.getObservaciones(),"geraldine8513@gmail.com")){
				   
				   log.info("envío Correcto");
				    
				  }else {
					  
			       log.info("envío Fallido");
				 
				 }
			
			}	
				objCobranzaOpera.setObservaciones(objCobranzaOpera.getObservaciones());
				objCobranzaOpera.setTbTipoLlamada(objTipoLLamadaService.findTipoLLamada(tipollamada));
					log.info("actualizando..... ");
					objCobranzaOperaService.updateCobranzaOpera(objCobranzaOpera);
					log.info("insertando..... ");
			 
			 }else{
			   nombre = "";
			   msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_ERROR_ID_COBRANZA, nombre);
			   FacesContext.getCurrentInstance().addMessage(null, msg);  
			 }
		} catch (Exception e) {
			e.printStackTrace();
			nombre = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return getViewList();
	}
	
	/*GETs Y SETs*/
	public String getViewList() {
		return "manteCobranzaOperaList";
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
	 * @return the objCobranzaOpera
	 */
	public CobranzaOperadoraSie getObjCobranzaOpera() {
		return objCobranzaOpera;
	}

	/**
	 * @param objCobranzaOpera the objCobranzaOpera to set
	 */
	public void setObjCobranzaOpera(CobranzaOperadoraSie objCobranzaOpera) {
		this.objCobranzaOpera = objCobranzaOpera;
	}

	/**
	 * @return the cobranzaOperaList
	 */
	public List<CobranzaOperadoraSie> getCobranzaOperaList() {
		return cobranzaOperaList;
	}

	/**
	 * @param cobranzaOperaList the cobranzaOperaList to set
	 */
	public void setCobranzaOperaList(List<CobranzaOperadoraSie> cobranzaOperaList) {
		this.cobranzaOperaList = cobranzaOperaList;
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
	 * @return the ide
	 */
	public int getIde() {
		return ide;
	}

	/**
	 * @param ide the ide to set
	 */
	public void setIde(int ide) {
		this.ide = ide;
	}

	/**
	 * @return the detallePagos
	 */
	public List<CobranzaSie> getDetallePagos() {
		return detallePagos;
	}

	/**
	 * @param detallePagos the detallePagos to set
	 */
	public void setDetallePagos(List<CobranzaSie> detallePagos) {
		this.detallePagos = detallePagos;
	}

	/**
	 * @return the objCobranza
	 */
	public CobranzaSie getObjCobranza() {
		return objCobranza;
	}

	/**
	 * @param objCobranza the objCobranza to set
	 */
	public void setObjCobranza(CobranzaSie objCobranza) {
		this.objCobranza = objCobranza;
	}

	/**
	 * @return the objcliente
	 */
	public ClienteSie getObjcliente() {
		return objcliente;
	}

	/**
	 * @param objcliente the objcliente to set
	 */
	public void setObjcliente(ClienteSie objcliente) {
		this.objcliente = objcliente;
	}

	/**
	 * @return the objtelefono
	 */
	public TelefonoPersonaSie getObjtelefono() {
		return objtelefono;
	}

	/**
	 * @param objtelefono the objtelefono to set
	 */
	public void setObjtelefono(TelefonoPersonaSie objtelefono) {
		this.objtelefono = objtelefono;
	}

	/**
	 * @return the listatelefono
	 */
	public List<TelefonoPersonaSie> getListatelefono() {
		return listatelefono;
	}

	/**
	 * @param listatelefono the listatelefono to set
	 */
	public void setListatelefono(List<TelefonoPersonaSie> listatelefono) {
		this.listatelefono = listatelefono;
	}

	/**
	 * @return the fecPagoNull
	 */
	public Date getFecPagoNull() {
		return fecPagoNull;
	}

	/**
	 * @param fecPagoNull the fecPagoNull to set
	 */
	public void setFecPagoNull(Date fecPagoNull) {
		this.fecPagoNull = fecPagoNull;
	}

	/**
	 * @return the tipollamada
	 */
	public int getTipollamada() {
		return tipollamada;
	}

	/**
	 * @param tipollamada the tipollamada to set
	 */
	public void setTipollamada(int tipollamada) {
		this.tipollamada = tipollamada;
	}

	/**
	 * @return the enviarMensaje
	 */
	public boolean isEnviarMensaje() {
		return enviarMensaje;
	}

	/**
	 * @param enviarMensaje the enviarMensaje to set
	 */
	public void setEnviarMensaje(boolean enviarMensaje) {
		this.enviarMensaje = enviarMensaje;
	}

	/**
	 * @return the idcontrato
	 */
	public int getIdcontrato() {
		return idcontrato;
	}

	/**
	 * @param idcontrato the idcontrato to set
	 */
	public void setIdcontrato(int idcontrato) {
		this.idcontrato = idcontrato;
	}
		
}
