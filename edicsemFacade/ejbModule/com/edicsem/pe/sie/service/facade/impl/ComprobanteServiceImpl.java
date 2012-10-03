package com.edicsem.pe.sie.service.facade.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ComprobanteSie;
import com.edicsem.pe.sie.model.dao.ComprobanteDAO;
import com.edicsem.pe.sie.service.facade.ComprobanteService;

@Stateless
public class ComprobanteServiceImpl implements ComprobanteService {

	@EJB
	private  ComprobanteDAO objComprobanteDao;
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ComprobanteService#insertComprobante(com.edicsem.pe.sie.entity.ComprobanteSie)
	 */
	
	public void insertComprobante(ComprobanteSie comp) {
		objComprobanteDao.insertComprobante(comp);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ComprobanteService#findComprobante(int)
	 */
	
	public ComprobanteSie findComprobante(int id) {
		
		return objComprobanteDao.findComprobante(id);
	}
	
	
}
