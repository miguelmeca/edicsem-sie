package com.edicsem.pe.sie.client.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;  
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;  
import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.KardexService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService; 
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean
@SessionScoped
public class MantenimientoKardex extends BaseMantenimientoAbstractAction {
	public static Log log = LogFactory.getLog(MantenimientoKardex.class);

	private List<SelectItem> selectItems, productosItems;
	private DataModel<KardexSie> kardexmodel;
	private KardexSie objKardexSie;
	private String mensaje;
	private int almacen;
	private int idproducto, idalmacen, tipoProducto;
	private Date fechaDesde, fechaHasta;
	private List<KardexSie> listadoKardex;
	private List<SelectItem> selectItems2;
	@EJB
	private TipoProductoService objTipoProductoService;
	


	private ArrayList<SelectItem> productositems;

	@EJB
	private ProductoService objProductoService;
	@EJB
	private AlmacenService objAlmacenService;
	@EJB
	private KardexService objKardexService;

	public MantenimientoKardex() {
		log.info("inicializando constructor MantenimientoKardex");
		init();
	}

	public void init() {
		log.info("init()");
		selectItems2 = new ArrayList<SelectItem>();
		selectItems = new ArrayList<SelectItem>();
		objKardexSie = new KardexSie();
		tipoProducto = -1;
	}

	/**
	 * @return the selectItems2
	 */
	public List<SelectItem> getSelectItems2() {
		selectItems2 = new ArrayList<SelectItem>();
		List lista = new ArrayList<TipoProductoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getListaTipoProducto()'");
			}
			lista = objTipoProductoService.listarTipo();

			for (int i = 0; i < lista.size(); i++) {
				TipoProductoSie tipo = new TipoProductoSie();
					tipo = (TipoProductoSie) lista.get(i);
					selectItems2.add(new SelectItem(tipo.getIdtipoproducto(),
							tipo.getNombretipoproducto()));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return selectItems2;
	}

	/**
	 * @param selectItems2 the selectItems2 to set
	 */
	public void setSelectItems2(List<SelectItem> selectItems2) {
		this.selectItems2 = selectItems2;
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
	 * @return the productosItems
	 */
	public List<SelectItem> getProductosItems() {
		return productosItems;
	}

	/**
	 * @param productosItems
	 *            the productosItems to set
	 */
	public void setProductosItems(List<SelectItem> productosItems) {
		this.productosItems = productosItems;
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
	 * @return the kardexmodel
	 */
	
	public DataModel<KardexSie> getKardexmodel() throws Exception {
		String fechaDesde = "";
		String fechaHasta = "";
		kardexmodel = new ListDataModel<KardexSie>(
				objKardexService.ConsultaProductos(getIdproducto(),
						getIdalmacen(), fechaDesde, fechaHasta));
		log.info(" DataModel Kardex " + kardexmodel.getRowCount());
		return kardexmodel;
	}

	/**
	 * @return the productositems
	 */
	public ArrayList<SelectItem> getProductositems() throws Exception {
		log.info("tipo --> " + getTipoProducto());
		productositems = new ArrayList<SelectItem>();
	 
			List listaP = new ArrayList<ProductoSie>();
			listaP = (List<ProductoSie>) objProductoService
					.listarProductosXTipo(tipoProducto);
			log.info("tamaño productos X tipo --> " + listaP.size());
			for (int i = 0; i < listaP.size(); i++) {
				ProductoSie producto = new ProductoSie();
				producto = (ProductoSie) listaP.get(i);
				productositems.add(new SelectItem(producto.getIdproducto(),
						producto.getDescripcionproducto()));
			}
		return productositems;
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
	 * @param productositems
	 *            the productositems to set
	 */
	public void setProductositems(ArrayList<SelectItem> productositems) {
		this.productositems = productositems;
	}

	/**
	 * @param kardexmodel
	 *            the kardexmodel to set
	 */
	public void setKardexmodel(DataModel<KardexSie> kardexmodel) {
		this.kardexmodel = kardexmodel;
	}

	/**
	 * @return the almacen
	 */
	public int getAlmacen() {
		return almacen;
	}

	/**
	 * @param almacen
	 *            the almacen to set
	 */
	public void setAlmacen(int almacen) {
		this.almacen = almacen;
	}

	public List<SelectItem> getSelectItems() {
		List lista = new ArrayList<PuntoVentaSie>();
		selectItems = new ArrayList<SelectItem>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getListarAlmacenes()'");
			}
			lista = objAlmacenService.listarAlmacenes();
			log.info(" tamaño " + lista.size());
			PuntoVentaSie punto;
			for (int i = 0; i < lista.size(); i++) {
				punto = new PuntoVentaSie();
				if (lista.get(i) != null) {
					punto = (PuntoVentaSie) lista.get(i);
					selectItems.add(new SelectItem(punto.getIdpuntoventa(),
							punto.getDescripcion()));
				} else {
					break;
				}
			}
			log.info("finalizacion del metodo 'getSelectItems' de Puntos de Venta");
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return selectItems;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #consultar()
	 */
	@Override
	public String consultar() throws Exception {

		List<KardexSie> lista = new ArrayList<KardexSie>();
		for (KardexSie c : getKardexmodel()) {
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
	@Override
	public String getViewList() {

		return "mantenimientoKardex";
	}

	/**
	 * @param selectItems
	 *            the selectItems to set
	 */
	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
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

}
