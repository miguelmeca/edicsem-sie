package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.model.dao.ContratoDAO;
import com.edicsem.pe.sie.service.facade.ContratoService;

@Stateless
public class ContratoServiceImpl implements ContratoService {

	@EJB
	private  ContratoDAO objContratoDao;
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#insertContrato(com.edicsem.pe.sie.entity.ContratoSie)
	 */
	
	public void insertContrato(ContratoSie contrato) {
		objContratoDao.insertContrato(contrato);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#updateContrato(com.edicsem.pe.sie.entity.ContratoSie)
	 */
	
	public void updateContrato(ContratoSie contrato) {
		objContratoDao.updateContrato(contrato);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#findContrato(int)
	 */
	
	public ContratoSie findContrato(int id) {
		return objContratoDao.findContrato(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#listarContratos()
	 */
	public List listarContratos() {
		return objContratoDao.listarContratos();
	}
}
