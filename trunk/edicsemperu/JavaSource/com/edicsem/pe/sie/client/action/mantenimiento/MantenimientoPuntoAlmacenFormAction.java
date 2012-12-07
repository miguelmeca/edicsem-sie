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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
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
	public String descripcion, direccion,ubigeoDefecto,idUbigeo;
	private String idProvincia, idDepartamento, idDistrito;
	private int ide, idEstadoGeneral;
	private boolean editMode, puntoVenta,defectoUbigeo;
	private boolean newRecord = false;
	private int estado;
	private PuntoVentaSie objAlmacenSie;

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
		puntoVenta = false;
		defectoUbigeo=true;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		defectoUbigeo=true;
		if(defectoUbigeo){
		comboManagerPunto.setIdDepartamento("15");
		comboManagerPunto.setIdProvincia("01");
		}
		
		ubigeoDefecto="";
		comboManagerPunto.setUbigeoDistriItems(null);
		editMode = true;
		puntoVenta = false;
		setNewRecord(true);
		objAlmacenSie = new PuntoVentaSie();
		return getViewList();
	}
	
	public void limpiardialog(){
		
		if (defectoUbigeo) {
			comboManagerPunto.setIdDepartamento("15");
			comboManagerPunto.setIdProvincia("01");
			log.info(" defecto lima true 1");
			ubigeoDefecto = "";
		} else {

			comboManagerPunto.setIdDepartamento(null);
			comboManagerPunto.setIdProvincia(null);
			log.info(" cambio ubigeo   false  otro");
		}
		comboManagerPunto.setUbigeoDeparItems(null);
		comboManagerPunto.setUbigeoProvinItems(null);
		comboManagerPunto.setUbigeoDistriItems(null);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	
	public String update() throws Exception {
		log.info("update()" + objAlmacenSie.getIdpuntoventa());

		PuntoVentaSie s = objAlmacenService.findAlmacen(objAlmacenSie.getIdpuntoventa());

		log.info(" id cargo " + s.getIdpuntoventa() + " des " + s.getDescripcion()+" direc "+s.getDireccion());

		setIdalmacen(s.getIdpuntoventa());
		setDescripcion(s.getDescripcion());
		setDireccion(s.getDireccion());
		setEstado(s.getTbEstadoGeneral().getIdestadogeneral());
		setIdUbigeo(s.getTbUbigeo().getIdubigeo()+"");
		if(objAlmacenSie.getAlmacen().equals("P"))
			puntoVenta=true;
		else puntoVenta=false;
	
		UbigeoSie ubigeo = objUbigeoService.findUbigeo(Integer.parseInt(getIdUbigeo()));
		setIdDepartamento(ubigeo.getCoddepartamento());
		comboManagerPunto.setIdDepartamento(idDepartamento);
		setIdProvincia(ubigeo.getCodprovincia());
		comboManagerPunto.setIdProvincia(idProvincia);
		setIdDistrito(ubigeo.getCoddistrito());
		ubigeoDefecto="";
		log.info("busquedaUbigeo "+ getIdDepartamento()+" - "+getIdProvincia()+" - "+getIdDistrito()+" - "+getIdUbigeo());
		setNewRecord(false);
		editMode = false;
		return getViewList();
	}

	public String DeshabilitarPunto() throws Exception {

		objAlmacenSie = new PuntoVentaSie();
		int parametroObtenido;
		PuntoVentaSie punto = new PuntoVentaSie();

		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'DeshabilitarPunto()'" + getIde());
			}
			parametroObtenido = getIde();
			log.info(" parametro ID "+ parametroObtenido);
			punto = objAlmacenService.findAlmacen(parametroObtenido);
			punto.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(14));

			objAlmacenService.updateAlmacen(punto);
			log.info("actualizando..... ");

		} catch (Exception e) {
			e.printStackTrace();

			descripcion = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
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
				+ objAlmacenSie.getDescripcion() +"  "+ "almacen"
				+ objAlmacenSie.getAlmacen() +" direc " + objAlmacenSie.getDireccion() );
		objAlmacenSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(13));
		int error = 0;
		try {
			if(puntoVenta) objAlmacenSie.setAlmacen("P");else objAlmacenSie.setAlmacen("A");
			objAlmacenSie.setTbUbigeo(objUbigeoService.findUbigeo(Integer.parseInt(idUbigeo)));
			
			if (isNewRecord()) {
				
				List<PuntoVentaSie> lista = mantenimientoPuntoAlmacenSearch.getAlmacenList();

				for (int i = 0; i < lista.size(); i++) {
					PuntoVentaSie s = lista.get(i);
					if (s.getDescripcion().equalsIgnoreCase(
							objAlmacenSie.getDescripcion())) {
						log.info("Error ... Ya se encuentra un almacen/Punto de Venta igual");
						error = 1;
						break;
					}
				}

				if (error == 0) {
					objAlmacenService.insertAlmacen(objAlmacenSie);
				} else {
					log.info("mensaje de error");
				}
			} else {

				objAlmacenSie.setIdpuntoventa(getIdalmacen());
				objAlmacenSie.setDescripcion(getDescripcion());
				objAlmacenSie.setDireccion(getDireccion());
				objAlmacenSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(13));
				objAlmacenService.updateAlmacen(objAlmacenSie);
				
				log.info("actualizado");
			}
		} catch (Exception e) {

			e.printStackTrace();
			descripcion = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		objAlmacenSie = new PuntoVentaSie();
		return mantenimientoPuntoAlmacenSearch.listar();
	}

	public void cambiar() {
		comboManagerPunto.setIdDepartamento(getIdDepartamento());
		comboManagerPunto.setIdProvincia(null);
		idProvincia = null;
		idUbigeo = null;
		log.info("cambiar   :D  --- ");
	}

	public void cambiar2() {
		comboManagerPunto.setIdDepartamento(getIdDepartamento());
		comboManagerPunto.setIdProvincia(getIdProvincia());
		log.info("cambiar 2  :D  --- ");
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
		return Constants.MANT_ALMACEN_FORM_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_ALMACEN_FORM_LIST_PAGE;
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
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
 

	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}

	/**
	 * @param idEstadoGeneral
	 *            the idEstadoGeneral to set
	 */
	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
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

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #insertar()
	 */


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
	 * @return the puntoVenta
	 */
	public boolean isPuntoVenta() {
		return puntoVenta;
	}

	/**
	 * @param puntoVenta the puntoVenta to set
	 */
	public void setPuntoVenta(boolean puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

}
