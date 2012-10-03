package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.model.dao.ProveedorDAO;
import com.edicsem.pe.sie.service.facade.ProveedorService;

@Stateless
public class ProveedorServiceImpl implements ProveedorService {

	@EJB
	private  ProveedorDAO objProveedorDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProveedorService#listarProveedores()
	 */
	
	public List listarProveedores() {
		
		return objProveedorDao.listarProveedores();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProveedorService#findProducto(int)
	 */
	
	public ProveedorSie findProducto(int id) {
		
		return objProveedorDao.findProducto(id);
	}
	
}
