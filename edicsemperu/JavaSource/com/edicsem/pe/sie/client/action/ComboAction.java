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
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.entity.TipoKardexProductoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.ProveedorService;
import com.edicsem.pe.sie.service.facade.TipoCasaService;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
import com.edicsem.pe.sie.service.facade.TipoKardexService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.service.facade.UbigeoService;
import com.edicsem.pe.sie.util.constants.Constants;

@ManagedBean(name = "comboAction")
@SessionScoped
public class ComboAction {

	private static Log log = LogFactory.getLog(ComboAction.class);
	private static FacesMessage msg = null;
	private String mensaje;
	private String codigoEstado;
	private String idProvincia="",  idDepartamento="";
	private Map<String, Integer> tipoitems = new HashMap<String, Integer>();
	private Map<String, Integer> productositems = new HashMap<String, Integer>();
	private Map<String, Integer> almacenItems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoDocumentoItems = new HashMap<String, Integer>();
	private Map<String, Integer> cargoEmpleadoItems = new HashMap<String, Integer>();
	private int tipoProducto;
	private Map<String, Map<String, Integer>> dataProducto = new HashMap<String, Map<String, Integer>>();
	private Map<String, Integer> estadoitems = new HashMap<String, Integer>();
	private Map<String, Integer> empresaitems = new HashMap<String, Integer>();
	private Map<String, Integer> proveedoritems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoKardexItems = new HashMap<String, Integer>();
	private Map<String, Integer> tipocasaItems = new HashMap<String, Integer>();
	private Map<String, String> ubigeoDeparItems = new HashMap<String, String>();
	private Map<String, String> ubigeoProvinItems = new HashMap<String, String>();
	private Map<String, Integer> ubigeoDistriItems = new HashMap<String, Integer>();
	
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
	@EJB
	private ProveedorService objProveedorService;
	@EJB
	private TipoKardexService objTipoKardexService;
	@EJB
	private TipoCasaService objTipoCasaService;
	@EJB
	private UbigeoService objUbigeoService;

	public ComboAction() {
		log.info("inicializando constructor");
		init();
	}

	public void init() {
		log.info("init()");
		tipoitems = new HashMap<String, Integer>();
		almacenItems = new HashMap<String, Integer>();
		tipoDocumentoItems = new HashMap<String, Integer>();
		cargoEmpleadoItems = new HashMap<String, Integer>();
	}

	public void cambiar() {
		if (tipoProducto != -1)
			productositems = dataProducto.get(tipoProducto);
		else
			productositems = new HashMap<String, Integer>();
	}

	/**
	 * @param productositems
	 *            the productositems to set
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
			productositems.put(producto.getDescripcionproducto(),
					producto.getIdproducto());
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
					almacenItems.put(punto.getDescripcion(),
							punto.getIdpuntoventa());
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
	 * @param almacenItems
	 *            the almacenItems to set
	 */
	public void setAlmacenItems(Map<String, Integer> almacenItems) {
		this.almacenItems = almacenItems;
	}

	/**
	 * @return the tipoitems
	 */
	public Map<String, Integer> getTipoitems() {
		tipoitems = new HashMap<String, Integer>();
		List lista = new ArrayList<TipoProductoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoitems()'");
			}
			lista = objTipoProductoService.listarTipo();

