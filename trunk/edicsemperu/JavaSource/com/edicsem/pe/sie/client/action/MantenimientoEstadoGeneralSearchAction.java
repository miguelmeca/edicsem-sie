package com.edicsem.pe.sie.client.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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

@ManagedBean(name="mantenimientoEstadoGeneralSearchAction")
@SessionScoped
public class MantenimientoEstadoGeneralSearchAction extends BaseMantenimientoAbstractAction {
	
	/*public static Log log = LogFactory.getLog(MantenimientoEstadoGeneralSearchAction.class);
	
	public String descripcion;
	public int idestadogeneral;

	private List<SelectItem> selectItems;
	private StreamedContent image; 
	private EstadoGeneralSie objEstadoGeneralSie;
	private DataModel<EstadoGeneralSie> EstadoGeneralmodel;
	private EstadoGeneralSie selectedestadoGeneral;
	
	
	public EstadoGeneralSie getObjEstadoGeneralSie() {
		return objEstadoGeneralSie;
	}



	public void setObjEstadoGeneralSie(EstadoGeneralSie objEstadoGeneralSie) {
		this.objEstadoGeneralSie = objEstadoGeneralSie;
	}



	public EstadoGeneralSie getSelectedestadoGeneral() {
		return selectedestadoGeneral;
	}



	public void setSelectedestadoGeneral(EstadoGeneralSie selectedestadoGeneral) {
		this.selectedestadoGeneral = selectedestadoGeneral;
	}



	public EstadogeneralService getObjEstadogeneralService() {
		return objEstadogeneralService;
	}





	private boolean editMode;
	private EstadoGeneralSie nuevo ;
	
	
	public List<SelectItem> getSelectItems() {
		return selectItems;
	}



	public void setEstadoGeneralmodel(DataModel<EstadoGeneralSie> estadoGeneralmodel) {
		EstadoGeneralmodel = estadoGeneralmodel;
	}



	

	public void setNuevo(EstadoGeneralSie nuevo) {
		this.nuevo = nuevo;
	}



	public int getIdestadogeneral() {
		return idestadogeneral;
	}



	public void setIdestadogeneral(int idestadogeneral) {
		this.idestadogeneral = idestadogeneral;
	}

	public StreamedContent getImage() {
		return image;
	}



	public void setImage(StreamedContent image) {
		this.image = image;
	}

	
	public DataModel<EstadoGeneralSie> getEstadoGeneralmodel() throws Exception {
		
		//EstadoGeneralmodel = new ListDataModel<estadoGeneralSie>(objEstadogeneralService.listarEstados(descripcion));
		
		return EstadoGeneralmodel;
	}




	public boolean isEditMode() {
		return editMode;
	}



	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}



	


	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}


	
	
	public static Log getLog() {
		return log;
	}



	public static void setLog(Log log) {
		MantenimientoEstadoGeneralSearchAction.log = log;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public void setObjEstadogeneralService (
			EstadogeneralService objEstadogeneralService) {
		this.objEstadogeneralService = objEstadogeneralService;
	}
	public MantenimientoEstadoGeneralSearchAction() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
	}

	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		selectItems = new ArrayList<SelectItem>();
		objEstadoGeneralSie = new EstadoGeneralSie();
		
		nuevo = new EstadoGeneralSie();
	}
	
	

	/*	public String insertar() throws Exception {
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()'");
			}
			
			

			if (objCargoEmpleadoSie.isNewRecord()) {
				log.info("insertando..... ");
				insertarValidation(objCargoEmpleadoSie);
				objEstadogeneralService.insertEstadogeneral(objCargoEmpleadoSie);
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
*/
	

	
	
	
}
