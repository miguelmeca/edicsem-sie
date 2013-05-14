package com.edicsem.pe.sie.client.action.mantenimiento;

import java.math.BigDecimal;
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

import com.edicsem.pe.sie.entity.CajaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.CajaService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "cajaForm")
@SessionScoped
public class MantenimientoCajaFormAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private Log log = LogFactory.getLog(MantenimientoCajaFormAction.class);
	private CajaSie objCajaSie;
	private boolean newRecord = false;
	private int idEmpleado;
	private List<CajaSie> cajaList;
	private BigDecimal saldoTotalEmpleado;
	
	@ManagedProperty(value = "#{cajaSearch}")
	private MantenimientoCajaSearchAction cajaSearch;
	
	@EJB
	private CajaService objCajaService;
	@EJB
	private EmpleadoSieService objEmpleadoService;
	
	public MantenimientoCajaFormAction() {
		log.info("inicializando constructor 'MantenimientoCajaFormAction'");
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init()");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		objCajaSie = new CajaSie();
		objCajaSie.setMonto(new BigDecimal(1.0));
		setNewRecord(true);
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #update()
	 */
	public String update() throws Exception {
		log.info("update()" );
		setNewRecord(false);
		return cajaSearch.getViewMant();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #insertar()
	 */
	public String insertar() {
		log.info("Entering my method 'insertar()' " );
		mensaje=null;
		String paginaRetorno="";
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		BigDecimal saldoActual=new BigDecimal(0.0);
		try {
			if (isNewRecord()) {
				if(idEmpleado!=0){
					//Entrada
					objCajaSie.setIdtipo(1);
					List<CajaSie> lstCaja= objCajaService.listarCajaPorEmpleado(idEmpleado);
					if(lstCaja.size()>0){
						saldoActual =lstCaja.get(lstCaja.size()-1).getSaldo();
					}
					log.info("saldo actual "+saldoActual);
					if(saldoActual.doubleValue()>0.0){
						objCajaSie.setSaldo(objCajaSie.getMonto().add(saldoActual));
					}else{
						objCajaSie.setSaldo(saldoActual);
					}
					log.info("Monto + saldo "+objCajaSie.getMonto());
					paginaRetorno = cajaSearch.consultar();
				}else{
					//Salida
					objCajaSie.setIdtipo(2);
					idEmpleado= sessionUsuario.getIdempleado();
					//Buscar último saldo
					List<CajaSie> lstCaja= objCajaService.listarCajaPorEmpleado(idEmpleado);
					if(lstCaja.size()>0){
						saldoActual =lstCaja.get(lstCaja.size()-1).getSaldo();
					}
					BigDecimal resta =saldoActual.subtract(objCajaSie.getMonto());
					if(resta.doubleValue()<0.0){
						mensaje="Su saldo no es suficiente para registrar gastos : "+saldoActual.doubleValue();
					}else{
						objCajaSie.setSaldo(resta);
					}
					paginaRetorno = cajaSearch.listar();
				}
				log.info("Mensaje "+mensaje );
				if(mensaje==null){
					objCajaSie.setUsuariocreacion(sessionUsuario.getUsuario());
					objCajaService.insertCaja(objCajaSie, idEmpleado);
					mensaje =Constants.MESSAGE_REGISTRO_TITULO;
					objCajaSie = new CajaSie();
				}
			} else {
				objCajaService.updateCaja(objCajaSie);
				paginaRetorno = cajaSearch.listar();
				mensaje =Constants.MESSAGE_ACTUALIZO_TITULO;
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return paginaRetorno;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	public String consultar() throws Exception {
		log.info("Consultar()");
		cajaList = objCajaService.listarCajaPorEmpleado(idEmpleado);
		for (int i = 0; i < cajaList.size(); i++) {
			cajaList.get(i).setEmpleadocreacion(objEmpleadoService.buscarEmpleadosPorUsuario(cajaList.get(i).getUsuariocreacion()).getNombresCompletos());
			cajaList.set(i, cajaList.get(i));
		}
		if(cajaList.size()>0){
			saldoTotalEmpleado=cajaList.get(cajaList.size()-1).getSaldo();
		}
		return getViewList();
	}
	
	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord
	 *            the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
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
	 * @return the objCajaSie
	 */
	public CajaSie getObjCajaSie() {
		return objCajaSie;
	}

	/**
	 * @param objCajaSie the objCajaSie to set
	 */
	public void setObjCajaSie(CajaSie objCajaSie) {
		this.objCajaSie = objCajaSie;
	}

	/**
	 * @return the cajaSearch
	 */
	public MantenimientoCajaSearchAction getCajaSearch() {
		return cajaSearch;
	}

	/**
	 * @param cajaSearch the cajaSearch to set
	 */
	public void setCajaSearch(MantenimientoCajaSearchAction cajaSearch) {
		this.cajaSearch = cajaSearch;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public List<CajaSie> getCajaList() {
		return cajaList;
	}

	public void setCajaList(List<CajaSie> cajaList) {
		this.cajaList = cajaList;
	}

	public BigDecimal getSaldoTotalEmpleado() {
		return saldoTotalEmpleado;
	}

	public void setSaldoTotalEmpleado(BigDecimal saldoTotalEmpleado) {
		this.saldoTotalEmpleado = saldoTotalEmpleado;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MOV_CAJA_FORM_LIST_PAGE;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MOV_CAJA_FORM_LIST_PAGE;
	}
	
}
