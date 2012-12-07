package com.edicsem.pe.sie.client.action.mantenimiento;

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

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.IncidenciaSie;
import com.edicsem.pe.sie.entity.ObservacionIncidenciaSie;
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.service.facade.ContratoService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.IncidenciaService;
import com.edicsem.pe.sie.service.facade.ObsIncidenciaService;
import com.edicsem.pe.sie.service.facade.ProveedorService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoIncidenciaForm")
@SessionScoped
public class MantenimientoIncidenciaFormAction extends BaseMantenimientoAbstractAction {
    /*Se crean los objetos de las entidades proveedor*/	
	private IncidenciaSie objIncidencia;
	private ClienteSie objCliente;
	private TelefonoPersonaSie objTelefono;
	private ObservacionIncidenciaSie objObsIncidencia;
	private List<ObservacionIncidenciaSie> listaObsIncidencia;
	private List<TelefonoPersonaSie> listatelefono;
	/*variables*/
	private String nombre;
	private int estado;
	public int idincidencia;
	private int estado2;
	private Date fechafin;
	/*variable bolean necesaria*/
	private boolean newRecord =false;
	/*variable que capta el id del proveedor*/
	private int idi;
		
	@ManagedProperty(value = "#{mantenimientoIncidenciaSearchAction}")
	private MantenimientoIncidenciaSearchAction mantenimientoIncidenciaSearch;
	
	@EJB
	private EstadogeneralService objEstadoService;
	@EJB
	private IncidenciaService objIncidenciaService;
	@EJB
	private ObsIncidenciaService objObsIncidenciaService;
	@EJB
	private ClienteService objClienteService;
	@EJB
	private TelefonoEmpleadoService objTelefonoService;
	
		
	public static Log log = LogFactory.getLog(MantenimientoIncidenciaFormAction.class);
	
	public MantenimientoIncidenciaFormAction() {
		log.info("ESTOY EN MI CONSTRUCTOR");
		log.info("inicializando mi constructor");
		init();
	}

	/*inicializamos los  objetos utilizados*/
	public void init() {
		log.info("init()");
		objIncidencia = new IncidenciaSie();
		objObsIncidencia = new ObservacionIncidenciaSie();
		objCliente = new ClienteSie();
		objTelefono = new TelefonoPersonaSie();
	} 
	
	/*método que se ejecuta al hacer click en el botón EDITAR de la lista*/
	public String update() throws Exception {
		log.info("actualizar");
		log.info("update()" + objIncidencia.getIdincidencia());
		/*busca el proveedor*/
		IncidenciaSie p = objIncidenciaService.findIncidencia(objIncidencia.getIdincidencia()); 
		/*carga la lista de obsincidencia*/ 
		listaObsIncidencia = objObsIncidenciaService.listarObsIncidencia(p.getIdincidencia());
		if (listaObsIncidencia == null) {
			listaObsIncidencia = new ArrayList<ObservacionIncidenciaSie>();
		}
		/*Seteo para mostrar los datos en el form*/
		    objIncidencia.setIdincidencia(p.getIdincidencia());
		    setIdincidencia(p.getIdincidencia());
		    objIncidencia.setFechacreacion(p.getFechacreacion());
		    objIncidencia.setFechafin(p.getFechafin());
		    objIncidencia.setUsuariocreacion(p.getUsuariocreacion());
		    objIncidencia.setFechamodifica(p.getFechamodifica());
		    objIncidencia.setUsuariomodifica(p.getUsuariomodifica());
		    objIncidencia.setDescripcion(p.getDescripcion());
		    objIncidencia.setDetalle(p.getDetalle());
		    setEstado(objIncidencia.getTbEstadoGeneral().getIdestadogeneral());
		    objIncidencia.setTbContrato(p.getTbContrato());		    
		 // mostramos los datos del cliente
			ClienteSie c = objClienteService.findCliente(p.getTbContrato().getTbCliente().getIdcliente()); 		
			objCliente.setIdcliente(c.getIdcliente());
			objCliente.setNombrecliente(c.getNombrecliente());
			objCliente.setCorreo(c.getCorreo());
			objCliente.setDirectrabajo(c.getDirectrabajo());
			objCliente.setEmpresatrabajo(c.getEmpresatrabajo());
			objCliente.setCargotrabajo(c.getCargotrabajo());
			objCliente.setTelftrabajo(c.getTelftrabajo());
			log.info("listartelefonos x idcliente");
			listatelefono = objTelefonoService.listarTelefonoEmpleadosXidcliente(objCliente.getIdcliente());
			if (listatelefono == null) {
				listatelefono = new ArrayList<TelefonoPersonaSie>();
			}
        /*método bolean necesario para actualizar que retorna al form */  
		setNewRecord(false);
		return getViewMant();
	}
	
