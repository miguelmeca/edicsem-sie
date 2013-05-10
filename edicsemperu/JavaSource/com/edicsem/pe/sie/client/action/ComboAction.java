package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CalificacionEquifaxSie;
import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.CriterioComisionSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.FactorSancionSie;
import com.edicsem.pe.sie.entity.FechaSie;
import com.edicsem.pe.sie.entity.GrupoVentaSie;
import com.edicsem.pe.sie.entity.ImporteSie;
import com.edicsem.pe.sie.entity.IncidenciaSie;
import com.edicsem.pe.sie.entity.MetaMesSie;
import com.edicsem.pe.sie.entity.MotivoSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.entity.ParametroActividadSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.entity.SancionSie;
import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.entity.TipoClienteSie;
import com.edicsem.pe.sie.entity.TipoCobranzaSie;
import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.entity.TipoEventoVentaSie;
import com.edicsem.pe.sie.entity.TipoFiltroSie;
import com.edicsem.pe.sie.entity.TipoImporteSie;
import com.edicsem.pe.sie.entity.TipoKardexProductoSie;
import com.edicsem.pe.sie.entity.TipoLlamadaSie;
import com.edicsem.pe.sie.entity.TipoPagoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.entity.TipoPuntoVentaSie;
import com.edicsem.pe.sie.entity.TipoRefinanciaSie;
import com.edicsem.pe.sie.entity.TurnoSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.CalificacionEquifaxService;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.ContratoEmpleadoService;
import com.edicsem.pe.sie.service.facade.CriterioComisionService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.FactorSancionService;
import com.edicsem.pe.sie.service.facade.FechaService;
import com.edicsem.pe.sie.service.facade.GrupoVentaService;
import com.edicsem.pe.sie.service.facade.ImporteService;
import com.edicsem.pe.sie.service.facade.IncidenciaService;
import com.edicsem.pe.sie.service.facade.MetaMesService;
import com.edicsem.pe.sie.service.facade.MotivoService;
import com.edicsem.pe.sie.service.facade.PaqueteService;
import com.edicsem.pe.sie.service.facade.ParametroActividadService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.ProveedorService;
import com.edicsem.pe.sie.service.facade.SancionService;
import com.edicsem.pe.sie.service.facade.TipoCasaService;
import com.edicsem.pe.sie.service.facade.TipoClienteService;
import com.edicsem.pe.sie.service.facade.TipoCobranzaService;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
import com.edicsem.pe.sie.service.facade.TipoEventoVentaService;
import com.edicsem.pe.sie.service.facade.TipoFiltroService;
import com.edicsem.pe.sie.service.facade.TipoImporteService;
import com.edicsem.pe.sie.service.facade.TipoKardexService;
import com.edicsem.pe.sie.service.facade.TipoLLamadaService;
import com.edicsem.pe.sie.service.facade.TipoPagoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.service.facade.TipoPuntoVentaService;
import com.edicsem.pe.sie.service.facade.TipoRefinanciaService;
import com.edicsem.pe.sie.service.facade.TurnoService;
import com.edicsem.pe.sie.service.facade.UbigeoService;
import com.edicsem.pe.sie.util.constants.Constants;

@ManagedBean(name = "comboAction")
@SessionScoped
public class ComboAction {

