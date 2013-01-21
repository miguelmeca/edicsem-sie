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
import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.FactorSancionSie;
import com.edicsem.pe.sie.entity.FechaSie;
import com.edicsem.pe.sie.entity.ImporteSie;
import com.edicsem.pe.sie.entity.MetaMesSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.entity.SancionSie;
import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.entity.TipoFiltroSie;
import com.edicsem.pe.sie.entity.TipoImporteSie;
import com.edicsem.pe.sie.entity.TipoKardexProductoSie;
import com.edicsem.pe.sie.entity.TipoLlamadaSie;
import com.edicsem.pe.sie.entity.TipoPagoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.entity.TipoPuntoVentaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.ContratoEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.FactorSancionService;
import com.edicsem.pe.sie.service.facade.FechaService;
import com.edicsem.pe.sie.service.facade.ImporteService;
import com.edicsem.pe.sie.service.facade.MetaMesService;
import com.edicsem.pe.sie.service.facade.PaqueteService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.ProveedorService;
import com.edicsem.pe.sie.service.facade.SancionService;
import com.edicsem.pe.sie.service.facade.TipoCasaService;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
import com.edicsem.pe.sie.service.facade.TipoFiltroService;
import com.edicsem.pe.sie.service.facade.TipoImporteService;
import com.edicsem.pe.sie.service.facade.TipoKardexService;
import com.edicsem.pe.sie.service.facade.TipoLLamadaService;
import com.edicsem.pe.sie.service.facade.TipoPagoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.service.facade.TipoPuntoVentaService;
import com.edicsem.pe.sie.service.facade.UbigeoService;
import com.edicsem.pe.sie.util.constants.Constants;

@ManagedBean(name = "comboAction")
@SessionScoped
public class ComboAction {

	private static Log log = LogFactory.getLog(ComboAction.class);
	private static FacesMessage msg = null;
	private String mensaje;
	private String codigoEstado;
	private String idProvincia, idDepartamento;
	private int idCargo, idFactor, tipoImporte,idEmpresa;
	private Map<String, Integer> tipoitems = new HashMap<String, Integer>();
	private Map<String, Integer> productositems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoalmacenitems = new HashMap<String, Integer>();
	private Map<String, Integer> almacenItems = new HashMap<String, Integer>();
	private Map<String, Integer> almacenItemsXTipo = new HashMap<String, Integer>();
	private Map<String, Integer> tipoDocumentoItems = new HashMap<String, Integer>();
	private Map<String, Integer> cargoEmpleadoItems = new HashMap<String, Integer>();
	private Map<String, Integer> productoPaqueteItems = new HashMap<String, Integer>();
	
	private int tipoProducto,tipoAlmacen;
	private int cargoEmpleado;
	private Map<String, Map<String, Integer>> dataProducto = new HashMap<String, Map<String, Integer>>();
	private Map<String, Map<String, Integer>> dataEmpleado = new HashMap<String, Map<String, Integer>>();
	private Map<String, Integer> estadoitems = new HashMap<String, Integer>();
	private Map<String, Integer> empresaitems = new HashMap<String, Integer>();
	private Map<String, Integer> empleadosXEmpresaitems = new HashMap<String, Integer>();
	private Map<String, Integer> proveedoritems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoKardexItems = new HashMap<String, Integer>();
	private Map<String, Integer> tipocasaItems = new HashMap<String, Integer>();
	private Map<String, String> ubigeoDeparItems = new HashMap<String, String>();
	private Map<String, String> ubigeoProvinItems = new HashMap<String, String>();
	private Map<String, Integer> ubigeoDistriItems = new HashMap<String, Integer>();
	private Map<String, Integer> MetaMesItems = new HashMap<String, Integer>();
	private Map<String, Integer> PaqueteItems = new HashMap<String, Integer>();
	private Map<String, Integer> empleadoItems = new HashMap<String, Integer>();
	private Map<String, Integer> tipollamada = new HashMap<String, Integer>();
	private Map<String, Integer> empleadoxcargo = new HashMap<String, Integer>();
	private Map<String, Integer> diasItems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoFiltroItems = new HashMap<String, Integer>();
	private Map<String, Integer> factorSancionItems = new HashMap<String, Integer>();
	private Map<String, Integer> sancionItems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoImporteItems = new HashMap<String, Integer>();
	private Map<String, Integer> importeItems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoPagoItems = new HashMap<String, Integer>();
	
