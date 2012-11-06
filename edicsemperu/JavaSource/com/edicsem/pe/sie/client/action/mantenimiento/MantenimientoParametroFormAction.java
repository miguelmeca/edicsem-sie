package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.ParametroSistemaSie;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.ParametroService;
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
	/*variable que capta el id*/
	private int ide;
		
	@ManagedProperty(value = "#{mantenimientoParametroSearchAction}")
	private MantenimientoProveedorSearchAction mantenimientoParametroSearch;
	
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
		/*busca el parámetro*/
		ParametroSistemaSie p = objParametroService.findParametro(objParametro.getIdparametrosistema());
		/*Seteo para mostrar los datos en el form*/
		objParametro.setIdparametrosistema(p.getIdparametrosistema());
		objParametro.setDescripcion(p.getDescripcion());
		objParametro.setValor(p.getValor());
		setEstado(p.getTbEstadoGeneral().getIdestadogeneral());
		objParametro.setAreasistema(p.getAreasistema()); 
		setNewRecord(false);
		return getViewMant();
	}
	
	/*método del botón GUARDAR(actualiza el parámetro)*/
	public String insertar() throws Exception {
		try {
				if (log.isInfoEnabled()) {
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

	/**
	 * @return the mantenimientoParametroSearch
	 */
	public MantenimientoProveedorSearchAction getMantenimientoParametroSearch() {
		return mantenimientoParametroSearch;
	}

	/**
	 * @param mantenimientoParametroSearch the mantenimientoParametroSearch to set
	 */
	public void setMantenimientoParametroSearch(
			MantenimientoProveedorSearchAction mantenimientoParametroSearch) {
		this.mantenimientoParametroSearch = mantenimientoParametroSearch;
	}
	
}
