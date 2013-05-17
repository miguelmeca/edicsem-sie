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
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.ZonificacionSie;
import com.edicsem.pe.sie.service.facade.DomicilioEmpleadoService;
import com.edicsem.pe.sie.service.facade.ZonificacionService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "reporteCobranzaCasa")
@SessionScoped
public class ReporteCobranzaCasaForm extends BaseMantenimientoAbstractAction{
	
	private List<DomicilioPersonaSie> lstDomicilio;
	public static Log log = LogFactory.getLog(ReporteCobranzaCasaForm.class);
	private String idDepartamento, idProvincia, idUbigeo;
	private String ContentType, mensaje;
	private int cantLista;
	private int radio;
	private List<String> letraList;
	private List<String> planoList;
	private List<String> sectorList;
	private Map<String, String> planoItems= new HashMap<String, String>();
	private Map<String, String> letraItems= new HashMap<String, String>();
	private Map<String, String> sectorItems= new HashMap<String, String>();
	
	@EJB
	private DomicilioEmpleadoService objDomicilioService;
	@EJB
	private ReporteExecutionService objReporteService;
	@EJB
	private ZonificacionService objZonificacionService;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;
	
	public ReporteCobranzaCasaForm(){
		
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		cantLista=0;
		idUbigeo="0";
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
		planoList = new ArrayList<String>();
		letraList = new ArrayList<String>();
		sectorList = new ArrayList<String>();
		lstDomicilio = new ArrayList<DomicilioPersonaSie>();
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
	
	public void buscarZonificacionXDistrito() {
		log.info("buscarZonificacionXDistrito() ");
		log.info("buscarZonificacionXDistrito() "+idUbigeo);
		 planoItems= new HashMap<String, String>();
		List<ZonificacionSie> lista = objZonificacionService.listarZonificacionXDistrito(idUbigeo);
		
		for (int i = 0; i < lista.size(); i++) {
			ZonificacionSie entidad = new ZonificacionSie();
			entidad = (ZonificacionSie) lista.get(i);
			planoItems.put(entidad.getCodplano(),entidad.getCodplano());
			comboManager.setPlanoItems(planoItems);
		}
		planoList= new ArrayList<String>();
		sectorList= new ArrayList<String>();
	}
	
	public void buscarZonificacionXPlano() {
		log.info("buscarZonificacionXPlano() ");
		letraItems= new HashMap<String, String>();
		sectorItems= new HashMap<String, String>();
		sectorList= new ArrayList<String>();
		log.info("buscarZonificacionXPlano() "+idUbigeo +" "+planoList.size()+" "+letraList.size());
		if(planoList.size()>0){
			List<ZonificacionSie> lista = objZonificacionService.listarZonificacionXPlano(idUbigeo,planoList);
			for (int i = 0; i < lista.size(); i++) {
				ZonificacionSie entidad = new ZonificacionSie();
				entidad = (ZonificacionSie) lista.get(i);
				letraItems.put(entidad.getCodletra(),entidad.getCodletra());
				comboManager.setLetraItems(letraItems);
			}
		}
		if(letraList.size()>0){
			List<ZonificacionSie> lista = objZonificacionService.listarZonificacionXPlanoXLetra(idUbigeo,planoList, letraList);
			for (int i = 0; i < lista.size(); i++) {
				ZonificacionSie entidad = new ZonificacionSie();
				entidad = (ZonificacionSie) lista.get(i);
				sectorItems.put(entidad.getCodsector(),entidad.getCodsector());
				comboManager.setSectorItems(sectorItems);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	public String consultar() {
		log.info("consultar() ");
		lstDomicilio = new ArrayList<DomicilioPersonaSie>();
		mensaje =null;
		try {
			lstDomicilio = objDomicilioService.listarClientesXZonificacion(idUbigeo, planoList, letraList, sectorList);
			cantLista = lstDomicilio.size();
			mensaje = "Consulta Realizada";
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.REPORTE_COBRANZA_CASA;
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

	/**
	 * @return the letraList
	 */
	public List<String> getLetraList() {
		return letraList;
	}

	/**
	 * @param letraList the letraList to set
	 */
	public void setLetraList(List<String> letraList) {
		this.letraList = letraList;
	}

	/**
	 * @return the planoList
	 */
	public List<String> getPlanoList() {
		return planoList;
	}

	/**
	 * @param planoList the planoList to set
	 */
	public void setPlanoList(List<String> planoList) {
		this.planoList = planoList;
	}

	/**
	 * @return the sectorList
	 */
	public List<String> getSectorList() {
		return sectorList;
	}

	/**
	 * @param sectorList the sectorList to set
	 */
	public void setSectorList(List<String> sectorList) {
		this.sectorList = sectorList;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	/**
	 * @return the lstDomicilio
	 */
	public List<DomicilioPersonaSie> getLstDomicilio() {
		return lstDomicilio;
	}

	/**
	 * @param lstDomicilio the lstDomicilio to set
	 */
	public void setLstDomicilio(List<DomicilioPersonaSie> lstDomicilio) {
		this.lstDomicilio = lstDomicilio;
	}
	
}
