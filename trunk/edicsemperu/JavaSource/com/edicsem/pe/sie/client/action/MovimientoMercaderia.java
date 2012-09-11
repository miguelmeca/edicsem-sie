package com.edicsem.pe.sie.client.action;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean
@SessionScoped
public class MovimientoMercaderia extends BaseMantenimientoAbstractAction {

	private String mensaje;
	public static Log log = LogFactory.getLog(MovimientoMercaderia.class);
	 
	private ProductoSie objProductoSie;

	@EJB
	private TipoProductoService objTipoProductoService;
	@EJB
	private ProductoService objProductoService;

	public MovimientoMercaderia() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
	}
	
	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		 
		objProductoSie = new ProductoSie();
		objProductoSie.setCodproducto("");

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
