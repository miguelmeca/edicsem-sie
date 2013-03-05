package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.GrupoVentaSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.GrupoVentaDAO;
import com.edicsem.pe.sie.service.facade.GrupoVentaService;

@Stateless
public class GrupoVentaServiceImpl implements GrupoVentaService {

	@EJB
	private  GrupoVentaDAO objGrupoDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.GrupoVentaService#insertGrupo(com.edicsem.pe.sie.entity.GrupoVentaSie)
	 */
	@Override
	public void insertGrupo(GrupoVentaSie g) {
		objGrupoDao.insertGrupo(g);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.GrupoVentaService#updateGrupo(com.edicsem.pe.sie.entity.GrupoVentaSie)
	 */
	public void updateGrupo(GrupoVentaSie g) {
		objGrupoDao.updateGrupo(g);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.GrupoVentaService#findGrupoVenta(int)
	 */
	public GrupoVentaSie findGrupoVenta(int id) {
		return objGrupoDao.findGrupoVenta(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.GrupoVentaService#listarGrupoVenta(int)
	 */
	public List listarGrupoVenta(int tipoVenta) {
		return objGrupoDao.listarGrupoVenta(tipoVenta);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.GrupoVentaService#listarGrupoVenta()
	 */
	public List listarGrupoVenta() {
		return objGrupoDao.listarGrupoVenta();
	}
	
}
