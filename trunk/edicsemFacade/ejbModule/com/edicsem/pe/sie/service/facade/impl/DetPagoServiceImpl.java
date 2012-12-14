package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetpagoSie;
import com.edicsem.pe.sie.model.dao.DetPagoDAO;
import com.edicsem.pe.sie.service.facade.DetPagoService;
@Stateless
public class DetPagoServiceImpl implements DetPagoService{
	
	public static Log log = LogFactory.getLog(DetPagoServiceImpl.class);
	
	@EJB
	private DetPagoDAO objDetPagoDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetPagoService#insertDetpago(com.edicsem.pe.sie.entity.DetpagoSie)
	 */
	public void insertDetpago(DetpagoSie a) {
		objDetPagoDao.insertDetpago(a);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetPagoService#updateDetpago(com.edicsem.pe.sie.entity.DetpagoSie)
	 */
	public void updateDetpago(DetpagoSie a) {
		objDetPagoDao.updateDetpago(a);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetPagoService#findDetpago(int)
	 */
	public DetpagoSie findDetpago(int id) {
		return objDetPagoDao.findDetpago(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetPagoService#listarDetpago()
	 */
	public List listarDetpago() {
		return objDetPagoDao.listarDetpago();
	}
	
}
