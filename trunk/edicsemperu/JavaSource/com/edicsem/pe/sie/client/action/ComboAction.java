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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.AlmacenService; 
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "comboAction")
@SessionScoped
public class ComboAction extends BaseMantenimientoAbstractAction {
	
	public static Log log = LogFactory.getLog(ComboAction.class);
	private String mensaje;
	 
	private List<SelectItem> almacenItems;
	private List<SelectItem> tipoitems;
	
	@EJB
	private AlmacenService objAlmacenService;
	@EJB
	private TipoProductoService objTipoProductoService;
	

	public ComboAction() {
		log.info("inicializando constructor");
		init();
	}

	public void init() {
		log.info("init()");
		tipoitems = new ArrayList<SelectItem>();  
		almacenItems = new ArrayList<SelectItem>();
	}


	/**
	 * @return the almacenItems
	 */
	public List<SelectItem> getAlmacenItems() {
		List lista = new ArrayList<PuntoVentaSie>();
		almacenItems = new ArrayList<SelectItem>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getAlmacenItems()'");
			}
			lista = objAlmacenService.listarAlmacenes();
			log.info(" tamaño " + lista.size());
			PuntoVentaSie punto;
			for (int i = 0; i < lista.size(); i++) {
				punto = new PuntoVentaSie();
				if (lista.get(i) != null) {
					punto = (PuntoVentaSie) lista.get(i);
					almacenItems.add(new SelectItem(punto.getIdpuntoventa(),
							punto.getDescripcion()));
				} else {
					break;
				}
			}
			log.info("finalizacion del metodo 'getAlmacenItems'  ");
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return almacenItems;
	}


	/**
	 * @return the tipoitems
	 */
	public List<SelectItem> getTipoitems() {
		tipoitems = new ArrayList<SelectItem>();
		List lista = new ArrayList<TipoProductoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoitems()'");
			}
			lista = objTipoProductoService.listarTipo();

			for (int i = 0; i < lista.size(); i++) {
				TipoProductoSie tipo = new TipoProductoSie();
					tipo = (TipoProductoSie) lista.get(i);
					tipoitems.add(new SelectItem(tipo.getIdtipoproducto(),
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
		return tipoitems;
	}

	/**
	 * @param almacenItems the almacenItems to set
	 */
	public void setAlmacenItems(List<SelectItem> almacenItems) {
		this.almacenItems = almacenItems;
	}
	/**
	 * @param tipoitems the tipoitems to set
	 */
	public void setTipoitems(List<SelectItem> tipoitems) {
		this.tipoitems = tipoitems;
	}

}
