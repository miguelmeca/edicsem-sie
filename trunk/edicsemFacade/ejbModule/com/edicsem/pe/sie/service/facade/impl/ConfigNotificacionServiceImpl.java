package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ConfigNotificacionSie;
import com.edicsem.pe.sie.model.dao.ConfigNotificacionDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.ConfigNotificacionService;

@Stateless
public class ConfigNotificacionServiceImpl implements ConfigNotificacionService {
	
	@EJB
	private  ConfigNotificacionDAO objConfigDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigNotificacionService#insertConfigNotificacion(com.edicsem.pe.sie.entity.ConfigNotificacionSie)
	 */
	public void insertConfigNotificacion(ConfigNotificacionSie n) {
		n.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(113));
		objConfigDao.insertConfigNotificacion(n);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigNotificacionService#updateConfigNotificacion(com.edicsem.pe.sie.entity.ConfigNotificacionSie)
	 */
	public void updateConfigNotificacion(ConfigNotificacionSie n) {
		objConfigDao.updateConfigNotificacion(n);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigNotificacionService#findConfigNotificacion(int)
	 */
	public ConfigNotificacionSie findConfigNotificacion(int id) {
		return objConfigDao.findConfigNotificacion(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigNotificacionService#listarConfigNotificacion()
	 */
	public List listarConfigNotificacion() {
		return objConfigDao.listarConfigNotificacion();
	}
	
}
