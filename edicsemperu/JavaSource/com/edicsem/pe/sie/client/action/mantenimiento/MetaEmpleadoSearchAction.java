package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.MetaEmpleadoSie;
import com.edicsem.pe.sie.service.facade.MetaEmpleadoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "metaEmpleadoSearch")
@SessionScoped
public class MetaEmpleadoSearchAction extends BaseMantenimientoAbstractAction {

	public static Log log = LogFactory.getLog(MetaEmpleadoSearchAction.class);
	
	public List<MetaEmpleadoSie> MetaEmpleadoList;
	
	@EJB
	private MetaEmpleadoService metaEmpleadoService;

	public MetaEmpleadoSearchAction() {
		init();
	}
	
	public void init() {
		log.info("Inicializando el Constructor de 'MetaEmpleadoSearchAction'");
		MetaEmpleadoList = new ArrayList<MetaEmpleadoSie>();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_META_EMPLEADO_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_META_EMPLEADO_FORM_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	public String consultar() throws Exception {
		
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar() ");
		MetaEmpleadoList = metaEmpleadoService.listarMetaEmpleado();
		if (MetaEmpleadoList == null) {
			MetaEmpleadoList = new ArrayList<MetaEmpleadoSie>();
		}
		return getViewList();
	}

	/**
	 * @return the metaEmpleadoList
	 */
	public List<MetaEmpleadoSie> getMetaEmpleadoList() {
		return MetaEmpleadoList;
	}

	/**
	 * @param metaEmpleadoList the metaEmpleadoList to set
	 */
	public void setMetaEmpleadoList(List<MetaEmpleadoSie> metaEmpleadoList) {
		MetaEmpleadoList = metaEmpleadoList;
	}
	
}
