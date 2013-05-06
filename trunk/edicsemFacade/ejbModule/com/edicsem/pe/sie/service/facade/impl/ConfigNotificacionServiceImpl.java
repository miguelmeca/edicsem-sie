package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ConfigNotificacionSie;
import com.edicsem.pe.sie.model.dao.CalificacionEquifaxDAO;
import com.edicsem.pe.sie.model.dao.ConfigNotificacionDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.NotificacionDAO;
import com.edicsem.pe.sie.model.dao.TipoClienteDAO;
import com.edicsem.pe.sie.service.facade.ConfigNotificacionService;

@Stateless
public class ConfigNotificacionServiceImpl implements ConfigNotificacionService {
	
	@EJB
	private ConfigNotificacionDAO objConfigDao;
	@EJB
	private EstadoGeneralDAO objEstadoGeneralDao;
	@EJB
	private TipoClienteDAO objTipoClienteDao;
	@EJB
	private CalificacionEquifaxDAO objCalificacionDao;
	@EJB
	private NotificacionDAO objNotificacionDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigNotificacionService#insertConfigNotificacion(com.edicsem.pe.sie.entity.ConfigNotificacionSie, int, int, int)
	 */
	public void insertConfigNotificacion(ConfigNotificacionSie n,int idtipocliente, int idCalificacion, int idNotificacion) {
		n.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(114));
		n.setTbTipoCliente(objTipoClienteDao.findTipoCliente(idtipocliente));
		n.setTbCalificacion(objCalificacionDao.findCalificacion(idCalificacion));
		n.setTbNotifica(objNotificacionDao.findNotificacion(idNotificacion));
		objConfigDao.insertConfigNotificacion(n);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigNotificacionService#updateConfigNotificacion(com.edicsem.pe.sie.entity.ConfigNotificacionSie, int, int, int)
	 */
	public void updateConfigNotificacion(ConfigNotificacionSie n,int idtipocliente, int idCalificacion, int idNotificacion) {
		if(idtipocliente!=0)n.setTbTipoCliente(objTipoClienteDao.findTipoCliente(idtipocliente));
		if(idCalificacion!=0)n.setTbCalificacion(objCalificacionDao.findCalificacion(idCalificacion));
		if(idNotificacion!=0)n.setTbNotifica(objNotificacionDao.findNotificacion(idNotificacion));
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
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ConfigNotificacionService#listarConfigNotificacionXNotificacion(int)
	 */
	public List listarConfigNotificacionXNotificacion(int idnotifica) {
		return objConfigDao.listarConfigNotificacionXNotificacion(idnotifica);
	}
	
}
