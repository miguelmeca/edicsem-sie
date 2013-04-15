package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.IncidenciaSie;
import com.edicsem.pe.sie.model.dao.IncidenciaDAO;
import com.edicsem.pe.sie.service.facade.IncidenciaService;
@Stateless
public class IncidenciaServiceImpl implements IncidenciaService{
	
	public static Log log = LogFactory.getLog(IncidenciaServiceImpl.class);
	
	@EJB
	private IncidenciaDAO objIncidenciaDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.UbigeoService#insertUbigeo(com.edicsem.pe.sie.entity.UbigeoSie)
	 */
	public void insertIncidencia(IncidenciaSie incidencia) {
		objIncidenciaDao.insertIncidencia(incidencia);
	}

	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.UbigeoService#updateUbigeo(com.edicsem.pe.sie.entity.UbigeoSie)
	 */
	public void updateIncidencia(IncidenciaSie incidencia) {
		objIncidenciaDao.updateIncidencia(incidencia);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarIncidencia(int id) {
		objIncidenciaDao.eliminarIncidencia(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#findEstadogeneral(int)
	 */
	public IncidenciaSie findIncidencia(int id) {
		return objIncidenciaDao.findIncidencia(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.UbigeoService#listarUbigeoDepartamentos()
	 */
	public List listarIncidencia() {
		return objIncidenciaDao.listarIncidencia();
	}
	
	
}
