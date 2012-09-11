package com.edicsem.pe.sie.client.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean
@SessionScoped
public class MantenimientoProducto extends BaseMantenimientoAbstractAction {

	private String mensaje;
	public static Log log = LogFactory.getLog(MantenimientoProducto.class);
	private int TipoProducto ;
	private List<SelectItem> selectItems;
	private StreamedContent image; 
	private ProductoSie objProductoSie;

	@EJB
	private TipoProductoService objTipoProductoService;
	@EJB
	private ProductoService objProductoService;

	public MantenimientoProducto() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
	}
	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		selectItems = new ArrayList<SelectItem>();
		objProductoSie = new ProductoSie();
		objProductoSie.setCodproducto("");

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

	public String insertar() throws Exception {
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()'");
			}

			TipoProductoSie t = objTipoProductoService
					.findTipoProducto(TipoProducto);
			log.info("seteo " + t.getIdtipoproducto() + " "
					+ t.getNombretipoproducto());
			objProductoSie.setTbTipoProducto(t);
			if (objProductoSie.isNewRecord()) {
				log.info("insertando..... ");
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
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return getViewList();
	}

	public void cargarImG(FileUploadEvent event) {
		log.info("a cargar imagen ");
		String photo = event.getFile().getFileName();
		 
		UploadedFile data = event.getFile();

		ServletContext servletContext = (ServletContext) FacesContext .getCurrentInstance().getExternalContext().getContext();
		String newFileName = servletContext.getRealPath("") + File.separator + "img" + File.separator + photo + ".png";
		log.info("imagen " +  photo + " FilName  " + newFileName ) ;
		FileImageOutputStream imageOutput;
		try {
			 image = new DefaultStreamedContent(event.getFile().getInputstream());
			imageOutput = new FileImageOutputStream(new File(newFileName));

			byte[] data2 = data.getContents();
			imageOutput.write(data2, 0, data2.length);
			imageOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
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
	 * @param objProductoSie
	 *            the objProductoSie to set
	 */
	public void setObjProductoSie(ProductoSie objProductoSie) {
		this.objProductoSie = objProductoSie;
	}

}
