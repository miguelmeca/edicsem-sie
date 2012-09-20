package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.beans.EmpresaDTO;
import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="empresaForm")
@SessionScoped
public class MantenimientoEmpresaFormAction extends BaseMantenimientoAbstractAction{
	
	private EmpresaSie empresaSie;
	private EmpresaDTO dto;
	private Log log = LogFactory.getLog(MantenimientoEmpresaFormAction.class);
	private boolean newRecord = false; 
	private int estado;
	
	@EJB
	private EmpresaService empresaService; 
	@EJB 
	private EstadogeneralService objEstadoGeneralService;

	public MantenimientoEmpresaFormAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoEmpresaFormAction'");
		empresaSie = new EmpresaSie();
		dto = new EmpresaDTO();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		dto= new EmpresaDTO();
		log.info("agregar()");
		setNewRecord(true);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()");
		setNewRecord(false);
		return insertar();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		
		log.info("insertar()   "+ isNewRecord());
		empresaSie.setDescripcion(dto.getDescripcion());
		empresaSie.setEmail(dto.getEmail()); 
		empresaSie.setRazonsocial(dto.getRazonsocial());
		empresaSie.setNumruc(dto.getNumruc());
		empresaSie.setNumtelefono(dto.getNumtelefono());
		empresaSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(getEstado()));
		log.info("seteo()");
		
		if (isNewRecord()) {
			empresaService.insertEmpresa(empresaSie);
		}else {
			empresaService.updateEmpresa(empresaSie);
		}
		empresaSie = new EmpresaSie();
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
		log.info("seteando camos de empresa");
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
	/**
	 * @return the dto
	 */
	public EmpresaDTO getDto() {
		return dto;
	}
	/**
	 * @param dto the dto to set
	 */
	public void setDto(EmpresaDTO dto) {
		this.dto = dto;
	}

	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
