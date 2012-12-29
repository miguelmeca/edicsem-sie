package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	 * @see com.edicsem.pe.sie.service.facade.HorarioPuntoVentaService#insertHorarioPunto(java.util.List, com.edicsem.pe.sie.entity.HorarioPuntoVentaSie, int)
	 */
	public void insertHorarioPunto(List<String> diaList, HorarioPuntoVentaSie h, int idpuntoventa) {
		log.info("  insertHorarioPunto ");
		
		for (String d : diaList) {
			HorarioPuntoVentaSie auxi = new HorarioPuntoVentaSie();	
			auxi.setDiafin(h.getDiafin());
			auxi.setDiainicio(h.getDiainicio());
			auxi.setHoraIngreso(h.getHoraIngreso());
			auxi.setHoraSalida(h.getHoraSalida());
			auxi.setObservacion(h.getObservacion());
			auxi.setTbFecha(objFechaDao.findFecha(Integer.parseInt(d)));
			auxi.setTbPuntoVenta(objAlmacenDao.findAlmacen(idpuntoventa));
			auxi.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(38));
			objHorarioPuntoDao.insertHorarioPunto(auxi);
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPuntoVentaService#updateHorarioPunto(com.edicsem.pe.sie.entity.HorarioPuntoVentaSie)
	 */
	public void updateHorarioPunto(HorarioPuntoVentaSie h) {
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
}
