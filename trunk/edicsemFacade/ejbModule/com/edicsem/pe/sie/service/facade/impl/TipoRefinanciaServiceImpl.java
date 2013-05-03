package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoRefinanciaSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TipoClienteDAO;
import com.edicsem.pe.sie.model.dao.TipoRefinanciaDAO;
import com.edicsem.pe.sie.service.facade.TipoRefinanciaService;

@Stateless
public class TipoRefinanciaServiceImpl implements TipoRefinanciaService {

	@EJB
	private  TipoRefinanciaDAO objTipoRefinanciaDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	@EJB
	private  TipoClienteDAO objTipoClienteDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoRefinanciaService#insertTipoRefinancia(com.edicsem.pe.sie.entity.TipoRefinanciaSie, int)
	 */
	public void insertTipoRefinancia(TipoRefinanciaSie t, int tipocliente) {
		t.setTbTipoCliente(objTipoClienteDao.findTipoCliente(tipocliente));
		t.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(21));
		objTipoRefinanciaDao.insertTipoRefinancia(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoRefinanciaService#updateTipoRefinancia(com.edicsem.pe.sie.entity.TipoRefinanciaSie, int)
	 */
	public void updateTipoRefinancia(TipoRefinanciaSie t, int tipocliente) {
		t.setTbTipoCliente(objTipoClienteDao.findTipoCliente(tipocliente));
		objTipoRefinanciaDao.updateTipoRefinancia(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoRefinanciaService#findTipoRefinancia(int)
	 */
	public TipoRefinanciaSie findTipoRefinancia(int id) {
		return objTipoRefinanciaDao.findTipoRefinancia(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoRefinanciaService#listarTipoRefinancia()
	 */
	public List listarTipoRefinancia() {
		return objTipoRefinanciaDao.listarTipoRefinancia();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoRefinanciaService#listarTipoRefinanciaXTipoCliente(int)
	 */
	public List listarTipoRefinanciaXTipoCliente(int tipocliente) {
		return objTipoRefinanciaDao.listarTipoRefinanciaXTipoCliente(tipocliente);
	}
	
}
