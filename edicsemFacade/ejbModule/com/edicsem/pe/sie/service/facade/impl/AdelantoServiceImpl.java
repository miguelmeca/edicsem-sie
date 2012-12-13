package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.AdelantoSie;
import com.edicsem.pe.sie.model.dao.AdelantoDAO;
import com.edicsem.pe.sie.service.facade.AdelantoService;
@Stateless
public class AdelantoServiceImpl implements AdelantoService{
	
	public static Log log = LogFactory.getLog(AdelantoServiceImpl.class);
	
	@EJB
	private AdelantoDAO objAdelantoDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.AdelantoService#insertAdelanto(com.edicsem.pe.sie.entity.AdelantoSie)
	 */
	public void insertAdelanto(AdelantoSie a) {
		objAdelantoDao.insertAdelanto(a);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.AdelantoService#updateAdelanto(com.edicsem.pe.sie.entity.AdelantoSie)
	 */
	public void updateAdelanto(AdelantoSie a) {
		objAdelantoDao.updateAdelanto(a);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.AdelantoService#findAdelanto(int)
	 */
	public AdelantoSie findAdelanto(int id) {
		return objAdelantoDao.findAdelanto(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.AdelantoService#listarAdelantos()
	 */
	public List listarAdelantos() {
		return objAdelantoDao.listarAdelantos();
	}
}
