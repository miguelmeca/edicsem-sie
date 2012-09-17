package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.AlmacenService; 
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "comboAction")
@SessionScoped
public class ComboAction extends BaseMantenimientoAbstractAction {
	
	public static Log log = LogFactory.getLog(ComboAction.class);
	private String mensaje;
	   
	private Map<String , Integer> tipoitems =  new HashMap<String, Integer>();
	private Map<String , Integer> productositems =  new HashMap<String, Integer>();
	 
	private Map<String , Integer> almacenItems =  new HashMap<String, Integer>();
	private int tipoProducto;
	 
    private Map<String,Map<String,Integer>> dataProducto = new HashMap<String, Map<String,Integer>>();  
   
	@EJB
	private AlmacenService objAlmacenService;
	@EJB
	private TipoProductoService objTipoProductoService;

	@EJB
	private ProductoService objProductoService;
 

	public ComboAction() {
		log.info("inicializando constructor");
		init();
	}

	public void init() {
		log.info("init()");
		tipoitems =  new HashMap<String, Integer>(); 
		almacenItems = new HashMap<String, Integer>(); 
	}
	 public void cambiar() {  
	        if( tipoProducto!=-1)  
	        	productositems = dataProducto.get(tipoProducto);  
	        else  
	        	productositems = new HashMap<String, Integer>();  
	    }  
	 
	 /**
	 * @param productositems the productositems to set
	 */
	public void setProductositems(Map<String, Integer> productositems) {
		this.productositems = productositems;
	}

	/**
	 * @return the productositems
	 */
	public Map<String, Integer> getProductositems() {
  		log.info("tipo --> " + getTipoProducto());
			productositems = new HashMap<String, Integer>(); 
		 
				List listaP = new ArrayList<ProductoSie>();
				listaP = (List<ProductoSie>) objProductoService
						.listarProductosXTipo(tipoProducto);
				log.info("tamaño productos X tipo --> " + listaP.size());
				for (int i = 0; i < listaP.size(); i++) {
					ProductoSie producto = new ProductoSie();
					producto = (ProductoSie) listaP.get(i);
					productositems.put(producto.getDescripcionproducto(),producto.getIdproducto());
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
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(int tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
 
 
 
	/**
	 * @return the almacenItems
	 */
	public Map<String, Integer> getAlmacenItems() {
		List lista = new ArrayList<PuntoVentaSie>();
		almacenItems = new HashMap<String, Integer>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getAlmacenItems()'");
			}
			lista = objAlmacenService.listarAlmacenes();
			log.info(" tamaño " + lista.size());
			PuntoVentaSie punto;
			for (int i = 0; i < lista.size(); i++) {
				punto = new PuntoVentaSie();
				if (lista.get(i) != null) {
					punto = (PuntoVentaSie) lista.get(i);
					almacenItems.put(punto.getDescripcion(), punto.getIdpuntoventa()
							);
				} else {
					break;
				}
			}
			log.info("finalizacion del metodo 'getAlmacenItems'  ");
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return almacenItems; 
	}

	/**
	 * @param almacenItems the almacenItems to set
	 */
	public void setAlmacenItems(Map<String, Integer> almacenItems) {
		this.almacenItems = almacenItems;
	}

	/**
	 * @return the tipoitems
	 */
	public Map<String, Integer> getTipoitems() {
		tipoitems =  new HashMap<String, Integer>();
		List lista = new ArrayList<TipoProductoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoitems()'");
			}
			lista = objTipoProductoService.listarTipo();

			for (int i = 0; i < lista.size(); i++) {
				TipoProductoSie tipo = new TipoProductoSie();
					tipo = (TipoProductoSie) lista.get(i);
					tipoitems.put(tipo.getNombretipoproducto(), tipo.getIdtipoproducto());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tipoitems;
	}

	/**
	 * @param tipoitems the tipoitems to set
	 */
	public void setTipoitems(Map<String, Integer> tipoitems) {
		this.tipoitems = tipoitems;
	} 
}
