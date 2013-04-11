package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.HistoricoObservacionesSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.HistoricoObservacionesDAO;
import com.edicsem.pe.sie.service.facade.HistoricoObservacionesService;

@Stateless
public class HistoricoObservacionesServiceImpl implements HistoricoObservacionesService {
	
	@EJB
	private  HistoricoObservacionesDAO objHistoricoObservacionesDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HistoricoObservacionesService#insertHistoricoObservaciones(com.edicsem.pe.sie.entity.HistoricoObservacionesSie)
	 */
	public void insertHistoricoObservaciones(HistoricoObservacionesSie c) {
		objHistoricoObservacionesDao.insertHistoricoObservaciones(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HistoricoObservacionesService#updateHistoricoObservaciones(com.edicsem.pe.sie.entity.HistoricoObservacionesSie)
	 */
	public void updateHistoricoObservaciones(HistoricoObservacionesSie c) {
		objHistoricoObservacionesDao.updateHistoricoObservaciones(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HistoricoObservacionesService#findHistoricoObservaciones(int)
	 */
	public HistoricoObservacionesSie findHistoricoObservaciones(int id) {
		return objHistoricoObservacionesDao.findHistoricoObservaciones(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HistoricoObservacionesService#listarHistoricoObservaciones()
	 */
	public List listarHistoricoObservaciones() {
		return objHistoricoObservacionesDao.listarHistoricoObservaciones();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HistoricoObservacionesService#listarHistorial(int)
	 */
	public List listarHistorial(int idcontrato) {
		return objHistoricoObservacionesDao.listarHistorial(idcontrato);
	}
	
}
