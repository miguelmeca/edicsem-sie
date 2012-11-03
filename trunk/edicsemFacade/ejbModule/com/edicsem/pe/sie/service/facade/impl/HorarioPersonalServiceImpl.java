package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 

import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.model.dao.HorarioPersonalDAO;
import com.edicsem.pe.sie.model.dao.TipoCasaDAO; 
import com.edicsem.pe.sie.service.facade.HorarioPersonalService;
@Stateless
public class HorarioPersonalServiceImpl implements HorarioPersonalService{
	
	public static Log log = LogFactory.getLog(HorarioPersonalServiceImpl.class);
	
	@EJB
	private HorarioPersonalDAO objHorarioPersonalDao;
	
	
	public void insertHorarioPersonal(HorarioPersonalSie horariopersonal) {
	
		
		objHorarioPersonalDao.insertHorarioPersonal(horariopersonal);
	}

	
	public void updateHorarioPersonal(HorarioPersonalSie horariopersonal) {
		objHorarioPersonalDao.updateHorarioPersonal(horariopersonal);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarHorarioPersonal(int id) {
		objHorarioPersonalDao.eliminarHorarioPersonal(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#findEstadogeneral(int)
	 */

	public HorarioPersonalSie findHorarioPersonal(int id) {
		// TODO Auto-generated method stub
		return objHorarioPersonalDao.findHorarioPersonal(id); 
	}

	public List listarHorarioPersonal() {
		log.info("En el servicio ");
		return objHorarioPersonalDao.listarHorarioPersonal();
	}

	public List listarHorarioPersonalXempleado(int id) {
		log.info("En el servicio ");
		return objHorarioPersonalDao.listarHorarioPersonalXempleado(id);
	}	
		
}
