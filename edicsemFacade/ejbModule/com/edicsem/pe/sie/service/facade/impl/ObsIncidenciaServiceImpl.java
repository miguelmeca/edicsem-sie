package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ObservacionIncidenciaSie;
import com.edicsem.pe.sie.model.dao.ObsIncidenciaDAO;
import com.edicsem.pe.sie.service.facade.ObsIncidenciaService;

@Stateless
public class ObsIncidenciaServiceImpl implements ObsIncidenciaService{
	
	public static Log log = LogFactory.getLog(ObsIncidenciaServiceImpl.class);
	
	@EJB
	private ObsIncidenciaDAO objObsIncidenciaDao;
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.UbigeoService#insertUbigeo(com.edicsem.pe.sie.entity.UbigeoSie)
	 */
	public void insertObsIncidencia(ObservacionIncidenciaSie obsincidencia) {
		objObsIncidenciaDao.insertObsIncidencia(obsincidencia);
	}

	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.UbigeoService#updateUbigeo(com.edicsem.pe.sie.entity.UbigeoSie)
	 */
	public void updateObsIncidencia(ObservacionIncidenciaSie obsincidencia) {
		objObsIncidenciaDao.updateObsIncidencia(obsincidencia);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarObsIncidencia(int id) {
		objObsIncidenciaDao.eliminarObsIncidencia(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#findEstadogeneral(int)
	 */

	public ObservacionIncidenciaSie findObsIncidencia(int id) {
		return objObsIncidenciaDao.findObsIncidencia(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.UbigeoService#listarUbigeoDepartamentos()
	 */
	public List listarObsIncidencia(int id) {
		log.info("En el servicio incidencia ");
		return objObsIncidenciaDao.listarObsIncidencia(id);
	}
	
}
