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
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.UbigeoService#insertUbigeo(com.edicsem.pe.sie.entity.UbigeoSie)
	 */
	public void insertUbigeo(UbigeoSie ubigeo) {
		objUbigeoDao.insertUbigeo(ubigeo);
	}

	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.UbigeoService#updateUbigeo(com.edicsem.pe.sie.entity.UbigeoSie)
	 */
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
		return objUbigeoDao.findUbigeo(id); 
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.UbigeoService#listarUbigeoDepartamentos()
	 */
	public List listarUbigeoDepartamentos() {
		log.info("En el servicio dep ");
		return objUbigeoDao.listarUbigeoDepartamentos();
	}


	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.UbigeoService#listarUbigeoProvincias(java.lang.String)
	 */
	public List listarUbigeoProvincias(String idDepartamento) {
		log.info("En el servicio prov ");
		return objUbigeoDao.listarUbigeoProvincias(idDepartamento);
	}


	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.UbigeoService#listarUbigeoDistritos(java.lang.String, java.lang.String)
	 */
	public List listarUbigeoDistritos(String idDepartamento, String idProvincia) {
		log.info("En el servicio dist ");
		return objUbigeoDao.listarUbigeoDistritos(idDepartamento, idProvincia);
	}
	
}
