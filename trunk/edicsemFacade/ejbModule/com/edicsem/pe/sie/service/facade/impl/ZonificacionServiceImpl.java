package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ZonificacionSie;
import com.edicsem.pe.sie.model.dao.ZonificacionDAO;
import com.edicsem.pe.sie.service.facade.ZonificacionService;

@Stateless
public class ZonificacionServiceImpl implements ZonificacionService {
	
	@EJB
	private ZonificacionDAO objZonificacionDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ZonificacionService#insertZonificacion(com.edicsem.pe.sie.entity.ZonificacionSie)
	 */
	public void insertZonificacion(ZonificacionSie c) {
		objZonificacionDao.insertZonificacion(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ZonificacionService#updateZonificacion(com.edicsem.pe.sie.entity.ZonificacionSie)
	 */
	public void updateZonificacion(ZonificacionSie c) {
		objZonificacionDao.updateZonificacion(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ZonificacionService#findZonificacion(int)
	 */
	public ZonificacionSie findZonificacion(int id) {
		return objZonificacionDao.findZonificacion(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ZonificacionService#listarZonificacion()
	 */
	public List listarZonificacion() {
		return objZonificacionDao.listarZonificacion();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ZonificacionService#listarZonificacionXDistrito(java.lang.String)
	 */
	public List listarZonificacionXDistrito(String idUbigeo) {
		return objZonificacionDao.listarZonificacionXDistrito(idUbigeo);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ZonificacionService#listarZonificacionXPlano(java.lang.String, java.util.List)
	 */
	public List listarZonificacionXPlano(String idUbigeo, List<String> planoList) {
		return objZonificacionDao.listarZonificacionXPlano(idUbigeo,planoList);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ZonificacionService#listarZonificacionXPlanoXLetra(java.lang.String, java.util.List, java.util.List)
	 */
	public List listarZonificacionXPlanoXLetra(String idUbigeo,List<String> planoList, List<String> letraList) {
		return objZonificacionDao.listarZonificacionXPlanoXLetra(idUbigeo, planoList, letraList);
	}
	
}
