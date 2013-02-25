package com.edicsem.pe.sie.client.action.mantenimiento;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.MotivoSie;
import com.edicsem.pe.sie.service.facade.MotivoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="motivoSearch")
@SessionScoped
public class MantenimientoMotivoSearchAction extends BaseMantenimientoAbstractAction{

	private MotivoSie motivo;
	private Log log = LogFactory.getLog(MantenimientoMotivoSearchAction.class);
	private List<MotivoSie> motivoList;
	private int idMotivo;
	private boolean newRecord = false;
	private String mensaje;
	
	@EJB
	private MotivoService motivoService;
	
	public MantenimientoMotivoSearchAction() {
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoMotivoSearchAction'");
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		motivo = new MotivoSie();
		motivoList= new ArrayList<MotivoSie>();
		newRecord = true;
		return getViewMant();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		newRecord = false;
		return getViewMant();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("Entering my method 'insertar()' " );
		String paginaRetorno ="";
		mensaje=null;
		//Capturando el empleado en session
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			if (isNewRecord()) {
				motivo.setUsuariocreacion(sessionUsuario.getUsuario());
				motivoService.insertMotivo(motivo);
				mensaje=Constants.MESSAGE_REGISTRO_TITULO;
			}else{
				motivo.setUsuariomodifica(sessionUsuario.getUsuario());
				motivo.setFechamodifica(new Timestamp(DateUtil.getToday().getTime().getTime()));
				motivoService.updateMotivo(motivo);
				mensaje=Constants.MESSAGE_ACTUALIZO_TITULO;
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			paginaRetorno =listar();
			context.execute("carDialogNuevomotivo.hide()");
			context.update("motivoTable");
			context.update("messages");
		}catch (Exception e) {
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return paginaRetorno;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#delete()
	 */
	public String delete() throws Exception {
		motivoService.deleteCargoEmpleado(motivo);
		mensaje=Constants.MESSAGE_DESHABILITO_TITULO;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return listar();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar 'MantenimientoMotivoSearchAction' ");
		motivoList = motivoService.listarMotivo();
		if (motivoList == null) {
			motivoList = new ArrayList<MotivoSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_MOTIVO_FORM_LIST_PAGE;
	}

	/**
	 * @return the motivo
	 */
	public MotivoSie getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(MotivoSie motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the motivoList
	 */
	public List<MotivoSie> getMotivoList() {
		return motivoList;
	}

	/**
	 * @param motivoList the motivoList to set
	 */
	public void setMotivoList(List<MotivoSie> motivoList) {
		this.motivoList = motivoList;
	}

	/**
	 * @return the idMotivo
	 */
	public int getIdMotivo() {
		return idMotivo;
	}

	/**
	 * @param idMotivo the idMotivo to set
	 */
	public void setIdMotivo(int idMotivo) {
		this.idMotivo = idMotivo;
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
}
