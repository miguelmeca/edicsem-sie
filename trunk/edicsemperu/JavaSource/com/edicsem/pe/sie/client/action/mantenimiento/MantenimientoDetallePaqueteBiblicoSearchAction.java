package com.edicsem.pe.sie.client.action.mantenimiento;

import java.sql.Timestamp;
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

import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.service.facade.DetallePaqueteService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "mantenimientoDetallePaqueteBiblicoSearchAction")
@SessionScoped
public class MantenimientoDetallePaqueteBiblicoSearchAction extends BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoDetallePaqueteBiblicoSearchAction.class);
	
	// PAQUETE BIBLICO
	public String mensaje;
	private List<DetPaqueteSie> detPaqueteBiblicoList;
	private DetPaqueteSie objDetPaqueteSie;
	private PaqueteSie objPaqueteSie;
	private List<DetPaqueteSie> lista;
	private int idc;
	private int idproducto;
	private int idEstadoGeneral;
	private int cantidad,idDetPaquete;
	private boolean newRecord = false;
	
	@ManagedProperty(value = "#{mantenimientoPaqueteBiblicoFormAction}")
	private MantenimientoPaqueteBiblicoFormAction mantenimientoPaqueteFormAction;
	
	@EJB
	private DetallePaqueteService objDetallePaqueteService;
	@EJB
	private ProductoService objProductoService;
	
	public MantenimientoDetallePaqueteBiblicoSearchAction() {
		init();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #init()
	 */
	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoDetallePaqueteBiblicoSearchAction'");
		objDetPaqueteSie = new DetPaqueteSie();
		objPaqueteSie = new PaqueteSie();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar 'MantenimientoDetallePaqueteBiblicoSearchAction' ");
		log.info("id  "+mantenimientoPaqueteFormAction.getObjPaqueteSie().getIdpaquete());
		detPaqueteBiblicoList = objDetallePaqueteService.listarDetPaquetes(mantenimientoPaqueteFormAction.getObjPaqueteSie().getIdpaquete());
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		objPaqueteSie = new PaqueteSie();
		objDetPaqueteSie = new DetPaqueteSie();
		idproducto=0;
		objDetPaqueteSie.setCantidad(1);
		setNewRecord(true);
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
	log.info("update()  " );
	setIdDetPaquete(objDetPaqueteSie.getIdDetPaquete());
	setIdproducto(objDetPaqueteSie.getTbProducto().getIdproducto());
	setCantidad(objDetPaqueteSie.getCantidad());
	setIdEstadoGeneral(objDetPaqueteSie.getTbEstadoGeneral().getIdestadogeneral());
	setNewRecord(false);
	return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() {
	log.info("insertar()");
	mensaje =null;
	HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
	String paginaRetorno="";
	
	try {
		int error = 0;
		if(mantenimientoPaqueteFormAction.getObjPaqueteSie().getIdpaquete()==null){
			mensaje=Constants.MESSAGE_ERROR_FATAL_TITULO_DETALLE;
			error = 1;
		}
		log.info("producto " +idproducto);
		lista = getDetPaqueteBiblicoList();
		if (isNewRecord() && error ==0) {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getTbProducto().getIdproducto().equals(idproducto)) {
					log.info("Error ... Ya se encuentra un producto igual");
					mensaje ="Ya se encuentra el Producto en el Paquete";
					paginaRetorno =listar();
					error = 1;
					break;
				}
			}
		}
		if (error == 0) {
			if(isNewRecord()){
				//buscar si existe el producto en dicho paquete
				objDetPaqueteSie.setTbPaquete(mantenimientoPaqueteFormAction.getObjPaqueteSie());
				objDetPaqueteSie.setTbProducto(objProductoService.findProducto(idproducto));
				objDetallePaqueteService.insertDetPaquete(objDetPaqueteSie);
				setNewRecord(false);
				mensaje ="Se registró el producto en el paquete correctamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			else{
				objDetPaqueteSie.setUsuariomodifica(sessionUsuario.getUsuario());
				objDetPaqueteSie.setFechamodifica(new Timestamp(DateUtil.getToday().getTime().getTime()));
				objDetPaqueteSie.setTbProducto(objProductoService.findProducto(getIdproducto()));
				objDetallePaqueteService.updateDetPaquete(objDetPaqueteSie);
				mensaje="Se actualizo el producto correctamente";
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			objDetPaqueteSie = new DetPaqueteSie();
			paginaRetorno =listar();
		}
		else {
			log.info("mensaje de error");
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
		}
	}catch (Exception e) {
		e.printStackTrace();
		mensaje = e.getMessage();
		msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
				Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
		log.error(e.getMessage());
	}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return  paginaRetorno;
	}
	
	public String eliminaDetProdPaquete() throws Exception {
	mensaje =null;
	try {
		if (log.isInfoEnabled()) {
			log.info("Entering my method 'eliminaDetProdPaquete()'");
		}
		objDetallePaqueteService.eliminarDetPaquete(objDetPaqueteSie.getIdDetPaquete());
		mensaje ="Se elimino el producto del paquete correctamente";
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_DESHABILITAR_TITULO, mensaje);
		
	} catch (Exception e) {
		e.printStackTrace();	
		mensaje = e.getMessage();
		msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
				Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
		log.error(e.getMessage());
	}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		objDetPaqueteSie = new DetPaqueteSie();
		return listar();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_PAQUETEBIBLICO_FORM_LIST_PAGE;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_DETALLEPAQUETEBIBLICO_FORM_PAGE;
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
	 * @param detPaqueteBiblicoList the detPaqueteBiblicoList to set
	 */
	public void setDetPaqueteBiblicoList(List<DetPaqueteSie> detPaqueteBiblicoList) {
		this.detPaqueteBiblicoList = detPaqueteBiblicoList;
	}

	public List<DetPaqueteSie> getDetPaqueteBiblicoList() {
		return detPaqueteBiblicoList;
	}
	
	/**
	 * @return the objDetPaqueteSie
	 */
	public DetPaqueteSie getObjDetPaqueteSie() {
		return objDetPaqueteSie;
	}

	/**
	 * @param objDetPaqueteSie the objDetPaqueteSie to set
	 */
	public void setObjDetPaqueteSie(DetPaqueteSie objDetPaqueteSie) {
		this.objDetPaqueteSie = objDetPaqueteSie;
	}
	
	/**
	 * @return the objPaqueteSie
	 */
	public PaqueteSie getObjPaqueteSie() {
		return objPaqueteSie;
	}

	/**
	 * @param objPaqueteSie the objPaqueteSie to set
	 */
	public void setObjPaqueteSie(PaqueteSie objPaqueteSie) {
		this.objPaqueteSie = objPaqueteSie;
	}

	/**
	 * @return the idproducto
	 */
	public int getIdproducto() {
		return idproducto;
	}

	/**
	 * @param idproducto the idproducto to set
	 */
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}

	/**
	 * @param idEstadoGeneral the idEstadoGeneral to set
	 */
	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
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

	/**
	 * @return the idDetPaquete
	 */
	public int getIdDetPaquete() {
		return idDetPaquete;
	}

	/**
	 * @param idDetPaquete the idDetPaquete to set
	 */
	public void setIdDetPaquete(int idDetPaquete) {
		this.idDetPaquete = idDetPaquete;
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
	 * @return the idc
	 */
	public int getIdc() {
		return idc;
	}

	/**
	 * @param idc the idc to set
	 */
	public void setIdc(int idc) {
		this.idc = idc;
	}

	/**
	 * @return the lista
	 */
	public List<DetPaqueteSie> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<DetPaqueteSie> lista) {
		this.lista = lista;
	}

	/**
	 * @return the mantenimientoPaqueteFormAction
	 */
	public MantenimientoPaqueteBiblicoFormAction getMantenimientoPaqueteFormAction() {
		return mantenimientoPaqueteFormAction;
	}

	/**
	 * @param mantenimientoPaqueteFormAction the mantenimientoPaqueteFormAction to set
	 */
	public void setMantenimientoPaqueteFormAction(
			MantenimientoPaqueteBiblicoFormAction mantenimientoPaqueteFormAction) {
		this.mantenimientoPaqueteFormAction = mantenimientoPaqueteFormAction;
	}
	
}
