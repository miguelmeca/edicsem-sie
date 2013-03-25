package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoTurnoSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TipoTurnoDAO;
import com.edicsem.pe.sie.service.facade.TipoTurnoService;

@Stateless
public class TipoTurnoServiceImpl implements TipoTurnoService {

	@EJB
	private  TipoTurnoDAO objTipoTurnoDAO;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoTurnoService#insertTipoTurno(com.edicsem.pe.sie.entity.TipoTurnoSie)
	 */
	public void insertTipoTurno(TipoTurnoSie t) {
		objTipoTurnoDAO.insertTipoTurno(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoTurnoService#updateTipoTurno(com.edicsem.pe.sie.entity.TipoTurnoSie)
	 */
	public void updateTipoTurno(TipoTurnoSie t) {
		objTipoTurnoDAO.updateTipoTurno(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoTurnoService#findTipoTurno(int)
	 */
	public TipoTurnoSie findTipoTurno(int id) {
		return objTipoTurnoDAO.findTipoTurno(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoTurnoService#listarTipoTurno()
	 */
	public List listarTipoTurno() {
		return objTipoTurnoDAO.listarTipoTurno();
	}
	
}
