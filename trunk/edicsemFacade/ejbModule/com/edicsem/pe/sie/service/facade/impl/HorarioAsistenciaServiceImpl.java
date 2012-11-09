package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.HorarioAsistenciaSie;
import com.edicsem.pe.sie.model.dao.HorarioAsistenciaDAO;
import com.edicsem.pe.sie.service.facade.HorarioAsistenciaService;

@Stateless
public class HorarioAsistenciaServiceImpl implements HorarioAsistenciaService{
	
	public static Log log = LogFactory.getLog(HorarioAsistenciaServiceImpl.class);
	
	@EJB
	private HorarioAsistenciaDAO objHorarioAsistenciaDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPersonalService#insertHorarioPersonal(java.util.List, com.edicsem.pe.sie.entity.HorarioPersonalSie, int)
	 */
	public void insertHorarioAsistencia(HorarioAsistenciaSie horarioasistencia) {
		objHorarioAsistenciaDao.updateHorarioAsistencia(horarioasistencia);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPersonalService#updateHorarioPersonal(com.edicsem.pe.sie.entity.HorarioPersonalSie)
	 */
	public void updateHorarioAsistencia(HorarioAsistenciaSie horarioasistencia) {
		objHorarioAsistenciaDao.updateHorarioAsistencia(horarioasistencia);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarHorarioAsistencia(int id) {
		objHorarioAsistenciaDao.eliminarHorarioAsistencia(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#findEstadogeneral(int)
	 */
	public HorarioAsistenciaSie findHorarioAsistencia(int id) {
		// TODO Auto-generated method stub
		return objHorarioAsistenciaDao.findHorarioAsistencia(id); 
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPersonalService#listarHorarioPersonal()
	 */
	public List listarHorarioAsistencia() {
		log.info("En el servicio ");
		return objHorarioAsistenciaDao.listarHorarioAsistencia();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPersonalService#listarHorarioPersonalXempleado(int)
	 */
	public List listarHorarioAsistenciaXempleado(int id) {
		log.info("En el servicio ");
		return objHorarioAsistenciaDao.listarHorarioAsistenciaXempleado(id);
	}	

	public List obtenerFechaFinalXempleado(int id) {
		log.info("En el servicio ");
		return objHorarioAsistenciaDao.obtenerFechaFinalXempleado(id);
	}
	
}
