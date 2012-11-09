package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.HorarioPuntoVentaSie;
import com.edicsem.pe.sie.service.facade.HorarioPuntoVentaService;

@Stateless
public class HorarioPuntoVentaServiceImpl implements HorarioPuntoVentaService{
	
	public static Log log = LogFactory.getLog(HorarioPuntoVentaServiceImpl.class);
	
	@EJB
	private HorarioPuntoVentaService objHorarioPuntoDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.HorarioPuntoVentaService#insertHorarioPunto(com.edicsem.pe.sie.entity.HorarioPuntoVentaSie)
	 */
	public void insertHorarioPunto(HorarioPuntoVentaSie h) {
		objHorarioPuntoDao.insertHorarioPunto(h);
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
	
}
