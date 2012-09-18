package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
import com.edicsem.pe.sie.util.redirections.Redirections;

@ManagedBean(name="empresaForm")
@SessionScoped
public class MantenimientoEmpresaFormAction extends BaseMantenimientoAbstractAction{
	
	private EmpresaSie empresaSie;
	private Log log = LogFactory.getLog(MantenimientoEmpresaFormAction.class);
	
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
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#addNewRecord()
	 */
	public void addNewRecord() {
		log.info("addNewRecord");
		empresaSie.setNewRecord(true);
		Redirections.redirectionsPage("", "mantenimientoEmpresaForm");
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public void update() throws Exception {
		empresaSie.setNewRecord(false);
		insertar();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public void insertar() throws Exception {
		if (empresaSie.isNewRecord()) {
			empresaService.insertEmpresa(empresaSie);
		}else {
			empresaService.updateEmpresa(empresaSie);
		}
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
}
