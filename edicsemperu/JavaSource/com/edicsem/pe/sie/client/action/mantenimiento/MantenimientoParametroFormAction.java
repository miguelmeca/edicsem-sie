package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.ParametroSistemaSie;
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.ParametroService;
import com.edicsem.pe.sie.service.facade.ProveedorService;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoParametroFormAction")
@SessionScoped
public class MantenimientoParametroFormAction extends BaseMantenimientoAbstractAction {
  
	private ParametroSistemaSie objParametro;
	private EstadoGeneralSie objEstado;
	private String nombre;
	private int estado;
	
	private boolean newRecord =false;
	/*variable que capta el id del proveedor*/
	private int ide;
		
	@ManagedProperty(value = "#{mantenimientoProveedorSearchAction}")
	private MantenimientoProveedorSearchAction mantenimientoProveedorSearch;
	
	@EJB
	private ParametroService objParametroService;
	@EJB
	private EstadogeneralService objEstadoService;
		
	public static Log log = LogFactory.getLog(MantenimientoParametroFormAction.class);
	
	public MantenimientoParametroFormAction() {
		System.out.println("ESTOY EN MI CONSTRUCTOR");
		log.info("inicializando mi constructor");
		init();
	}

	/*inicializamos los  objetos utilizados*/
	public void init() {
		log.info("init()");
		
	} 

	
	/*método que se ejecuta al hacer click en el botón EDITAR de la lista*/
	public String update() throws Exception {
	    log.info("actualizar");
		log.info("update()" + objParametro.getDescripcion());
		/*busca el empleado por medio del id del ¿datatable?*/
		ParametroSistemaSie p = objParametroService.findParametro(objParametro.getIdparametrosistema());
		//log.info(" id " + p.getIdproveedor()+ " nombre " + p.getCodproveedor()); 
		/*Seteo para mostrar los datos en el form*/
		objParametro.setIdparametrosistema(p.getIdparametrosistema());
		objParametro.setDescripcion(p.getDescripcion());
		objParametro.setValor(p.getValor());
		setEstado(p.getTbEstadoGeneral().getIdestadogeneral());
		objParametro.setAreasistema(p.getAreasistema());
		
        /*método bolean necesario para actualizar que retorna al form */  
		setNewRecord(false);
		return getViewMant();
	}
	
	/*método del botón GUARDAR(inserta o actualiza el empleado, domicilio y telefono)*/
	public String insertar() throws Exception {
		try {
				if (log.isInfoEnabled()) {
					//log.info("Entering my method 'insertar(registrar, actualizar)'"+ objProveedor.getCodproveedor());
				}
				
				objParametro.setTbEstadoGeneral(objEstadoService.findEstadogeneral(estado));
				
					log.info("actualizando..... ");
					objParametroService.actualizarParametro(objParametro);
					log.info("insertando..... ");
				
		} catch (Exception e) {
			e.printStackTrace();
			nombre = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return getViewList();
	}

	/*métodos GET y SET*/
	
	public String getViewList() {
		return "mantenimientoParametrosList";
	}
	
	public String getViewMant() {
		return "mantenimientoParametrosForm";
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
	 * @return the mantenimientoProveedorSearch
	 */
	public MantenimientoProveedorSearchAction getMantenimientoProveedorSearch() {
		return mantenimientoProveedorSearch;
	}

	/**
	 * @param mantenimientoProveedorSearch the mantenimientoProveedorSearch to set
	 */
	public void setMantenimientoProveedorSearch(
			MantenimientoProveedorSearchAction mantenimientoProveedorSearch) {
		this.mantenimientoProveedorSearch = mantenimientoProveedorSearch;
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
	 * @return the ide
	 */
	public int getIde() {
		return ide;
	}

	/**
	 * @param ide the ide to set
	 */
	public void setIde(int ide) {
		this.ide = ide;
	}

	/**
	 * @return the objParametro
	 */
	public ParametroSistemaSie getObjParametro() {
		return objParametro;
	}

	/**
	 * @param objParametro the objParametro to set
	 */
	public void setObjParametro(ParametroSistemaSie objParametro) {
		this.objParametro = objParametro;
	}

	/**
	 * @return the objEstado
	 */
	public EstadoGeneralSie getObjEstado() {
		return objEstado;
	}

	/**
	 * @param objEstado the objEstado to set
	 */
	public void setObjEstado(EstadoGeneralSie objEstado) {
		this.objEstado = objEstado;
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
	   	
	
	
}
