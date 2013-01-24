package com.edicsem.pe.sie.client.action;

import java.text.SimpleDateFormat;
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

import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.KardexService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "kardexAction")
@SessionScoped
public class KardexAction extends BaseMantenimientoAbstractAction {
	private Log log = LogFactory.getLog(KardexAction.class);
	private List<KardexSie> kardexList;
	private KardexSie objKardexSie;
	private String mensaje,idtipoAlmacen;
	private int tipoProducto, stockActual,idtipopuntoventa;
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
		comboManagerPunto.setTipoAlmacen(idtipopuntoventa);
		this.idtipopuntoventa = idtipopuntoventa;
	}

	private String valorActual;
	private int idproducto, idalmacen, idempresa=0;
	private Date fechaDesde, fechaHasta;
	private List<KardexSie> listadoKardex;
	private boolean editMode, newRecord;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManagerPunto;
	
	@EJB
	private KardexService objKardexService;
	@EJB
	private EmpresaService objEmpresaService;
	
	public KardexAction() {
		log.info("inicializando constructor MantenimientoKardex");
		init();
	}

	public void init() {
		log.info("init()");
		objKardexSie = new KardexSie();
		stockActual = 0;
		valorActual ="";
		idempresa=0;
		kardexList = new ArrayList<KardexSie>();
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
		comboManagerPunto.setTipoProducto(0);
		tipoProducto=0;
		idempresa=0;
		idalmacen=0;
		idproducto=0;
		idtipopuntoventa=0;
		fechaDesde=null;
		fechaHasta=null;
		objKardexSie = new KardexSie();
		kardexList = new ArrayList<KardexSie>();
		setNewRecord(true);
		return getViewList();
	}
	
	/**
	 * @return the kardexList
	 */
	public List<KardexSie> getKardexList() {
		
		return kardexList;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #consultar()
	 */
	public String consultar() throws Exception {
		mensaje =null;
		stockActual=0;
		kardexList = new ArrayList<KardexSie>();
		log.info("entreando a consultar() x");
		
		log.info(" dentro de getKardexList  " + getTipoProducto() + " " + getIdproducto() + "" + getIdalmacen());
		log.info(" f  "+fechaDesde +" "+fechaHasta);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaD = "", fechaH = "";
			log.info((fechaDesde == null )+"  - "+(fechaHasta != null));
			log.info((fechaDesde == null && fechaHasta != null));
			if (fechaDesde == null && fechaHasta != null){
				log.info(" f  ");
				mensaje ="Por favor ingresar una fecha de inicio ";
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
			if (fechaDesde != null && fechaHasta == null){
				//la fecha hasta será la actual
				fechaHasta= DateUtil.getToday().getTime();
			}
			if (fechaDesde == null){
				fechaD = "";
			}if (fechaHasta == null){
				fechaH = "";
			}else if (fechaDesde != null && fechaHasta != null){
				fechaD = "" + sdf.format(fechaDesde);
				fechaH = "" + sdf.format(fechaHasta);
			}log.info("con ");
			kardexList = objKardexService.ConsultaProductos(getIdproducto(), getIdalmacen(), fechaD, fechaH);

			if(kardexList.size()==0){
				kardexList = new ArrayList<KardexSie>();
				stockActual=0;
				setMensaje(" consulta realizada ");
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{ 
				
				log.info("cantidad existente :D "+ kardexList.get(kardexList.size() - 1).getCantexistencia());
				stockActual = kardexList.get(kardexList.size() - 1).getCantexistencia();
				valorActual = kardexList.get(kardexList.size() - 1).getValorunitarioexistencia();
				log.info("nuevo stock actual " + getStockActual());
				setMensaje(" consulta realizada ");
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	
	public String update() throws Exception {
		log.info("update ()"+ objKardexSie.getIdkardex() );  
		idempresa=0;
		log.info(" empresa " + idempresa);
		setNewRecord(false);
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	
	public String insertar() throws Exception {
		log.info(" insertar() " +idempresa);
		if(idempresa!=0){
			objKardexSie.setTbEmpresa(objEmpresaService.findEmpresa(idempresa));
		}
	
		objKardexService.updateKardex(objKardexSie);
		mensaje="Se actualizó correctamente el movimiento a la empresa "+ objKardexSie.getTbEmpresa().getDescripcion();
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		idempresa=0;
		return consultar();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */

	public String getViewList() {

		return "mantenimientoKardex";
	}

	/**
	 * @return the idproducto
	 */
	public int getIdproducto() {
		return idproducto;
	}

	/**
	 * @param idproducto
	 *            the idproducto to set
	 */
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	/**
	 * @return the idalmacen
	 */
	public int getIdalmacen() {
		return idalmacen;
	}

	/**
	 * @param idalmacen
	 *            the idalmacen to set
	 */
	public void setIdalmacen(int idalmacen) {
		this.idalmacen = idalmacen;
	}

	/**
	 * @return the fechaDesde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde
	 *            the fechaDesde to set
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
	 * @param fechaHasta
	 *            the fechaHasta to set
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @return the listadoKardex
	 */
	public List<KardexSie> getListadoKardex() {
		return listadoKardex;
	}

	/**
	 * @param listadoKardex
	 *            the listadoKardex to set
	 */
	public void setListadoKardex(List<KardexSie> listadoKardex) {
		this.listadoKardex = listadoKardex;
	}

	/**
	 * @return the objKardexSie
	 */
	public KardexSie getObjKardexSie() {
		return objKardexSie;
	}

	/**
	 * @param objKardexSie
	 *            the objKardexSie to set
	 */
	public void setObjKardexSie(KardexSie objKardexSie) {
		this.objKardexSie = objKardexSie;
	}

	/**
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * @return the stockActual
	 */
	public int getStockActual() {
		return stockActual;
	}

	/**
	 * @param stockActual
	 *            the stockActual to set
	 */
	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

	/**
	 * @param kardexList
	 *            the kardexList to set
	 */
	public void setKardexList(List<KardexSie> kardexList) {
		this.kardexList = kardexList;
	}

	/**
	 * @return the tipoProducto
	 */
	public int getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * @param tipoProducto
	 *            the tipoProducto to set
	 */
	public void setTipoProducto(int tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	/**
	 * @return the idempresa
	 */
	public int getIdempresa() {
		return idempresa;
	}

	/**
	 * @param idempresa the idempresa to set
	 */
	public void setIdempresa(int idempresa) {
		this.idempresa = idempresa;
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
	 * @return the idtipoAlmacen
	 */
	public String getIdtipoAlmacen() {
		return idtipoAlmacen;
	}

	/**
	 * @param idtipoAlmacen the idtipoAlmacen to set
	 */
	public void setIdtipoAlmacen(String idtipoAlmacen) {
		this.idtipoAlmacen = idtipoAlmacen;
	}

	/**
	 * @return the valorActual
	 */
	public String getValorActual() {
		return valorActual;
	}

	/**
	 * @param valorActual the valorActual to set
	 */
	public void setValorActual(String valorActual) {
		this.valorActual = valorActual;
	}

	public ComboAction getComboManagerPunto() {
		return comboManagerPunto;
	}

	public void setComboManagerPunto(ComboAction comboManagerPunto) {
		this.comboManagerPunto = comboManagerPunto;
	}

}
