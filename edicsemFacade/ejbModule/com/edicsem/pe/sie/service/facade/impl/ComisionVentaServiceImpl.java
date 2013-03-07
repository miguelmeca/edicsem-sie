package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ComisionVentaSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.ComisionVentaDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.ComisionVentaService;

@Stateless
public class ComisionVentaServiceImpl implements ComisionVentaService {

	@EJB
	private  ComisionVentaDAO objComisionVentaDAO;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ComisionVentaService#insertComisionVenta(com.edicsem.pe.sie.entity.ComisionVentaSie)
	 */
	public void insertComisionVenta(ComisionVentaSie c) {
		objComisionVentaDAO.insertComisionVenta(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ComisionVentaService#updateComisionVenta(com.edicsem.pe.sie.entity.ComisionVentaSie)
	 */
	public void updateComisionVenta(ComisionVentaSie c) {
		objComisionVentaDAO.updateComisionVenta(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ComisionVentaService#findComisionVenta(int)
	 */
	public ComisionVentaSie findComisionVenta(int id) {
		return objComisionVentaDAO.findComisionVenta(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ComisionVentaService#listarComisionVenta()
	 */
	public List listarComisionVenta() {
		return objComisionVentaDAO.listarComisionVenta();
	}
	
}
