package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.DetContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.DetContratoEmpleadoService;

@Stateless
public class DetContratoEmpleadoServiceImpl implements DetContratoEmpleadoService {

	@EJB
	private  DetContratoEmpleadoDAO objDetContratoEmpleadoDAO;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetContratoEmpleadoService#insertDetContratoEmpleado(com.edicsem.pe.sie.entity.DetContratoEmpleadoSie)
	 */
	public void insertDetContratoEmpleado(DetContratoEmpleadoSie d) {
		objDetContratoEmpleadoDAO.insertDetContratoEmpleado(d);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetContratoEmpleadoService#updateDetContratoEmpleado(com.edicsem.pe.sie.entity.DetContratoEmpleadoSie)
	 */
	public void updateDetContratoEmpleado(DetContratoEmpleadoSie d) {
		objDetContratoEmpleadoDAO.updateDetContratoEmpleado(d);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetContratoEmpleadoService#findDetContratoEmpleado(int)
	 */
	public DetContratoEmpleadoSie findDetContratoEmpleado(int id) {
		return objDetContratoEmpleadoDAO.findDetContratoEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetContratoEmpleadoService#listarDetContratoEmpleado()
	 */
	public List listarDetContratoEmpleado() {
		return objDetContratoEmpleadoDAO.listarDetContratoEmpleado();
	}
}
