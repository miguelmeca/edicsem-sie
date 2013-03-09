package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO; 
import com.edicsem.pe.sie.model.dao.impl.EstadogeneralDAOImpl;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;

/**
 * @author karen
 *
 */
@Stateless
public class EstadogeneralServiceImpl implements EstadogeneralService{
	
	@EJB
	private EstadoGeneralDAO objEstadogeneralDao;
	
	private static Log log = LogFactory.getLog(EstadogeneralServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#insertEstadogeneral(com.edicsem.pe.sie.entity.EstadoGeneralSie)
	 */
	
	public void insertEstadogeneral(EstadoGeneralSie estadogeneral) {
		objEstadogeneralDao.insertEstadogeneral(estadogeneral);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#updateEstadogeneral(com.edicsem.pe.sie.entity.EstadoGeneralSie)
	 */
	public void updateEstadogeneral(EstadoGeneralSie estadogeneral) {
		objEstadogeneralDao.updateEstadogeneral(estadogeneral);
	}
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#eliminarEstadogeneral(int)
	 */
	public void eliminarEstadogeneral(int id) {
		objEstadogeneralDao.eliminarEstadogeneral(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#findEstadogeneral(int)
	 */

	public EstadoGeneralSie findEstadogeneral(int id) {
		
		log.info("en el servicio EstadoGeneralSie"+"  "+id);
		
		return objEstadogeneralDao.findEstadoGeneral(id); 
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#listarEstados(java.lang.String)
	 */
	public List listarEstados(String codigo) {	 
		return objEstadogeneralDao.listarEstados(codigo);
	}
}
