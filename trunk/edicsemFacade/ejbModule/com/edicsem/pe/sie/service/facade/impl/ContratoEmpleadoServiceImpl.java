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
		contrato.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(66));
		objContratoEmpleadoDao.insertContratoEmpleado(contrato);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoEmpleadoService#updateContratoEmpleado(com.edicsem.pe.sie.entity.ContratoEmpleadoSie)
	 */
	public void updateContratoEmpleado(ContratoEmpleadoSie contrato) {
		log.info("en el servicio");
		objContratoEmpleadoDao.updateContratoEmpleado(contrato);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoEmpleadoService#findContratoEmpleado(int)
	 */
	public ContratoEmpleadoSie findContratoEmpleado(int id) {
		log.info("en el servicio"+ id);
		return objContratoEmpleadoDao.findContratoEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoEmpleadoService#listarContrato()
	 */
	public List listarContrato() {
		return objContratoEmpleadoDao.listarContrato();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoEmpleadoService#listarPatrocinados(int, java.lang.String, java.lang.String)
	 */
	public List listarPatrocinados(int idEmpleado,String fechaInicio,String fechaFin) {
		return objContratoEmpleadoDao.listarPatrocinados(idEmpleado, fechaInicio, fechaFin );
	}


	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoEmpleadoService#listarxCargo(int)
	 */
	public List listarxCargo(int cargo) {
		return objContratoEmpleadoDao.listarxCargo(cargo);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoEmpleadoService#listarCargoXEmp(int)
	 */
	public List listarCargoXEmp(int idEmpleado) {
		return objContratoEmpleadoDao.listarCargoXEmp(idEmpleado);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoEmpleadoService#verificarEmpleadoConCargo(int)
	 */
	public boolean verificarEmpleadoConCargo(int idcargo) {
		log.info("en el servicio" + idcargo);
		return objContratoEmpleadoDao.verificarEmpleadoConCargo(idcargo);
	}

	
}
