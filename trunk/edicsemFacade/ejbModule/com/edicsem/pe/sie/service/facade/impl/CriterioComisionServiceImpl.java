package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.CriterioComisionSie;
import com.edicsem.pe.sie.model.dao.CriterioComisionDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.CriterioComisionService;

@Stateless
public class CriterioComisionServiceImpl implements CriterioComisionService {

	@EJB
	private  CriterioComisionDAO objCriterioComisionDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CriterioComisionService#insertCriterioComision(com.edicsem.pe.sie.entity.CriterioComisionSie)
	 */
	public void insertCriterioComision(CriterioComisionSie c) {
		c.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(88));
		objCriterioComisionDao.insertCriterioComision(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CriterioComisionService#updateCriterioComision(com.edicsem.pe.sie.entity.CriterioComisionSie)
	 */
	public void updateCriterioComision(CriterioComisionSie c) {
		objCriterioComisionDao.updateCriterioComision(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CriterioComisionService#findCriterioComision(int)
	 */
	public CriterioComisionSie findCriterioComision(int id) {
		return objCriterioComisionDao.findCriterioComision(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CriterioComisionService#listarCriterioComision()
	 */
	public List listarCriterioComision() {
		return objCriterioComisionDao.listarCriterioComision();
	}
	
}
