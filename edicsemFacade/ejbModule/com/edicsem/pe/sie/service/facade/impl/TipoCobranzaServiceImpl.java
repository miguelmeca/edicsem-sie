package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoCobranzaSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TipoCobranzaDAO;
import com.edicsem.pe.sie.service.facade.TipoCobranzaService;

@Stateless
public class TipoCobranzaServiceImpl implements TipoCobranzaService {

	@EJB
	private  TipoCobranzaDAO objTipoCobranzaDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoCobranzaService#insertTipoCobranza(com.edicsem.pe.sie.entity.TipoCobranzaSie)
	 */
	public void insertTipoCobranza(TipoCobranzaSie t) {
		t.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(102));
		objTipoCobranzaDao.insertTipoCobranza(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoCobranzaService#updateTipoCobranza(com.edicsem.pe.sie.entity.TipoCobranzaSie)
	 */
	public void updateTipoCobranza(TipoCobranzaSie t) {
		objTipoCobranzaDao.updateTipoCobranza(t);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoCobranzaService#findTipoCobranza(int)
	 */
	public TipoCobranzaSie findTipoCobranza(int id) {
		return objTipoCobranzaDao.findTipoCobranza(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoCobranzaService#listarTipoCobranza()
	 */
	public List listarTipoCobranza() {
		return objTipoCobranzaDao.listarTipoCobranza();
	}
	
}
