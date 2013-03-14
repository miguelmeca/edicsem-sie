package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoEventoVentaSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TipoEventoVentaDAO;
import com.edicsem.pe.sie.service.facade.TipoEventoVentaService;

@Stateless
public class TipoEventoVentaServiceImpl implements TipoEventoVentaService {

	@EJB
	private  TipoEventoVentaDAO objTipoEventoVentaDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoEventoVentaService#insertTipoEventoVenta(com.edicsem.pe.sie.entity.TipoEventoVentaSie)
	 */
	public void insertTipoEventoVenta(TipoEventoVentaSie t) {
		t.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(86));
		objTipoEventoVentaDao.insertTipoEventoVenta(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoEventoVentaService#updateTipoEventoVenta(com.edicsem.pe.sie.entity.TipoEventoVentaSie)
	 */
	public void updateTipoEventoVenta(TipoEventoVentaSie t) {
		objTipoEventoVentaDao.updateTipoEventoVenta(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoEventoVentaService#findTipoEventoVenta(int)
	 */
	public TipoEventoVentaSie findTipoEventoVenta(int id) {
		return objTipoEventoVentaDao.findTipoEventoVenta(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoEventoVentaService#listarTipoEventoVenta()
	 */
	public List listarTipoEventoVenta() {
		return objTipoEventoVentaDao.listarTipoEventoVenta();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoEventoVentaService#findTipoEventoVenta(java.lang.String)
	 */
	public TipoEventoVentaSie findTipoEventoVenta(String evento) {
		return objTipoEventoVentaDao.findTipoEventoVenta(evento);
	}
	
}
