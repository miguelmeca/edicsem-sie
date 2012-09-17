package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bsh.Console;

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
import com.edicsem.pe.sie.util.FaceMessage.FaceMessage;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoEmpleadoFormAction")
@SessionScoped
public class MantenimientoEmpleadoFormAction extends BaseMantenimientoAbstractAction {

	public static Log log = LogFactory.getLog(MantenimientoEmpleadoFormAction.class);
	private int CargoEmpleado;
	private int DomicilioPersona;
	private int TelefonoPersona;
	private int TipoDocumento;
	private String mensaje;
	private EmpleadoSie objEmpleado;
	private List<SelectItem> itemsTipoDoc;
	private int codigoTipoDocumento;
	private int codigoCargoEmpleado;
	
	@EJB 
	private TelefonoEmpleadoService objTelefonoEmpleadoService;
	@EJB 
	private TipoDocumentoService objTipoDocumentoService;
	@EJB 
	private CargoEmpleadoService objCargoEmpleadoService;
	@EJB 
	private EmpleadoSieService objEmpleadoService;
	@EJB 
	private DomicilioEmpleadoService objDomicilioEmpleadoService;
	
	public MantenimientoEmpleadoFormAction() {
		System.out.println("ESTOY EN MI CONSNTRUCTOR");
		log.info("inicializando mi constructor");
		init();
	}

	public void init() {
		log.info("init()");
		objEmpleado = new EmpleadoSie();
		itemsTipoDoc = new ArrayList<SelectItem>();
		log.info("despues de inicializar 22 ");
	}

	
	/**
	 * @return the itemsTipoDoc
	 */
	public List<SelectItem> getItemsTipoDoc() {
		log.info("prueba2*******************");
		List lista = new ArrayList<TipoDocumentoIdentidadSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getListaTipoDoc()'");
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
			log.info("finalizacion del metodo 'getItemstipoDoc'");
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
	

	/**
	 * @param itemsCargoEmpl the itemsCargoEmpl to set
	 */
	
	public int getCargoEmpleado() {
		return CargoEmpleado;
	}

	public void setCargoEmpleado(int cargoEmpleado) {
		CargoEmpleado = cargoEmpleado;
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
		return TipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #insertar()
	 */
	public void insertar() throws Exception {
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()'");
			}
			/*// todo eso se hace en el Service, no se hace en el Action.
			CargoEmpleadoSie c = objCargoEmpleadoService.buscarCargoEmpleado(CargoEmpleado);
			log.info("seteo " + c.getIdcargoempleado() + " "+ c.getDescipcion());
			objEmpleado.setTbCargoEmpleado(c);*/
			log.info("aaaa"+ codigoTipoDocumento);
			
			
			if (codigoTipoDocumento !=-1 || codigoCargoEmpleado!=-1) {
				TipoDocumentoIdentidadSie tipo = objTipoDocumentoService.buscarTipoDocumento(TipoDocumento);
				log.info("seteo " + tipo.getIdtipodocumentoidentidad() + " "+ tipo.getDescripcion());
				objEmpleado.setTbTipoDocumentoIdentidad(tipo);
				
				CargoEmpleadoSie c = objCargoEmpleadoService.buscarCargoEmpleado(CargoEmpleado);
				log.info("seteo " + c.getIdcargoempleado() + " "+ c.getDescripcion());
				objEmpleado.setTbCargoEmpleado(c);
				
				DomicilioPersonaSie d = objDomicilioEmpleadoService.buscarDomicilioEmpleado(DomicilioPersona);
				log.info("seteo " + d.getIddomiciliopersona() + " "+ d.getDomicilio());
				objEmpleado.setTbDomicilioPersona(d);

				TelefonoPersonaSie t = objTelefonoEmpleadoService.buscarTelefonoEmpleado(TelefonoPersona);
				log.info("seteo " + t.getIdtelefonopersona() + " "+ t.getTelefono());
				objEmpleado.setTbTelefonoPersona(t);

				/*TipoDocumentoIdentidadSie td = objTipoDocumentoService.buscarTipoDocumento(TipoDocumento);
				log.info("seteo " + td.getIdtipodocumentoidentidad() + " "+ td.getDescripcion());
				objEmpleado.setTbTipoDocumentoIdentidad(td);*/
				
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
			}else{
				log.info("no se encontró tipoDocumento");
				//FaceMessage.FaceMessageError(Constants.TITULO_MESSAGE_ERROR_COMBO, Constants.CODIGO_TIPO_DOCUMENTO_MESSAGE);
				//FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.TITULO_MESSAGE_ERROR_COMBO, Constants.CODIGO_TIPO_DOCUMENTO_MESSAGE);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		//return getViewList();
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

	/**
	 * @return the codigoTipoDocumento
	 */
	public int getCodigoCargoEmpleado() {
		return codigoCargoEmpleado;
	}

	/**
	 * @param codigoTipoDocumento the codigoTipoDocumento to set
	 */
	public void setCodigoCargoEmpleado(int codigoCargoEmpleado) {
		this.codigoCargoEmpleado = codigoCargoEmpleado;
	}
	
	public int getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	/**
	 * @param codigoTipoDocumento the codigoTipoDocumento to set
	 */
	public void setCodigoTipoDocumento(int codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}
	
	
	
}
