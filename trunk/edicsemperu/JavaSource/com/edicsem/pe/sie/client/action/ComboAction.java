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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.edicsem.pe.sie.entity.CargoEmpleadoSie;

import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.AlmacenService; 
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.util.constants.Constants;

@ManagedBean(name = "comboAction")
@SessionScoped
public class ComboAction {
	
	private static Log log = LogFactory.getLog(ComboAction.class);
	private static FacesMessage msg = null;
	private String mensaje;
	private String codigoEstado;
	
	private Map<String , Integer> tipoitems =  new HashMap<String, Integer>();
	private Map<String , Integer> productositems =  new HashMap<String, Integer>();
	private Map<String , Integer> almacenItems =  new HashMap<String, Integer>();
	private Map<String , Integer> tipoDocumentoItems =  new HashMap<String, Integer>();
	private Map<String , Integer> cargoEmpleadoItems =  new HashMap<String, Integer>();
	private int tipoProducto;
    private Map<String,Map<String,Integer>> dataProducto = new HashMap<String, Map<String,Integer>>();  
    private Map<String , Integer> estadoitems =  new HashMap<String, Integer>();
    private Map<String , Integer> empresaitems =  new HashMap<String, Integer>();
    
	@EJB
	private AlmacenService objAlmacenService;
	@EJB
	private TipoProductoService objTipoProductoService;
	@EJB
	private ProductoService objProductoService;
	@EJB 
	private EstadogeneralService objEstadoGeneralService;
	@EJB 
	private TipoDocumentoService objTipoDocumentoService;
	@EJB 
	private CargoEmpleadoService objCargoEmpleadoService;
	
	@EJB 
	private EmpresaService objEmpresaService;

	public ComboAction() {
		log.info("inicializando constructor");
		init();
	}

	public void init() {
		log.info("init()");
		tipoitems =  new HashMap<String, Integer>(); 
		almacenItems = new HashMap<String, Integer>();
		tipoDocumentoItems =  new HashMap<String, Integer>(); 
		cargoEmpleadoItems = new HashMap<String, Integer>();
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
	

	/**
	 * @return the estadoitems
	 */
	public Map<String, Integer> getEstadoitems() {
		List lista = new ArrayList<TipoProductoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getEstadoitems()'");
			}
			lista = objEstadoGeneralService.listarEstados(this.getCodigoEstado());

			for (int i = 0; i < lista.size(); i++) {
				EstadoGeneralSie entidad = new EstadoGeneralSie();
				entidad = (EstadoGeneralSie) lista.get(i);
					estadoitems.put(entidad.getDescripcion(), entidad.getIdestadogeneral());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return estadoitems;
	}

	/**
	 * @param estadoitems the estadoitems to set
	 */
	public void setEstadoitems(Map<String, Integer> estadoitems) {
		this.estadoitems = estadoitems;
	}

	/**
	 * @return the empresaitems
	 */
	public Map<String, Integer> getEmpresaitems() {
		List lista = new ArrayList<EmpresaSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getEstadoitems()'");
			}
			lista = objEmpresaService.listarEmpresas();

			for (int i = 0; i < lista.size(); i++) {
				EmpresaSie entidad = new EmpresaSie();
				entidad = (EmpresaSie) lista.get(i);
				empresaitems.put(entidad.getDescripcion(), entidad.getIdempresa());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return empresaitems;
	}

	/**
	 * @param empresaitems the empresaitems to set
	 */
	public void setEmpresaitems(Map<String, Integer> empresaitems) {
		this.empresaitems = empresaitems;
	}

	/**
	 * @return the codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * @param codigoEstado the codigoEstado to set
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	} 
	
	/*Combobox TipoDocumento*/
	public Map<String, Integer> getTipoDocumentoitems() {
		tipoDocumentoItems =  new HashMap<String, Integer>();
		List lista = new ArrayList<TipoDocumentoIdentidadSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoDocumentoitems()'");
			}
			lista = objTipoDocumentoService.listarTipoDocumentos();

			for (int i = 0; i < lista.size(); i++) {
				TipoDocumentoIdentidadSie entidad = new TipoDocumentoIdentidadSie();
				entidad = (TipoDocumentoIdentidadSie) lista.get(i);
					tipoDocumentoItems.put(entidad.getDescripcion(), entidad.getIdtipodocumentoidentidad());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tipoDocumentoItems;
	}

	/**
	 * @param estadoitems the estadoitems to set
	 */
	public void setTipoDocumentoitems(Map<String, Integer> tipodocumento) {
		this.tipoDocumentoItems = tipodocumento;
	} 
	
	/*Combobox Cargo Empleado*/
	public Map<String, Integer> getCargoEmpleadoItems() {
		cargoEmpleadoItems =  new HashMap<String, Integer>();
		List lista = new ArrayList<CargoEmpleadoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getcargoEmpleadoItems()'");
			}
			lista = objCargoEmpleadoService.listarCargoEmpleado();

			for (int i = 0; i < lista.size(); i++) {
				CargoEmpleadoSie entidad = new CargoEmpleadoSie();
				entidad = (CargoEmpleadoSie) lista.get(i);
				cargoEmpleadoItems.put(entidad.getDescripcion(), entidad.getIdcargoempleado());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return cargoEmpleadoItems;
	}

	/**
	 * @param estadoitems the estadoitems to set
	 */
	public void setCargoEmpleadoitems(Map<String, Integer> cargoempleado) {
		this.cargoEmpleadoItems = cargoempleado;
	} 
	
}
