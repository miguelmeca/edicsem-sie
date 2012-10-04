package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.model.dao.UbigeoDAO; 
import com.edicsem.pe.sie.service.facade.UbigeoService;
@Stateless
public class UbigeoServiceImpl implements UbigeoService{
	
	public static Log log = LogFactory.getLog(UbigeoServiceImpl.class);
	
	@EJB
	private UbigeoDAO objUbigeoDao;
	
	
	public void insertUbigeo(UbigeoSie ubigeo) {
	
		
		objUbigeoDao.insertUbigeo(ubigeo);
	}

	
	public void updateUbigeo(UbigeoSie ubigeo) {
		objUbigeoDao.updateUbigeo(ubigeo);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarUbigeo(int id) {
		objUbigeoDao.eliminarUbigeo(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EstadogeneralService#findEstadogeneral(int)
	 */

	public UbigeoSie findUbigeo(int id) {
		// TODO Auto-generated method stub
		return objUbigeoDao.findUbigeo(id); 
	}

	public List listarUbigeoDepartamentos() {
		log.info("En el servicio ");
		return objUbigeoDao.listarUbigeoDepartamentos();
	}

	
		
}
