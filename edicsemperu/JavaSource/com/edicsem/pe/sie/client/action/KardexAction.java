package com.edicsem.pe.sie.client.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.KardexService;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "kardexAction")
@SessionScoped
public class KardexAction extends BaseMantenimientoAbstractAction {
	public static Log log = LogFactory.getLog(KardexAction.class);

	private List<KardexSie> kardexList;
	private KardexSie objKardexSie;
	private String mensaje;
	private int tipoProducto, stockActual;
	private int idproducto, idalmacen, idempresa=0;
	private Date fechaDesde, fechaHasta;
	private List<KardexSie> listadoKardex;
	private boolean editMode, newRecord;

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
		idempresa=0;
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
		limpiarCampos();
		objKardexSie = new KardexSie();
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
		stockActual=0;
		log.info("entreando a consultar()");
		
		log.info(" dentro de getKardexList  " + getTipoProducto() + ""
				+ getIdproducto() + "" + getIdalmacen());

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaD = "", fechaH = "";
			if (fechaDesde == null)
				fechaD = "";
			if (fechaDesde == null)
				fechaH = "";
			else {
				fechaD = "" + sdf.format(fechaDesde);
				fechaH = "" + sdf.format(fechaHasta);
			}
			kardexList = objKardexService.ConsultaProductos(getIdproducto(),
					getIdalmacen(), fechaD, fechaH);
			if(kardexList.size()==0){
				kardexList = new ArrayList<KardexSie>();
				stockActual=0;
			}else{ 
				
			log.info("cantidad existente :D "+ kardexList.get(kardexList.size() - 1).getCantexistencia());
			stockActual = kardexList.get(kardexList.size() - 1).getCantexistencia();
			log.info("nuevo stock actual " + getStockActual());
			setMensaje(" consulta realizada ");
			}
		return getViewList();
	}
	

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	
	public String update() throws Exception {
		log.info("update a() b c "+ objKardexSie.getIdkardex() );  
		//objKardexSie = objKardexService.findKardex(objKardexSie.getIdkardex());
//		
//		if(objKardexSie.getTbEmpresa().getIdempresa()==null){
//			log.info(" es null");
//			idempresa=0;
//		}else{
//			log.info(" NO es null");
//			idempresa = objKardexSie.getTbEmpresa().getIdempresa();
//		}
		idempresa=0;
		log.info(" empresa " + idempresa);
		setNewRecord(false);
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	
	public String insertar() throws Exception {
		log.info(" insertar() a  " +idempresa);
		if(idempresa!=0){
			objKardexSie.setTbEmpresa(objEmpresaService.findEmpresa(idempresa));
		}
		log.info(" insertar() "+  objKardexSie.getTbEmpresa().getIdempresa());
		objKardexService.updateKardex(objKardexSie);
		limpiarCampos();
		return listar();
	}
	
	public void limpiarCampos(){
		idempresa=0;
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
	
}
