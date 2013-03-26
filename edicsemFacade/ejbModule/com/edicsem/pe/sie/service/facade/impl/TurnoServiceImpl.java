package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TurnoSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TurnoDAO;
import com.edicsem.pe.sie.service.facade.TurnoService;

@Stateless
public class TurnoServiceImpl implements TurnoService{

	@EJB
	private  TurnoDAO objTurnoDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TurnoDAO#insertTurno(com.edicsem.pe.sie.entity.TurnoSie)
	 */
	public void insertTurno(TurnoSie t) {
		t.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(96));
		objTurnoDao.insertTurno(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TurnoDAO#updateTurno(com.edicsem.pe.sie.entity.TurnoSie)
	 */
	public void updateTurno(TurnoSie t) {
		objTurnoDao.updateTurno(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TurnoDAO#findTurno(int)
	 */
	public TurnoSie findTurno(int id) {
		return objTurnoDao.findTurno(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TurnoDAO#listarTurno()
	 */
	public List listarTurno() {
		return objTurnoDao.listarTurno();
	}
	
}
