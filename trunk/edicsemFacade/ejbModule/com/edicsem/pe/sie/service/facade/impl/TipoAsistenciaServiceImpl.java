package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoAsistenciaSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TipoAsistenciaDAO;
import com.edicsem.pe.sie.service.facade.TipoAsistenciaService;

@Stateless
public class TipoAsistenciaServiceImpl implements TipoAsistenciaService {

	@EJB
	private  TipoAsistenciaDAO objTipoAsistenciaDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoAsistenciaService#findTipoAsistencia(int)
	 */
	public TipoAsistenciaSie findTipoAsistencia(int id) {
		return objTipoAsistenciaDao.findTipoAsistencia(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoAsistenciaService#listarTipoAsistencia()
	 */
	public List listarTipoAsistencia() {
		return objTipoAsistenciaDao.listarTipoAsistencia();
	}
	
}
