package com.edicsem.pe.sie.client.action;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.primefaces.model.StreamedContent;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.DomicilioEmpleadoService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoEmpleadoSearchAction")
@SessionScoped
public class MantenimientoEmpleadoSearchAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	public static Log log = LogFactory.getLog(MantenimientoEmpleadoSearchAction.class);
	private int cargoEmpleado;
	private int DomicilioPersona;
	private int TelefonoPersona;
	private int tipoDocumento;
	private List<SelectItem> itemsTipoDoc;
	private List<SelectItem> itemsCargoEmpl;
	//private StreamedContent image; 
	private EmpleadoSie objEmpleado;
	private DataModel<EmpleadoSie> empleadosmodel;
	private EmpleadoSie selectedEmpleado;
	private boolean editMode;
	private EmpleadoSie nuevo ;
	
	public boolean isEditMode() {
		return editMode;
	}
	/**
	 * @param editMode the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	public EmpleadoSie getSelectedEmpleado() {
		return selectedEmpleado;
	}
	public void setSelecteEmpleado(EmpleadoSie selectedEmpleado) {
		this.selectedEmpleado = selectedEmpleado;
	}
	@SuppressWarnings("unchecked")
	public DataModel<EmpleadoSie> getEmpleadosmodel()  throws Exception {
		 
		empleadosmodel = new ListDataModel<EmpleadoSie>(objEmpleadoService.listarEmpleados());
		 
		return empleadosmodel;
	}
	public void setEmpleadosmodel(DataModel<EmpleadoSie> empleadosmodel) {
		this.empleadosmodel = empleadosmodel;
	}
	
	@EJB 
	private TelefonoEmpleadoService objTelefonoEmpleadoService;
	@EJB 
	private TipoDocumentoService objTipoDocumentoService;
	@EJB 
	private  CargoEmpleadoService objCargoEmpleadoService;
	@EJB 
	private EmpleadoSieService objEmpleadoService;
	@EJB 
	private DomicilioEmpleadoService objDomicilioEmpleadoService;
	
	public MantenimientoEmpleadoSearchAction() {
		System.out.println("ESTOY EN MI CONSNTRUCTOR");
		log.info("inicializando mi constructor");
		init();
	}

	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		
		itemsCargoEmpl = new ArrayList<SelectItem>();
		itemsTipoDoc = new ArrayList<SelectItem>();
		objEmpleado = new EmpleadoSie();
		objEmpleado.setNombreemp("");
		nuevo = new EmpleadoSie();
		log.info("despues de inicializar  "); 
		
	}
	
	public void Nuevo(ActionEvent e) throws Exception {

		setObjEmpleado(null);
	}

	public EmpleadoSie getNuevo() {
		return nuevo;
	}
	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(EmpleadoSie nuevo) {
		this.nuevo = nuevo;
	}
	/**
	 * @return the itemsTipoDoc
	 */
	public List<SelectItem> getItemsTipoDoc() {
		log.info("prueba2*******************");
		List lista = new ArrayList<TipoDocumentoIdentidadSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getListaCargoEmpleado()'");
			}
			log.info("-----   +++ ");
			lista = objTipoDocumentoService.listarTipoDocumentos();

			for (int i = 0; i < lista.size(); i++) {
				TipoDocumentoIdentidadSie tipo = new TipoDocumentoIdentidadSie();
				if (lista.get(i) != null) {
					tipo = (TipoDocumentoIdentidadSie) lista.get(i);
					itemsTipoDoc.add(new SelectItem(tipo.getIdtipodocumentoidentidad(),
							tipo.getDescripcion()));
				} else {
					
					break;
				}
			}
			log.info("finalizacion del metodo 'getItemsCargo'");
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return itemsTipoDoc;
	}

	/**
	 * @param itemsTipoDoc the itemsTipoDoc to set
	 */
	public void setItemsTipoDoc(List<SelectItem> itemsTipoDoc) {
		this.itemsTipoDoc = itemsTipoDoc;
	}

	/**
	 * @return the itemsCargoEmpl
	 */
	public List<SelectItem> getItemsCargoEmpl() {
		log.info("prueba  cargo*******************");
		List lista = new ArrayList<TipoDocumentoIdentidadSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getListaCargoEmpleado()'");
			}
			log.info("-----   +++ ");
			lista = objCargoEmpleadoService.listarCargoEmpleado();

			for (int i = 0; i < lista.size(); i++) {
				CargoEmpleadoSie c = new CargoEmpleadoSie();
				if (lista.get(i) != null) {
					c = (CargoEmpleadoSie) lista.get(i);
					itemsCargoEmpl.add(new SelectItem(c.getIdcargoempleado(),
							c.getDescipcion()));
				} else {
					break;
				}
			}
			log.info("finalizacion del metodo 'getItemsCargo'");
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return itemsCargoEmpl;
	}

	/**
	 * @param itemsCargoEmpl the itemsCargoEmpl to set
	 */
	public void setItemsCargoEmpl(List<SelectItem> itemsCargoEmpl) {
		this.itemsCargoEmpl = itemsCargoEmpl;
	}


	public int getCargoEmpleado() {
		return cargoEmpleado;
	}

	public void setCargoEmpleado(int cargoEmpleado2) {
		cargoEmpleado = cargoEmpleado2;
	}

	public int getDomicilioPersona() {
		return DomicilioPersona;
	}

	public void setDomicilioPersona(int domicilioPersona) {
		DomicilioPersona = domicilioPersona;
	}

	public int getTelefonoPersona() {
		return TelefonoPersona;
	}

	public void setTelefonoPersona(int telefonoPersona) {
		TelefonoPersona = telefonoPersona;
	}

	public int getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento1) {
		tipoDocumento = tipoDocumento1;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #insertar()
	 */
	public String insertar() throws Exception {
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()'");
			}
			// todo eso se hace en el Service, no se hace en el Action.
			CargoEmpleadoSie c = objCargoEmpleadoService
					.buscarCargoEmpleado(cargoEmpleado);
			log.info("seteo " + c.getIdcargoempleado() + " "
					+ c.getDescipcion());
			objEmpleado.setTbCargoEmpleado(c);

			DomicilioPersonaSie d = objDomicilioEmpleadoService
					.buscarDomicilioEmpleado(DomicilioPersona);
			log.info("seteo " + d.getIddomiciliopersona() + " "
					+ d.getDomicilio());
			objEmpleado.setTbDomicilioPersona(d);

			TelefonoPersonaSie t = objTelefonoEmpleadoService
					.buscarTelefonoEmpleado(TelefonoPersona);
			log.info("seteo " + t.getIdtelefonopersona() + " "
					+ t.getTelefono());
			objEmpleado.setTbTelefonoPersona(t);

			TipoDocumentoIdentidadSie td = objTipoDocumentoService
					.buscarTipoDocumento(tipoDocumento);
			log.info("seteo " + td.getIdtipodocumentoidentidad() + " "
					+ td.getDescripcion());
			objEmpleado.setTbTipoDocumentoIdentidad(td);

			if (objEmpleado.isNewRecord()) {
				// objEmpleado.s
				log.info("insertando.....");
				insertarValidation(objEmpleado);
				objEmpleadoService.insertarEmpleado(objEmpleado);
				log.info("insertando.....");
				objEmpleado.setNewRecord(false);
			} else {
				log.info("objEmpleado.isNewRecord() : "
						+ objEmpleado.isNewRecord());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	public String getViewList() {
		return "index";
	}

	/**
	 * @return the objUsuario
	 */
	public EmpleadoSie getObjEmpleado() {
		return objEmpleado;
	}

	/**
	 * @param objUsuario
	 *            the objUsuario to set
	 */
	public void setObjEmpleado(EmpleadoSie objEmpleado) {
		this.objEmpleado = objEmpleado;
	}
 
	
}
