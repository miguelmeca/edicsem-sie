package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.NotificacionSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.NotificacionDAO;
import com.edicsem.pe.sie.service.facade.NotificacionService;

@Stateless
public class NotificacionServiceImpl implements NotificacionService {

	@EJB
	private  NotificacionDAO objNotificacionDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.NotificacionService#insertNotificacion(com.edicsem.pe.sie.entity.NotificacionSie)
	 */
	public void insertNotificacion(NotificacionSie n) {
		n.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(112));
		objNotificacionDao.insertNotificacion(n);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.NotificacionService#updateNotificacion(com.edicsem.pe.sie.entity.NotificacionSie)
	 */
	public void updateNotificacion(NotificacionSie n) {
		objNotificacionDao.updateNotificacion(n);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.NotificacionService#findNotificacion(int)
	 */
	public NotificacionSie findNotificacion(int id) {
		return objNotificacionDao.findNotificacion(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.NotificacionService#listarNotificacion()
	 */
	public List listarNotificacion() {
		return objNotificacionDao.listarNotificacion();
	}
	
}
