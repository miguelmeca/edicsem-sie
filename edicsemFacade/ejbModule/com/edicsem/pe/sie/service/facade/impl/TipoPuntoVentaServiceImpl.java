package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.model.dao.TipoPuntoVentaDAO;
import com.edicsem.pe.sie.service.facade.TipoPuntoVentaService;

@Stateless
public class TipoPuntoVentaServiceImpl implements TipoPuntoVentaService {

	@EJB
	private TipoPuntoVentaDAO objTipoPuntoVentaDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoPuntoVentaService#listarTipoPuntoVenta()
	 */
	public List listarTipoPuntoVenta() {
		return objTipoPuntoVentaDao.listarTipoPuntoVenta();
	}
}
