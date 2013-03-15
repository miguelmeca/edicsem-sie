package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.LugarVentaSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.LugarVentaDAO;
import com.edicsem.pe.sie.service.facade.LugarVentaService;

@Stateless
public class LugarVentaServiceImpl implements LugarVentaService {

	@EJB
	private  LugarVentaDAO objlugarventaDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.LugarVentaService#insertLugarVenta(com.edicsem.pe.sie.entity.LugarVentaSie)
	 */
	public void insertLugarVenta(LugarVentaSie l) {
		l.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(90));
		objlugarventaDao.insertLugarVenta(l);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.LugarVentaService#updateLugarVenta(com.edicsem.pe.sie.entity.LugarVentaSie)
	 */
	public void updateLugarVenta(LugarVentaSie l) {
		objlugarventaDao.updateLugarVenta(l);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.LugarVentaService#findLugarVenta(int)
	 */
	public LugarVentaSie findLugarVenta(int id) {
		return objlugarventaDao.findLugarVenta(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.LugarVentaService#listarLugarVenta()
	 */
	public List listarLugarVenta() {
		return objlugarventaDao.listarLugarVenta();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.LugarVentaService#findLugarVenta(java.lang.String)
	 */
	public LugarVentaSie findLugarVenta(String lugardelaentrega) {
		return objlugarventaDao.findLugarVenta(lugardelaentrega);
	}
	
}
