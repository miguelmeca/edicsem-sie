package com.edicsem.pe.sie.client.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;   
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;  
import com.edicsem.pe.sie.entity.KardexSie; 
import com.edicsem.pe.sie.service.facade.KardexService; 
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
@ManagedBean( name="kardexSieAction" )
@SessionScoped
public class KardexSieAction extends BaseMantenimientoAbstractAction {
	public static Log log = LogFactory.getLog(KardexSieAction.class);
  
	private List<KardexSie> kardexList;
	private KardexSie objKardexSie;
	private String mensaje;
	private int tipoProducto, stockActual;
	private int idproducto, idalmacen;
	private Date fechaDesde, fechaHasta;
	private List<KardexSie> listadoKardex;
	private boolean editMode;
 
	@EJB
	private KardexService objKardexService;

	public KardexSieAction() {
		log.info("inicializando constructor MantenimientoKardex");
		init();
	}

	public void init() {
		log.info("init()");
		objKardexSie = new KardexSie();
		stockActual=0;
	}
	
	/**
	 * @return the kardexList
	 */
	@SuppressWarnings("unchecked")
	public List<KardexSie> getKardexList() {
		String fechaDesde = "";
		String fechaHasta = "";
		kardexList = objKardexService.ConsultaProductos(getIdproducto(),
						getIdalmacen(), fechaDesde, fechaHasta);
		log.info(" DataModel Kardex " + kardexList.size());
		log.info("cantidad existente :D "  +kardexList.get(kardexList.size()-1).getCantexistencia());
		stockActual=kardexList.get(kardexList.size()-1).getCantexistencia();
		//setStockActual(kardexList.get(kardexList.size()).getCantexistencia());
		log.info("nuevo stock actual "+ getStockActual() );
		return kardexList;
	}

	/**
	 * @param kardexList the kardexList to set
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
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(int tipoProducto) {
		this.tipoProducto = tipoProducto;
	} 


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #consultar()
	 */
	public String consultar() throws Exception {

		List<KardexSie> lista = new ArrayList<KardexSie>();
		for (KardexSie c : getKardexList()) {
			lista.add(c);
		}
		setListadoKardex(lista);
		return getViewList();
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
	 * @param editMode the editMode to set
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
	 * @param stockActual the stockActual to set
	 */
	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

}
