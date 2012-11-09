package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.ParametroSistemaSie;
import com.edicsem.pe.sie.service.facade.ParametroService;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoParametroSearchAction")
@SessionScoped
public class MantenimientoParametroSearchAction extends BaseMantenimientoAbstractAction {
	/*variables*/
	private ParametroSistemaSie objParametro;
	private List<ParametroSistemaSie> parametroList;
	private boolean editMode;
	
	@EJB 
	private ParametroService objParametroService;
	
	public static Log log = LogFactory.getLog(MantenimientoParametroSearchAction.class);
	
	public MantenimientoParametroSearchAction() {
		log.info("ESTOY EN MI CONSTRUCTOR");
		log.info("inicializando mi constructor");
		init();
	}

	/*inicializamos los  objetos utilizados*/
	public void init() {
		log.info("init()");
		objParametro = new ParametroSistemaSie();
		log.info("despues de inicializar  ");		
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	/*método que lista al hacer click en el menú del template*/
	public String listar() {
		// TODO Auto-generated method stub
		log.info("listarparametro 'MantenimientoParametroSearchAction' ");
		parametroList = objParametroService.listarParametros();
		if (parametroList == null) {
			parametroList = new ArrayList<ParametroSistemaSie>();
		}
		return getViewList();
	}
	
	/*GETs Y SETs*/
	public String getViewList() {
		return "mantenimientoParametrosList";
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
	 * @return the objParametro
	 */
	public ParametroSistemaSie getObjParametro() {
		return objParametro;
	}

	/**
	 * @param objParametro the objParametro to set
	 */
	public void setObjParametro(ParametroSistemaSie objParametro) {
		this.objParametro = objParametro;
	}

	/**
	 * @return the parametroList
	 */
	public List<ParametroSistemaSie> getParametroList() {
		return parametroList;
	}

	/**
	 * @param parametroList the parametroList to set
	 */
	public void setParametroList(List<ParametroSistemaSie> parametroList) {
		this.parametroList = parametroList;
	}
    
}
