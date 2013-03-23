package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.DetTurnoEmplSie;
import com.edicsem.pe.sie.model.dao.DetTurnoEmplDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.DetTurnoEmplService;

@Stateless
public class DetTurnoEmplServiceImpl implements DetTurnoEmplService {

	@EJB
	private  DetTurnoEmplDAO objDetTurnoEmplDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetTurnoEmplService#insertDetTurnoEmpl(com.edicsem.pe.sie.entity.DetTurnoEmplSie)
	 */
	public void insertDetTurnoEmpl(DetTurnoEmplSie t) {
		objDetTurnoEmplDao.insertDetTurnoEmpl(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetTurnoEmplService#updateDetTurnoEmpl(com.edicsem.pe.sie.entity.DetTurnoEmplSie)
	 */
	public void updateDetTurnoEmpl(DetTurnoEmplSie t) {
		objDetTurnoEmplDao.updateDetTurnoEmpl(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetTurnoEmplService#findDetTurnoEmpl(int)
	 */
	public DetTurnoEmplSie findDetTurnoEmpl(int id) {
		return objDetTurnoEmplDao.findDetTurnoEmpl(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetTurnoEmplService#listarDetTurnoEmpl()
	 */
	public List listarDetTurnoEmpl() {
		return objDetTurnoEmplDao.listarDetTurnoEmpl();
	}
	
}
