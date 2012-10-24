package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

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

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ComprobanteService#listarComprobantes()
	 */
	public List listarComprobantes() {
		return objComprobanteDao.listarComprobantes();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ComprobanteService#updateComprobante(com.edicsem.pe.sie.entity.ComprobanteSie)
	 */
	public void updateComprobante(ComprobanteSie comp) {
		objComprobanteDao.updateComprobante(comp);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ComprobanteService#findComprobantePorNumero(java.lang.String)
	 */
	public ComprobanteSie findComprobantePorNumero(String num) {
		return objComprobanteDao.findComprobantePorNumero(num);
	}
	
}
