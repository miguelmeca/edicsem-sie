package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.DragDropEvent;

import com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie;
import com.edicsem.pe.sie.service.facade.DetGrupoEmpleadoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="grupo")
@SessionScoped
public class MantenimientoDetEmpresaEmpleadoSearchAction extends BaseMantenimientoAbstractAction{

	private Log log = LogFactory.getLog(MantenimientoDetEmpresaEmpleadoSearchAction.class);
	
	private List<DetGrupoEmpleadoSie> detGrupoEmplList;
	private int idGrupo;
	@EJB
	private DetGrupoEmpleadoService detgrupoemplService;
	
	public MantenimientoDetEmpresaEmpleadoSearchAction() {
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoDetEmpresaEmpleadoSearchAction'");
		}
		idGrupo =0;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		detGrupoEmplList = new ArrayList<DetGrupoEmpleadoSie>();
		idGrupo=0;
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar() ' x grupo' " + idGrupo);
		detGrupoEmplList = detgrupoemplService.listarEmpleadosXGrupo(idGrupo);
		if (detGrupoEmplList == null) {
			detGrupoEmplList = new ArrayList<DetGrupoEmpleadoSie>();
		}
		return getViewList();
	}
	
	public void onCarDrop(DragDropEvent ddEvent) {  
        DetGrupoEmpleadoSie d = ((DetGrupoEmpleadoSie) ddEvent.getData());  
        droppedCars.add(d);  
        carsSmall.remove(d);  
    }
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_DET_EMPRESA_EMPLEADO_FORM_LIST_PAGE;
	}

	/**
	 * @return the detGrupoEmplList
	 */
	public List<DetGrupoEmpleadoSie> getDetGrupoEmplList() {
		return detGrupoEmplList;
	}

	/**
	 * @param detGrupoEmplList the detGrupoEmplList to set
	 */
	public void setDetGrupoEmplList(List<DetGrupoEmpleadoSie> detGrupoEmplList) {
		this.detGrupoEmplList = detGrupoEmplList;
	}

	/**
	 * @return the idGrupo
	 */
	public int getIdGrupo() {
		return idGrupo;
	}

	/**
	 * @param idGrupo the idGrupo to set
	 */
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	
}
