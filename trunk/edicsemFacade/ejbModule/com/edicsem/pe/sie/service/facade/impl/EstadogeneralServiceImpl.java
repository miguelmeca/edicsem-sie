package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO; 
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
@Stateless
public class EstadogeneralServiceImpl implements EstadogeneralService{
	
	public static Log log = LogFactory.getLog(EstadogeneralServiceImpl.class);
	
	@EJB
	private EstadoGeneralDAO objEstadogeneralDao;
	
	
	public void insertEstadogeneral(EstadoGeneralSie estadogeneral) {
	
		
		objEstadogeneralDao.insertEstadogeneral(estadogeneral);
	}

	
	public void updateEstadogeneral(EstadoGeneralSie estadogeneral) {
		objEstadogeneralDao.updateEstadogeneral(estadogeneral);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarEstadogeneral(int id) {
		objEstadogeneralDao.eliminarEstadogeneral(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#findEstadogeneral(int)
	 */

	public EstadoGeneralSie findEstadogeneral(int id) {
		// TODO Auto-generated method stub
		return objEstadogeneralDao.findEstadoGeneral(id); 
	}

	public List listarEstados() {
		log.info("En el servicio ");
		return objEstadogeneralDao.listarEstados();
	}

	
		
}
