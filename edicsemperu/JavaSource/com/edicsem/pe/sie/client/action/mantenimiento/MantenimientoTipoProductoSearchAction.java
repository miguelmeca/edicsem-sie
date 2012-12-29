package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="tipoProductoSearch")
@SessionScoped
public class MantenimientoTipoProductoSearchAction extends BaseMantenimientoAbstractAction{

	public static Log log = LogFactory.getLog(MantenimientoTipoProductoSearchAction.class);	
	
	
	private List<TipoProductoSie> tipoProdList;
	private TipoProductoSie objTipoProdSie;
	private TipoProductoSie nuevo;
	//private int idEstadoGeneral;
	
	@EJB
	private TipoProductoService tipoProdService;
	
	public void init() {
		log.info("init()");
		objTipoProdSie= new TipoProductoSie();
		/*objTipoProdSie.setCodtipoproducto("");
		objTipoProdSie.setFechacreacion("");
		objTipoProdSie.setNumruc("");
		objTipoProdSie.setNumtelefono("");
		objTipoProdSie.setNumtelefono("");
		objTipoProdSie.setEmail("");*/		
		nuevo = new TipoProductoSie();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarTipoProducto 'MantenimientoTipoProductoSearchAction' ");
		tipoProdList = tipoProdService.listarTipo();
		return getViewList();
	}

	public String getViewList() {
		return Constants.MANT_TIPO_PRODUCTO_FORM_LIST_PAGE;
	}

	/**
	 * @return the tipoProdList
	 */
	public List<TipoProductoSie> getTipoProdList() {
		return tipoProdList;
	}

	/**
	 * @param tipoProdList the tipoProdList to set
	 */
	public void setTipoProdList(List<TipoProductoSie> tipoProdList) {
		this.tipoProdList = tipoProdList;
	}

	/**
	 * @return the objTipoProdSie
	 */
	public TipoProductoSie getObjTipoProdSie() {
		return objTipoProdSie;
	}

	/**
	 * @param objTipoProdSie the objTipoProdSie to set
	 */
	public void setObjTipoProdSie(TipoProductoSie objTipoProdSie) {
		this.objTipoProdSie = objTipoProdSie;
	}

	/**
	 * @return the nuevo
	 */
	public TipoProductoSie getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(TipoProductoSie nuevo) {
		this.nuevo = nuevo;
	}

}
