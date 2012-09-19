package com.edicsem.pe.sie.client.action.mantenimiento;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.edicsem.pe.sie.beans.EmpresaDTO;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "productoForm")
@SessionScoped
public class MantenimientoProductoFormAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	public static Log log = LogFactory.getLog(MantenimientoProductoFormAction.class);
	private int TipoProducto; 
	private StreamedContent image;
	private ProductoSie objProductoSie;
	private ProductoSie selectedProducto;
	private boolean editMode;
	private ProductoSie nuevo;
	private boolean newRecord = false; 
	@EJB
	private TipoProductoService objTipoProductoService;
	@EJB
	private ProductoService objProductoService;

	public MantenimientoProductoFormAction() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
	}

	public void init() {
		log.info("init()"); 
		objProductoSie = new ProductoSie();
		nuevo = new ProductoSie();
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
	
	public String Nuevo() throws Exception {
		objProductoSie = new ProductoSie();
		return getViewList();
	}

	/**
	 * @return the nuevo
	 */
	public ProductoSie getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo
	 *            the nuevo to set
	 */
	public void setNuevo(ProductoSie nuevo) {
		this.nuevo = nuevo;
	}

	/**
	 * @return the image
	 */
	public StreamedContent getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public void cargarImagenInsertar(FileUploadEvent event) {
		log.info("cargarImagenInsertar");
		String photo = event.getFile().getFileName();
		log.info("XD " + event.getFile().getFileName());
		FileImageOutputStream imageOutput;
		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		// String newFileName = servletContext.getRealPath("") + File.separator
		// + "images" + File.separator + photo ;
		String newFileName = "C:\\proyecto-sie\\ws-sie\\edicsemperu\\WebContent"
				+ File.separator + "images" + File.separator + photo;
		try {
			image = new DefaultStreamedContent(event.getFile().getInputstream());
			setImage(image);
			log.info("ruta " + newFileName + " - "
					+ event.getFile().getFileName());
			imageOutput = new FileImageOutputStream(new File(newFileName));
			objProductoSie.setRutaimagenproducto(newFileName);
			byte[] foto = event.getFile().getContents();
			imageOutput.write(foto, 0, foto.length);
			imageOutput.close();
			FacesMessage msg = new FacesMessage("Acción Completada!!!", event
					.getFile().getFileName() + " se cargó.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() {
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()' " + TipoProducto);
			}

			if (TipoProducto == -1) {
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_ERROR_FATAL_TITULO,
						"Debe ingresar un tipo de producto válido");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return getViewList();
			} else {
				objProductoSie.setTbTipoProducto(objTipoProductoService.findTipoProducto(TipoProducto));

				if (objProductoSie.isNewRecord()) {
					if (image == null) {
						log.info("imagen nula");
						InputStream stream = ((ServletContext) FacesContext
								.getCurrentInstance().getExternalContext()
								.getContext())
								.getResourceAsStream("/images/bibliaXDefecto.png");
						log.info("ruta" + "/images/bibliaXDefecto.png");
						objProductoSie
								.setRutaimagenproducto("/images/bibliaXDefecto.png");
					}
					if (isNewRecord()) {
						objProductoService.insertProducto(objProductoSie);
					}else {
						objProductoService.updateProducto(objProductoSie);
					}
					 
				} else {
					log.info("objProductoSie.isNewRecord() : " + objProductoSie.isNewRecord());
				}
			}
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
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	public String getViewList() {
		return "mantenimientoProductoForm";
	}
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return "mantenimientoProductoFormList";
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()");
		setNewRecord(false);
		return getViewList() ;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		objProductoSie= new ProductoSie();
		log.info("agregar()");
		setNewRecord(true);
		return getViewMant();
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
}
