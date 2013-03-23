package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.CarroSie;
import com.edicsem.pe.sie.model.dao.CarroDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.CarroService;

@Stateless
public class CarroServiceImpl implements CarroService {

	@EJB
	private  CarroDAO objCarroDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CarroService#insertCarro(com.edicsem.pe.sie.entity.CarroSie)
	 */
	public void insertCarro(CarroSie t) {
		objCarroDao.insertCarro(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CarroService#updateCarro(com.edicsem.pe.sie.entity.CarroSie)
	 */
	public void updateCarro(CarroSie t) {
		objCarroDao.updateCarro(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CarroService#findCarro(int)
	 */
	public CarroSie findCarro(int id) {
		return objCarroDao.findCarro(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CarroService#listarCarro()
	 */
	public List listarCarro() {
		return objCarroDao.listarCarro();
	}
	
}
