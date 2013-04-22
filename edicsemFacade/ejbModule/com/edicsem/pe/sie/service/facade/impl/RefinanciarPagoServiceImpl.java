package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.RefinanciarPagoSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.RefinanciarPagoDAO;
import com.edicsem.pe.sie.service.facade.RefinanciarPagoService;

@Stateless
public class RefinanciarPagoServiceImpl implements RefinanciarPagoService {

	@EJB
	private  RefinanciarPagoDAO objRefinanDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.RefinanciarPagoService#insertRefinanPago(com.edicsem.pe.sie.entity.RefinanciarPagoSie)
	 */
	public void insertRefinanPago(RefinanciarPagoSie r) {
		r.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(85));
		objRefinanDao.insertRefinanPago(r);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.RefinanciarPagoService#updateRefinanPago(com.edicsem.pe.sie.entity.RefinanciarPagoSie)
	 */
	public void updateRefinanPago(RefinanciarPagoSie r) {
		objRefinanDao.updateRefinanPago(r);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.RefinanciarPagoService#findRefinanPago(int)
	 */
	public RefinanciarPagoSie findRefinanPago(int id) {
		return objRefinanDao.findRefinanPago(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.RefinanciarPagoService#listarRefinanciarPago()
	 */
	public List listarRefinanciarPago() {
		return objRefinanDao.listarRefinanciarPago();
	}
	
}
