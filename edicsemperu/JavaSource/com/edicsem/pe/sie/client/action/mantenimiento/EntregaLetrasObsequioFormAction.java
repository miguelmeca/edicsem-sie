package com.edicsem.pe.sie.client.action.mantenimiento;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.client.report.service.ReporteExecutionService;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.service.facade.CobranzaService;
import com.edicsem.pe.sie.service.facade.ContratoService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "entregaForm")
@SessionScoped
public class EntregaLetrasObsequioFormAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	public static Log log = LogFactory.getLog(EntregaLetrasObsequioFormAction.class);
	private int idestadoEntrega, idtipodoc;
	private boolean newRecord = false;
	private Date dhoy, fechaDesde, fechaHasta;
	
	//Consultar
	private String numDocumento,codigoContrato,apePatCliente,apeMatCliente,nombreCliente;
	private int radio;
	private List<ContratoSie> contratoList;
	
	//Reporte
	private String ContentType;
	
	@EJB
	private ContratoService objContratoService;
	@EJB
	private CobranzaService objCobranzaService;
	@EJB
	private EstadogeneralService objEstadoService;
	@EJB
	private ReporteExecutionService objReporteService;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;

	public EntregaLetrasObsequioFormAction() {
		log.info("inicializando constructor MantenimientoContrato");
		init();
	}
	
	public void init() {
		log.info("init() ");
		radio=1;
		ContentType="";
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
		comboManager.setCodigoEstado(Constants.COD_ESTADO_TB_DET_CONTRATO_PRODUCTO);
		contratoList = new ArrayList<ContratoSie>(); 
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()");
		setNewRecord(false);
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	public String consultar() throws Exception {
		log.info("consultar() ");
		contratoList = objContratoService.listarContratoEntregaLetraObsequio(numDocumento, codigoContrato, nombreCliente, apePatCliente, apeMatCliente);
		mensaje = "Consulto realizada";
		
		log.info(" consulta finalizada "+ mensaje);
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_ENTREGA_LETRAS;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_ENTREGA_LETRAS;
	}
	
	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the apePatCliente
	 */
	public String getApePatCliente() {
		return apePatCliente;
	}

	/**
	 * @param apePatCliente the apePatCliente to set
	 */
	public void setApePatCliente(String apePatCliente) {
		this.apePatCliente = apePatCliente;
	}

	/**
	 * @return the apeMatCliente
	 */
	public String getApeMatCliente() {
		return apeMatCliente;
	}

	/**
	 * @param apeMatCliente the apeMatCliente to set
	 */
	public void setApeMatCliente(String apeMatCliente) {
		this.apeMatCliente = apeMatCliente;
	}

	/**
	 * @return the radio
	 */
	public int getRadio() {
		if(radio==1){
			codigoContrato=null;
			nombreCliente=null;
			apePatCliente=null;
			apeMatCliente = null;
		}else if (radio ==2){
			numDocumento=null;
			nombreCliente=null;
			apePatCliente=null;
			apeMatCliente = null;
		}else{
			numDocumento=null;
			codigoContrato=null;
		}
		return radio;
	}

	/**
	 * @param radio the radio to set
	 */
	public void setRadio(int radio) {
		
		if(radio==1){
			codigoContrato=null;
			nombreCliente=null;
			apePatCliente=null;
			apeMatCliente = null;
		}else if (radio ==2){
			numDocumento=null;
			nombreCliente=null;
			apePatCliente=null;
			apeMatCliente = null;
		}else{
			numDocumento=null;
			codigoContrato=null;
		}
		
		this.radio = radio;
	}

	/**
	 * @return the objCobranzaService
	 */
	public CobranzaService getObjCobranzaService() {
		return objCobranzaService;
	}

	/**
	 * @param objCobranzaService the objCobranzaService to set
	 */
	public void setObjCobranzaService(CobranzaService objCobranzaService) {
		this.objCobranzaService = objCobranzaService;
	}

	/**
	 * @return the dhoy
	 */
	public Date getDhoy() {
		try {
			dhoy = DateUtil.getToday().getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dhoy;
	}

	/**
	 * @param dhoy the dhoy to set
	 */
	public void setDhoy(Date dhoy) {
		this.dhoy = dhoy;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return ContentType;
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
	 * @return the idestadoEntrega
	 */
	public int getIdestadoEntrega() {
		return idestadoEntrega;
	}

	/**
	 * @param idestadoEntrega the idestadoEntrega to set
	 */
	public void setIdestadoEntrega(int idestadoEntrega) {
		this.idestadoEntrega = idestadoEntrega;
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
	 * @return the fechaDesde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @return the codigoContrato
	 */
	public String getCodigoContrato() {
		return codigoContrato;
	}

	/**
	 * @param codigoContrato the codigoContrato to set
	 */
	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
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
	 * @return the idtipodoc
	 */
	public int getIdtipodoc() {
		return idtipodoc;
	}

	/**
	 * @param idtipodoc the idtipodoc to set
	 */
	public void setIdtipodoc(int idtipodoc) {
		this.idtipodoc = idtipodoc;
	}

	/**
	 * @return the contratoList
	 */
	public List<ContratoSie> getContratoList() {
		return contratoList;
	}

	/**
	 * @param contratoList the contratoList to set
	 */
	public void setContratoList(List<ContratoSie> contratoList) {
		this.contratoList = contratoList;
	}

	/**
	 * @return the numDocumento
	 */
	public String getNumDocumento() {
		return numDocumento;
	}

	/**
	 * @param numDocumento the numDocumento to set
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		ContentType = contentType;
	}
	
}
