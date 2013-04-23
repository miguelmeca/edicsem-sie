package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie;
import com.edicsem.pe.sie.model.dao.ConfigCobranzaDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TipoClienteDAO;
import com.edicsem.pe.sie.model.dao.TipoCobranzaDAO;
import com.edicsem.pe.sie.service.facade.ConfigCobranzaService;

@Stateless
public class ConfigCobranzaServiceImpl implements ConfigCobranzaService {

	@EJB
	private  ConfigCobranzaDAO objConfigCobranzaDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	@EJB
	private  TipoCobranzaDAO objTipoCobranzaDao;
	@EJB
	private  TipoClienteDAO objTipoClienteDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigCobranzaService#insertConfigCobranza(com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie, int, int)
	 */
	public void insertConfigCobranza(ConfigCobranzaOperaSie c,int idtipocobranza,int idtipocliente) {
		c.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(104));
		c.setTbTipoCliente(objTipoClienteDao.findTipoCliente(idtipocliente));
		c.setTbTipoCobranza(objTipoCobranzaDao.findTipoCobranza(idtipocobranza));
		objConfigCobranzaDao.insertConfigCobranza(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigCobranzaService#updateConfigCobranza(com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie, int, int)
	 */
	public void updateConfigCobranza(ConfigCobranzaOperaSie c,int idtipocobranza,int idtipocliente) {
		c.setTbTipoCliente(objTipoClienteDao.findTipoCliente(idtipocliente));
		c.setTbTipoCobranza(objTipoCobranzaDao.findTipoCobranza(idtipocobranza));
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
