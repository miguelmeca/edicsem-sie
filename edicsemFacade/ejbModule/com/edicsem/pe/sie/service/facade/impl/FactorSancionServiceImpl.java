package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.FactorSancionSie;
import com.edicsem.pe.sie.model.dao.FactorSancionDAO;
import com.edicsem.pe.sie.service.facade.FactorSancionService;

@Stateless
public class FactorSancionServiceImpl implements FactorSancionService {

	@EJB
	private  FactorSancionDAO objFactorSancionDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.FactorSancionService#insertFactorSancion(com.edicsem.pe.sie.entity.FactorSancionSie)
	 */
	public void insertFactorSancion(FactorSancionSie s) {
		objFactorSancionDao.insertFactorSancion(s);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.FactorSancionService#updateFactorSancion(com.edicsem.pe.sie.entity.FactorSancionSie)
	 */
	public void updateFactorSancion(FactorSancionSie s) {
		objFactorSancionDao.updateFactorSancion(s);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.FactorSancionService#findFactorSancion(int)
	 */
	public FactorSancionSie findFactorSancion(int id) {
		return objFactorSancionDao.findFactorSancion(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.FactorSancionService#listarFactorSancion()
	 */
	public List listarFactorSancion() {
		return objFactorSancionDao.listarFactorSancion();
	}
	
}
