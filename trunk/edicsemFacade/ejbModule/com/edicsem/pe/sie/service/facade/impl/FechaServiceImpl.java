package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.FechaSie;
import com.edicsem.pe.sie.model.dao.FechaDAO;
import com.edicsem.pe.sie.service.facade.FechaService;

@Stateless
public class FechaServiceImpl implements FechaService {

	@EJB
	private  FechaDAO objFechaDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.FechaService#listarFechas()
	 */
	public List listarFechas() {
		return objFechaDao.listarFechas();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.FechaService#findFecha(int)
	 */
	public FechaSie findFecha(int id) {
		return objFechaDao.findFecha(id);
	}
}