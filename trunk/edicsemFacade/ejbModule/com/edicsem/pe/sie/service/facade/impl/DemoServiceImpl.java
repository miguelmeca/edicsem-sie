package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.Usuario;
import com.edicsem.pe.sie.model.dao.DemoDAO;
import com.edicsem.pe.sie.service.facade.DemoService;
@Stateless
public class DemoServiceImpl implements DemoService{
	
	@EJB
	private DemoDAO objDemoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertDemo(Usuario usuario) {
		objDemoDao.insertDemo(usuario);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void updateDemo(Usuario usuario) {
		objDemoDao.updateDemo(usuario);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void deleteDemo(String id) {
		objDemoDao.deleteDemo(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#findDemo(java.lang.String)
	 */
	public Usuario findDemo(String id) {
		return objDemoDao.findDemo(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	public List listarUsuarios() {
		return objDemoDao.listarUsuarios();
	}

	
		
}
