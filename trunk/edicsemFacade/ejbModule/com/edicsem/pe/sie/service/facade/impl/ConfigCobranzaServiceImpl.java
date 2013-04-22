package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie;
import com.edicsem.pe.sie.model.dao.ConfigCobranzaDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.ConfigCobranzaService;

@Stateless
public class ConfigCobranzaServiceImpl implements ConfigCobranzaService {

	@EJB
	private  ConfigCobranzaDAO objConfigCobranzaDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigCobranzaService#insertConfigCobranza(com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie)
	 */
	public void insertConfigCobranza(ConfigCobranzaOperaSie c) {
		c.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(5));
		objConfigCobranzaDao.insertConfigCobranza(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigCobranzaService#updateConfigCobranza(com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie)
	 */
	public void updateConfigCobranza(ConfigCobranzaOperaSie c) {
		objConfigCobranzaDao.updateConfigCobranza(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigCobranzaService#findConfigCobranza(int)
	 */
	public ConfigCobranzaOperaSie findConfigCobranza(int id) {
		return objConfigCobranzaDao.findConfigCobranza(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigCobranzaService#listarConfigCobranza()
	 */
	public List listarConfigCobranza() {
		return objConfigCobranzaDao.listarConfigCobranza();
	}
	
}
