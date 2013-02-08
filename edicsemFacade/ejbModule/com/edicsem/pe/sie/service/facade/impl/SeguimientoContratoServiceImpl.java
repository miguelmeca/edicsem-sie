package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.SeguimientoContratoSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.SeguimientoContratoDAO;
import com.edicsem.pe.sie.service.facade.SeguimientoContratoService;

@Stateless
public class SeguimientoContratoServiceImpl implements SeguimientoContratoService {

	@EJB
	private  SeguimientoContratoDAO objSegContratoDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.SeguimientoContratoService#insertSeguimientoContrato(com.edicsem.pe.sie.entity.SeguimientoContratoSie)
	 */
	public void insertSeguimientoContrato(SeguimientoContratoSie s) {
		objSegContratoDao.insertSeguimientoContrato(s);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.SeguimientoContratoService#updateSeguimientoContrato(com.edicsem.pe.sie.entity.SeguimientoContratoSie)
	 */
	public void updateSeguimientoContrato(SeguimientoContratoSie s) {
		objSegContratoDao.updateSeguimientoContrato(s);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.SeguimientoContratoService#findSeguimientoContrato(int)
	 */
	public SeguimientoContratoSie findSeguimientoContrato(int id) {
		return objSegContratoDao.findSeguimientoContrato(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.SeguimientoContratoService#listarSeguimientoContrato(int)
	 */
	public List listarSeguimientoContrato(int codcontrato) {
		return objSegContratoDao.listarSeguimientoContrato(codcontrato);
	}
	
}
