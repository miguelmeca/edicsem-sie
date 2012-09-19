package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="empresaForm")
@SessionScoped
public class MantenimientoEmpresaFormAction extends BaseMantenimientoAbstractAction{
	
	private EmpresaSie empresaSie;
	private Log log = LogFactory.getLog(MantenimientoEmpresaFormAction.class);
	private boolean newRecord = false; 
	
	@EJB
	private EmpresaService empresaService;
	
	public MantenimientoEmpresaFormAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoEmpresaFormAction'");
		empresaSie = new EmpresaSie();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("addNewRecord");
		setNewRecord(true);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		setNewRecord(false);
		return insertar();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		if (isNewRecord()) {
			empresaService.insertEmpresa(empresaSie);
		}else {
			empresaService.updateEmpresa(empresaSie);
		}
		return getViewList();
	}

	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_EMPRESA_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_EMPRESA_FORM_PAGE;
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
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
	
	
}
