package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.model.dao.TipoClienteDAO;
import com.edicsem.pe.sie.service.facade.TipoClienteService;
@Stateless
public class TipoClienteServiceImpl implements TipoClienteService{
	
	@EJB
	private TipoClienteDAO objTipoClienteDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoClienteService#listarCliente()
	 */
	public List listarCliente() {
		return objTipoClienteDao.listarTipoCliente();
	}
}
