package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.MetaEmpleadoSie;
import com.edicsem.pe.sie.model.dao.MetaEmpleadoDAO;
import com.edicsem.pe.sie.service.facade.MetaEmpleadoService;

@Stateless
public class MetaEmpleadoServiceImpl implements MetaEmpleadoService {

	@EJB
	private  MetaEmpleadoDAO objMetaEmpleadoDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.MetaEmpleadoService#insertMetaEmpleado(com.edicsem.pe.sie.entity.MetaEmpleadoSie)
	 */
	public void insertMetaEmpleado(MetaEmpleadoSie m) {
		objMetaEmpleadoDao.insertMetaEpleado(m);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.MetaEmpleadoService#updateMetaEmpleado(com.edicsem.pe.sie.entity.MetaEmpleadoSie)
	 */
	public void updateMetaEmpleado(MetaEmpleadoSie m) {
		objMetaEmpleadoDao.updateMetaEpleado(m);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.MetaEmpleadoService#findMetaEmpleado(int)
	 */
	public MetaEmpleadoSie findMetaEmpleado(int id) {
		return objMetaEmpleadoDao.findMetaEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.MetaEmpleadoService#listarMetaEmpleado()
	 */
	public List listarMetaEmpleado() {
		return objMetaEmpleadoDao.listarMetaEmpleado();
	}
	
}
