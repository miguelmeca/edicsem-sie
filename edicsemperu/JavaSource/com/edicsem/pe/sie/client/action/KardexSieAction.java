package com.edicsem.pe.sie.client.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
import com.edicsem.pe.sie.service.facade.impl.TipoProductoServiceImpl;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
@ManagedBean( name="kardexSieAction" )
@SessionScoped
public class KardexSieAction extends BaseMantenimientoAbstractAction {
	public static Log log = LogFactory.getLog(KardexSieAction.class);
 
	private ArrayList<SelectItem> productositems;
	private DataModel<KardexSie> kardexmodel;
	private KardexSie objKardexSie;
	private String mensaje;
	private int tipoProducto;
	private int idproducto, idalmacen;
	private Date fechaDesde, fechaHasta;
	private List<KardexSie> listadoKardex;
	private Map<String,String> tipoProd ;
  
	@EJB
	private ProductoService objProductoService;
 
	@EJB
	private KardexService objKardexService;

	public KardexSieAction() {
		log.info("inicializando constructor MantenimientoKardex");
		init();
	}

	public void init() {
		log.info("init()");
		objKardexSie = new KardexSie();
		productositems= new ArrayList<SelectItem>();
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
	public void consultar() throws Exception {

		List<KardexSie> lista = new ArrayList<KardexSie>();
		for (KardexSie c : getKardexmodel()) {
			lista.add(c);
		}
		setListadoKardex(lista);
		//return getViewList();
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
