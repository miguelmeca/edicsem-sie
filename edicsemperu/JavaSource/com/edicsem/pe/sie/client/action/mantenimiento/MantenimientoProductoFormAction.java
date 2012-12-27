package com.edicsem.pe.sie.client.action.mantenimiento;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "productoForm")
@SessionScoped
public class MantenimientoProductoFormAction extends
		BaseMantenimientoAbstractAction {
	
	@ManagedProperty(value="#{comboAction}") 
	private ComboAction comboManager;

	private String mensaje;
	private Log log = LogFactory.getLog(MantenimientoProductoFormAction.class);
	private int TipoProducto, estadoProducto;
	private StreamedContent image;
	private ProductoSie objProductoSie;
	private ProductoSie selectedProducto;
	private boolean editMode;
	private boolean newRecord = false;
	
	@ManagedProperty(value = "#{productoSearch}")
	private MantenimientoProductoSearchAction productoSearch;
	
	@EJB
	private ProductoService objProductoService;

	public MantenimientoProductoFormAction() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
	}

	public void init() {
		log.info("init()");
		objProductoSie = new ProductoSie();
		image = null;
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
		return productoSearch.getViewMant();
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
		TipoProducto = objProductoSie.getTbTipoProducto().getIdtipoproducto();
		estadoProducto = objProductoSie.getTbEstadoGeneral().getIdestadogeneral();
		log.info("update()"+ TipoProducto +"  "+estadoProducto );
		InputStream stream = new FileInputStream(objProductoSie.getRutaimagenproducto());
		setImage( new DefaultStreamedContent(stream));
		setNewRecord(false);
		return productoSearch.getViewMant();
	}

	public String cargarImagenInsertar(FileUploadEvent event) {
		log.info("cargarImagenInsertar " + event.getFile().getFileName() );
		String photo = event.getFile().getFileName();
		FileImageOutputStream imageOutput;
		String newFileName = "C:\\proyecto-sie\\ws-sie\\edicsemperu\\WebContent"
				+ File.separator + "images" + File.separator + photo;
		try {
			image = new DefaultStreamedContent(event.getFile().getInputstream());
			
			log.info("ruta " + newFileName + " - " + event.getFile().getFileName());
			imageOutput = new FileImageOutputStream(new File(newFileName));
			if(objProductoSie.getRutaimagenproducto()==null){
			objProductoSie.setRutaimagenproducto(newFileName);
			log.info(" Ruta " + objProductoSie.getRutaimagenproducto()   );
			byte[] foto = event.getFile().getContents();
			imageOutput.write(foto, 0, foto.length);
			imageOutput.close();
			 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return productoSearch.getViewMant();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #insertar()
	 */
	public String insertar() {
		mensaje=null;
		String paginaRetorno="";
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()' " );
			}
			
			if (isNewRecord()) {
				if(objProductoSie.getStkmaximo()<= objProductoSie.getStkminimoproducto()){
					mensaje ="El stock minimo no puede ser mayor o igual al máximo";
					paginaRetorno = productoSearch.getViewMant();
				}
				else{
					 if (image == null) {
						log.info("imagen nula");
						String rutaDefecto ="C:\\proyecto-sie\\ws-sie\\edicsemperu\\WebContent\\images\\bibliaXDefecto.png";
						log.info("ruta" + rutaDefecto);
						objProductoSie.setRutaimagenproducto(rutaDefecto);
					 }
				log.info("a insertar "+TipoProducto +" " +estadoProducto);
				objProductoService.insertProducto(objProductoSie,TipoProducto, estadoProducto);
				mensaje ="Se registro correctamente";
				objProductoSie = new ProductoSie();
				limpiarCampos();
				paginaRetorno = productoSearch.getViewList();
				}
			} else {
				log.info("a actualizar "+ TipoProducto +" " +estadoProducto+ " ruta " + objProductoSie.getRutaimagenproducto());
				if(TipoProducto>0||estadoProducto>0){
					objProductoService.updateProducto(objProductoSie,TipoProducto, estadoProducto);
					mensaje ="Se actualizó correctamente";
					objProductoSie = new ProductoSie();
					limpiarCampos();
					paginaRetorno = productoSearch.getViewList();
				}
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		log.info("pagina retorno " +paginaRetorno);
		return paginaRetorno;
	}
	
	public void limpiarCampos(){
		setImage(null);
		TipoProducto=0;
		estadoProducto=0;
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
	 * @return the image
	 */
	public StreamedContent getImage() {
		log.info(" getImage() ");
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(StreamedContent image) {
		log.info(" setImage() ");
		this.image = image;
	}

	/**
	 * @return the tipoProducto
	 */
	public int getTipoProducto() {
		return TipoProducto;
	}

	/**
	 * @param tipoProducto
	 *            the tipoProducto to set
	 */
	public void setTipoProducto(int tipoProducto) {
		TipoProducto = tipoProducto;
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
		return Constants.MANT_PRODUCTO_FORM_LIST_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewMant()
	 */
	public String getViewMant() {
		log.info("getViewMant *******");
		return Constants.MANT_PRODUCTO_FORM_PAGE;
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
	 * @return the estadoProducto
	 */
	public int getEstadoProducto() {
		return estadoProducto;
	}

	/**
	 * @param estadoProducto
	 *            the estadoProducto to set
	 */
	public void setEstadoProducto(int estadoProducto) {
		this.estadoProducto = estadoProducto;
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

}
