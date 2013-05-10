package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ParametroActividadSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.ParametroActividadDAO;
import com.edicsem.pe.sie.service.facade.ParametroActividadService;

@Stateless
public class ParametroActividadServiceImpl implements ParametroActividadService {
	
	@EJB
	private  ParametroActividadDAO objParametroDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ParametroActividadService#insertParametroActividad(com.edicsem.pe.sie.entity.ParametroActividadSie)
	 */
	public void insertParametroActividad(ParametroActividadSie p) {
		p.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(117));
		objParametroDao.insertParametroActividad(p);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ParametroActividadService#updateParametroActividad(com.edicsem.pe.sie.entity.ParametroActividadSie)
	 */
	public void updateParametroActividad(ParametroActividadSie p) {
		objParametroDao.updateParametroActividad(p);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ParametroActividadService#findParametroActividad(int)
	 */
	public ParametroActividadSie findParametroActividad(int id) {
		return objParametroDao.findParametroActividad(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ParametroActividadService#listarParametroActividad()
	 */
	public List listarParametroActividad() {
		return objParametroDao.listarParametroActividad();
	}
	
}