	@EJB
	private TipoPuntoVentaService objTipoPuntoVentaService;
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
	@EJB
	private MetaMesService objMetaMesService;
	@EJB
	private PaqueteService objPaqueteService;
	@EJB
	private EmpleadoSieService objEmpleadoService;
	@EJB
	private TipoLLamadaService objTipoLLamadaService;
	@EJB
	private FechaService objFechaService;
	@EJB
	private TipoFiltroService objTipoFiltroService;
	@EJB
	private FactorSancionService objFactorService;
	@EJB
	private SancionService objSancionService;
	@EJB
	private TipoImporteService objTipoImporteService;
	@EJB
	private ImporteService objImporteService;
	@EJB 
	private TipoPagoService objTipoPagoService;
	@EJB 
	private ContratoEmpleadoService objContratoEmpleadoService;
	
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
		productoPaqueteItems = new HashMap<String, Integer>();
		
		MetaMesItems = new HashMap<String, Integer>();
		tipollamada = new HashMap<String, Integer>();
		empleadoxcargo = new HashMap<String, Integer>();
		tipoalmacenitems = new HashMap<String, Integer>();
	}

	public void cambiar() {
		if (tipoProducto != -1)
			productositems = dataProducto.get(tipoProducto);
		else
			productositems = new HashMap<String, Integer>();
	}
	
	public void cambiar2() {
		if (cargoEmpleado != -1)
			empleadoxcargo = dataEmpleado.get(cargoEmpleado);
		else
			empleadoxcargo = new HashMap<String, Integer>();
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

	/* Combobox TipoLLamada */
	public Map<String, Integer> gettipollamada() {
		tipollamada = new HashMap<String, Integer>();
		List lista = new ArrayList<TipoLlamadaSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'gettipollamada()'");
			}
			lista = objTipoLLamadaService.listarTipoLLamada();

			for (int i = 0; i < lista.size(); i++) {
				TipoLlamadaSie entidad = new TipoLlamadaSie();
				entidad = (TipoLlamadaSie) lista.get(i);
				tipollamada.put(entidad.getDescripcionabreviado(),
						entidad.getIdtipoLlamada());
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tipollamada;
	}

	/**
	 * @param estadoitems
	 *            the estadoitems to set
	 */
	public void settipollamada(Map<String, Integer> tipollamada) {
		this.tipollamada = tipollamada;
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

	/* Combobox Listar Productos en el Formulario Detalle paquete biblico editar y agregar */
	public Map<String, Integer> getProductoPaqueteItems() {
		
		productoPaqueteItems = new HashMap<String, Integer>();
		List lista = new ArrayList<ProductoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("entrando al HashMap producto 'getProductoPaqueteItems()'");
			}
			
			lista = objProductoService.listarProductos();

			for (int i = 0; i < lista.size(); i++) {
				ProductoSie producto = new ProductoSie();
				producto = (ProductoSie) lista.get(i);
				productoPaqueteItems.put(producto.getDescripcionproducto(),
						producto.getIdproducto());
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return productoPaqueteItems;
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
		List<TipoCasaSie> lista = new ArrayList<TipoCasaSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipocasaItems()'");
			}
			lista = objTipoCasaService.listarTipoCasa();

			for (int i = 0; i < lista.size(); i++) {
				tipocasaItems.put(lista.get(i).getDescripcion(),
						lista.get(i).getIdtipocasa());
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
		List<UbigeoSie> lista = new ArrayList<UbigeoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getUbigeoDeparItems()'");
			}
			lista = objUbigeoService.listarUbigeoDepartamentos();
			for (int i = 0; i < lista.size(); i++) {
				ubigeoDeparItems.put(lista.get(i).getNombre(),
						lista.get(i).getCoddepartamento());
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		ubigeoProvinItems = new HashMap<String, String>();
		ubigeoDistriItems = new HashMap<String, Integer>();
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
	 * @param idProvincia
	 *            the idProvincia to set
	 */
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}

	/**
	 * @return the ubigeoProvinItems
	 */
	public Map<String, String> getUbigeoProvinItems() {
		ubigeoProvinItems = new HashMap<String, String>();
		List<UbigeoSie> lista = new ArrayList<UbigeoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getUbigeoProvinItems()'"
						+ this.getIdDepartamento());
			}
			lista = objUbigeoService.listarUbigeoProvincias(this.getIdDepartamento());
			for (int i = 0; i < lista.size(); i++) {
				log.info(i+" "+lista.get(i).getNombre()+"  "+lista.get(i).getCoddepartamento()+"  "+lista.get(i).getCodprovincia()+"  "+lista.get(i).getCoddistrito());
				ubigeoProvinItems.put(lista.get(i).getNombre(),
						lista.get(i).getCodprovincia());
			}
			
			log.info(" ubigeoProvinItems  " );
			log.info(" ubigeoProvinItems  " +ubigeoProvinItems);
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		ubigeoDistriItems = new HashMap<String, Integer>();
		return ubigeoProvinItems;
	}

	/**
	 * @param ubigeoProvinItems
	 *            the ubigeoProvinItems to set
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
				log.info("Entering my method 'getUbigeoDistriItems()'"
						+ this.getIdDepartamento() + "  "
						+ this.getIdProvincia());
			}
			if (this.getIdDepartamento() != null
					&& this.getIdProvincia() != null) {
				lista = objUbigeoService.listarUbigeoDistritos(
						this.getIdDepartamento(), this.getIdProvincia());
			} else
				lista = new ArrayList<UbigeoSie>();

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
	 * @param ubigeoDistriItems
	 *            the ubigeoDistriItems to set
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
	 * @param idDepartamento
	 *            the idDepartamento to set
	 */
	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	/* Combobox META MES */
	/**
	 * @return the metaMesItems
	 */
	public Map<String, Integer> getMetaMesItems() {
		MetaMesItems = new HashMap<String, Integer>();
		List lista = new ArrayList<MetaMesSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getMetaMesItems()'");
			}
			lista = objMetaMesService.listarMetaMeses();

			for (int i = 0; i < lista.size(); i++) {
				MetaMesSie entidad = new MetaMesSie();
				entidad = (MetaMesSie) lista.get(i);
				MetaMesItems.put(entidad.getMes(), entidad.getIdmetames());
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return MetaMesItems;
	}

	/**
	 * @param metaMesItems the metaMesItems to set
	 */
	public void setMetaMesItems(Map<String, Integer> metaMesItems) {
		MetaMesItems = metaMesItems;
	}

	/**
	 * @return the paqueteItems
	 */
	public Map<String, Integer> getPaqueteItems() {
		List lista = new ArrayList<PaqueteSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getPaqueteitems()'");
			}
			lista = objPaqueteService.listarPaquetes();

			for (int i = 0; i < lista.size(); i++) {
				PaqueteSie entidad = new PaqueteSie();
				entidad = (PaqueteSie) lista.get(i);
				PaqueteItems.put(entidad.getCodpaquete(),
						entidad.getIdpaquete());
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return PaqueteItems;
	}

	/**
	 * @param paqueteItems the paqueteItems to set
	 */
	public void setPaqueteItems(Map<String, Integer> paqueteItems) {
		PaqueteItems = paqueteItems;
	}
	
	/**
	 * @return the idCargo
	 */
	public int getIdCargo() {
		return idCargo;
	}
	/**
	 * @param idCargo the idCargo to set
	 */
	public void setIdCargo(int idCargo) {
		log.info("idCargo    seteando ** "+ idCargo);
		this.idCargo = idCargo;
	}

	/**
	 * @return the empleadoItems
	 */
	public Map<String, Integer> getEmpleadoItems() {
		List lista = new ArrayList<EmpleadoSie>();
		empleadoItems = new HashMap<String, Integer>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getEmpleadoItems()' ");
			}
			lista = objEmpleadoService.listarEmpleados();

			for (int i = 0; i < lista.size(); i++) {
				EmpleadoSie entidad = new EmpleadoSie();
				entidad = (EmpleadoSie) lista.get(i);
				empleadoItems.put(entidad.getNombresCompletos(),
						entidad.getIdempleado());
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return empleadoItems;
	}

	/**
	 * @param empleadoItems the empleadoItems to set
	 */
	public void setEmpleadoItems(Map<String, Integer> empleadoItems) {
		this.empleadoItems = empleadoItems;
	}

	/**
	 * @return the empleadoxcargo
	 */
	public Map<String, Integer> getEmpleadoxcargo() {
		
		log.info("cargo --> " + getCargoEmpleado());
		empleadoxcargo = new HashMap<String, Integer>();

		List listaP = new ArrayList<ContratoEmpleadoSie>();
		listaP = (List<ContratoEmpleadoSie>) objContratoEmpleadoService.listarxCargo(cargoEmpleado);
		log.info("tamaño empleado X cargo --> " + listaP.size());
		for (int i = 0; i < listaP.size(); i++) {
			ContratoEmpleadoSie c = new ContratoEmpleadoSie();
			c = (ContratoEmpleadoSie) listaP.get(i);
			empleadoxcargo.put(c.getTbEmpleado1().getNombresCompletos(),
					c.getTbEmpleado1().getIdempleado());
		}
		
		return empleadoxcargo;
	}

	/**
	 * @param empleadoxcargo the empleadoxcargo to set
	 */
	public void setEmpleadoxcargo(Map<String, Integer> empleadoxcargo) {
		this.empleadoxcargo = empleadoxcargo;
	}

	/**
	 * @return the cargoEmpleado
	 */
	public int getCargoEmpleado() {
		return cargoEmpleado;
	}

	/**
	 * @param cargoEmpleado the cargoEmpleado to set
	 */
	public void setCargoEmpleado(int cargoEmpleado) {
		this.cargoEmpleado = cargoEmpleado;
	}

	/**
	 * @return the diasItems
	 */
	public Map<String, Integer> getDiasItems() {
		List lista = new ArrayList<FechaSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getDiasItems()'");
			}
			lista = objFechaService.listarFechas();
			
			for (int i = 0; i < lista.size(); i++) {
				FechaSie entidad = new FechaSie();
				entidad = (FechaSie) lista.get(i);
				diasItems.put(entidad.getDia(),
						entidad.getIdFecha());
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return diasItems;
	}

	/**
	 * @param diasItems the diasItems to set
	 */
	public void setDiasItems(Map<String, Integer> diasItems) {
		this.diasItems = diasItems;
	}

	/**
	 * @return the tipoFiltroItems
	 */
	public Map<String, Integer> getTipoFiltroItems() {
		List lista = new ArrayList<TipoFiltroSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoFiltroItems()'");
			}
			lista = objTipoFiltroService.listarTipoFiltro();
			
			for (int i = 0; i < lista.size(); i++) {
				TipoFiltroSie entidad = new TipoFiltroSie();
				entidad = (TipoFiltroSie) lista.get(i);
				tipoFiltroItems.put(entidad.getDescripcion(),
						entidad.getIdtipofiltro());
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} 
		return tipoFiltroItems;
	}

	/**
	 * @param tipoFiltroItems the tipoFiltroItems to set
	 */
	public void setTipoFiltroItems(Map<String, Integer> tipoFiltroItems) {
		this.tipoFiltroItems = tipoFiltroItems;
	}

	/**
	 * @return the factorSancionItems
	 */
	public Map<String, Integer> getFactorSancionItems() {
		List lista = new ArrayList<FactorSancionSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getFactorSancionItems()'");
			}
			lista = objFactorService.listarFactorSancion();
			for (int i = 0; i < lista.size(); i++) {
				FactorSancionSie entidad = new FactorSancionSie();
				entidad = (FactorSancionSie) lista.get(i);
				factorSancionItems.put(entidad.getDescripcion(),
						entidad.getIdfactor());
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return factorSancionItems;
	}

	/**
	 * @param factorSancionItems the factorSancionItems to set
	 */
	public void setFactorSancionItems(Map<String, Integer> factorSancionItems) {
		this.factorSancionItems = factorSancionItems;
	}

	/**
	 * @return the sancionItems
	 */
	public Map<String, Integer> getSancionItems() {
		log.info("idFactor --> " + idFactor);
		sancionItems = new HashMap<String, Integer>();

		List lista = new ArrayList<SancionSie>();
		if(idFactor>0){
		lista = (List<ProductoSie>) objSancionService.listarSanciones(idFactor);
		}
		
		log.info("tamaño Sanciones  X factor --> " + lista.size());
		for (int i = 0; i < lista.size(); i++) {
			SancionSie s = new SancionSie();
			s =  (SancionSie) lista.get(i);
			sancionItems.put(s.getDescripcion(),s.getIdsancion());
		}
		return sancionItems;
	}

	/**
	 * @param sancionItems the sancionItems to set
	 */
	public void setSancionItems(Map<String, Integer> sancionItems) {
		this.sancionItems = sancionItems;
	}

	/**
	 * @return the idFactor
	 */
	public int getIdFactor() {
		return idFactor;
	}

	/**
	 * @param idFactor the idFactor to set
	 */
	public void setIdFactor(int idFactor) {
		this.idFactor = idFactor;
	}

	/**
	 * @return the tipoImporteItems
	 */
	public Map<String, Integer> getTipoImporteItems() {
		tipoImporteItems = new HashMap<String, Integer>();

		List lista = new ArrayList<TipoImporteSie>();

		lista = (List<TipoImporteSie>) objTipoImporteService.listarTipoImporte();
		
		log.info("tamaño Tipo Importe  --> " + lista.size());
		for (int i = 0; i < lista.size(); i++) {
			TipoImporteSie s = new TipoImporteSie();
			s =  (TipoImporteSie) lista.get(i);
			tipoImporteItems.put(s.getDescripcion(),s.getIdtipoimporte());
		}
		return tipoImporteItems;
	}

	/**
	 * @param tipoImporteItems the tipoImporteItems to set
	 */
	public void setTipoImporteItems(Map<String, Integer> tipoImporteItems) {
		this.tipoImporteItems = tipoImporteItems;
	}

	/**
	 * @return the importeItems
	 */
	public Map<String, Integer> getImporteItems() {
		log.info("tipoImporte --> " + tipoImporte);
		importeItems = new HashMap<String, Integer>();
		List lista = new ArrayList<ImporteSie>();
		lista = (List<ImporteSie>) objImporteService.listarImporte(tipoImporte);
		
		log.info("tamaño Importe  --> " + lista.size());
		for (int i = 0; i < lista.size(); i++) {
			ImporteSie s = new ImporteSie();
			s =  (ImporteSie) lista.get(i);
			importeItems.put(s.getDescripcion(),s.getIdimporte());
		}
		return importeItems;
	}

	/**
	 * @param importeItems the importeItems to set
	 */
	public void setImporteItems(Map<String, Integer> importeItems) {
		this.importeItems = importeItems;
	}

	/**
	 * @return the tipoImporte
	 */
	public int getTipoImporte() {
		return tipoImporte;
	}

	/**
	 * @param tipoImporte the tipoImporte to set
	 */
	public void setTipoImporte(int tipoImporte) {
		this.tipoImporte = tipoImporte;
	}

	/**
	 * @return the idEmpresa
	 */
	public int getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return the empleadosXEmpresaitems
	 */
	public Map<String, Integer> getEmpleadosXEmpresaitems() {
		List lista = new ArrayList<EmpleadoSie>();
		empleadosXEmpresaitems = new HashMap<String, Integer>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getEmpleadoItems()' "+getIdCargo());
			}
			lista = objEmpleadoService.listarEmpleadoxEmpresas(getIdEmpresa());
			
			for (int i = 0; i < lista.size(); i++) {
				EmpleadoSie entidad = new EmpleadoSie();
				entidad = (EmpleadoSie) lista.get(i);
				empleadosXEmpresaitems.put(entidad.getNombresCompletos(),
						entidad.getIdempleado());
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return empleadosXEmpresaitems;
	}

	/**
	 * @param empleadosXEmpresaitems the empleadosXEmpresaitems to set
	 */
	public void setEmpleadosXEmpresaitems(
			Map<String, Integer> empleadosXEmpresaitems) {
		this.empleadosXEmpresaitems = empleadosXEmpresaitems;
	}

	/**
	 * @return the tipoPagoItems
	 */
	public Map<String, Integer> getTipoPagoItems() {
		List lista = new ArrayList<TipoPagoSie>();
		tipoPagoItems = new HashMap<String, Integer>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoPago()' ");
			}
			lista = objTipoPagoService.listarTipoPago();
			
			for (int i = 0; i < lista.size(); i++) {
				TipoPagoSie entidad = new TipoPagoSie();
				entidad = (TipoPagoSie) lista.get(i);
				tipoPagoItems.put(entidad.getDescripcion(),
						entidad.getIdtipopago());
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tipoPagoItems;
	}

	/**
	 * @param tipoPagoItems the tipoPagoItems to set
	 */
	public void setTipoPagoItems(Map<String, Integer> tipoPagoItems) {
		this.tipoPagoItems = tipoPagoItems;
	}

	/**
	 * @return the almacenItemsXTipo
	 */
	public Map<String, Integer> getAlmacenItemsXTipo() {
		List lista = new ArrayList<PuntoVentaSie>();
		almacenItemsXTipo = new HashMap<String, Integer>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getAlmacenItemsXTipo()'");
			}
			lista = objAlmacenService.listarAlmacenXtipo(tipoAlmacen);
			log.info(" tamaño " + lista.size());
			PuntoVentaSie punto;
			for (int i = 0; i < lista.size(); i++) {
				punto = new PuntoVentaSie();
				if (lista.get(i) != null) {
					punto = (PuntoVentaSie) lista.get(i);
					almacenItemsXTipo.put(punto.getDescripcion(),
							punto.getIdpuntoventa());
				} else {
					break;
				}
			}
			log.info("finalizacion del metodo 'getAlmacenItemsXTipo'  ");
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return almacenItemsXTipo;
	}

	/**
	 * @param almacenItemsXTipo the almacenItemsXTipo to set
	 */
	public void setAlmacenItemsXTipo(Map<String, Integer> almacenItemsXTipo) {
		this.almacenItemsXTipo = almacenItemsXTipo;
	}

	/**
	 * @return the tipoalmacenitems
	 */
	public Map<String, Integer> getTipoalmacenitems() {
		tipoalmacenitems = new HashMap<String, Integer>();
		List lista = new ArrayList<TipoPuntoVentaSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoitems()'");
			}
			lista = objTipoPuntoVentaService.listarTipoPuntoVenta();

			for (int i = 0; i < lista.size(); i++) {
				TipoPuntoVentaSie tipo = new TipoPuntoVentaSie();
				tipo = (TipoPuntoVentaSie) lista.get(i);
				tipoalmacenitems.put(tipo.getDescripcion(),
						tipo.getIdtipopuntoventa());
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tipoalmacenitems;
	}

	/**
	 * @param tipoalmacenitems the tipoalmacenitems to set
	 */
	public void setTipoalmacenitems(Map<String, Integer> tipoalmacenitems) {
		this.tipoalmacenitems = tipoalmacenitems;
	}

	/**
	 * @return the tipoAlmacen
	 */
	public int getTipoAlmacen() {
		return tipoAlmacen;
	}

	/**
	 * @param tipoAlmacen the tipoAlmacen to set
	 */
	public void setTipoAlmacen(int tipoAlmacen) {
		this.tipoAlmacen = tipoAlmacen;
	}

	/**
	 * @return the productoPaqueteItems
	 */
	

	/**
	 * @param productoPaqueteItems the productoPaqueteItems to set
	 */
	public void setProductoPaqueteItems(Map<String, Integer> productoPaqueteItems) {
		this.productoPaqueteItems = productoPaqueteItems;
	}

	/**
	 * @return the productoOficialItems
	 */
	

	
	
}
