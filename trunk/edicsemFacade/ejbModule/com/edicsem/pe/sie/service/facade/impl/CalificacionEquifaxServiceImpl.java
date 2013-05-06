package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.CalificacionEquifaxSie;
import com.edicsem.pe.sie.model.dao.CalificacionEquifaxDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.CalificacionEquifaxService;

@Stateless
public class CalificacionEquifaxServiceImpl implements CalificacionEquifaxService {
	
	@EJB
	private  CalificacionEquifaxDAO objCalificacionDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CalificacionEquifaxService#insertCalificacion(com.edicsem.pe.sie.entity.CalificacionEquifaxSie)
	 */
	public void insertCalificacion(CalificacionEquifaxSie c) {
		c.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(115));
		objCalificacionDao.insertCalificacion(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CalificacionEquifaxService#updateCalificacion(com.edicsem.pe.sie.entity.CalificacionEquifaxSie)
	 */
	public void updateCalificacion(CalificacionEquifaxSie c) {
		objCalificacionDao.updateCalificacion(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CalificacionEquifaxService#findCalificacion(int)
	 */
	public CalificacionEquifaxSie findCalificacion(int id) {
		return objCalificacionDao.findCalificacion(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CalificacionEquifaxService#listarCalificacionEquifax()
	 */
	public List listarCalificacionEquifax() {
		return objCalificacionDao.listarCalificacionEquifax();
	}
	
}
