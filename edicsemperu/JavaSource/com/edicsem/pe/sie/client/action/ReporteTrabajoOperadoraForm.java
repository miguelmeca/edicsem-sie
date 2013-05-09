package com.edicsem.pe.sie.client.action;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.ReporteParams;
import com.edicsem.pe.sie.beans.TrabajoOperadoraDTO;
import com.edicsem.pe.sie.client.report.service.ReporteExecutionService;
import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;
import com.edicsem.pe.sie.service.facade.CobranzaOperaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "reporteTrabajoOperadora")
@SessionScoped
public class ReporteTrabajoOperadoraForm extends BaseMantenimientoAbstractAction {

	private List<TrabajoOperadoraDTO> lstTrabajoOperadora;
	public static Log log = LogFactory.getLog(ReporteTrabajoOperadoraForm.class);
	private int cantLista;
	private int idTipoCliente;
	private String ContentType;
	private Date dhoy;
	
	@EJB
	private CobranzaOperaService objCobranzaOperaService;
	@EJB
	private ReporteExecutionService objReporteService;
	
	public ReporteTrabajoOperadoraForm() {
		log.info("INICIALIZANDO REPORTE");
		cantLista=0;
		idTipoCliente=0;
		ContentType="";
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar() :D ");
		lstTrabajoOperadora = new ArrayList<TrabajoOperadoraDTO>();
		List<CobranzaOperadoraSie> lstCobranzaOpera = new ArrayList<CobranzaOperadoraSie>();
		try {
			setDhoy(DateUtil.getToday().getTime());
			lstCobranzaOpera = objCobranzaOperaService.listarCobranzasOperaFechaActual(dhoy);
			int idempleado =0;
			TrabajoOperadoraDTO obj=null;
			for (int i = 0; i < lstCobranzaOpera.size(); i++) {
				if( i == 0){
					idempleado = lstCobranzaOpera.get(i).getTbEmpleado().getIdempleado();
					obj=new TrabajoOperadoraDTO();
					obj.setCantidadPostergada(0);
					obj.setCantidadRealizada(0);
					obj.setCantPromesaPago(0);
					obj.setCantidaTotal(0);
					obj.setCantOtraFecha(0);
				}else{
					if(idempleado!= lstCobranzaOpera.get(i).getTbEmpleado().getIdempleado()){
						lstTrabajoOperadora.add(obj);
						idempleado = lstCobranzaOpera.get(i).getTbEmpleado().getIdempleado();
						obj=new TrabajoOperadoraDTO();
						obj.setCantidadPostergada(0);
						obj.setCantidadRealizada(0);
						obj.setCantPromesaPago(0);
						obj.setCantidaTotal(0);
						obj.setCantOtraFecha(0);
					}
				}
					obj.setNombreCompleto(lstCobranzaOpera.get(i).getTbEmpleado().getNombresCompletos());
					obj.setIdEmpleado(lstCobranzaOpera.get(i).getTbEmpleado().getIdempleado());
					//Si paga fecha diferente a la fecha de expiracion
					if(lstCobranzaOpera.get(i).getFechapromesapago()!=null){
						if( lstCobranzaOpera.get(i).getFechacreacion().before(lstCobranzaOpera.get(i).getFechapromesapago())&&
							lstCobranzaOpera.get(i).getFechaexpira().after(lstCobranzaOpera.get(i).getFechapromesapago())||
							lstCobranzaOpera.get(i).getFechacreacion().equals(lstCobranzaOpera.get(i).getFechapromesapago())&&
							lstCobranzaOpera.get(i).getFechaexpira().equals(lstCobranzaOpera.get(i).getFechapromesapago())){
							obj.setCantPromesaPago(obj.getCantPromesaPago()+1);
						}else{
							obj.setCantOtraFecha(obj.getCantOtraFecha()+1);
						}
					}
					if(lstCobranzaOpera.get(i).getTbEstadoGeneral().getIdestadogeneral()==109){
						obj.setCantidadPostergada(obj.getCantidadPostergada()+1);
						obj.setCantidaTotal(obj.getCantidaTotal()+1);
					}
					if(lstCobranzaOpera.get(i).getTbEstadoGeneral().getIdestadogeneral()==108){
						obj.setCantidaTotal(obj.getCantidaTotal()+1);
					}
					if(lstCobranzaOpera.get(i).getTbEstadoGeneral().getIdestadogeneral()==110){
						obj.setCantidaTotal(obj.getCantidaTotal()+1);
					}
					if(lstCobranzaOpera.get(i).getFechamodifica() != null){
						obj.setCantidadRealizada(obj.getCantidadRealizada()+1);
						if(obj.getUltimaActualizacion()==null){
							obj.setUltimaActualizacion(new Time(lstCobranzaOpera.get(i).getFechamodifica().getTime()));
						}else if(new Time(lstCobranzaOpera.get(i).getFechamodifica().getTime()).after(obj.getUltimaActualizacion())){
							obj.setUltimaActualizacion(new Time(lstCobranzaOpera.get(i).getFechamodifica().getTime()));
						}
					}
					if(lstCobranzaOpera.size()-1==i){
						lstTrabajoOperadora.add(obj);
					}
				}
			cantLista=lstTrabajoOperadora.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.REPORTE_LLAMADA_OPERADORA;
	}
	
	public void ReportingClientes() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		ReporteParams parametros = new ReporteParams();
		
		try {
			parametros.setJasperFileName(Constants.REPORTE_CLIENTE_JASPER);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			Map criteria = new HashMap();
			criteria.put(Constants.REPORTE_TITULO, Constants.REPORTE_CLIENTE_LIST+"_"+ sdf.format(new Date(System.currentTimeMillis())));
			criteria.put(Constants.REPORTE_TIPO_CLIENTE, idTipoCliente );
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
	 * @return the idTipoCliente
	 */
	public int getIdTipoCliente() {
		return idTipoCliente;
	}

	/**
	 * @param idTipoCliente the idTipoCliente to set
	 */
	public void setIdTipoCliente(int idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
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
	 * @return the dhoy
	 */
	public Date getDhoy() {
		return dhoy;
	}

	/**
	 * @param dhoy the dhoy to set
	 */
	public void setDhoy(Date dhoy) {
		this.dhoy = dhoy;
	}

	/**
	 * @return the lstTrabajoOperadora
	 */
	public List<TrabajoOperadoraDTO> getLstTrabajoOperadora() {
		return lstTrabajoOperadora;
	}

	/**
	 * @param lstTrabajoOperadora the lstTrabajoOperadora to set
	 */
	public void setLstTrabajoOperadora(List<TrabajoOperadoraDTO> lstTrabajoOperadora) {
		this.lstTrabajoOperadora = lstTrabajoOperadora;
	}
	
}
