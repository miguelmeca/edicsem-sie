package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.client.dataModel.CobranzaDataModel;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.CobranzaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "asignarTipoClienteForm")
@SessionScoped
public class AsignarTipoClienteFormAction extends BaseMantenimientoAbstractAction {
	
	private String mensaje;
	public static Log log = LogFactory.getLog(AsignarTipoClienteFormAction.class);
	
	//Consultar
	private int idTipocliente, idCalificacion, cuotasxpagar,diasRetrazo, tamanoLista;
	private List<CobranzaSie> cobranzaList;
	private Date fechaEntregaDesde, fechaEntregaHasta;
	private boolean newRecord;
	private CobranzaDataModel cobranzaModel;
	private CobranzaSie[] selectedCob;
	private String contentType;
	
	@EJB
	private CobranzaService objCobranzaService;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;

	public AsignarTipoClienteFormAction() {
		log.info("inicializando constructor MantenimientoContrato");
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init() ");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #agregar()
	 */
	public String agregar() {
		log.info("agregar()))");
		setNewRecord(true);
		idCalificacion=0;
		setTamanoLista(0);
		cobranzaList= new ArrayList<CobranzaSie>();
		cobranzaModel = new CobranzaDataModel(cobranzaList);
		return getViewMant();
	}
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #update()
	 */
	public String update() throws Exception {
		log.info("update()");
		setNewRecord(false);
		return getViewList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #insertar()
	 */
	public String insertar() {
		mensaje=null;
		String paginaRetorno = null;
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()' ");
			}
			
			if (isNewRecord()) {
				//objContratoSie.setUsuariocreacion(sessionUsuario.getUsuario());
				mensaje = "Se inserto correctamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						Constants.MESSAGE_INFO_TITULO, mensaje);
				
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
		return paginaRetorno;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	public String consultar() throws Exception {
		log.info("consultar() ");
		setTamanoLista(0);
		setNewRecord(true);
		cobranzaList = objCobranzaService.listarCobranzasporParametro(idTipocliente, idCalificacion, 
						cuotasxpagar,diasRetrazo, fechaEntregaDesde, fechaEntregaHasta);
		if(cobranzaList!=null){
			setTamanoLista(cobranzaList.size());
			cobranzaModel = new CobranzaDataModel(cobranzaList);
		}
		if(cobranzaList==null){
			cobranzaList=new ArrayList<CobranzaSie>();
		}
		log.info(cobranzaModel.getRowCount()+" lista x consultaaa "+cobranzaList.size()+" MENSAJE "+ mensaje);
		
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.ASIGNAR_TIPO_CLIENTE_FORM;
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
	 * @return the cobranzaList
	 */
	public List<CobranzaSie> getCobranzaList() {
		return cobranzaList;
	}

	/**
	 * @param cobranzaList the cobranzaList to set
	 */
	public void setCobranzaList(List<CobranzaSie> cobranzaList) {
		this.cobranzaList = cobranzaList;
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
	 * @return the idTipocliente
	 */
	public int getIdTipocliente() {
		return idTipocliente;
	}

	/**
	 * @param idTipocliente the idTipocliente to set
	 */
	public void setIdTipocliente(int idTipocliente) {
		this.idTipocliente = idTipocliente;
	}

	/**
	 * @return the idCalificacion
	 */
	public int getIdCalificacion() {
		return idCalificacion;
	}

	/**
	 * @param idCalificacion the idCalificacion to set
	 */
	public void setIdCalificacion(int idCalificacion) {
		this.idCalificacion = idCalificacion;
	}

	/**
	 * @return the cuotasxpagar
	 */
	public int getCuotasxpagar() {
		return cuotasxpagar;
	}

	/**
	 * @param cuotasxpagar the cuotasxpagar to set
	 */
	public void setCuotasxpagar(int cuotasxpagar) {
		this.cuotasxpagar = cuotasxpagar;
	}

	/**
	 * @return the diasRetrazo
	 */
	public int getDiasRetrazo() {
		return diasRetrazo;
	}

	/**
	 * @param diasRetrazo the diasRetrazo to set
	 */
	public void setDiasRetrazo(int diasRetrazo) {
		this.diasRetrazo = diasRetrazo;
	}

	/**
	 * @return the fechaEntregaDesde
	 */
	public Date getFechaEntregaDesde() {
		return fechaEntregaDesde;
	}

	/**
	 * @param fechaEntregaDesde the fechaEntregaDesde to set
	 */
	public void setFechaEntregaDesde(Date fechaEntregaDesde) {
		this.fechaEntregaDesde = fechaEntregaDesde;
	}

	/**
	 * @return the fechaEntregaHasta
	 */
	public Date getFechaEntregaHasta() {
		return fechaEntregaHasta;
	}

	/**
	 * @param fechaEntregaHasta the fechaEntregaHasta to set
	 */
	public void setFechaEntregaHasta(Date fechaEntregaHasta) {
		this.fechaEntregaHasta = fechaEntregaHasta;
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
	 * @return the cobranzaModel
	 */
	public CobranzaDataModel getCobranzaModel() {
		return cobranzaModel;
	}

	/**
	 * @param cobranzaModel the cobranzaModel to set
	 */
	public void setCobranzaModel(CobranzaDataModel cobranzaModel) {
		this.cobranzaModel = cobranzaModel;
	}

	/**
	 * @return the selectedCob
	 */
	public CobranzaSie[] getSelectedCob() {
		return selectedCob;
	}

	/**
	 * @param selectedCob the selectedCob to set
	 */
	public void setSelectedCob(CobranzaSie[] selectedCob) {
		this.selectedCob = selectedCob;
	}

	public int getTamanoLista() {
		return tamanoLista;
	}

	public void setTamanoLista(int tamanoLista) {
		this.tamanoLista = tamanoLista;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
}
