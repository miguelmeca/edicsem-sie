package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.ContratoEmpleadoService;

@Stateless
public class ContratoEmpleadoServiceImpl implements ContratoEmpleadoService {

	@EJB
	private ContratoEmpleadoDAO objContratoEmpleadoDao;
	@EJB
	private EstadoGeneralDAO objEstadoGeneralDao;
	
	public static Log log = LogFactory.getLog(ContratoEmpleadoServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoEmpleadoService#insertContratoEmpleado(com.edicsem.pe.sie.entity.ContratoEmpleadoSie)
	 */
	public void insertContratoEmpleado(ContratoEmpleadoSie contrato) {
		contrato.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(56));
		objContratoEmpleadoDao.insertContratoEmpleado(contrato);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoEmpleadoService#updateContratoEmpleado(com.edicsem.pe.sie.entity.ContratoEmpleadoSie)
	 */
	public void updateContratoEmpleado(ContratoEmpleadoSie contrato) {
		objContratoEmpleadoDao.updateContratoEmpleado(contrato);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoEmpleadoService#findContratoEmpleado(int)
	 */
	public ContratoEmpleadoSie findContratoEmpleado(int id) {
		return objContratoEmpleadoDao.findContratoEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoEmpleadoService#listarContrato()
	 */
	public List listarContrato() {
		return objContratoEmpleadoDao.listarContrato();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoEmpleadoService#listarPatrocinados(int)
	 */
	public List listarPatrocinados(int idEmpleado) {
		return objContratoEmpleadoDao.listarPatrocinados(idEmpleado);
	}
}
