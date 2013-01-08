package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoModuloSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TipoModuloDAO;
import com.edicsem.pe.sie.service.facade.TipoModuloService;

@Stateless
public class TipoModuloServiceImpl implements TipoModuloService {

	@EJB
	private  TipoModuloDAO objTipoModuloDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoModuloService#insertTipoModulo(com.edicsem.pe.sie.entity.TipoModuloSie)
	 */
	public void insertTipoModulo(TipoModuloSie p) {
		objTipoModuloDao.insertTipoModulo(p);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoModuloService#updateTipoModulo(com.edicsem.pe.sie.entity.TipoModuloSie)
	 */
	public void updateTipoModulo(TipoModuloSie p) {
		objTipoModuloDao.updateTipoModulo(p);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoModuloService#findTipoModulo(int)
	 */
	public TipoModuloSie findTipoModulo(int id) {
		return objTipoModuloDao.findTipoModulo(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoModuloService#listarTipoModulo()
	 */
	public List listarTipoModulo() {
		return objTipoModuloDao.listarTipoModulo();
	}
	
}
