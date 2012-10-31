package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.model.dao.CobranzaDAO;
import com.edicsem.pe.sie.service.facade.CobranzaService;

@Stateless
public class CobranzaServiceImpl implements CobranzaService {

	@EJB
	private  CobranzaDAO objCobranzaDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#insertCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void insertCobranza(CobranzaSie Cobranza) {
		objCobranzaDao.insertCobranza(Cobranza);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#updateCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void updateCobranza(CobranzaSie Cobranza) {
		objCobranzaDao.updateCobranza(Cobranza);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#findCobranza(int)
	 */
	public CobranzaSie findCobranza(int id) {
		return objCobranzaDao.findCobranza(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#listarCobranzas()
	 */
	public List listarCobranzas() {
		return objCobranzaDao.listarCobranzas();
	}
	
	public List listarCobranzasXidcontrato(int idcontrato) {
		return objCobranzaDao.listarCobranzasXidcontrato(idcontrato);
	}
}
