package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ConfigCorrreosIncidenteSie;
import com.edicsem.pe.sie.model.dao.ConfigCorreosIncidenteDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.ConfigCorreosIncidenteService;

@Stateless
public class ConfigCorreosIncidenteServiceImpl implements ConfigCorreosIncidenteService {

	@EJB
	private  ConfigCorreosIncidenteDAO objConfigCorreoDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigCorreosIncidenteService#insertConfigCorreos(com.edicsem.pe.sie.entity.ConfigCorrreosIncidenteSie)
	 */
	public void insertConfigCorreos(ConfigCorrreosIncidenteSie c) {
		c.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(100));
		objConfigCorreoDao.insertConfigCorreos(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigCorreosIncidenteService#updateConfigCorreos(com.edicsem.pe.sie.entity.ConfigCorrreosIncidenteSie)
	 */
	public void updateConfigCorreos(ConfigCorrreosIncidenteSie c) {
		objConfigCorreoDao.updateConfigCorreos(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigCorreosIncidenteService#findConfigCorreo(int)
	 */
	public ConfigCorrreosIncidenteSie findConfigCorreo(int id) {
		return objConfigCorreoDao.findConfigCorreo(id);
	}
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigCorreosIncidenteService#listarConfigCorreos()
	 */
	public List listarConfigCorreos() {
		return objConfigCorreoDao.listarConfigCorreos();
	}
	
}