	private static Log log = LogFactory.getLog(ComboAction.class);
	private static FacesMessage msg = null;
	private String mensaje;
	private String codigoEstado;
	private int idGrupo;
	private String idProvincia, idDepartamento;
	private int idCargo, idFactor, tipoImporte,idEmpresa;
	private int idtipocliente;
	private Map<String, Integer> tipoitems = new HashMap<String, Integer>();
	private Map<String, Integer> productositems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoalmacenitems = new HashMap<String, Integer>();
	private Map<String, Integer> almacenItems = new HashMap<String, Integer>();
	private Map<String, Integer> almacenItemsXTipo = new HashMap<String, Integer>();
	private Map<String, Integer> tipoDocumentoItems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoClienteItems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoCobranzaItems = new HashMap<String, Integer>();
	private Map<String, Integer> cargoEmpleadoItems = new HashMap<String, Integer>();
	private Map<String, Integer> productoPaqueteItems = new HashMap<String, Integer>();
	private Map<String, Integer> grupoItems = new HashMap<String, Integer>();
	private Map<String, Integer> motivoitems = new HashMap<String, Integer>();
	private Map<String, Integer> criterioComisionitems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoEventoVentaitems = new HashMap<String, Integer>();
	private Map<String, Integer> incidenteitems = new HashMap<String, Integer>();
	private Map<String, Integer> tiporefinaitems = new HashMap<String, Integer>();
	private int tipoVenta;
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
	private Map<String, Integer> expositorItems = new HashMap<String, Integer>();
	private Map<String, Integer> vendedorItems  = new HashMap<String, Integer>();
	private Map<String, Integer> cerradorItems  = new HashMap<String, Integer>();
	private Map<String, Integer> relacionistaItems  = new HashMap<String, Integer>();
	private Map<String, Integer> tipollamada = new HashMap<String, Integer>();
	private Map<String, Integer> empleadoxcargo = new HashMap<String, Integer>();
	private Map<String, Integer> diasItems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoFiltroItems = new HashMap<String, Integer>();
	private Map<String, Integer> factorSancionItems = new HashMap<String, Integer>();
	private Map<String, Integer> sancionItems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoImporteItems = new HashMap<String, Integer>();
	private Map<String, Integer> importeItems = new HashMap<String, Integer>();
	private Map<String, Integer> tipoPagoItems = new HashMap<String, Integer>();
	private Map<String, Integer> turnoItems = new HashMap<String, Integer>();
	private Map<String, Integer> calificacionItems = new HashMap<String, Integer>();
	private Map<String, Integer> parametroActividadItems = new HashMap<String, Integer>();
	
