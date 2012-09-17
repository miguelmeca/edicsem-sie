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
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoProductoAction")
@SessionScoped
public class MantenimientoProductoAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	public static Log log = LogFactory.getLog(MantenimientoProductoAction.class);
	private int TipoProducto ;
	private List<SelectItem> selectItems;
	private StreamedContent image; 
	private ProductoSie objProductoSie;
	private DataModel<ProductoSie> productosmodel;
	private ProductoSie selectedProducto;
	private boolean editMode;
	private ProductoSie nuevo ;
	
	@EJB
	private TipoProductoService objTipoProductoService;
	@EJB
	private ProductoService objProductoService;
	 
	public MantenimientoProductoAction() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
	}
	
	public void init() {
		log.info("init()");
		selectItems = new ArrayList<SelectItem>();
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
	 * @param editMode the editMode to set
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
	@SuppressWarnings("unchecked")
	public DataModel<ProductoSie> getProductosmodel()  throws Exception {
		 
		productosmodel = new ListDataModel<ProductoSie>(objProductoService.listarProductos());
		 
		return productosmodel;
	}
	public void setProductosmodel(DataModel<ProductoSie> productosmodel) {
		this.productosmodel = productosmodel;
	}


	public String Nuevo() throws Exception { 
		objProductoSie=new ProductoSie();
		
	return	getViewList();
		
		//
	}
	/**
	 * @return the nuevo
	 */
	public ProductoSie getNuevo() {
		return nuevo;
	}
	/**
	 * @param nuevo the nuevo to set
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
	 * @param image the image to set
	 */
	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public List<SelectItem> getSelectItems() {
		selectItems = new ArrayList<SelectItem>();
		List lista = new ArrayList<TipoProductoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getListaTipoProducto()'");
			}
			lista = objTipoProductoService.listarTipo();

			for (int i = 0; i < lista.size(); i++) {
				TipoProductoSie tipo = new TipoProductoSie();
					tipo = (TipoProductoSie) lista.get(i);
					selectItems.add(new SelectItem(tipo.getIdtipoproducto(),
							tipo.getNombretipoproducto()));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return selectItems;
	}
	public void cargarImagenInsertar(FileUploadEvent event) {  
		log.info("cargarImagenInsertar");
	    String photo = event.getFile().getFileName();
    	log.info("XD " + event.getFile().getFileName());
    	FileImageOutputStream imageOutput;
    	ServletContext servletContext = (ServletContext) FacesContext .getCurrentInstance().getExternalContext().getContext();
	   // String newFileName = servletContext.getRealPath("") + File.separator + "images" + File.separator + photo ;
    	 String newFileName = "C:\\proyecto-sie\\ws-sie\\edicsemperu\\WebContent" + File.separator + "images" + File.separator + photo ;
    	try {
    	    image = new DefaultStreamedContent(event.getFile().getInputstream());
    	    setImage(image);
    	    log.info("ruta " + newFileName +" - "+ event.getFile().getFileName());
    	    imageOutput = new FileImageOutputStream(new File(newFileName));
    	    objProductoSie.setRutaimagenproducto(newFileName);
    	    byte[] foto = event.getFile().getContents();
    	    imageOutput.write(foto, 0, foto.length);
			imageOutput.close();
    	    FacesMessage msg = new FacesMessage("Acción Completada!!!", event.getFile().getFileName() + " se cargó.");
    	    FacesContext.getCurrentInstance().addMessage(null, msg);
    	  } catch (Exception ex) {
    		  ex.printStackTrace();
    	} 
    }


	public String insertar(ActionEvent ae){
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()'");
			}
			
			TipoProductoSie t = objTipoProductoService.findTipoProducto(TipoProducto);
			log.info("seteo " + t.getIdtipoproducto() + " " + t.getNombretipoproducto());
			objProductoSie.setTbTipoProducto(t);
			if (objProductoSie.isNewRecord()) {
				log.info("insertando..... ");
				if(image==null){
					log.info("imagen nula");
					InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/images/bibliaXDefecto.png");
					log.info("ruta" + stream.toString());
					objProductoSie.setRutaimagenproducto(stream.toString());
				}else{
				log.info("image no nula");
				}
				insertarValidation(objProductoSie);
				objProductoService.insertProducto(objProductoSie);
				log.info("insertando..... ");
				objProductoSie.setNewRecord(false);
			} else {
				log.info("objProductoSie.isNewRecord() : "
						+ objProductoSie.isNewRecord());
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return getViewList();
	}

	/**
	 * @param selectItems
	 *            the selectItems to set
	 */
	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
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
	 * @param objProductoSie the objProductoSie to set
	 */
	public void setObjProductoSie(ProductoSie objProductoSie) {
		this.objProductoSie = objProductoSie;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */ 
	public String getViewList() { 
		return "mantenimientoProductoForm";
	}

}
