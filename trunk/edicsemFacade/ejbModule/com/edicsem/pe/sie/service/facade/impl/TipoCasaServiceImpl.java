package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.model.dao.TipoCasaDAO; 
import com.edicsem.pe.sie.service.facade.TipoCasaService;
@Stateless
public class TipoCasaServiceImpl implements TipoCasaService{
	
	public static Log log = LogFactory.getLog(TipoCasaServiceImpl.class);
	
	@EJB
	private TipoCasaDAO objTipoCasaDao;
	
	
	public void insertTipoCasa(TipoCasaSie tipocasa) {
	
		
		objTipoCasaDao.insertTipoCasa(tipocasa);
	}

	
	public void updateTipoCasa(TipoCasaSie tipocasa) {
		objTipoCasaDao.updateTipoCasa(tipocasa);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarTipoCasa(int id) {
		objTipoCasaDao.eliminarTipoCasa(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#findEstadogeneral(int)
	 */

	public TipoCasaSie findTipoCasa(int id) {
		// TODO Auto-generated method stub
		return objTipoCasaDao.findTipoCasa(id); 
	}

	public List listarTipoCasa() {
		log.info("En el servicio ");
		return objTipoCasaDao.listarTipoCasa();
	}

	
		
}
