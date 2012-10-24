package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetalleComprobanteSie;
import com.edicsem.pe.sie.model.dao.DetalleComprobanteDAO;
import com.edicsem.pe.sie.service.facade.DetalleComprobanteService;
@Stateless
public class DetalleComprobanteServiceImpl implements DetalleComprobanteService{
	
	public static Log log = LogFactory.getLog(DetalleComprobanteServiceImpl.class);
	@EJB
	private DetalleComprobanteDAO objDetCompDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetalleComprobanteService#insertComprobante(com.edicsem.pe.sie.entity.DetalleComprobanteSie)
	 */
	public void insertComprobante(DetalleComprobanteSie comp) {
		objDetCompDao.insertComprobante(comp);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetalleComprobanteService#listarDetComprobantes(int)
	 */
	public List listarDetComprobantes(int codcomp) {
		return objDetCompDao.listarDetComprobantes(codcomp);
	}
	
		
}
