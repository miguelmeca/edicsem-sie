package com.edicsem.pe.sie.service.facade.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.AuditoriaUsuarioSie;
import com.edicsem.pe.sie.model.dao.AuditoriaUsuarioDAO;
import com.edicsem.pe.sie.service.facade.AuditoriaUsuarioService;

@Stateless
public class AuditoriaUsuarioServiceImpl implements AuditoriaUsuarioService {

	@EJB
	private  AuditoriaUsuarioDAO objAlmacenDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.AuditoriaUsuarioService#insertAuditoriaUsuario(com.edicsem.pe.sie.entity.AuditoriaUsuarioSie)
	 */
	public void insertAuditoriaUsuario(AuditoriaUsuarioSie a) {
		objAlmacenDao.insertAuditoriaUsuario(a);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.AuditoriaUsuarioService#listarAuditoriaUsuario(java.lang.String)
	 */
	public AuditoriaUsuarioSie listarAuditoriaUsuario(String usuario) {
		return objAlmacenDao.listarAuditoriaUsuario(usuario);
	}
	
}
