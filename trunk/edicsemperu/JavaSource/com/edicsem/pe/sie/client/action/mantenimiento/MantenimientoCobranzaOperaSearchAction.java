package com.edicsem.pe.sie.client.action.mantenimiento;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.SMTPConfig;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.DetProductoContratoSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.HistoricoObservacionesSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.service.facade.CobranzaOperaService;
import com.edicsem.pe.sie.service.facade.CobranzaService;
import com.edicsem.pe.sie.service.facade.ContratoService;
import com.edicsem.pe.sie.service.facade.DetProductoContratoService;
import com.edicsem.pe.sie.service.facade.HistoricoObservacionesService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
import com.edicsem.pe.sie.service.facade.TipoLLamadaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
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
	private List<CobranzaOperadoraSie> filtrarCobranza;
	private List<CobranzaSie> detallePagos;
	private List<TelefonoPersonaSie> listatelefono;
	private List<DetProductoContratoSie> productoContratoList;
	private Date fechoy;
	private int tipollamada; 
	private boolean editMode;
	private boolean enviarMensaje;
	private String nombre;
	private boolean newRecord =false;
	private int ide;
	private int idcontrato;
	private ContratoSie objContrato;
	private List<HistoricoObservacionesSie> lstHistorico;
	HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
	
	@EJB 
	private ContratoService objContratoService;
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
	@EJB
	private DetProductoContratoService objProductoContratoService;
	@EJB
	private HistoricoObservacionesService objHistoricoService;
	
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
		lstHistorico = new ArrayList<HistoricoObservacionesSie>();
		enviarMensaje=false;
		idcontrato=0;
		log.info("despues de inicializar  ");
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarcobranzaopera 'MantenimientoCobranzaOperadoraSieSearchAction' ");
		log.info(" session  "+sessionUsuario.getUsuario());
		cobranzaOperaList = objCobranzaOperaService.listarCobranzasOpera(sessionUsuario.getUsuario());
		if (cobranzaOperaList == null) {
			cobranzaOperaList = new ArrayList<CobranzaOperadoraSie>();
		}
		//Mostramos el historial
		lstHistorico = objHistoricoService.listarHistorial(idcontrato);
//		log.info(" Listando historial *** ");
//		lstHistorico= new ArrayList<HistoricoObservacionesSie>();
//		for (int i = 0; i < 10; i++) {
//			HistoricoObservacionesSie h = new HistoricoObservacionesSie();
//			h.setUsuariocreacion("Kgil "+i);
//			h.setObservacion("Cliente no vive en casa, persona que se encontro dice no vovler a llamar");
//			lstHistorico.add(h);
//		}
		
		return getViewList();
	}
	
	public String mostrar() throws Exception {
		log.info("mostrar()");
		idcontrato = objCobranzaOpera.getTbCobranza().getTbContrato().getIdcontrato();
		// comenzamos a mostrar el detalles
			log.info("listarDetalleDePagos 'MantenimientoCobranzasPorCuotasSearchAction' ");
			detallePagos = objCobranzaService.listarCobranzasXidcontrato(idcontrato);
			if (detallePagos == null) {
				detallePagos = new ArrayList<CobranzaSie>();
			}
		objContrato = objContratoService.findContrato(idcontrato);
	    // mostramos los datos del cliente
		objcliente = objClienteService.findCliente(objCobranzaOpera.getTbCobranza().getIdcliente());
		log.info("listartelefonos x idcliente");
		listatelefono = objTelefonoService.listarTelefonoEmpleadosXidcliente(objcliente.getIdcliente());
		if (listatelefono == null) {
			listatelefono = new ArrayList<TelefonoPersonaSie>();
		}
		//productos del contrato
		productoContratoList =objProductoContratoService.listarDetProductoContratoXContrato(idcontrato);
		
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
					//mensaje
					nombre ="";
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							Constants.MESSAGE_REGISTRO_TITULO, nombre);
					FacesContext.getCurrentInstance().addMessage(null, msg);
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

	/**
	 * @return the filtrarCobranza
	 */
	public List<CobranzaOperadoraSie> getFiltrarCobranza() {
		return filtrarCobranza;
	}

	/**
	 * @param filtrarCobranza the filtrarCobranza to set
	 */
	public void setFiltrarCobranza(List<CobranzaOperadoraSie> filtrarCobranza) {
		this.filtrarCobranza = filtrarCobranza;
	}

	/**
	 * @return the objContrato
	 */
	public ContratoSie getObjContrato() {
		return objContrato;
	}

	/**
	 * @param objContrato the objContrato to set
	 */
	public void setObjContrato(ContratoSie objContrato) {
		this.objContrato = objContrato;
	}

	/**
	 * @return the fechoy
	 */
	public Date getFechoy() {
		try {
			fechoy = DateUtil.getToday().getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fechoy;
	}

	/**
	 * @param fechoy the fechoy to set
	 */
	public void setFechoy(Date fechoy) {
		this.fechoy = fechoy;
	}

	/**
	 * @return the productoContratoList
	 */
	public List<DetProductoContratoSie> getProductoContratoList() {
		return productoContratoList;
	}

	/**
	 * @param productoContratoList the productoContratoList to set
	 */
	public void setProductoContratoList(
			List<DetProductoContratoSie> productoContratoList) {
		this.productoContratoList = productoContratoList;
	}

	/**
	 * @return the lstHistorico
	 */
	public List<HistoricoObservacionesSie> getLstHistorico() {
		return lstHistorico;
	}

	/**
	 * @param lstHistorico the lstHistorico to set
	 */
	public void setLstHistorico(List<HistoricoObservacionesSie> lstHistorico) {
		this.lstHistorico = lstHistorico;
	}
		
}
