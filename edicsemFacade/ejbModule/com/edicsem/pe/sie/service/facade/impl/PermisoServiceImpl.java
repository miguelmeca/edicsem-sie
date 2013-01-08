package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.PermisoSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.PermisoDAO;
import com.edicsem.pe.sie.service.facade.PermisoService;

@Stateless
public class PermisoServiceImpl implements PermisoService {

	@EJB
	private  PermisoDAO objPermisoDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.PermisoService#insertPermiso(com.edicsem.pe.sie.entity.PermisoSie)
	 */
	public void insertPermiso(PermisoSie p) {
		objPermisoDao.insertPermiso(p);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.PermisoService#updatePermiso(com.edicsem.pe.sie.entity.PermisoSie)
	 */
	public void updatePermiso(PermisoSie p) {
		objPermisoDao.updatePermiso(p);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.PermisoService#findPermiso(int)
	 */
	
	public PermisoSie findPermiso(int id) {
		return objPermisoDao.findPermiso(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.PermisoService#listarPermiso()
	 */
	public List listarPermiso() {
		return objPermisoDao.listarPermiso();
	}
	
}
