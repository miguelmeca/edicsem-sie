package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.FechaDAO;
import com.edicsem.pe.sie.model.dao.HorarioPersonalDAO;
import com.edicsem.pe.sie.service.facade.HorarioPersonalService;
@Stateless
public class HorarioPersonalServiceImpl implements HorarioPersonalService{
	
	public static Log log = LogFactory.getLog(HorarioPersonalServiceImpl.class);
	
	@EJB
	private HorarioPersonalDAO objHorarioPersonalDao;
	@EJB
	private EmpleadoSieDAO objEmpleadoDao;
	@EJB
	private EstadoGeneralDAO objEstadoGeneralDao;
	@EJB
	private FechaDAO objFechaDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPersonalService#insertHorarioVenta(java.util.List)
	 */
	public void insertHorarioVenta(List<HorarioPersonalSie> horariopersonal) {
		for (int i = 0; i < horariopersonal.size(); i++) {
			log.info("insert Horario Venta ");
			horariopersonal.get(i).setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(36));
			objHorarioPersonalDao.insertHorarioPersonal(horariopersonal.get(i));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPersonalService#insertHorarioPersonal(java.util.List, com.edicsem.pe.sie.entity.HorarioPersonalSie, int)
	 */
	public void insertHorarioPersonal(List<String> diaList,HorarioPersonalSie horariopersonal, int idEmpleado) {
		for (int i = 0; i < diaList.size(); i++) {
			HorarioPersonalSie auxi = new HorarioPersonalSie();
			auxi=horariopersonal;
			auxi.setTbFecha(objFechaDao.findFecha(Integer.parseInt(diaList.get(i))));
			auxi.setTbEmpleado(objEmpleadoDao.buscarEmpleado(idEmpleado));
			auxi.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(36));
			log.info("insertando 1 ");
			objHorarioPersonalDao.insertHorarioPersonal(auxi);
			log.info("insertando 2  ");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPersonalService#updateHorarioPersonal(com.edicsem.pe.sie.entity.HorarioPersonalSie)
	 */
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
		log.info("id " +id);
		return objHorarioPersonalDao.findHorarioPersonal(id); 
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPersonalService#listarHorarioPersonal()
	 */
	public List listarHorarioPersonal() {
		log.info("En el servicio ");
		return objHorarioPersonalDao.listarHorarioPersonal();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPersonalService#listarHorarioPersonalXempleado(int)
	 */
	public List listarHorarioPersonalXempleado(int id) {
		log.info("En el servicio ");
		return objHorarioPersonalDao.listarHorarioPersonalXempleado(id);
	}
		
}
