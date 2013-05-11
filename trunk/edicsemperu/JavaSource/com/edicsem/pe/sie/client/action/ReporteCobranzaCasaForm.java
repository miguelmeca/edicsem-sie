package com.edicsem.pe.sie.client.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.ReporteParams;
import com.edicsem.pe.sie.client.report.service.ReporteExecutionService;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "reporteCobranzaCasa")
@SessionScoped
public class ReporteCobranzaCasaForm extends BaseMantenimientoAbstractAction{
	
	private List<ClienteSie> lstCliente;
	public static Log log = LogFactory.getLog(ReporteCobranzaCasaForm.class);
	private String idDepartamento, idProvincia, idUbigeo;
	private String letra, plano, sector;
	private String ContentType, mensaje;
	private int stockActual,cantLista;
	
	@EJB
	private ClienteService objClienteService;
	
	@EJB
	private ReporteExecutionService objReporteService;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;
	
	public ReporteCobranzaCasaForm(){
		
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		cantLista=0;
		ContentType="";
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		comboManager.setIdDepartamento("15");
		comboManager.setIdProvincia("01");
		comboManager.setUbigeoDeparItems(null);
		comboManager.setUbigeoProvinItems(null);
		comboManager.setUbigeoDistriItems(null);
		idDepartamento="15";
		idProvincia="01";
		idUbigeo="0";
		comboManager.setUbigeoDistriItems(null);
		return getViewMant();
	}
	
	public void cambiar() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(null);
		idProvincia = null;
		idUbigeo = null;
	}
	
	public void cambiar2() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(getIdProvincia());
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	public String consultar() {
		setLstCliente(new ArrayList<ClienteSie>());
		mensaje =null;
		stockActual=0;
		try {
			
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void exportar() {
		log.info("exportar() ");
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		ReporteParams parametros = new ReporteParams();
		
		try {
			parametros.setJasperFileName(Constants.REPORTE_KARDEX_JASPER);
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_hhmmss");
			Map criteria = new HashMap();
			criteria.put(Constants.REPORTE_TITULO, Constants.REPORTE_KARDEX_LIST+"_"+ sdf.format(new Date(System.currentTimeMillis())));
//			criteria.put(Constants.REPORTE_KARDEX_FECHA_DESDE, fechaDesde);
//			criteria.put(Constants.REPORTE_KARDEX_FECHA_HASTA, fechaHasta);
//			parametros.setQueryParams(criteria);
			
			HttpServletResponse response = (HttpServletResponse)context.getResponse();
			objReporteService.executeReporte(parametros, response, ContentType);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FacesContext.getCurrentInstance().responseComplete(); 
		}
	}
	
	
	/**
	 * @return the cantLista
	 */
	public int getCantLista() {
		return cantLista;
	}

	/**
	 * @param cantLista the cantLista to set
	 */
	public void setCantLista(int cantLista) {
		this.cantLista = cantLista;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return ContentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		ContentType = contentType;
	}
	
	/**
	 * @return the objReporteService
	 */
	public ReporteExecutionService getObjReporteService() {
		return objReporteService;
	}

	/**
	 * @param objReporteService the objReporteService to set
	 */
	public void setObjReporteService(ReporteExecutionService objReporteService) {
		this.objReporteService = objReporteService;
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
	 * @return the stockActual
	 */
	public int getStockActual() {
		return stockActual;
	}

	/**
	 * @param stockActual the stockActual to set
	 */
	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}
	
	/**
	 * @return the letra
	 */
	public String getLetra() {
		return letra;
	}

	/**
	 * @param letra the letra to set
	 */
	public void setLetra(String letra) {
		this.letra = letra;
	}

	/**
	 * @return the plano
	 */
	public String getPlano() {
		return plano;
	}

	/**
	 * @param plano the plano to set
	 */
	public void setPlano(String plano) {
		this.plano = plano;
	}

	/**
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * @param sector the sector to set
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	public List<ClienteSie> getLstCliente() {
		return lstCliente;
	}

	public void setLstCliente(List<ClienteSie> lstCliente) {
		this.lstCliente = lstCliente;
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
