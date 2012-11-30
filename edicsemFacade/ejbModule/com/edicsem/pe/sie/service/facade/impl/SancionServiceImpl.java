package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.DetSancionCargoSie;
import com.edicsem.pe.sie.entity.SancionSie;
import com.edicsem.pe.sie.model.dao.DetSancionCargoDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.FactorSancionDAO;
import com.edicsem.pe.sie.model.dao.SancionDAO;
import com.edicsem.pe.sie.service.facade.SancionService;

@Stateless
public class SancionServiceImpl implements SancionService {

	@EJB
	private  SancionDAO objSancionDao;
	@EJB
	private FactorSancionDAO objFactorSancionDao;
	@EJB
	private EstadoGeneralDAO objEstadoDao;
	@EJB
	private DetSancionCargoDAO objDetSancionCargoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.SancionService#insertSancion(com.edicsem.pe.sie.entity.SancionSie, int, java.util.List)
	 */
	public void insertSancion(SancionSie s, int factor, List<DetSancionCargoSie> detSancionCargo) {

		s.setTbFactorSancion(objFactorSancionDao.findFactorSancion(factor));
		s.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(46));
		for (int i = 0; i < detSancionCargo.size(); i++) {
			detSancionCargo.get(i).setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(46));
			objDetSancionCargoDao.insertDetSancionCargo(detSancionCargo.get(i));
		}
		objSancionDao.insertSancion(s);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.SancionService#updateSancion(com.edicsem.pe.sie.entity.SancionSie)
	 */
	public void updateSancion(SancionSie s) {
		objSancionDao.updateSancion(s);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.SancionService#findSancion(int)
	 */
	public SancionSie findSancion(int id) {
		return objSancionDao.findSancion(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.SancionService#listarSanciones(int)
	 */
	public List listarSanciones(int id) {
		return objSancionDao.listarSanciones(id);
	}
	 
}
