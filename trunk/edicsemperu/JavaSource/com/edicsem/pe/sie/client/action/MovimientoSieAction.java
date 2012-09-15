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
import javax.faces.model.SelectItem;

import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.TipoKardexProductoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.KardexService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoKardexService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "movimientoMercaderia")
@SessionScoped
public class MovimientoSieAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private int idproducto, tipoKardex, idtipokardexproducto, idAlmacen;
	private KardexSie objKardexSie = new KardexSie();
	private List<SelectItem> tipoKardexItems;
	private List<KardexSie> data;

	@EJB
	private TipoKardexService objTipoKardexService;

	@EJB
	private KardexService objKardexService;

	@EJB
	private ProductoService objProductoService;

	@EJB
	private AlmacenService objAlmacenService;

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
				log.info("Entering my method 'getListaTipoProducto()'");
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
		return idtipokardexproducto;
	}

	/**
	 * @param idtipokardexproducto
	 *            the idtipokardexproducto to set
	 */
	public void setIdtipokardexproducto(int idtipokardexproducto) {
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
	 * @return the tipoKardex
	 */
	public int getTipoKardex() {
		return tipoKardex;
	}

	/**
	 * @param tipoKardex
	 *            the tipoKardex to set
	 */
	public void setTipoKardex(int tipoKardex) {
		this.tipoKardex = tipoKardex;
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
				log.info("Entering my method 'anadir()'");
			}
			/*3 servicios diferentes*/
			
			
			objKardexSie.setTbProducto(objProductoService
					.findProducto(getIdproducto()));
			log.info("se capturo producto "
					+ objKardexSie.getTbProducto().getDescripcionproducto());
			objKardexSie.setTbTipoKardexProducto(objTipoKardexService
					.findTipoKardex(getIdtipokardexproducto()));

			objKardexSie.setTbPuntoVenta(objAlmacenService
					.findAlmacen(getIdAlmacen()));
			/* Invocar solo a un servicio o Dao   */
			data.add(objKardexSie);
			setData(data);
			objKardexSie = new KardexSie();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		/* Insertando kardex total */
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()'");
			}
			log.info("tamaño lista " + data.size());
			for (KardexSie kardex : data) {
				objKardexService.insertMovimiento(kardex);
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
		return "movimientoMercaderiaForm";
	}

}
