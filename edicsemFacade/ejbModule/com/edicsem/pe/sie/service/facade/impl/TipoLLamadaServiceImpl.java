package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.entity.TipoLlamadaSie;
import com.edicsem.pe.sie.model.dao.TipoCasaDAO; 
import com.edicsem.pe.sie.model.dao.TipoLLamadaDAO;
import com.edicsem.pe.sie.service.facade.TipoLLamadaService;
@Stateless
public class TipoLLamadaServiceImpl implements TipoLLamadaService{
	
	public static Log log = LogFactory.getLog(TipoLLamadaServiceImpl.class);
	
	@EJB
	private TipoLLamadaDAO objTipoLLamadaDao;
	
	
	public void insertTipoLLamada(TipoLlamadaSie tipollamada) {
	
		
		objTipoLLamadaDao.insertTipoLLamada(tipollamada);
	}

	
	public void updateTipoLLamada(TipoLlamadaSie tipollamada) {
		objTipoLLamadaDao.updateTipoLLamada(tipollamada);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarTipoLLamada(int id) {
		objTipoLLamadaDao.eliminarTipoLLamada(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#findEstadogeneral(int)
	 */

	public TipoLlamadaSie findTipoLLamada(int id) {
		// TODO Auto-generated method stub
		return objTipoLLamadaDao.findTipoLLamada(id); 
	}

	public List listarTipoLLamada() {
		log.info("En el servicio ");
		return objTipoLLamadaDao.listarTipoLLamada();
	}

	
		
}
