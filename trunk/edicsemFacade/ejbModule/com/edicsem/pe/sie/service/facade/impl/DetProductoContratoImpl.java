package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.DetProductoContratoSie;
import com.edicsem.pe.sie.model.dao.DetProductoContratoDAO;
import com.edicsem.pe.sie.service.facade.DetProductoContratoService;

@Stateless
public class DetProductoContratoImpl implements DetProductoContratoService {

	@EJB
	private  DetProductoContratoDAO objDetProductoContratoDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetProductoContratoService#insertDetProductoContrato(com.edicsem.pe.sie.entity.DetProductoContratoSie)
	 */
	public void insertDetProductoContrato(DetProductoContratoSie det) {
		objDetProductoContratoDao.insertDetProductoContrato(det);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetProductoContratoService#updateDetProductoContrato(com.edicsem.pe.sie.entity.DetProductoContratoSie)
	 */
	public void updateDetProductoContrato(DetProductoContratoSie det) {
		objDetProductoContratoDao.updateDetProductoContrato(det);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetProductoContratoService#findDetProductoContrato(int)
	 */
	public DetProductoContratoSie findDetProductoContrato(int id) {
		return objDetProductoContratoDao.findDetProductoContrato(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetProductoContratoService#listarDetProductoContrato()
	 */
	public List listarDetProductoContrato() {
		return objDetProductoContratoDao.listarDetProductoContrato();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetProductoContratoService#listarDetProductoContratoXContrato(int)
	 */
	public List listarDetProductoContratoXContrato(int idContrato) {
		return objDetProductoContratoDao.listarDetProductoContratoXContrato(idContrato);
	}
}
