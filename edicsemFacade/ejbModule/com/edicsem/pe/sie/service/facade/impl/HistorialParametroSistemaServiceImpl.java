package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.HistorialParametroSistemaSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.HistorialParametroSistemaDAO;
import com.edicsem.pe.sie.service.facade.HistorialParametroSistemaService;

@Stateless
public class HistorialParametroSistemaServiceImpl implements HistorialParametroSistemaService {

	@EJB
	private  HistorialParametroSistemaDAO objhistorialDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HistorialParametroSistemaDAO#insertHistorial(com.edicsem.pe.sie.entity.HistorialParametroSistemaSie)
	 */
	public void insertHistorial(HistorialParametroSistemaSie h) {
		objhistorialDao.insertHistorial(h);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HistorialParametroSistemaDAO#updateHistorial(com.edicsem.pe.sie.entity.HistorialParametroSistemaSie)
	 */
	public void updateHistorial(HistorialParametroSistemaSie h) {
		objhistorialDao.updateHistorial(h);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HistorialParametroSistemaDAO#findHistorial(int)
	 */
	public HistorialParametroSistemaSie findHistorial(int id) {
		return objhistorialDao.findHistorial(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HistorialParametroSistemaDAO#listarhistorial()
	 */
	public List listarhistorial() {
		return objhistorialDao.listarhistorial();
	}
}
