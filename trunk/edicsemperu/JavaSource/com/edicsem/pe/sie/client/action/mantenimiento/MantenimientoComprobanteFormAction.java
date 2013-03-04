package com.edicsem.pe.sie.client.action.mantenimiento;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.edicsem.pe.sie.entity.ComprobanteSie;
import com.edicsem.pe.sie.entity.DetalleComprobanteSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.ComprobanteService;
import com.edicsem.pe.sie.service.facade.DetalleComprobanteService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "comprobanteForm")
@SessionScoped
public class MantenimientoComprobanteFormAction extends
		BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoComprobanteFormAction.class);
	
	public int idalmacen;
	public String descripcion, direccion,ubigeoDefecto,idUbigeo;
	private String idProvincia, idDepartamento, idDistrito;
	private int ide, idEstadoGeneral;
	private boolean editMode, puntoVenta,defectoUbigeo;
	private boolean newRecord = false;
	private int estado;
	private ComprobanteSie objComprobanteSie;
	private List<DetalleComprobanteSie> detComplist;
	private double montoTotal;

	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManagerPunto;

	@ManagedProperty(value = "#{almacenSearch}")
	private MantenimientoPuntoAlmacenSearchAction mantenimientoPuntoAlmacenSearch;

	@EJB
	private ComprobanteService objComprobanteService;
	
	@EJB
	private DetalleComprobanteService objDetComprobanteService;
	
	@EJB
	private EstadogeneralService objEstadoGeneralService;

	public MantenimientoComprobanteFormAction() {
		init();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #init()
	 */
	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoPuntoAlmacenFormAction'");
		objComprobanteSie = new ComprobanteSie();
		puntoVenta = false;
		defectoUbigeo=true;
		montoTotal=0.0;
		detComplist = new ArrayList<DetalleComprobanteSie>();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		defectoUbigeo=true;
		editMode = true;
		puntoVenta = false;
		setNewRecord(true);
		objComprobanteSie = new ComprobanteSie();
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		montoTotal=0.0;
		log.info("update() "+ objComprobanteSie.getIdcomprobante() );
		setObjComprobanteSie(objComprobanteService.findComprobante(objComprobanteSie.getIdcomprobante()));
		log.info("  " +objComprobanteSie.getCodcomprobante());
		detComplist = objDetComprobanteService.listarDetComprobantes(objComprobanteSie.getIdcomprobante()) ;
		for (int i = 0; i < detComplist.size(); i++) {
			log.info("  "+detComplist.get(i).getTbKardex().getValortotal());
			montoTotal+= Double.parseDouble(detComplist.get(i).getTbKardex().getValortotal());
		}
		
		setNewRecord(false);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() {
		log.info("insertar()" );
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		try {
			objComprobanteSie.setUsuariomodifica(sessionUsuario.getUsuario());
			objComprobanteSie.setFechamodifica(new Timestamp(DateUtil.getToday().getTime().getTime()));
			objComprobanteService.updateComprobante(objComprobanteSie);
			log.info("actualizado");
			
		} catch (Exception e) {

			e.printStackTrace();
			descripcion = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		objComprobanteSie = new ComprobanteSie();
		return mantenimientoPuntoAlmacenSearch.listar();
	}

	public void cambiar() {
		comboManagerPunto.setIdDepartamento(getIdDepartamento());
		comboManagerPunto.setIdProvincia(null);
		idProvincia = null;
		idUbigeo = null;
		log.info("cambiar   :D  --- ");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_COMPROBANTE_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_COMPROBANTE_FORM_PAGE;
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
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
 

	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}

	/**
	 * @param idEstadoGeneral
	 *            the idEstadoGeneral to set
	 */
	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * @return the ide
	 */
	public int getIde() {
		return ide;
	}

	/**
	 * @param ide
	 *            the ide to set
	 */
	public void setIde(int ide) {
		this.ide = ide;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public MantenimientoPuntoAlmacenSearchAction getMantenimientoPuntoAlmacenSearch() {
		return mantenimientoPuntoAlmacenSearch;
	}

	public void setMantenimientoPuntoAlmacenSearch(
			MantenimientoPuntoAlmacenSearchAction mantenimientoPuntoAlmacenSearch) {
		this.mantenimientoPuntoAlmacenSearch = mantenimientoPuntoAlmacenSearch;
	}

	public ComboAction getComboManagerPunto() {
		return comboManagerPunto;
	}

	public void setComboManagerPunto(ComboAction comboManagerPunto) {
		comboManagerPunto.setCodigoEstado(Constants.COD_ESTADO_TB_PUNTO_ALMACEN);
		log.info("aqui--->>" + Constants.COD_ESTADO_TB_PUNTO_ALMACEN);
		this.comboManagerPunto = comboManagerPunto;
	}

	public int getIdalmacen() {
		return idalmacen;
	}

	public void setIdalmacen(int idalmacen) {
		this.idalmacen = idalmacen;
	}
	
	/**
	 * @return the puntoVenta
	 */
	public boolean isPuntoVenta() {
		return puntoVenta;
	}

	/**
	 * @param puntoVenta the puntoVenta to set
	 */
	public void setPuntoVenta(boolean puntoVenta) {
		this.puntoVenta = puntoVenta;
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
	 * @return the ubigeoDefecto
	 */
	public String getUbigeoDefecto() {
		return ubigeoDefecto;
	}

	/**
	 * @param ubigeoDefecto the ubigeoDefecto to set
	 */
	public void setUbigeoDefecto(String ubigeoDefecto) {
		this.ubigeoDefecto = ubigeoDefecto;
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
	 * @return the defectoUbigeo
	 */
	public boolean isDefectoUbigeo() {
		return defectoUbigeo;
	}

	/**
	 * @param defectoUbigeo the defectoUbigeo to set
	 */
	public void setDefectoUbigeo(boolean defectoUbigeo) {
		this.defectoUbigeo = defectoUbigeo;
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
	 * @return the idDistrito
	 */
	public String getIdDistrito() {
		return idDistrito;
	}

	/**
	 * @param idDistrito the idDistrito to set
	 */
	public void setIdDistrito(String idDistrito) {
		this.idDistrito = idDistrito;
	}

	/**
	 * @return the objComprobanteSie
	 */
	public ComprobanteSie getObjComprobanteSie() {
		return objComprobanteSie;
	}

	/**
	 * @param objComprobanteSie the objComprobanteSie to set
	 */
	public void setObjComprobanteSie(ComprobanteSie objComprobanteSie) {
		this.objComprobanteSie = objComprobanteSie;
	}

	/**
	 * @return the detComplist
	 */
	public List<DetalleComprobanteSie> getDetComplist() {
		return detComplist;
	}

	/**
	 * @param detComplist the detComplist to set
	 */
	public void setDetComplist(List<DetalleComprobanteSie> detComplist) {
		this.detComplist = detComplist;
	}

	/**
	 * @return the montoTotal
	 */
	public double getMontoTotal() {
		return montoTotal;
	}

	/**
	 * @param montoTotal the montoTotal to set
	 */
	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

}
