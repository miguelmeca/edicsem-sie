package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.CajaSie;
import com.edicsem.pe.sie.model.dao.CajaDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.CajaService;

@Stateless
public class CajaServiceImpl implements CajaService {
	
	@EJB
	private  CajaDAO objCajaDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	@EJB
	private  EmpleadoSieDAO objEmpleadoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CajaService#insertCaja(com.edicsem.pe.sie.entity.CajaSie)
	 */
	public void insertCaja(CajaSie c, int idEmpleado) {
		if(idEmpleado!=0){
			c.setTbEmpleado(objEmpleadoDao.buscarEmpleado(idEmpleado));
		}
		objCajaDao.insertCaja(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CajaService#updateCaja(com.edicsem.pe.sie.entity.CajaSie)
	 */
	public void updateCaja(CajaSie c) {
		objCajaDao.updateCaja(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CajaService#findCaja(int)
	 */
	public CajaSie findCaja(int id) {
		return objCajaDao.findCaja(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CajaService#listarCaja()
	 */
	public List listarCaja() {
		return objCajaDao.listarCaja();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CajaService#listarCajaPorEmpleado(java.lang.Integer)
	 */
	public List listarCajaPorEmpleado(Integer idempleado) {
		return objCajaDao.listarCajaPorEmpleado(idempleado);
	}
	
}
