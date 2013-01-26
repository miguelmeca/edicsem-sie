package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.FechaSie;
import com.edicsem.pe.sie.entity.HorarioPuntoVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.FechaDAO;
import com.edicsem.pe.sie.model.dao.HorarioPuntoVentaDAO;
import com.edicsem.pe.sie.service.facade.HorarioPuntoVentaService;

@Stateless
public class HorarioPuntoVentaServiceImpl implements HorarioPuntoVentaService{
	
	public static Log log = LogFactory.getLog(HorarioPuntoVentaServiceImpl.class);
	
	@EJB
	private HorarioPuntoVentaDAO objHorarioPuntoDao;
	@EJB
	private EstadoGeneralDAO objEstadoGeneralDao;
	@EJB
	private FechaDAO objFechaDao;
	@EJB
	private AlmacenDAO objAlmacenDao;
	

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPuntoVentaService#insertHorarioPunto(com.edicsem.pe.sie.entity.HorarioPuntoVentaSie, java.util.List)
	 */
	public void insertHorarioPunto(HorarioPuntoVentaSie h, List<String> diaList) {
		log.info("  insertar HorarioPunto en el servicio" + diaList);
		
		for (String d : diaList) {
			log.info(" dia "+d);
			HorarioPuntoVentaSie auxi = new HorarioPuntoVentaSie();	
			auxi.setDiafin(h.getDiafin());
			auxi.setDiainicio(h.getDiainicio());
			auxi.setHoraIngreso(h.getHoraIngreso());
			auxi.setHoraSalida(h.getHoraSalida());
			auxi.setTbEstadoGeneral(h.getTbEstadoGeneral());
			auxi.setTbPuntoVenta(h.getTbPuntoVenta());
			FechaSie fec= objFechaDao.findFecha(Integer.parseInt(d));
			auxi.setTbFecha(fec);
			log.info(" dia "+fec.getDia());
			objHorarioPuntoDao.insertHorarioPunto(auxi);
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPuntoVentaService#updateHorarioPunto(com.edicsem.pe.sie.entity.HorarioPuntoVentaSie)
	 */
	public void updateHorarioPunto(HorarioPuntoVentaSie h) {
		log.info("  actualizar HorarioPunto en el servicio");
		objHorarioPuntoDao.updateHorarioPunto(h);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPuntoVentaService#findHorarioPunto(int)
	 */
	public HorarioPuntoVentaSie findHorarioPunto(int id) {
		return objHorarioPuntoDao.findHorarioPunto(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPuntoVentaService#listarHorarioPunto()
	 */
	public List listarHorarioPunto() {
		return objHorarioPuntoDao.listarHorarioPunto();
	}
	
	public List listarHorarioPuntoVentaXidPV(int id) {
		log.info("En el servicio ");
		return objHorarioPuntoDao.listarHorarioPuntoVentaXidPV(id);
		
	}


	public void eliminarHorarioPunto(int id) {
		log.info("dentro del servicio en el metodo eliminar horario");
		objHorarioPuntoDao.eliminarHorarioPunto(id);
		
	}	
	
}