			for (int i = 0; i < lista.size(); i++) {
				TipoProductoSie tipo = new TipoProductoSie();
				tipo = (TipoProductoSie) lista.get(i);
				tipoitems.put(tipo.getNombretipoproducto(),
						tipo.getIdtipoproducto());
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
	 * @param tipoitems
	 *            the tipoitems to set
	 */
	public void setTipoitems(Map<String, Integer> tipoitems) {
		this.tipoitems = tipoitems;
	}

	/**
	 * @return the estadoitems
	 */
	public Map<String, Integer> getEstadoitems() {
		List lista = new ArrayList<EstadoGeneralSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getEstadoitems()'");
			}
			lista = objEstadoGeneralService.listarEstados(this
					.getCodigoEstado());

			for (int i = 0; i < lista.size(); i++) {
				EstadoGeneralSie entidad = new EstadoGeneralSie();
				entidad = (EstadoGeneralSie) lista.get(i);
				estadoitems.put(entidad.getDescripcion(),
						entidad.getIdestadogeneral());
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
	 * @param estadoitems
	 *            the estadoitems to set
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
				log.info("Entering my method 'getEmpresaitems()'");
			}
			lista = objEmpresaService.listarEmpresas();

			for (int i = 0; i < lista.size(); i++) {
				EmpresaSie entidad = new EmpresaSie();
				entidad = (EmpresaSie) lista.get(i);
				empresaitems.put(entidad.getRazonsocial(),
						entidad.getIdempresa());
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
	 * @param empresaitems
	 *            the empresaitems to set
	 */
	public void setEmpresaitems(Map<String, Integer> empresaitems) {
		this.empresaitems = empresaitems;
	}

	/* Combobox TipoDocumento */
	public Map<String, Integer> getTipoDocumentoitems() {
		tipoDocumentoItems = new HashMap<String, Integer>();
		List lista = new ArrayList<TipoDocumentoIdentidadSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoDocumentoitems()'");
			}
			lista = objTipoDocumentoService.listarTipoDocumentos();

			for (int i = 0; i < lista.size(); i++) {
				TipoDocumentoIdentidadSie entidad = new TipoDocumentoIdentidadSie();
				entidad = (TipoDocumentoIdentidadSie) lista.get(i);
				tipoDocumentoItems.put(entidad.getDescripcion(),
						entidad.getIdtipodocumentoidentidad());
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
	 * @param estadoitems
	 *            the estadoitems to set
	 */
	public void setTipoDocumentoitems(Map<String, Integer> tipodocumento) {
		this.tipoDocumentoItems = tipodocumento;
	}

	/* Combobox Cargo Empleado */
	public Map<String, Integer> getCargoEmpleadoItems() {
		cargoEmpleadoItems = new HashMap<String, Integer>();
		List lista = new ArrayList<CargoEmpleadoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getcargoEmpleadoItems()'");
			}
			lista = objCargoEmpleadoService.listarCargoEmpleado();

			for (int i = 0; i < lista.size(); i++) {
				CargoEmpleadoSie entidad = new CargoEmpleadoSie();
				entidad = (CargoEmpleadoSie) lista.get(i);
				cargoEmpleadoItems.put(entidad.getDescripcion(),
						entidad.getIdcargoempleado());
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
	 * @param estadoitems
	 *            the estadoitems to set
	 */
	public void setCargoEmpleadoitems(Map<String, Integer> cargoempleado) {
		this.cargoEmpleadoItems = cargoempleado;
	}

	/**
	 * @return the proveedoritems
	 */
	public Map<String, Integer> getProveedoritems() {
		proveedoritems = new HashMap<String, Integer>();
		List lista = new ArrayList<ProveedorSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getProveedoritems()'");
			}
			lista = objProveedorService.listarProveedores();

			for (int i = 0; i < lista.size(); i++) {
				ProveedorSie entidad = new ProveedorSie();
				entidad = (ProveedorSie) lista.get(i);
				proveedoritems.put(entidad.getNombreempresa(),
						entidad.getIdproveedor());
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return proveedoritems;
	}

	/**
	 * @param proveedoritems
	 *            the proveedoritems to set
	 */
	public void setProveedoritems(Map<String, Integer> proveedoritems) {
		this.proveedoritems = proveedoritems;
	}

	/**
	 * @return the tipoKardexItems
	 */
	public Map<String, Integer> getTipoKardexItems() {
		tipoKardexItems = new HashMap<String, Integer>();
		List lista = new ArrayList<TipoKardexProductoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoKardexItems()'");
			}
			lista = objTipoKardexService.listaTipoKardex();

			for (int i = 0; i < lista.size(); i++) {
				TipoKardexProductoSie entidad = new TipoKardexProductoSie();
				entidad = (TipoKardexProductoSie) lista.get(i);
				tipoKardexItems.put(entidad.getDescripcion(),
						entidad.getIdtipokardexproducto());
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tipoKardexItems;
	}

	/**
	 * @param tipoKardexItems
	 *            the tipoKardexItems to set
	 */
	public void setTipoKardexItems(Map<String, Integer> tipoKardexItems) {
		this.tipoKardexItems = tipoKardexItems;
	}

	/**
	 * @return the TipocasaItems
	 */
	public Map<String, Integer> getTipocasaItems() {
		tipocasaItems = new HashMap<String, Integer>();
		List lista = new ArrayList<TipoCasaSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipocasaItems()'");
			}
			lista = objTipoCasaService.listarTipoCasa();

			for (int i = 0; i < lista.size(); i++) {
				TipoCasaSie entidad = new TipoCasaSie();
				entidad = (TipoCasaSie) lista.get(i);
				tipocasaItems.put(entidad.getDescripcion(),
						entidad.getIdtipocasa());
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tipocasaItems;
	}

	/**
	 * @param tipocasaItems
	 *            the tipocasaItems to set
	 */
	public void setTipocasaItems(Map<String, Integer> tipocasaItems) {
		this.tipocasaItems = tipocasaItems;
	}

	public Map<String, String> getUbigeoDeparItems() {
		ubigeoDeparItems = new HashMap<String, String>();
		List lista = new ArrayList<UbigeoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getUbigeoDeparItems()'");
			}
			lista = objUbigeoService.listarUbigeoDepartamentos();

			for (int i = 0; i < lista.size(); i++) {
				UbigeoSie entidad = new UbigeoSie();
				entidad = (UbigeoSie) lista.get(i);
				ubigeoDeparItems.put(entidad.getNombre(),
						entidad.getCoddepartamento());
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		ubigeoProvinItems =  new HashMap<String, String>();
		ubigeoDistriItems  =  new HashMap<String, Integer>();
		return ubigeoDeparItems;
	}

	public void setUbigeoDeparItems(Map<String, String> ubigeoDeparItems) {
		this.ubigeoDeparItems = ubigeoDeparItems;
	}

	/**
	 * @return the codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * @param codigoEstado
	 *            the codigoEstado to set
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
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
	 * @return the ubigeoProvinItems
	 */
	public Map<String, String> getUbigeoProvinItems() {
		ubigeoProvinItems = new HashMap<String, String>();
		List lista = new ArrayList<UbigeoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getUbigeoProvinItems()'");
			}
			lista = objUbigeoService.listarUbigeoProvincias( this.getIdDepartamento());

			for (int i = 0; i < lista.size(); i++) {
				UbigeoSie entidad = new UbigeoSie();
				entidad = (UbigeoSie) lista.get(i);
				ubigeoProvinItems.put(entidad.getNombre(),
						entidad.getCodprovincia());
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		ubigeoDistriItems =  new HashMap<String, Integer>();
		return ubigeoProvinItems;
	}

	/**
	 * @param ubigeoProvinItems the ubigeoProvinItems to set
	 */
	public void setUbigeoProvinItems(Map<String, String> ubigeoProvinItems) {
		this.ubigeoProvinItems = ubigeoProvinItems;
	}

	/**
	 * @return the ubigeoDistriItems
	 */
	public Map<String, Integer> getUbigeoDistriItems() {
		ubigeoDistriItems = new HashMap<String, Integer>();
		List lista = new ArrayList<UbigeoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getUbigeoDistriItems()'");
			}
			lista = objUbigeoService.listarUbigeoDistritos(this.getIdDepartamento(), this.getIdProvincia());

			for (int i = 0; i < lista.size(); i++) {
				UbigeoSie entidad = new UbigeoSie();
				entidad = (UbigeoSie) lista.get(i);
				ubigeoDistriItems.put(entidad.getNombre(),
						entidad.getIdubigeo());
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} 
		return ubigeoDistriItems;
	}

	/**
	 * @param ubigeoDistriItems the ubigeoDistriItems to set
	 */
	public void setUbigeoDistriItems(Map<String, Integer> ubigeoDistriItems) {
		this.ubigeoDistriItems = ubigeoDistriItems;
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
	
}