	public String deshabilitar() throws Exception {
		objIncidencia = new IncidenciaSie();
		int parametroObtenido;
		IncidenciaSie p = new IncidenciaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'DESHABILITAR()'");
			}
			parametroObtenido = getIdi();
			log.info(" ------>>>>>>aqui captura el parametro ID "+ parametroObtenido);
			p = objIncidenciaService.findIncidencia(parametroObtenido);
			/*seteo proveedor*/
		    objIncidencia.setIdincidencia(p.getIdincidencia());
		    setIdincidencia(p.getIdincidencia());
		    objIncidencia.setFechacreacion(p.getFechacreacion());
		    objIncidencia.setFechafin(p.getFechafin());
		    objIncidencia.setUsuariocreacion(p.getUsuariocreacion());
		    objIncidencia.setFechamodifica(p.getFechamodifica());
		    objIncidencia.setUsuariomodifica(p.getUsuariomodifica());
		    objIncidencia.setDescripcion(p.getDescripcion());
		    objIncidencia.setDetalle(p.getDetalle());
		    /*Estado de la Incidencia: deshabilitado(41)*/
		    objIncidencia.setTbEstadoGeneral(objEstadoService.findEstadogeneral(41));
		    objIncidencia.setTbContrato(p.getTbContrato());
			objIncidenciaService.updateIncidencia(objIncidencia);
			log.info("actualizando..... ");
			log.info("deshabilitando..... ");
		} catch (Exception e2) {
			e2.printStackTrace();
			nombre = e2.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
			log.error(e2.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objIncidencia = new IncidenciaSie();
		return getViewList();
	}

	/*método del botón GUARDAR(inserta o actualiza el proveedor)*/
	public String insertar() throws Exception {
		try {
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'insertar(registrar, actualizar)'"+ objIncidencia.getIdincidencia());
				}
				//objIncidencia.setTbContrato(objIncidencia.getTbContrato());
				objIncidencia.setTbEstadoGeneral(objEstadoService.findEstadogeneral(estado));
					log.info("actualizando..... ");
					objIncidenciaService.updateIncidencia(objIncidencia);
					log.info("insertando..... ");	
		} catch (Exception e) {
			e.printStackTrace();
			nombre = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objIncidencia = new IncidenciaSie();
		return getViewList();
	}

	/*método del botón GUARDAR(inserta o actualiza el proveedor)*/
	public String insertarObs() throws Exception {
		try {
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'insertar(registrar, actualizar)'"+idincidencia);
				}
				    
			        /*Estado de la Observación Incidencia: habilitado(40)*/ 
				    objObsIncidencia.setTbEstadoGeneral(objEstadoService.findEstadogeneral(40));
				    objObsIncidencia.setTbIncidencia(objIncidenciaService.findIncidencia(idincidencia));
				    objObsIncidencia.setFechafin(fechafin);
					log.info("actualizando..... ");
					objObsIncidenciaService.insertObsIncidencia(objObsIncidencia);
					log.info("insertando..... ");	
		} catch (Exception e) {
			e.printStackTrace();
			nombre = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objObsIncidencia = new ObservacionIncidenciaSie();
		return getViewMant();
	}
	
	/*métodos GET y SET*/
	
	public String getViewList() {
		return "mantenimientoIncidenciaList";
	}
	
	public String getViewMant() {
		return "mantenimientoIncidenciaForm";
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the log
	 */
	public static Log getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public static void setLog(Log log) {
		MantenimientoProveedorSearchAction.log = log;
	}

	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * @return the objIncidencia
	 */
	public IncidenciaSie getObjIncidencia() {
		return objIncidencia;
	}

	/**
	 * @param objIncidencia the objIncidencia to set
	 */
	public void setObjIncidencia(IncidenciaSie objIncidencia) {
		this.objIncidencia = objIncidencia;
	}

	/**
	 * @return the mantenimientoIncidenciaSearch
	 */
	public MantenimientoIncidenciaSearchAction getMantenimientoIncidenciaSearch() {
		return mantenimientoIncidenciaSearch;
	}

	/**
	 * @param mantenimientoIncidenciaSearch the mantenimientoIncidenciaSearch to set
	 */
	public void setMantenimientoIncidenciaSearch(
			MantenimientoIncidenciaSearchAction mantenimientoIncidenciaSearch) {
		this.mantenimientoIncidenciaSearch = mantenimientoIncidenciaSearch;
	}

	/**
	 * @return the idi
	 */
	public int getIdi() {
		return idi;
	}

	/**
	 * @param idi the idi to set
	 */
	public void setIdi(int idi) {
		this.idi = idi;
	}

	/**
	 * @return the idincidencia
	 */
	public int getIdincidencia() {
		return idincidencia;
	}

	/**
	 * @param idincidencia the idincidencia to set
	 */
	public void setIdincidencia(int idincidencia) {
		this.idincidencia = idincidencia;
	}

	/**
	 * @return the objObsIncidencia
	 */
	public ObservacionIncidenciaSie getObjObsIncidencia() {
		return objObsIncidencia;
	}

	/**
	 * @param objObsIncidencia the objObsIncidencia to set
	 */
	public void setObjObsIncidencia(ObservacionIncidenciaSie objObsIncidencia) {
		this.objObsIncidencia = objObsIncidencia;
	}

	/**
	 * @return the estado2
	 */
	public int getEstado2() {
		return estado2;
	}

	/**
	 * @param estado2 the estado2 to set
	 */
	public void setEstado2(int estado2) {
		this.estado2 = estado2;
	}

	/**
	 * @return the fechafin
	 */
	public Date getFechafin() {
		return fechafin;
	}

	/**
	 * @param fechafin the fechafin to set
	 */
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	/**
	 * @return the listaObsIncidencia
	 */
	public List<ObservacionIncidenciaSie> getListaObsIncidencia() {
		return listaObsIncidencia;
	}

	/**
	 * @param listaObsIncidencia the listaObsIncidencia to set
	 */
	public void setListaObsIncidencia(
			List<ObservacionIncidenciaSie> listaObsIncidencia) {
		this.listaObsIncidencia = listaObsIncidencia;
	}

	/**
	 * @return the objCliente
	 */
	public ClienteSie getObjCliente() {
		return objCliente;
	}

	/**
	 * @param objCliente the objCliente to set
	 */
	public void setObjCliente(ClienteSie objCliente) {
		this.objCliente = objCliente;
	}

	/**
	 * @return the objTelefono
	 */
	public TelefonoPersonaSie getObjTelefono() {
		return objTelefono;
	}

	/**
	 * @param objTelefono the objTelefono to set
	 */
	public void setObjTelefono(TelefonoPersonaSie objTelefono) {
		this.objTelefono = objTelefono;
	}

	/**
	 * @return the listatelefono
	 */
	public List<TelefonoPersonaSie> getListatelefono() {
		return listatelefono;
	}

	/**
	 * @param listatelefono the listatelefono to set
	 */
	public void setListatelefono(List<TelefonoPersonaSie> listatelefono) {
		this.listatelefono = listatelefono;
	}
	
	
	
}
