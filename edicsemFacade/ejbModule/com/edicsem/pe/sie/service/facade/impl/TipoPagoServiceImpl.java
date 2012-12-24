package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoPagoSie;
import com.edicsem.pe.sie.model.dao.TipoPagoDAO;
import com.edicsem.pe.sie.service.facade.TipoPagoService;

@Stateless
public class TipoPagoServiceImpl implements TipoPagoService {

	@EJB
	private  TipoPagoDAO objTipoPagoDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoPagoService#findTipoPago(int)
	 */
	public TipoPagoSie findTipoPago(int id) {
		return objTipoPagoDao.findTipoPago(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoPagoService#listarTipoPago()
	 */
	public List listarTipoPago() {
		return objTipoPagoDao.listarTipoPago();
	}
}
