package com.edicsem.pe.sie.client.action.mantenimiento;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "productoForm")
@SessionScoped
public class MantenimientoProductoFormAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private Log log = LogFactory.getLog(MantenimientoProductoFormAction.class);
	private int TipoProducto, idFoto=1;
	private StreamedContent image;
	private ProductoSie objProductoSie,selectedProducto;
	private List<String> lista;
	private boolean newRecord = false;
	byte[] foto ;
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
		foto =null;
		lista= new ArrayList<String>();
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
		image = null;
		foto =null;
		setNewRecord(true);
		return getViewMant();
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
		if(objProductoSie.getRutaimagenproducto()!=null){
			InputStream stream = new FileInputStream(objProductoSie.getRutaimagenproducto());
			setImage( new DefaultStreamedContent(stream));
		}else{
			log.info("imagen nula ");
			setImage(null);
		}
		
		setNewRecord(false);
		return productoSearch.getViewMant();
	}

	public String cargarImagenInsertar(FileUploadEvent event) {
		log.info("cargarImagenInsertar** " + event.getFile().getFileName() );
		String photo = event.getFile().getFileName();
		FileImageOutputStream imageOutput;
		String newFileName = Constants.RUTA_IMAGENES_PRODUCTO + File.separator + photo;
		try {
			
			File outputFile = new File(newFileName);
			File parentFile = outputFile.getParentFile();
			
			if (!parentFile.exists()){
				parentFile.mkdirs();
			}
			
			setImage(new DefaultStreamedContent(event.getFile().getInputstream()));
			
			log.info("ruta " + newFileName + " - " + event.getFile().getFileName());
			imageOutput = new FileImageOutputStream(new File(newFileName));
			
			objProductoSie.setRutaimagenproducto(newFileName);
			log.info(" Ruta " + objProductoSie.getRutaimagenproducto()   );
			
			foto = event.getFile().getContents();
			imageOutput.write(foto, 0, foto.length);
			imageOutput.close();
			event.getFile().getInputstream().close();
			idFoto+=1;
			
		} catch (IOException e) {
			e.printStackTrace();
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
		log.info("Entering my method 'insertar()' " );
		mensaje=null;
		String paginaRetorno="";
		//Capturando el empleado en session
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		try {
			
			lista = objProductoService.listarCodigosProductos();
			objProductoSie.setCodproducto(objProductoSie.getCodproducto().trim().toUpperCase());
			if(!isNewRecord()){
				lista.remove(objProductoSie.getCodproducto());
			}
			int error = 0;
			for (int i = 0; i < lista.size(); i++) {
				if (lista.contains(objProductoSie.getCodproducto())) {
					log.info("Error ... Ya se encuentra un código igual");
					mensaje ="El código se encuentra asignado con otro producto";
					paginaRetorno =productoSearch.getViewMant();
					error = 1;
					break;
				}
			}
			if (error == 0) {
			if(objProductoSie.getStkmaximo()<= objProductoSie.getStkminimoproducto()){
				mensaje ="El stock minimo no puede ser mayor o igual al máximo";
				paginaRetorno = productoSearch.getViewMant();
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			else if (isNewRecord()) {
				if(image == null) {
					log.info("imagen nula");
					String rutaDefecto = Constants.RUTA_IMAGEN_DEFECTO;
					log.info("ruta" + rutaDefecto);
					objProductoSie.setRutaimagenproducto(rutaDefecto);
				}
				log.info("a insertar "+TipoProducto +" " );
				objProductoSie.setUsuariocreacion(sessionUsuario.getUsuario());
				objProductoService.insertProducto(objProductoSie,TipoProducto);
				objProductoSie = new ProductoSie();
				limpiarCampos();
				paginaRetorno = productoSearch.listar();
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, Constants.MESSAGE_REGISTRO_TITULO);
			} else {
				log.info("a actualizar "+ TipoProducto +" " + " ruta " + objProductoSie.getRutaimagenproducto());
				if(TipoProducto>0){
					objProductoSie.setUsuariomodifica(sessionUsuario.getUsuario());
					objProductoSie.setFechamodifica(new Timestamp(DateUtil.getToday().getTime().getTime()));
					objProductoService.updateProducto(objProductoSie,TipoProducto);
					objProductoSie = new ProductoSie();
					limpiarCampos();
					paginaRetorno = productoSearch.listar();
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, Constants.MESSAGE_ACTUALIZO_TITULO);
				}
			}
			}else{
				log.info("mensaje de error");
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		log.info("pagina retorno " +paginaRetorno);
		return paginaRetorno;
	}
	
	public void limpiarCampos(){
		setImage(null);
		TipoProducto=0;
		foto =null;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	
	public String consultar() throws Exception {
		log.info("en el consultar ");
		return getViewMant();
	}
	
	/**
	 * @return the image
	 */
	public StreamedContent getImage() {
		log.info(" getImage() "+ image);
		if(foto!=null){
			log.info(" foto!=null" );
		image =  new DefaultStreamedContent(new ByteArrayInputStream(foto));
		}
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
	
	/**
	 * @return the idFoto
	 */
	public int getIdFoto() {
		return idFoto;
	}

	/**
	 * @param idFoto the idFoto to set
	 */
	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}

	/**
	 * @return the selectedProducto
	 */
	public ProductoSie getSelectedProducto() {
		return selectedProducto;
	}

	/**
	 * @param selectedProducto the selectedProducto to set
	 */
	public void setSelectedProducto(ProductoSie selectedProducto) {
		this.selectedProducto = selectedProducto;
	}

	/**
	 * @return the lista
	 */
	public List<String> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<String> lista) {
		this.lista = lista;
	}
	
}
