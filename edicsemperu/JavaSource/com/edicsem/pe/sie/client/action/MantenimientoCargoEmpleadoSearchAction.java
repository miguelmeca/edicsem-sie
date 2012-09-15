package com.edicsem.pe.sie.client.action;

import java.io.File;
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
import org.primefaces.model.UploadedFile;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoCargoEmpleadoSearchAction")
@SessionScoped
public class MantenimientoCargoEmpleadoSearchAction extends BaseMantenimientoAbstractAction {
	private String mensaje;
	public String descripcion;
	public static Log log = LogFactory.getLog(MantenimientoCargoEmpleadoSearchAction.class);
	private List<SelectItem> estadosItems;
	private StreamedContent image; 
	private CargoEmpleadoSie objCargoEmpleadoSie;
	private DataModel<CargoEmpleadoSie> CargoEmpleadomodel;
	private CargoEmpleadoSie selectedCargoEmpleado;
	private boolean editMode;
	private CargoEmpleadoSie nuevo ; 
	private  int idEstadoGeneral;

	@EJB 
	private CargoEmpleadoService objCargoEmpleadoService;

	@EJB 
	private EstadogeneralService objEstadoGeneralService;
 
	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}



	/**
	 * @param idEstadoGeneral the idEstadoGeneral to set
	 */
	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
	}



	public StreamedContent getImage() {
		return image;
	}



	public void setImage(StreamedContent image) {
		this.image = image;
	}



	public CargoEmpleadoSie getObjCargoEmpleadoSie() {
		return objCargoEmpleadoSie;
	}



	public void setObjCargoEmpleadoSie(CargoEmpleadoSie objCargoEmpleadoSie) {
		this.objCargoEmpleadoSie = objCargoEmpleadoSie;
	}



	
	 
	
	public DataModel<CargoEmpleadoSie> getCargoEmpleadomodel() throws Exception {
		

		CargoEmpleadomodel = new ListDataModel<CargoEmpleadoSie>(objCargoEmpleadoService.listarCargoEmpleado(descripcion));
		return CargoEmpleadomodel;
	}



	public void setCargoEmpleadomodel(DataModel<CargoEmpleadoSie> cargoEmpleadomodel) {
		CargoEmpleadomodel = cargoEmpleadomodel;
	}



	public CargoEmpleadoSie getSelectedCargoEmpleado() {
		return selectedCargoEmpleado;
	}



	public void setSelectedCargoEmpleado(CargoEmpleadoSie selectedCargoEmpleado) {
		this.selectedCargoEmpleado = selectedCargoEmpleado;
	}



	public boolean isEditMode() {
		return editMode;
	}



	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	
	
	public void setNuevo(CargoEmpleadoSie nuevo) {
		this.nuevo = nuevo;
	}
 

	public CargoEmpleadoSie getNuevo() {
		return nuevo;
	}



	
	
	
	public static Log getLog() {
		return log;
	}



	public static void setLog(Log log) {
		MantenimientoCargoEmpleadoSearchAction.log = log;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public CargoEmpleadoService getObjCargoEmpleadoService() {
		return objCargoEmpleadoService;
	}



	public void setObjCargoEmpleadoService(
			CargoEmpleadoService objCargoEmpleadoService) {
		this.objCargoEmpleadoService = objCargoEmpleadoService;
	}
	public MantenimientoCargoEmpleadoSearchAction() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
	}

	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		estadosItems = new ArrayList<SelectItem>();
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		objCargoEmpleadoSie.setDescipcion("");
		nuevo = new CargoEmpleadoSie();
	}
	
	public void NuevoCE(ActionEvent e) throws Exception {

	
		 
	}
	

	public String insertar() throws Exception {
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()'");
			}
				log.info(" ------ "+ idEstadoGeneral);	
			objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(idEstadoGeneral));
	
			if (objCargoEmpleadoSie.isNewRecord()) {
				log.info("insertando..... ");
				insertarValidation(objCargoEmpleadoSie);
				objCargoEmpleadoService.insertarCargoEmpleado(objCargoEmpleadoSie);
				log.info("insertando..... ");
				objCargoEmpleadoSie.setNewRecord(false);
			} else {
				log.info("objCargoEmpleadoSie.isNewRecord() : "
						+ objCargoEmpleadoSie.isNewRecord());
			}

		} catch (Exception e) {
			e.printStackTrace();
			descripcion = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
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
			descripcion = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}



	/**
	 * @return the estadosItems
	 */
	public List<SelectItem> getEstadosItems() {
		estadosItems = new ArrayList<SelectItem>();
		List lista = new ArrayList<TipoProductoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getListaTipoProducto()'");
			}
			lista = objEstadoGeneralService.listarEstados();

			for (int i = 0; i < lista.size(); i++) {
				EstadoGeneralSie e = new EstadoGeneralSie();
					e = (EstadoGeneralSie) lista.get(i);
					estadosItems.add(new SelectItem(e.getIdestadogeneral(),
							e.getDescripcion()));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	 
		return estadosItems;
	}



	/**
	 * @param estadosItems the estadosItems to set
	 */
	public void setEstadosItems(List<SelectItem> estadosItems) {
		this.estadosItems = estadosItems;
	}



	
	public String getMensaje() {
		return mensaje;
	}



	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
 	
	
}
