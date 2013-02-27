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

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.VerificaClienteSie;
import com.edicsem.pe.sie.entity.VerificaProductoSie;
import com.edicsem.pe.sie.entity.VerificaTelefonoSie;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.VerificaClienteService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "verifica")
@SessionScoped
public class VerificaClienteAction extends BaseMantenimientoAbstractAction {
	private Log log = LogFactory.getLog(VerificaClienteAction.class);
	private VerificaClienteSie objverificaclienteSie;
	private String mensaje;
	private int idtipodoc,tipoTelef,operadortelefonico, idproducto;
	private VerificaProductoSie verificaProd;
	private VerificaTelefonoSie verificaTel;
	private List<VerificaProductoSie> lstProducto;
	private List<VerificaTelefonoSie> lstTelefono;
	private boolean newRecord;
	
	@EJB
	private VerificaClienteService objverificaClienteService;
	@EJB
	private ProductoService objProductoService;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction combo;
	
	public VerificaClienteAction() {
		log.info("inicializando constructor VerificaClienteAction");
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
		objverificaclienteSie = new VerificaClienteSie();
		verificaTel= new VerificaTelefonoSie();
		verificaProd = new VerificaProductoSie(); 
		lstProducto= new ArrayList<VerificaProductoSie>();
		lstTelefono = new ArrayList<VerificaTelefonoSie>();
		setNewRecord(true);
		return  getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update ()");
		setNewRecord(false);
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("Entering my method 'insertar()' " );
		mensaje =null;
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		try {
			if (isNewRecord()) {
				objverificaclienteSie.setUsuariocreacion(sessionUsuario.getUsuario());
				objverificaClienteService.insertVerificaCliente(objverificaclienteSie, lstProducto, lstTelefono);
				mensaje=Constants.MESSAGE_REGISTRO_TITULO;
				objverificaclienteSie = new VerificaClienteSie();
			}else {
				objverificaclienteSie.setUsuariomodifica(sessionUsuario.getUsuario());
				objverificaclienteSie.setFechamodifica(new Timestamp(DateUtil.getToday().getTime().getTime()));
				objverificaClienteService.updateVerificaCliente(objverificaclienteSie);
				mensaje =Constants.MESSAGE_ACTUALIZO_TITULO;
			}
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
	public void productoAgregar(){
		log.info("Producto agregar " );
		verificaProd.setTbProducto(objProductoService.findProducto(idproducto));
		lstProducto.add(verificaProd);
	}
	
	public void  telefonoAgregar(){
		log.info("telefono agregar "+verificaTel.getTelefono() );
		
		if( verificaTel.getTelefono()==null||verificaTel.getTelefono().equals("")){
			mensaje= "Debe ingresar un número telefónico";
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else{
		boolean verifica= false;
		mensaje=null;
		if(tipoTelef==1)verificaTel.setTipotelefono("F");
		else
			verificaTel.setTipotelefono("C");
		//claro
		if( getOperadortelefonico()==1)
			verificaTel.setOperadortelefonico("Claro");
		else if(getOperadortelefonico()==2)
			verificaTel.setOperadortelefonico("Movistar");
		else if(getOperadortelefonico()==3)
			verificaTel.setOperadortelefonico("Nextel");
		
		for (int i = 0; i < lstTelefono.size(); i++) {
			log.info("  "+lstTelefono.get(i).getTelefono() +" "+ verificaTel.getTelefono());
			if(lstTelefono.get(i).getTelefono().equals(verificaTel.getTelefono())){
				 verifica= false;
				 mensaje = "El telefono ya se encuentra registrado en la lista de referencias";
				 break;
			}else{
				 verifica= true;
				
			}
		}if( lstTelefono.size()==0){
			 verifica= true;
		}
		if( verifica){
			lstTelefono.add(verificaTel);
			mensaje="Se agregó telefono";
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Constants.MESSAGE_INFO_TITULO, mensaje);
			log.info("se agrego " + verificaTel.getTelefono());
		}
		if( mensaje!=null && verifica==false){
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				Constants.MESSAGE_INFO_TITULO, mensaje);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		verificaTel = new VerificaTelefonoSie();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.VERIFICA_CLIENTE_FORM;
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

	/**
	 * @return the objverificaclienteSie
	 */
	public VerificaClienteSie getObjverificaclienteSie() {
		return objverificaclienteSie;
	}

	/**
	 * @param objverificaclienteSie the objverificaclienteSie to set
	 */
	public void setObjverificaclienteSie(VerificaClienteSie objverificaclienteSie) {
		this.objverificaclienteSie = objverificaclienteSie;
	}

	/**
	 * @return the idtipodoc
	 */
	public int getIdtipodoc() {
		return idtipodoc;
	}

	/**
	 * @param idtipodoc the idtipodoc to set
	 */
	public void setIdtipodoc(int idtipodoc) {
		this.idtipodoc = idtipodoc;
	}

	/**
	 * @return the lstProducto
	 */
	public List<VerificaProductoSie> getLstProducto() {
		return lstProducto;
	}

	/**
	 * @param lstProducto the lstProducto to set
	 */
	public void setLstProducto(List<VerificaProductoSie> lstProducto) {
		this.lstProducto = lstProducto;
	}

	/**
	 * @return the lstTelefono
	 */
	public List<VerificaTelefonoSie> getLstTelefono() {
		return lstTelefono;
	}

	/**
	 * @param lstTelefono the lstTelefono to set
	 */
	public void setLstTelefono(List<VerificaTelefonoSie> lstTelefono) {
		this.lstTelefono = lstTelefono;
	}

	/**
	 * @return the verificaProd
	 */
	public VerificaProductoSie getVerificaProd() {
		return verificaProd;
	}

	/**
	 * @param verificaProd the verificaProd to set
	 */
	public void setVerificaProd(VerificaProductoSie verificaProd) {
		this.verificaProd = verificaProd;
	}

	/**
	 * @return the verificaTel
	 */
	public VerificaTelefonoSie getVerificaTel() {
		return verificaTel;
	}

	/**
	 * @param verificaTel the verificaTel to set
	 */
	public void setVerificaTel(VerificaTelefonoSie verificaTel) {
		this.verificaTel = verificaTel;
	}

	/**
	 * @return the tipoTelef
	 */
	public int getTipoTelef() {
		return tipoTelef;
	}

	/**
	 * @param tipoTelef the tipoTelef to set
	 */
	public void setTipoTelef(int tipoTelef) {
		this.tipoTelef = tipoTelef;
	}

	/**
	 * @return the operadortelefonico
	 */
	public int getOperadortelefonico() {
		return operadortelefonico;
	}

	/**
	 * @param operadortelefonico the operadortelefonico to set
	 */
	public void setOperadortelefonico(int operadortelefonico) {
		this.operadortelefonico = operadortelefonico;
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
	
}
