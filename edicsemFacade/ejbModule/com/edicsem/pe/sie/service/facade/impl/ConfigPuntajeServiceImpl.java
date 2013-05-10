package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ConfigPuntajeSie;
import com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.ConfigPuntajeDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.ParametroActividadDAO;
import com.edicsem.pe.sie.model.dao.TipoClienteDAO;
import com.edicsem.pe.sie.service.facade.ConfigPuntajeService;

@Stateless
public class ConfigPuntajeServiceImpl implements ConfigPuntajeService {
	
	@EJB
	private  ConfigPuntajeDAO objConfigPuntajeDao;
	@EJB
	private  TipoClienteDAO objTipoClienteDao;
	@EJB
	private  CargoEmpleadoDAO objCargoEmpleadoDao;
	@EJB
	private  ParametroActividadDAO objParametroDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigPuntajeService#insertConfigPuntaje(com.edicsem.pe.sie.entity.ConfigPuntajeSie, int, int, int)
	 */
	public void insertConfigPuntaje(ConfigPuntajeSie c, int idparametro, int idtipocliente, int idcargo) {
		c.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(119));
		c.setTbTipoCliente(objTipoClienteDao.findTipoCliente(idtipocliente));
		c.setTbCargoempleado(objCargoEmpleadoDao.findCargoEmpleado(idcargo));
		c.setTbParametroActividad(objParametroDao.findParametroActividad(idparametro));
		objConfigPuntajeDao.insertConfigPuntaje(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigPuntajeService#updateConfigPuntaje(com.edicsem.pe.sie.entity.ConfigPuntajeSie, int, int, int)
	 */
	public void updateConfigPuntaje(ConfigPuntajeSie c, int idparametro, int idtipocliente, int idcargo) {
		c.setTbTipoCliente(objTipoClienteDao.findTipoCliente(idtipocliente));
		c.setTbCargoempleado(objCargoEmpleadoDao.findCargoEmpleado(idcargo));
		c.setTbParametroActividad(objParametroDao.findParametroActividad(idparametro));
		objConfigPuntajeDao.updateConfigPuntaje(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigPuntajeService#findConfigPuntaje(int)
	 */
	public ConfigPuntajeSie findConfigPuntaje(int id) {
		return objConfigPuntajeDao.findConfigPuntaje(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigPuntajeService#listarConfigPuntaje()
	 */
	public List listarConfigPuntaje() {
		return objConfigPuntajeDao.listarConfigPuntaje();
	}
	
}
