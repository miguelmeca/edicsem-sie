package com.edicsem.pe.sie.client.action.mantenimiento;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.service.facade.ContratoService;
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
	private ProductoSie objProductoSie;
	private ProductoSie selectedProducto;
	private boolean editMode;
	private boolean newRecord = false;
	
	@ManagedProperty(value = "#{productoSearch}")
	private MantenimientoProductoSearchAction productoSearch;
	
	@EJB
	private ContratoService objContratoService;

	public MantenimientoContratoFormAction() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
	}

	public void init() {
		log.info("init()");
		objProductoSie = new ProductoSie();
		editMode=true;
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
		log.info("update()"+ objProductoSie.getRutaimagenproducto() );
		  
		InputStream stream = new FileInputStream(objProductoSie.getRutaimagenproducto());
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
				log.info("Entering my method 'insertar()' " + objProductoSie.getRutaimagenproducto());
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
		
		return productoSearch.listar() ;
	}
	
	public void limpiarCampos(){
		Tipocasa=0;
		idempresa=0;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	
	public String consultar() throws Exception {
		log.info("en el consultar ");
		return getViewMant();
	}

	/**
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
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
		return Constants.MANT_PRODUCTO_FORM_PAGE;
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

	/**
	 * @return the productoSearch
	 */
	public MantenimientoProductoSearchAction getProductoSearch() {
		return productoSearch;
	}

	/**
	 * @param productoSearch the productoSearch to set
	 */
	public void setProductoSearch(MantenimientoProductoSearchAction productoSearch) {
		this.productoSearch = productoSearch;
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

}
