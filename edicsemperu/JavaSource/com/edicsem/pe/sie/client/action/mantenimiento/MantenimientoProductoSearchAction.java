package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="productoSearch")
@SessionScoped
public class MantenimientoProductoSearchAction extends BaseMantenimientoAbstractAction{

	private EmpresaSie empresaSie;
	private Log log = LogFactory.getLog(MantenimientoProductoSearchAction.class);
	private List<ProductoSie> productoList;
	
	@EJB
	private ProductoService objProductoService;
	
	public MantenimientoProductoSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoProductoSearchAction'");
		}
		empresaSie = new EmpresaSie();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listarProductos 'MantenimientoProductoSearchAction' ");
		productoList = objProductoService.listarProductos();
		if (productoList == null) {
			productoList = new ArrayList<ProductoSie>();
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_PRODUCTO_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_PRODUCTO_FORM_PAGE;
	}
	
	/**
	 * @return the empresaSie
	 */
	public EmpresaSie getEmpresaSie() {
		return empresaSie;
	}

	/**
	 * @param empresaSie the empresaSie to set
	 */
	public void setEmpresaSie(EmpresaSie empresaSie) {
		this.empresaSie = empresaSie;
	}

	/**
	 * @return the productoList
	 */
	public List<ProductoSie> getProductoList() {
		return productoList= objProductoService.listarProductos();
	} 
	/**
	 * @param productoList the productoList to set
	 */
	public void setProductoList(List<ProductoSie> productoList) {
		this.productoList = productoList;
	}
 

}
