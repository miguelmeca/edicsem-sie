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
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.ReporteParams;
import com.edicsem.pe.sie.client.report.service.ReporteExecutionService;
import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.service.facade.KardexService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "reporteKardex")
@SessionScoped
public class ReporteKardexForm extends BaseMantenimientoAbstractAction{

	private List<KardexSie> lstkardex;
	public static Log log = LogFactory.getLog(ReporteKardexForm.class);
	private int cantLista;
	private int idalmacen, idproducto, idtipopuntoventa;
	private Date fechaDesde,fechaHasta;
	private String ContentType, mensaje;
	private int stockActual;

	@EJB
	private KardexService objkardexService;
	@EJB
	private ReporteExecutionService objReporteService;
	
	public ReporteKardexForm(){
		
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		cantLista=0;
		idalmacen=0;
		idproducto=0;
		idtipopuntoventa=0;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	public String consultar() {
		lstkardex = new ArrayList<KardexSie>();
		mensaje =null;
		stockActual=0;
		try {
			log.info(" f  "+fechaDesde +" "+fechaHasta);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaD = "", fechaH = "";
			log.info((fechaDesde == null )+"  - "+(fechaHasta != null));
			log.info((fechaDesde == null && fechaHasta != null));
		
		if (fechaDesde == null && fechaHasta != null){
			mensaje ="Por favor ingresar una fecha de inicio ";
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			if (fechaDesde != null && fechaHasta == null){
				fechaHasta= DateUtil.getToday().getTime();
			}
			if (fechaDesde == null){
				fechaD = "";
			}if (fechaHasta == null){
				fechaH = "";
			}else if (fechaDesde != null && fechaHasta != null){
				fechaD = "" + sdf.format(fechaDesde);
				fechaH = "" + sdf.format(fechaHasta);
			}
			lstkardex = objkardexService.ConsultaProductos(getIdproducto(), getIdalmacen(), fechaD, fechaH);
			cantLista=lstkardex.size();
			if(lstkardex.size()==0){
				lstkardex = new ArrayList<KardexSie>();
				stockActual=0;
				setMensaje(" consulta realizada ");
			}
			else{
				log.info("cantidad existente :D "+ lstkardex.get(lstkardex.size() - 1).getCantexistencia());
				stockActual = lstkardex.get(lstkardex.size() - 1).getCantexistencia();
				log.info("nuevo stock actual " + getStockActual());
				setMensaje(" consulta realizada ");
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void exportar() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		ReporteParams parametros = new ReporteParams();
		
		try {
			parametros.setJasperFileName(Constants.REPORTE_KARDEX_LIST);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			Map criteria = new HashMap();
			criteria.put("titulo", Constants.REPORTE_KARDEX_LIST+"_"+ sdf.format(new Date(System.currentTimeMillis())));
			criteria.put("almacen", idalmacen);
			criteria.put("producto", idproducto);
			criteria.put("fechaDesde", fechaDesde);
			criteria.put("fechaHasta", fechaHasta);
			parametros.setQueryParams(criteria);
			
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
	 * @return the lstkardex
	 */
	public List<KardexSie> getLstkardex() {
		return lstkardex;
	}

	/**
	 * @param lstkardex the lstkardex to set
	 */
	public void setLstkardex(List<KardexSie> lstkardex) {
		this.lstkardex = lstkardex;
	}

	/**
	 * @return the idalmacen
	 */
	public int getIdalmacen() {
		return idalmacen;
	}

	/**
	 * @param idalmacen the idalmacen to set
	 */
	public void setIdalmacen(int idalmacen) {
		this.idalmacen = idalmacen;
	}

	/**
	 * @return the idproducto
	 */
	public int getIdproducto() {
		return idproducto;
	}

	/**
	 * @param idproducto the idproducto to set
	 */
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	/**
	 * @return the idtipopuntoventa
	 */
	public int getIdtipopuntoventa() {
		return idtipopuntoventa;
	}

	/**
	 * @param idtipopuntoventa the idtipopuntoventa to set
	 */
	public void setIdtipopuntoventa(int idtipopuntoventa) {
		this.idtipopuntoventa = idtipopuntoventa;
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
	 * @return the objkardexService
	 */
	public KardexService getObjkardexService() {
		return objkardexService;
	}

	/**
	 * @param objkardexService the objkardexService to set
	 */
	public void setObjkardexService(KardexService objkardexService) {
		this.objkardexService = objkardexService;
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
}
