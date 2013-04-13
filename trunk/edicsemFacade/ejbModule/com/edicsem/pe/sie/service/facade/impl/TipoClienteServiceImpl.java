package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoClienteSie;
import com.edicsem.pe.sie.model.dao.TipoClienteDAO;
import com.edicsem.pe.sie.service.facade.TipoClienteService;
@Stateless
public class TipoClienteServiceImpl implements TipoClienteService{
	
	@EJB
	private TipoClienteDAO objTipoClienteDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoClienteService#listarTipoCliente()
	 */
	public List listarTipoCliente() {
		return objTipoClienteDao.listarTipoCliente();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoClienteService#insertTipoCliente(com.edicsem.pe.sie.entity.TipoClienteSie)
	 */
	public void insertTipoCliente(TipoClienteSie objTipoClienteSie) {
		objTipoClienteDao.insertTipoCliente(objTipoClienteSie);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoClienteService#updateTipoCliente(com.edicsem.pe.sie.entity.TipoClienteSie)
	 */
	public void updateTipoCliente(TipoClienteSie objTipoClienteSie) {
		objTipoClienteDao.updateTipoCliente(objTipoClienteSie);
	}
}
