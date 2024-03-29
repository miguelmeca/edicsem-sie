package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.entity.TipoPuntoVentaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.UbigeoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "almacenForm")
@SessionScoped
public class MantenimientoPuntoAlmacenFormAction extends
		BaseMantenimientoAbstractAction {

	private Log log = LogFactory.getLog(MantenimientoPuntoAlmacenFormAction.class);
	
	public int idalmacen;
	public String mensaje,ubigeoDefecto,idUbigeo;
	private String idProvincia, idDepartamento, idDistrito;
	private int ide;
	private boolean defectoUbigeo;
	private boolean newRecord = false;
	private PuntoVentaSie objAlmacenSie;
	private TipoPuntoVentaSie objTipoPv;
	List<PuntoVentaSie> lista2 ;

	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManagerPunto;

	@ManagedProperty(value = "#{almacenSearch}")
	private MantenimientoPuntoAlmacenSearchAction mantenimientoPuntoAlmacenSearch;

	@EJB
	private AlmacenService objAlmacenService;
	
	@EJB
	private UbigeoService objUbigeoService;
	
	@EJB
	private EstadogeneralService objEstadoGeneralService;

	public MantenimientoPuntoAlmacenFormAction() {
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
		log.info("Inicializando el Constructor de 'MantenimientoPuntoAlmacenFormAction'");
		objAlmacenSie = new PuntoVentaSie();
		objTipoPv = new TipoPuntoVentaSie();
		defectoUbigeo=true;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		defectoUbigeo=true;
		comboManagerPunto.setIdDepartamento("15");
		comboManagerPunto.setIdProvincia("01");
		comboManagerPunto.setUbigeoDeparItems(null);
		comboManagerPunto.setUbigeoProvinItems(null);
		comboManagerPunto.setUbigeoDistriItems(null);
		idDepartamento="15";
		idProvincia="01";
		idUbigeo="0";
		ubigeoDefecto="";
		comboManagerPunto.setUbigeoDistriItems(null);
		setNewRecord(true);
		objAlmacenSie = new PuntoVentaSie();
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	
	public String update() throws Exception {
		log.info("update()" + objAlmacenSie.getIdpuntoventa());

		objAlmacenSie = objAlmacenService.findAlmacen(objAlmacenSie.getIdpuntoventa());

		log.info(" id cargo " + objAlmacenSie.getIdpuntoventa() + " des " +objAlmacenSie.getDescripcion()+" direc "+objAlmacenSie.getDireccion());

		setIdalmacen(objAlmacenSie.getIdpuntoventa());
		setIdUbigeo(objAlmacenSie.getTbUbigeo().getIdubigeo()+"");
		objTipoPv.setIdtipopuntoventa(objAlmacenSie.getTbTipoPuntoVenta().getIdtipopuntoventa());
		
		UbigeoSie ubigeo = objUbigeoService.findUbigeo(Integer.parseInt(getIdUbigeo()));
		setIdDepartamento(ubigeo.getCoddepartamento());
		comboManagerPunto.setIdDepartamento(idDepartamento);
		setIdProvincia(ubigeo.getCodprovincia());
		comboManagerPunto.setIdProvincia(idProvincia);
		setIdDistrito(ubigeo.getCoddistrito());
		ubigeoDefecto="";
		log.info("busquedaUbigeo "+ getIdDepartamento()+" - "+getIdProvincia()+" - "+getIdDistrito()+" - "+getIdUbigeo());
		setNewRecord(false);
		lista2 = mantenimientoPuntoAlmacenSearch.getAlmacenList();
		if(newRecord==false){
			for (int i = 0; i < lista2.size(); i++) {
				if (lista2.get(i).getDescripcion().equalsIgnoreCase(objAlmacenSie.getDescripcion())) {
					lista2.remove(i);
					break;
				}
			}
		}
		return getViewMant();
	}

	public String DeshabilitarPunto() throws Exception {
		
		mensaje=null;
		objAlmacenSie = new PuntoVentaSie();
		
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'DeshabilitarPunto()'" + getIde());
			}
			log.info(" parametro ID "+ getIde());
			objAlmacenSie = objAlmacenService.findAlmacen(getIde());
			
			//deshabilitando punto de venta
			objAlmacenSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(14));

			objAlmacenService.updateAlmacen(objAlmacenSie);
			mensaje="Se deshabilit� correctamente el almac�n "+objAlmacenSie.getDescripcion();
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info("deshabilitando punto ..... ");

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objAlmacenSie = new PuntoVentaSie();

		return mantenimientoPuntoAlmacenSearch.listar();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() {

		log.info("insertar() sv " + isNewRecord() + " desc "
				+ objAlmacenSie.getDescripcion() +"  "+ "almacen"+" direc " + objAlmacenSie.getDireccion() );
		int error = 0;
		String paginaRetorno="";
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		try {
			objAlmacenSie.setTbTipoPuntoVenta(objTipoPv);
			objAlmacenSie.setTbUbigeo(objUbigeoService.findUbigeo(Integer.parseInt(idUbigeo)));
			paginaRetorno =mantenimientoPuntoAlmacenSearch.getViewList();
			for (int i = 0; i < lista2.size(); i++) {
				if ( lista2.get(i).getDescripcion().trim().equalsIgnoreCase(objAlmacenSie.getDescripcion().trim())) {
					mensaje ="Existe un almac�n o Punto de Venta con el mismo nombre";
					error = 1;
					break;
				}
			}
			if(error==1){
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
					paginaRetorno =getViewMant();
			}
			else if (isNewRecord() && error == 0) {
					objAlmacenSie.setUsuariocreacion(sessionUsuario.getUsuario());
					objAlmacenService.insertAlmacen(objAlmacenSie);
					mensaje ="Se registro correctamente";
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
					objAlmacenSie = new PuntoVentaSie();
				
			} else {
					objAlmacenSie.setUsuariomodifica(sessionUsuario.getUsuario());
					objAlmacenService.updateAlmacen(objAlmacenSie);
					mensaje ="Se actualiz� correctamente";
					log.info("actualizado");
					objAlmacenSie = new PuntoVentaSie();
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		log.info("pagina  "+ paginaRetorno+" mensaj "+mensaje);
		
		return paginaRetorno =mantenimientoPuntoAlmacenSearch.listar();
	}

	public void cambiar() {
		comboManagerPunto.setIdDepartamento(getIdDepartamento());
		comboManagerPunto.setIdProvincia(null);
		idProvincia = null;
		idUbigeo = null;
	}

	public void cambiar2() {
		comboManagerPunto.setIdDepartamento(getIdDepartamento());
		comboManagerPunto.setIdProvincia(getIdProvincia());
	}
	
	public void cambioUbica() {
		log.info("Ubigeo -------->" + idUbigeo + " " + ubigeoDefecto);
		String dist = "";
		Iterator it = comboManagerPunto.getUbigeoDistriItems().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idUbigeo)) {
				dist = (String) e.getKey();
				log.info("dist " + dist);
				break;
			}
		}
		ubigeoDefecto = "LIMA-LIMA-" + dist;
	}
	
 	public void busquedaUbigeo(){
		log.info("busquedaUbigeo");
		UbigeoSie ubigeo = objUbigeoService.findUbigeo(Integer.parseInt(getIdUbigeo()));
		
		log.info("busquedaUbigeo "+ getIdDepartamento()+" - "+getIdProvincia()+" - "+getIdDistrito()+" - "+getIdUbigeo());
		
		Iterator it = comboManagerPunto.getUbigeoDeparItems().entrySet().iterator();
		Iterator it2 = comboManagerPunto.getUbigeoProvinItems().entrySet().iterator();
		Iterator it3 = comboManagerPunto.getUbigeoDistriItems().entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idDepartamento)) {
				ubigeoDefecto = (String) e.getKey();
				log.info("ubigeo depa " + ubigeoDefecto);
				break;
			}
		}
		while (it2.hasNext()) {
			Map.Entry e = (Map.Entry) it2.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idProvincia)) {
				ubigeoDefecto += "-" + (String) e.getKey();
				log.info("ubigeo prov " + ubigeoDefecto);
				break;
			}
		}
		while (it3.hasNext()) {
			Map.Entry e = (Map.Entry) it3.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idUbigeo)) {
				ubigeoDefecto += "-" + (String) e.getKey();
				log.info("ubigeo distrito " + ubigeoDefecto);
				break;
			}
		}
		log.info("ubigeo ------> :D   " + ubigeoDefecto);
	} 
	
	public void ingresarUbigeo() {
		// enviamos el nombre completo del depa- provincia-distrito

		log.info("ingresarUbigeo :D a --- " + idUbigeo);
		ubigeoDefecto = "otro ubigeo";

		Iterator it = comboManagerPunto.getUbigeoDeparItems().entrySet().iterator();
		Iterator it2 = comboManagerPunto.getUbigeoProvinItems().entrySet().iterator();
		Iterator it3 = comboManagerPunto.getUbigeoDistriItems().entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idDepartamento)) {
				ubigeoDefecto = (String) e.getKey();
				log.info("ubigeo depa " + ubigeoDefecto);
				break;
			}
		}
		while (it2.hasNext()) {
			Map.Entry e = (Map.Entry) it2.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idProvincia)) {
				ubigeoDefecto += "-" + (String) e.getKey();
				log.info("ubigeo prov " + ubigeoDefecto);
				break;
			}
		}
		while (it3.hasNext()) {
			Map.Entry e = (Map.Entry) it3.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idUbigeo)) {
				ubigeoDefecto += "-" + (String) e.getKey();
				log.info("ubigeo distrito " + ubigeoDefecto);
				break;
			}
		}
		log.info("ubigeo ------> :D   " + ubigeoDefecto);
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_ALMACEN_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_ALMACEN_FORM_PAGE;
	}

	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord
	 *            the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
	
	/**
	 * @return the ide
	 */
	public int getIde() {
		return ide;
	}

	/**
	 * @param ide
	 *            the ide to set
	 */
	public void setIde(int ide) {
		this.ide = ide;
	}

	public MantenimientoPuntoAlmacenSearchAction getMantenimientoPuntoAlmacenSearch() {
		return mantenimientoPuntoAlmacenSearch;
	}

	public void setMantenimientoPuntoAlmacenSearch(
			MantenimientoPuntoAlmacenSearchAction mantenimientoPuntoAlmacenSearch) {
		this.mantenimientoPuntoAlmacenSearch = mantenimientoPuntoAlmacenSearch;
	}

	public ComboAction getComboManagerPunto() {
		return comboManagerPunto;
	}

	public void setComboManagerPunto(ComboAction comboManagerPunto) {
		comboManagerPunto.setCodigoEstado(Constants.COD_ESTADO_TB_PUNTO_ALMACEN);
		log.info("aqui--->>" + Constants.COD_ESTADO_TB_PUNTO_ALMACEN);
		this.comboManagerPunto = comboManagerPunto;
	}

	public int getIdalmacen() {
		return idalmacen;
	}

	public void setIdalmacen(int idalmacen) {
		this.idalmacen = idalmacen;
	}

	/**
	 * @return the objAlmacenSie
	 */
	public PuntoVentaSie getObjAlmacenSie() {
		return objAlmacenSie;
	}

	/**
	 * @param objAlmacenSie the objAlmacenSie to set
	 */
	public void setObjAlmacenSie(PuntoVentaSie objAlmacenSie) {
		this.objAlmacenSie = objAlmacenSie;
	}

	/**
	 * @return the ubigeoDefecto
	 */
	public String getUbigeoDefecto() {
		return ubigeoDefecto;
	}

	/**
	 * @param ubigeoDefecto the ubigeoDefecto to set
	 */
	public void setUbigeoDefecto(String ubigeoDefecto) {
		this.ubigeoDefecto = ubigeoDefecto;
	}

	/**
	 * @return the idUbigeo
	 */
	public String getIdUbigeo() {
		return idUbigeo;
	}

	/**
	 * @param idUbigeo the idUbigeo to set
	 */
	public void setIdUbigeo(String idUbigeo) {
		this.idUbigeo = idUbigeo;
	}

	/**
	 * @return the defectoUbigeo
	 */
	public boolean isDefectoUbigeo() {
		return defectoUbigeo;
	}

	/**
	 * @param defectoUbigeo the defectoUbigeo to set
	 */
	public void setDefectoUbigeo(boolean defectoUbigeo) {
		this.defectoUbigeo = defectoUbigeo;
	}

	/**
	 * @return the idProvincia
	 */
	public String getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @param idProvincia the idProvincia to set
	 */
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}

	/**
	 * @return the idDepartamento
	 */
	public String getIdDepartamento() {
		return idDepartamento;
	}

	/**
	 * @param idDepartamento the idDepartamento to set
	 */
	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	/**
	 * @return the idDistrito
	 */
	public String getIdDistrito() {
		return idDistrito;
	}

	/**
	 * @param idDistrito the idDistrito to set
	 */
	public void setIdDistrito(String idDistrito) {
		this.idDistrito = idDistrito;
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
	 * @return the objTipoPv
	 */
	public TipoPuntoVentaSie getObjTipoPv() {
		return objTipoPv;
	}

	/**
	 * @param objTipoPv the objTipoPv to set
	 */
	public void setObjTipoPv(TipoPuntoVentaSie objTipoPv) {
		this.objTipoPv = objTipoPv;
	}

}
