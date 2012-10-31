package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.model.dao.CobranzaDAO;
import com.edicsem.pe.sie.model.dao.CobranzaOperaDAO;
import com.edicsem.pe.sie.service.facade.CobranzaOperaService;
import com.edicsem.pe.sie.service.facade.CobranzaService;

@Stateless
public class CobranzaOperaServiceImpl implements CobranzaOperaService {

	@EJB
	private  CobranzaOperaDAO objCobranzaOperaDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#insertCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void insertCobranzaOpera(CobranzaOperadoraSie cobranzaopera) {
		objCobranzaOperaDao.insertCobranzaOpera(cobranzaopera);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#updateCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void updateCobranzaOpera(CobranzaOperadoraSie cobranzaopera) {
		objCobranzaOperaDao.updateCobranzaOpera(cobranzaopera);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#findCobranza(int)
	 */
	public CobranzaOperadoraSie findCobranzaOpera(int id) {
		return objCobranzaOperaDao.findCobranzaOpera(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#listarCobranzas()
	 */
	public List listarCobranzasOpera() {
		return objCobranzaOperaDao.listarCobranzasOpera();
	}
}
