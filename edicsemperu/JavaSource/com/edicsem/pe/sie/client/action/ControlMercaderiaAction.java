package com.edicsem.pe.sie.client.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.mantenimiento.MantenimientoProductoSearchAction;
import com.edicsem.pe.sie.entity.ControlKardexSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.ControlMercaderiaService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "control")
@SessionScoped
public class ControlMercaderiaAction extends BaseMantenimientoAbstractAction {
	private Log log = LogFactory.getLog(ControlMercaderiaAction.class);
	private ControlKardexSie objcontrolSie;
	private String mensaje;
	private int idalmacen, idProducto, idEmpleado, cantidad, cantidadDeberia;
	private List<ControlKardexSie> lstControl;
	private boolean newRecord;
	
	@EJB
	private ProductoService objProductoService;
	@EJB
	private ControlMercaderiaService objControlService;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction combo;
	
	public ControlMercaderiaAction() {
		log.info("inicializando constructor MantenimientoKardex");
		init();
	}

	public void init() {
		log.info("init()");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		idEmpleado=0;
		idalmacen=0;
		idProducto=0;
		objcontrolSie = new ControlKardexSie();
		lstControl= new ArrayList<ControlKardexSie>();
		combo.setCargoEmpleado(12);
		setNewRecord(true);
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update ()");
		setNewRecord(false);
		return getViewList();
	}
	
	public String addControl() {
		log.info("addControl()");
		objcontrolSie.setTbProducto(objProductoService.findProducto(idProducto));
		lstControl.add(objcontrolSie);
		objcontrolSie = new ControlKardexSie();
		setNewRecord(true);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("Entering my method 'insertar()' " );
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		try {
			if (isNewRecord()) {
				objcontrolSie.setUsuariocreacion(sessionUsuario.getUsuario());
				objControlService.insertControlKardex(lstControl,idalmacen, idEmpleado );
				mensaje=Constants.MESSAGE_REGISTRO_TITULO;
				objcontrolSie = new ControlKardexSie();
			}else {
				objcontrolSie.setUsuariomodifica(sessionUsuario.getUsuario());
				objcontrolSie.setFechamodifica(new Timestamp(DateUtil.getToday().getTime().getTime()));
				objControlService.updateControlKardex(objcontrolSie);
				mensaje =Constants.MESSAGE_ACTUALIZO_TITULO;
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.CONTROL_MERCADERIA_FORM;
	}

	/**
	 * @return the idalmacen
	 */
	public int getIdalmacen() {
		return idalmacen;
	}

	/**
	 * @param idalmacen
	 *            the idalmacen to set
	 */
	public void setIdalmacen(int idalmacen) {
		this.idalmacen = idalmacen;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	 * @return the objcontrolSie
	 */
	public ControlKardexSie getObjcontrolSie() {
		return objcontrolSie;
	}

	/**
	 * @param objcontrolSie the objcontrolSie to set
	 */
	public void setObjcontrolSie(ControlKardexSie objcontrolSie) {
		this.objcontrolSie = objcontrolSie;
	}

	/**
	 * @return the idProducto
	 */
	public int getIdProducto() {
		return idProducto;
	}

	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * @return the idEmpleado
	 */
	public int getIdEmpleado() {
		return idEmpleado;
	}

	/**
	 * @param idEmpleado the idEmpleado to set
	 */
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidadDeberia() {
		return cantidadDeberia;
	}

	public void setCantidadDeberia(int cantidadDeberia) {
		this.cantidadDeberia = cantidadDeberia;
	}

	/**
	 * @return the lstControl
	 */
	public List<ControlKardexSie> getLstControl() {
		return lstControl;
	}

	/**
	 * @param lstControl the lstControl to set
	 */
	public void setLstControl(List<ControlKardexSie> lstControl) {
		this.lstControl = lstControl;
	}

	/**
	 * @return the combo
	 */
	public ComboAction getCombo() {
		return combo;
	}

	/**
	 * @param combo the combo to set
	 */
	public void setCombo(ComboAction combo) {
		this.combo = combo;
	}
	
}