	@EJB
	private TurnoService objTurnoService;
	@EJB
	private CriterioComisionService objCriterioComisionService;
	@EJB
	private MotivoService objMotivoService;
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
	private TipoClienteService objTipoClienteService;
	@EJB
	private TipoCobranzaService objTipoCobranzaService;
	@EJB
	private TipoRefinanciaService objTipoRefinaService;
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
	@EJB
	private GrupoVentaService objGrupoService;
	@EJB
	private TipoEventoVentaService objTipoEventoVentaService;
	@EJB
	private IncidenciaService objIncidenteService;
	@EJB
	private CalificacionEquifaxService objCalificacionService;
	@EJB
	private ParametroActividadService objParametroActividadService;
	
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
		relacionistaItems = new HashMap<String, Integer>();
	}

	public void cambiar() {
		log.info(" tipo  prod "+tipoProducto);
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
		productositems = sortByComparator(productositems);
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
			almacenItems = sortByComparator(almacenItems);
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
			tipoitems = sortByComparator(tipoitems);
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
		estadoitems =  new HashMap<String, Integer>();
		List lista = new ArrayList<EstadoGeneralSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getEstadoitems()'"+ this.getCodigoEstado());
			}
			lista = objEstadoGeneralService.listarEstados(this
					.getCodigoEstado());

			for (int i = 0; i < lista.size(); i++) {
				EstadoGeneralSie entidad = new EstadoGeneralSie();
				entidad = (EstadoGeneralSie) lista.get(i);
				estadoitems.put(entidad.getDescripcion(),
						entidad.getIdestadogeneral());
			}
			estadoitems = sortByComparator(estadoitems);
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
			empresaitems = sortByComparator(empresaitems);
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
			tipoDocumentoItems = sortByComparator(tipoDocumentoItems);
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
			tipollamada = sortByComparator(tipollamada);
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
			cargoEmpleadoItems = sortByComparator(cargoEmpleadoItems);
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
				productoPaqueteItems.put(producto.getDescripcionproducto(),producto.getIdproducto());
			}
			productoPaqueteItems = sortByComparator(productoPaqueteItems);
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
			proveedoritems = sortByComparator(proveedoritems);
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
			tipoKardexItems = sortByComparator(tipoKardexItems);
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
			tipocasaItems = sortByComparator(tipocasaItems);
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
				ubigeoDeparItems.put(lista.get(i).getNombre(),lista.get(i).getCoddepartamento());
			}
			ubigeoDeparItems = sortByComparator(ubigeoDeparItems);
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
			ubigeoProvinItems = sortByComparator(ubigeoProvinItems);
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
			ubigeoDistriItems = sortByComparator(ubigeoDistriItems);
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
		List<MetaMesSie> lista = new ArrayList<MetaMesSie>();
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
			
			MetaMesItems = sortByComparatorId(MetaMesItems);
			
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
			PaqueteItems = sortByComparator(PaqueteItems);
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
			empleadoItems = sortByComparator(empleadoItems);
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
		
		if(idGrupo!=0){
			log.info("cargo --> " + getCargoEmpleado()+" grupo "+idGrupo);
		listaP = (List<ContratoEmpleadoSie>) objContratoEmpleadoService.listarxCargoxgrupo(cargoEmpleado,idGrupo);
		}
		else{
			listaP = (List<ContratoEmpleadoSie>) objContratoEmpleadoService.listarxCargo(cargoEmpleado);
		}
		log.info("tamaño empleado X cargo --> " + listaP.size());
		for (int i = 0; i < listaP.size(); i++) {
			ContratoEmpleadoSie c = new ContratoEmpleadoSie();
			c = (ContratoEmpleadoSie) listaP.get(i);
			empleadoxcargo.put(c.getTbEmpleado1().getNombresCompletos(),
					c.getTbEmpleado1().getIdempleado());
		}
		empleadoxcargo = sortByComparator(empleadoxcargo);
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
		diasItems = new HashMap<String, Integer>();
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
			diasItems = sortByComparatorId(diasItems);
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
			tipoFiltroItems = sortByComparator(tipoFiltroItems);
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
			factorSancionItems = sortByComparator(factorSancionItems);
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
		sancionItems = sortByComparator(sancionItems);
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
		tipoImporteItems = sortByComparator(tipoImporteItems);
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
		importeItems = sortByComparator(importeItems);
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
		List lista = null;
		empleadosXEmpresaitems = new HashMap<String, Integer>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getEmpleadoItems()' "+getIdEmpresa());
			}
			lista = objEmpleadoService.listarEmpleadoxEmpresas(getIdEmpresa());
			if(lista!=null){
				for (int i = 0; i < lista.size(); i++) {
					EmpleadoSie entidad = new EmpleadoSie();
					entidad = (EmpleadoSie) lista.get(i);
					empleadosXEmpresaitems.put(entidad.getNombresCompletos(),
							entidad.getIdempleado());
				}
				empleadosXEmpresaitems = sortByComparator(empleadosXEmpresaitems);
			}else{
				lista = new ArrayList<EmpleadoSie>();
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
	public void setEmpleadosXEmpresaitems(Map<String, Integer> empleadosXEmpresaitems) {
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
			tipoPagoItems = sortByComparator(tipoPagoItems);
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
			almacenItemsXTipo = sortByComparator(almacenItemsXTipo);
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
			tipoalmacenitems = sortByComparator(tipoalmacenitems);
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
	 * @param productoPaqueteItems the productoPaqueteItems to set
	 */
	public void setProductoPaqueteItems(Map<String, Integer> productoPaqueteItems) {
		this.productoPaqueteItems = productoPaqueteItems;
	}
	
	/**
	 * @return the vendedorItems
	 */
	public Map<String, Integer> getVendedorItems() {
		return vendedorItems;
	}

	/**
	 * @param vendedorItems the vendedorItems to set
	 */
	public void setVendedorItems(Map<String, Integer> vendedorItems) {
		if(vendedorItems==null)
		vendedorItems = sortByComparator(vendedorItems);
		this.vendedorItems = vendedorItems;
	}

	/**
	 * @return the expositorItems
	 */
	public Map<String, Integer> getExpositorItems() {
		if(expositorItems==null)
		expositorItems = sortByComparator(expositorItems);
		return expositorItems;
	}

	/**
	 * @param expositorItems the expositorItems to set
	 */
	public void setExpositorItems(Map<String, Integer> expositorItems) {
		this.expositorItems = expositorItems;
	}

	/**
	 * @return the grupoItems
	 */
	public Map<String, Integer> getGrupoItems() {
		List lista = new ArrayList<GrupoVentaSie>();
		grupoItems = new HashMap<String, Integer>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getGrupoItems()'"+ tipoVenta);
			}
			lista = objGrupoService.listarGrupoVenta(tipoVenta);
			log.info(" tamaño " + lista.size());
			GrupoVentaSie g;
			for (int i = 0; i < lista.size(); i++) {
				g = new GrupoVentaSie();
				if (lista.get(i) != null) {
					g = (GrupoVentaSie) lista.get(i);
					grupoItems.put(g.getDescripcion(),
							g.getIdgrupo());
				} else {
					break;
				}
			}
			grupoItems = sortByComparator(grupoItems);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return grupoItems;
	}

	/**
	 * @param grupoItems the grupoItems to set
	 */
	public void setGrupoItems(Map<String, Integer> grupoItems) {
		this.grupoItems = grupoItems;
	}

	/**
	 * @return the idGrupo
	 */
	public int getIdGrupo() {
		return idGrupo;
	}

	/**
	 * @param idGrupo the idGrupo to set
	 */
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	/**
	 * @return the tipoVenta
	 */
	public int getTipoVenta() {
		return tipoVenta;
	}

	/**
	 * @param tipoVenta the tipoVenta to set
	 */
	public void setTipoVenta(int tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	/**
	 * @return the tipoClienteItems
	 */
	public Map<String, Integer> getTipoClienteItems() {
		tipoClienteItems = new HashMap<String, Integer>();
		List lista = new ArrayList<TipoClienteSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoClienteItems()'");
			}
			lista = objTipoClienteService.listarTipoCliente();
			
			for (int i = 0; i < lista.size(); i++) {
				TipoClienteSie entidad = new TipoClienteSie();
				entidad = (TipoClienteSie) lista.get(i);
				tipoClienteItems.put(entidad.getDescripcion(),
						entidad.getIdtipocliente());
			}
			tipoClienteItems = sortByComparator(tipoClienteItems);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tipoClienteItems;
	}

	/**
	 * @param tipoClienteItems the tipoClienteItems to set
	 */
	public void setTipoClienteItems(Map<String, Integer> tipoClienteItems) {
		this.tipoClienteItems = tipoClienteItems;
	}

	/**
	 * @return the motivoitems
	 */
	public Map<String, Integer> getMotivoitems() {
		motivoitems = new HashMap<String, Integer>();
		List lista = new ArrayList<MotivoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getMotivoitems()'");
			}
			lista = objMotivoService.listarMotivo();
			
			for (int i = 0; i < lista.size(); i++) {
				MotivoSie entidad = new MotivoSie();
				entidad = (MotivoSie) lista.get(i);
				motivoitems.put(entidad.getDescripcion(),
						entidad.getIdmotivo());
			}
			motivoitems = sortByComparator(motivoitems);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return motivoitems;
	}

	/**
	 * @param motivoitems the motivoitems to set
	 */
	public void setMotivoitems(Map<String, Integer> motivoitems) {
		this.motivoitems = motivoitems;
	}

	/**
	 * @return the criterioComisionitems
	 */
	public Map<String, Integer> getCriterioComisionitems() {
		criterioComisionitems = new HashMap<String, Integer>();
		List lista = new ArrayList<CriterioComisionSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getCriterioComisionitems()'");
			}
			lista = objCriterioComisionService.listarCriterioComision();

			for (int i = 0; i < lista.size(); i++) {
				CriterioComisionSie tipo = new CriterioComisionSie();
				tipo = (CriterioComisionSie) lista.get(i);
				criterioComisionitems.put(tipo.getDescripcion(),
						tipo.getIdcriterio());
			}
			criterioComisionitems = sortByComparator(criterioComisionitems);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return criterioComisionitems;
	}

	/**
	 * @param criterioComisionitems the criterioComisionitems to set
	 */
	public void setCriterioComisionitems(Map<String, Integer> criterioComisionitems) {
		this.criterioComisionitems = criterioComisionitems;
	}

	/**
	 * @return the tipoEventoVentaitems
	 */
	public Map<String, Integer> getTipoEventoVentaitems() {
		Map<String, Integer>  map= new HashMap<String, Integer>();
		tipoEventoVentaitems = new HashMap<String, Integer>();
		List lista = new ArrayList<TipoEventoVentaSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoEventoVentaitems()'");
			}
			lista = objTipoEventoVentaService.listarTipoEventoVenta();

			for (int i = 0; i < lista.size(); i++) {
				TipoEventoVentaSie tipo = new TipoEventoVentaSie();
				tipo = (TipoEventoVentaSie) lista.get(i);
				tipoEventoVentaitems.put(tipo.getDescripcion(),
						tipo.getIdtipoevento());
			}
			map = sortByComparator(tipoEventoVentaitems);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return map;
	}

	/**
	 * @param tipoEventoVentaitems the tipoEventoVentaitems to set
	 */
	public void setTipoEventoVentaitems(Map<String, Integer> tipoEventoVentaitems) {
		this.tipoEventoVentaitems = tipoEventoVentaitems;
	}

	/**
	 * @return the relacionistaItems
	 */
	public Map<String, Integer> getRelacionistaItems() {
		relacionistaItems = new HashMap<String, Integer>();
		List listaP = new ArrayList<EmpleadoSie>();
		CargoEmpleadoSie ca = objCargoEmpleadoService.buscarCargoEmpleado(Constants.CARGO_RELACIONISTA);
		cargoEmpleado =ca.getIdcargoempleado();
		listaP = (List<EmpleadoSie>) objContratoEmpleadoService.listarxCargo(cargoEmpleado);
		if(listaP==null)listaP = new ArrayList<EmpleadoSie>();
		log.info("tamaño empleado X cargo --> " + listaP.size());
		for (int i = 0; i < listaP.size(); i++) {
			EmpleadoSie c = new EmpleadoSie();
			c = (EmpleadoSie) listaP.get(i);
			relacionistaItems.put(c.getNombresCompletos(),
					c.getIdempleado());
		}
		relacionistaItems = sortByComparator(relacionistaItems);
		return relacionistaItems;
	}

	/**
	 * @param relacionistaItems the relacionistaItems to set
	 */
	public void setRelacionistaItems(Map<String, Integer> relacionistaItems) {
		this.relacionistaItems = relacionistaItems;
	}
	
	/**
	 * @return the turnoItems
	 */
	public Map<String, Integer> getTurnoItems() {
		turnoItems = new HashMap<String, Integer>();
		List lista = new ArrayList<TurnoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTurnoItems()'");
			}
			lista = objTurnoService.listarTurno();
			
			for (int i = 0; i < lista.size(); i++) {
				TurnoSie t = new TurnoSie();
				t = (TurnoSie) lista.get(i);
				turnoItems.put(t.getDescripcion(),
						t.getIdturno());
			}
			turnoItems = sortByComparator(turnoItems);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return turnoItems;
	}

	/**
	 * @param turnoItems the turnoItems to set
	 */
	public void setTurnoItems(Map<String, Integer> turnoItems) {
		this.turnoItems = turnoItems;
	}

	/**
	 * @return the cerradorItems
	 */
	public Map<String, Integer> getCerradorItems() {
		cerradorItems = sortByComparator(cerradorItems);
		return cerradorItems;
	}

	/**
	 * @param cerradorItems the cerradorItems to set
	 */
	public void setCerradorItems(Map<String, Integer> cerradorItems) {
		this.cerradorItems = cerradorItems;
	}
	
	/**
	 * @return the incidenteitems
	 */
	public Map<String, Integer> getIncidenteitems() {
		incidenteitems = new HashMap<String, Integer>();
		List lista = new ArrayList<TurnoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getIncidenteitems()'");
			}
			lista = objIncidenteService.listarIncidencia();
			
			for (int i = 0; i < lista.size(); i++) {
				IncidenciaSie t = new IncidenciaSie();
				t = (IncidenciaSie) lista.get(i);
				incidenteitems.put(t.getDescripcion(),t.getIdincidencia());
			}
			incidenteitems = sortByComparator(incidenteitems);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return incidenteitems;
	}

	/**
	 * @param incidenteitems the incidenteitems to set
	 */
	public void setIncidenteitems(Map<String, Integer> incidenteitems) {
		this.incidenteitems = incidenteitems;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	private Map sortByComparator(Map unsortMap) {
		 
		List list = new LinkedList(unsortMap.entrySet());
 
		// sort list based on comparator
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getKey())
                                       .compareTo(((Map.Entry) (o2)).getKey());
			}
		});
 
		// put sorted list into map again
                //LinkedHashMap make sure order in which keys were inserted
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	private Map sortByComparatorId(Map unsortMap) {
		 
		List list = new LinkedList(unsortMap.entrySet());
 
		// sort list based on comparator
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
                                       .compareTo(((Map.Entry) (o2)).getValue());
			}
		});
 
		// put sorted list into map again
                //LinkedHashMap make sure order in which keys were inserted
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	/**
	 * @return the tipoCobranzaItems
	 */
	public Map<String, Integer> getTipoCobranzaItems() {
		tipoCobranzaItems = new HashMap<String, Integer>();
		List lista = new ArrayList<TipoCobranzaSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoCobranzaItems()'");
			}
			lista = objTipoCobranzaService.listarTipoCobranza();
			
			for (int i = 0; i < lista.size(); i++) {
				TipoCobranzaSie entidad = new TipoCobranzaSie();
				entidad = (TipoCobranzaSie) lista.get(i);
				tipoCobranzaItems.put(entidad.getDescripcion(),
						entidad.getIdtipocobranza());
			}
			tipoCobranzaItems = sortByComparator(tipoCobranzaItems);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tipoCobranzaItems;
	}

	/**
	 * @param tipoCobranzaItems the tipoCobranzaItems to set
	 */
	public void setTipoCobranzaItems(Map<String, Integer> tipoCobranzaItems) {
		this.tipoCobranzaItems = tipoCobranzaItems;
	}

	/**
	 * @return the tiporefinaitems
	 */
	public Map<String, Integer> getTiporefinaitems() {
		tiporefinaitems = new HashMap<String, Integer>();
		List lista = new ArrayList<TipoRefinanciaSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTiporefinaitems()'");
			}
			lista = objTipoRefinaService.listarTipoRefinanciaXTipoCliente(idtipocliente);
			
			for (int i = 0; i < lista.size(); i++) {
				TipoRefinanciaSie entidad = new TipoRefinanciaSie();
				entidad = (TipoRefinanciaSie) lista.get(i);
				tiporefinaitems.put(entidad.getDescripcion(),
						entidad.getIdtiporefin());
			}
			tiporefinaitems = sortByComparator(tiporefinaitems);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tiporefinaitems;
	}

	/**
	 * @param tiporefinaitems the tiporefinaitems to set
	 */
	public void setTiporefinaitems(Map<String, Integer> tiporefinaitems) {
		this.tiporefinaitems = tiporefinaitems;
	}

	/**
	 * @return the idtipocliente
	 */
	public int getIdtipocliente() {
		return idtipocliente;
	}

	/**
	 * @param idtipocliente the idtipocliente to set
	 */
	public void setIdtipocliente(int idtipocliente) {
		this.idtipocliente = idtipocliente;
	}

	/**
	 * @return the calificacionItems
	 */
	public Map<String, Integer> getCalificacionItems() {
		calificacionItems = new HashMap<String, Integer>();
		List lista = new ArrayList<CalificacionEquifaxSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getCalificacionItems()'");
			}
			lista = objCalificacionService.listarCalificacionEquifax();
			
			for (int i = 0; i < lista.size(); i++) {
				CalificacionEquifaxSie entidad = new CalificacionEquifaxSie();
				entidad = (CalificacionEquifaxSie) lista.get(i);
				calificacionItems.put(entidad.getDescripcion(),
						entidad.getIdcalificacion());
			}
			calificacionItems = sortByComparator(calificacionItems);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return calificacionItems;
	}

	/**
	 * @param calificacionItems the calificacionItems to set
	 */
	public void setCalificacionItems(Map<String, Integer> calificacionItems) {
		this.calificacionItems = calificacionItems;
	}

	/**
	 * @return the parametroActividadItems
	 */
	public Map<String, Integer> getParametroActividadItems() {
		parametroActividadItems = new HashMap<String, Integer>();
		List lista = new ArrayList<ParametroActividadSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getParametroActividadItems()'");
			}
			lista = objParametroActividadService.listarParametroActividad();
			
			for (int i = 0; i < lista.size(); i++) {
				ParametroActividadSie entidad = new ParametroActividadSie();
				entidad = (ParametroActividadSie) lista.get(i);
				parametroActividadItems.put(entidad.getDescripcion(),
						entidad.getIdparametroactividad());
			}
			parametroActividadItems = sortByComparator(parametroActividadItems);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return parametroActividadItems;
	}

	/**
	 * @param parametroActividadItems the parametroActividadItems to set
	 */
	public void setParametroActividadItems(Map<String, Integer> parametroActividadItems) {
		this.parametroActividadItems = parametroActividadItems;
	}
	
}
