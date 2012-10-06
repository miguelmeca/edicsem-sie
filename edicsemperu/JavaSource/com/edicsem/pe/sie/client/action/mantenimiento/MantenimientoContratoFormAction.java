package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "contratoForm")
@SessionScoped
public class MantenimientoContratoFormAction extends
		BaseMantenimientoAbstractAction {
	
	@ManagedProperty(value="#{comboAction}") 
	private ComboAction comboManager;

	private String mensaje;
	public static Log log = LogFactory.getLog(MantenimientoContratoFormAction.class);
	private int Tipocasa,idempresa;
	private String idProvincia,idDepartamento, idUbigeo, ubigeoDefecto;
	private ProductoSie objProductoSie;
	private ClienteSie objClienteSie;
	private DomicilioPersonaSie objDomicilioSie;
	private ContratoSie objContratoSie;
	private ProductoSie selectedProducto;
	private CobranzaSie objCobranzaSie;
	private boolean defectoUbigeo;
	private boolean newRecord = false;
	
	public MantenimientoContratoFormAction() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
	}

	public void init() {
		log.info("init()");
		objProductoSie = new ProductoSie();
		objDomicilioSie = new DomicilioPersonaSie();
		objClienteSie = new ClienteSie();
		objCobranzaSie = new CobranzaSie();
		defectoUbigeo=true;
	}
	
	public void cambioUbigeoDefecto() {
		 if(defectoUbigeo){
		log.info(" defecto lima " );
		ubigeoDefecto ="Lima - Lima - Lima";
		 }else
			 log.info(" cambio ubigeo " );
	}
 
	public void cambiar() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(null);
		idProvincia=null;
		idUbigeo = null;
		log.info("cambiar   :D  --- " );
	}
	
	public void cambiar2() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(getIdProvincia());
		log.info("cambiar 2  :D  --- " );
	}
	public String  ingresarUbigeo(){
		log.info("ingresarUbigeo :D  --- " + idUbigeo );
		ubigeoDefecto = "otro ubigeo";
		return getViewList();
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
		limpiarCampos();
		objProductoSie = new ProductoSie();
		setNewRecord(true);
		return getViewList();
	}
 
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #update()
	 */
	public String update() throws Exception {
		log.info("update()" );
		setNewRecord(false);
		return getViewList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #insertar()
	 */
	public String insertar() {
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()' ");
			}
			
			/*if (isNewRecord()) {
				
				log.info("a insertar "+TipoProducto +" " +estadoProducto);
				//objProductoService.insertProducto(objProductoSie,TipoProducto, estadoProducto);
				objProductoSie = new ProductoSie();
				limpiarCampos();
			} else {
				log.info("a actualizar "+ TipoProducto +" " +estadoProducto+ " ruta " + objProductoSie.getRutaimagenproducto());
				if(TipoProducto>0||estadoProducto>0){
				//objProductoService.updateProducto(objProductoSie,TipoProducto, estadoProducto);
				objProductoSie = new ProductoSie();
				limpiarCampos();
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return getViewList();
	}
 
	public void limpiarCampos(){
		 
	}
	
	public ProductoSie getSelectedProducto() {
		return selectedProducto;
	}

	public void setSelectedProducto(ProductoSie selectedProducto) {
		this.selectedProducto = selectedProducto;
	}

	/**
	 * @return the objProductoSie
	 */
	public ProductoSie getObjProductoSie() {
		return objProductoSie;
	}

	/**
	 * @param objProductoSie
	 *            the objProductoSie to set
	 */
	public void setObjProductoSie(ProductoSie objProductoSie) {
		this.objProductoSie = objProductoSie;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_CONTRATO_FORM_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_PRODUCTO_FORM_LIST_PAGE;
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
	 * @return the comboManager
	 */
	public ComboAction getComboManager() {
		return comboManager;
	}

	/**
	 * @param comboManager the comboManager to set
	 */
	public void setComboManager(ComboAction comboManager) {
		comboManager.setCodigoEstado(Constants.COD_ESTADO_TB_PRODUCTO);
		this.comboManager = comboManager;
	}

	public int getTipocasa() {
		return Tipocasa;
	}

	public void setTipocasa(int tipocasa) {
		Tipocasa = tipocasa;
	}

	public int getIdempresa() {
		return idempresa;
	}

	public void setIdempresa(int idempresa) {
		this.idempresa = idempresa;
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
	 * @return the objContratoSie
	 */
	public ContratoSie getObjContratoSie() {
		return objContratoSie;
	}

	/**
	 * @param objContratoSie the objContratoSie to set
	 */
	public void setObjContratoSie(ContratoSie objContratoSie) {
		this.objContratoSie = objContratoSie;
	}

	/**
	 * @return the objClienteSie
	 */
	public ClienteSie getObjClienteSie() {
		return objClienteSie;
	}

	/**
	 * @param objClienteSie the objClienteSie to set
	 */
	public void setObjClienteSie(ClienteSie objClienteSie) {
		this.objClienteSie = objClienteSie;
	}

	/**
	 * @return the objDomicilioSie
	 */
	public DomicilioPersonaSie getObjDomicilioSie() {
		return objDomicilioSie;
	}

	/**
	 * @param objDomicilioSie the objDomicilioSie to set
	 */
	public void setObjDomicilioSie(DomicilioPersonaSie objDomicilioSie) {
		this.objDomicilioSie = objDomicilioSie;
	}

	/**
	 * @return the objCobranzaSie
	 */
	public CobranzaSie getObjCobranzaSie() {
		return objCobranzaSie;
	}

	/**
	 * @param objCobranzaSie the objCobranzaSie to set
	 */
	public void setObjCobranzaSie(CobranzaSie objCobranzaSie) {
		this.objCobranzaSie = objCobranzaSie;
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
	
}
