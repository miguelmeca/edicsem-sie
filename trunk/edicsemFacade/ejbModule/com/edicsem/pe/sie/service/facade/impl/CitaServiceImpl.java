package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.CitaSie;
import com.edicsem.pe.sie.model.dao.CitaDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.CitaService;

@Stateless
public class CitaServiceImpl implements CitaService {

	@EJB
	private  CitaDAO objCitaDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CitaService#insertCita(com.edicsem.pe.sie.entity.CitaSie)
	 */
	public void insertCita(CitaSie c) {
		objCitaDao.insertCita(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CitaService#updateCita(com.edicsem.pe.sie.entity.CitaSie)
	 */
	public void updateCita(CitaSie c) {
		objCitaDao.updateCita(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CitaService#findCita(int)
	 */
	public CitaSie findCita(int id) {
		return objCitaDao.findCita(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CitaService#listarCitas()
	 */
	public List listarCitas() {
		return objCitaDao.listarCitas();
	}
	
}
