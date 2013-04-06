package com.edicsem.pe.sie.client.action.mantenimiento;

import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.ParametroSistemaSie;
import com.edicsem.pe.sie.service.facade.ParametroService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoParametroFormAction")
@SessionScoped
public class MantenimientoParametroFormAction extends BaseMantenimientoAbstractAction {
	private ParametroSistemaSie objParametro;
	private EstadoGeneralSie objEstado;
	private String mensaje;
	private boolean newRecord =false;
	/*variable que capta el id*/
	private int ide;
	private String valorAnterior;
	
	@ManagedProperty(value = "#{mantenimientoParametroSearchAction}")
	private MantenimientoParametroSearchAction mantenimientoParametroSearch;
	
	@EJB
	private ParametroService objParametroService;
		
	public static Log log = LogFactory.getLog(MantenimientoParametroFormAction.class);
	
	public MantenimientoParametroFormAction() {
		log.info("ESTOY EN MI CONSTRUCTOR");
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
		valorAnterior= objParametro.getValor();
		/*busca el parámetro*/
		ParametroSistemaSie p = objParametroService.findParametro(objParametro.getIdparametrosistema());
		/*Seteo para mostrar los datos en el form*/
		objParametro.setIdparametrosistema(p.getIdparametrosistema());
		objParametro.setDescripcion(p.getDescripcion());
		objParametro.setValor(p.getValor());
		objParametro.setAreasistema(p.getAreasistema()); 
		setNewRecord(false);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		try {
			if(isNewRecord()){
				objParametro.setUsuarioCreacion(sessionUsuario.getUsuario());
				objParametroService.insertarParametro(objParametro);
			}else{
				objParametro.setFechamodifica(new Timestamp(DateUtil.getToday().getTime().getTime()));
				objParametro.setUsuariomodifica(sessionUsuario.getUsuario());
				objParametroService.actualizarParametro(objParametro, valorAnterior);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_REGISTRO_TITULO, mensaje);
			}
		}catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		objParametro=new ParametroSistemaSie();
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
	 * @return the mantenimientoParametroSearch
	 */
	public MantenimientoParametroSearchAction getMantenimientoParametroSearch() {
		return mantenimientoParametroSearch;
	}

	/**
	 * @param mantenimientoParametroSearch the mantenimientoParametroSearch to set
	 */
	public void setMantenimientoParametroSearch(
			MantenimientoParametroSearchAction mantenimientoParametroSearch) {
		this.mantenimientoParametroSearch = mantenimientoParametroSearch;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the valorAnterior
	 */
	public String getValorAnterior() {
		return valorAnterior;
	}

	/**
	 * @param valorAnterior the valorAnterior to set
	 */
	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}
	
}
