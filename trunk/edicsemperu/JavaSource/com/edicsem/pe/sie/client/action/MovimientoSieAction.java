package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.entity.TipoKardexProductoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.KardexService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoKardexService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "movimientoMercaderia")
@SessionScoped
public class MovimientoSieAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private int idproducto, idtipokardexproducto, idAlmacen,item;
	private KardexSie objKardexSie = new KardexSie();
	private List<SelectItem> tipoKardexItems;
	private List<KardexSie> data;
	private boolean editMode;
	private Log log = LogFactory.getLog(MovimientoSieAction.class);
	
	@EJB
	private TipoKardexService objTipoKardexService;

	@EJB
	private KardexService objKardexService;

	@EJB
	private ProductoService objProductoService;

	@EJB
	private AlmacenService objAlmacenService;
	
	public void cambioTipo(ValueChangeEvent evento) {
		log.info("TipoKardex " + evento.getNewValue().toString());
		if("1".equalsIgnoreCase(evento.getNewValue().toString()))
			 editMode =true;
		else if("2".equalsIgnoreCase(evento.getNewValue().toString()))
			editMode =false;
		else
			editMode =true;
	}
	
	public MovimientoSieAction() {
		log.info("inicializando constructor MovimientoSieAction");
		init();
	}

	public void init() {
		log.info("init()");
		data = new ArrayList<KardexSie>();
		objKardexSie = new KardexSie();
		tipoKardexItems = new ArrayList<SelectItem>();
	}

	/**
	 * @return the tipoKardexItems
	 */
	public List<SelectItem> getTipoKardexItems() {

		tipoKardexItems = new ArrayList<SelectItem>();
		List lista = new ArrayList<TipoProductoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoKardexItems()'");
			}
			lista = objTipoKardexService.listaTipoKardex();

			for (int i = 0; i < lista.size(); i++) {
				TipoKardexProductoSie tipo = new TipoKardexProductoSie();
				tipo = (TipoKardexProductoSie) lista.get(i);
				tipoKardexItems.add(new SelectItem(tipo
						.getIdtipokardexproducto(), tipo.getDescripcion()));
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tipoKardexItems;
	}

	/**
	 * @return the idtipokardexproducto
	 */
	public int getIdtipokardexproducto() {
		log.info(" tipo ------------ > " +idtipokardexproducto); 
		return idtipokardexproducto;
	}

	/**
	 * @param idtipokardexproducto
	 *            the idtipokardexproducto to set
	 */
	public void setIdtipokardexproducto(int idtipokardexproducto) {
		log.info(" tipo ------------ > " +idtipokardexproducto); 
		this.idtipokardexproducto = idtipokardexproducto;
	}

	/**
	 * @param tipoKardexItems
	 *            the tipoKardexItems to set
	 */
	public void setTipoKardexItems(List<SelectItem> tipoKardexItems) {
		this.tipoKardexItems = tipoKardexItems;
	}

	/**
	 * @return the data
	 */
	public List<KardexSie> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<KardexSie> data) {
		this.data = data;
	}
 

	/**
	 * @return the idAlmacen
	 */
	public int getIdAlmacen() {
		return idAlmacen;
	}

	/**
	 * @param idAlmacen
	 *            the idAlmacen to set
	 */
	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	/**
	 * @return the idproducto
	 */
	public int getIdproducto() {
		return idproducto;
	}

	/**
	 * @param idproducto
	 *            the idproducto to set
	 */
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	/**
	 * @return the objKardexSie
	 */
	public KardexSie getObjKardexSie() {
		return objKardexSie;
	}

	/**
	 * @param objKardexSie
	 *            the objKardexSie to set
	 */
	public void setObjKardexSie(KardexSie objKardexSie) {
		this.objKardexSie = objKardexSie;
	}

	public String anadir() {

		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'anadir()' ");
			}
			if(objKardexSie.getCantentrada()==null )objKardexSie.setCantentrada(0);
			
			if(objKardexSie.getCantsalida()==null )objKardexSie.setCantsalida(0);
			if (idtipokardexproducto == -1 || idproducto ==-1) {
				mensaje="Debe seleccionar un tipo de kardex v�lido";
			}else if ( idAlmacen ==-1 || idproducto ==-1) {
				mensaje="Debe seleccionar un tipo de almac�n v�lido";
			}else if ( idproducto ==-1) {
				mensaje="Debe seleccionar un producto v�lido";
			}else if(objKardexSie.getCantentrada() == 0 && objKardexSie.getCantsalida()==0){
				mensaje= "Debe ingresar cantidad";
			}
			 
			else{
			objKardexSie.setTbProducto(objProductoService.findProducto(getIdproducto()));
			 
			objKardexSie.setTbTipoKardexProducto(objTipoKardexService.findTipoKardex(getIdtipokardexproducto()));

			objKardexSie.setTbPuntoVenta(objAlmacenService.findAlmacen(getIdAlmacen()));
			//busco al item mayor y le aumento 1 al proximo
			int	itemMax=0;
			for (int j = 0; j < data.size(); j++) {
				itemMax = data.get(j).getItem();
				if(data.get(j).getItem()>=itemMax)
					itemMax= data.get(j).getItem();
			}
			
			objKardexSie.setItem(itemMax+1);
			data.add(objKardexSie);
			setData(data);
			objKardexSie = new KardexSie();
			mensaje="Se a�adio correctamente";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		 msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return getViewList();
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
			log.info("tama�o lista " + data.size());
			for (KardexSie kardex : data) {
				objKardexService.insertMovimiento(kardex);
			}
			mensaje= "Se registro";
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg); 
			
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
	
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void quitar( ActionEvent e){
	 	 
		log.info("kardex llego como "+  e.getComponent().getAttributes().get("d")); 
		int item = Integer.parseInt(e.getComponent().getAttributes().get("d").toString());
		 
		log.info("ahh!");
		for (int j = 0; j < data.size(); j++) {
			 if(data.get(j).getItem()== item){
				 data.remove(data.get(j));
			 }
		} 
		 
		setData(data);
		log.info("en el metodo Quitar!!!");
	}
}
