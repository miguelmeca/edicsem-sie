package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ModuloOpcionSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.ModuloOpcionDAO;
import com.edicsem.pe.sie.service.facade.ModuloOpcionService;

@Stateless
public class ModuloOpcionServiceImpl implements ModuloOpcionService {

	@EJB
	private  ModuloOpcionDAO objModuloDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ModuloOpcionService#insertModuloOpcion(com.edicsem.pe.sie.entity.ModuloOpcionSie)
	 */
	public void insertModuloOpcion(ModuloOpcionSie p) {
		objModuloDao.insertModuloOpcion(p);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ModuloOpcionService#updateModuloOpcion(com.edicsem.pe.sie.entity.ModuloOpcionSie)
	 */
	public void updateModuloOpcion(ModuloOpcionSie p) {
		objModuloDao.updateModuloOpcion(p);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ModuloOpcionService#findModuloOpcion(int)
	 */
	public ModuloOpcionSie findModuloOpcion(int id) {
		return objModuloDao.findModuloOpcion(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ModuloOpcionService#listarModuloOpcion()
	 */
	public List<String> listarModuloOpcion() {
		return objModuloDao.listarModuloOpcion();
	}
	
}
