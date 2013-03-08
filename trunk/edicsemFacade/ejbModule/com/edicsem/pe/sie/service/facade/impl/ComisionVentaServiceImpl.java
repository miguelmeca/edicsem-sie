package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ComisionVentaSie;
import com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.ComisionVentaDAO;
import com.edicsem.pe.sie.model.dao.CriterioComisionDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TipoEventoVentaDAO;
import com.edicsem.pe.sie.service.facade.ComisionVentaService;

@Stateless
public class ComisionVentaServiceImpl implements ComisionVentaService {

	@EJB
	private  ComisionVentaDAO objComisionVentaDAO;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	@EJB
	private  CargoEmpleadoDAO objCargoDAO;
	@EJB
	private  CriterioComisionDAO objcriterioDAO;
	@EJB
	private  TipoEventoVentaDAO objeventoDAO;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ComisionVentaService#insertComisionVenta(com.edicsem.pe.sie.entity.ComisionVentaSie, int, int, int)
	 */
	public void insertComisionVenta(ComisionVentaSie c,int idcargo,int idcriterio,int idevento){
		if(idcargo!=0)
		c.setTbCargoempleado(objCargoDAO.buscarCargoEmpleado(idcargo));
		if(idcriterio!=0)
		c.setTbCriterioComision(objcriterioDAO.findCriterioComision(idcriterio));
		if(idevento!=0)
		c.setTbTipoEventoVenta(objeventoDAO.findTipoEventoVenta(idevento));
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
