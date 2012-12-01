package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetSancionCargoSie;
import com.edicsem.pe.sie.model.dao.DetSancionCargoDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.DetSancionCargoService;

@Stateless
public class DetSancionCargoServiceImpl implements DetSancionCargoService {

	@EJB
	private DetSancionCargoDAO objDetSancionCargoDao;
	@EJB
	private EstadoGeneralDAO objEstadoGeneralDao;
	
	public static Log log = LogFactory.getLog(DetSancionCargoServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetSancionCargoService#insertDetSancionCargo(com.edicsem.pe.sie.entity.DetSancionCargoSie)
	 */
	public void insertDetSancionCargo(DetSancionCargoSie d) {
		d.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(33));
		objDetSancionCargoDao.insertDetSancionCargo(d);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetSancionCargoService#updateDetSancionCargo(com.edicsem.pe.sie.entity.DetSancionCargoSie)
	 */
	public void updateDetSancionCargo(DetSancionCargoSie d) {
		objDetSancionCargoDao.updateDetSancionCargo(d);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetSancionCargoService#findDetSancionCargo(int)
	 */
	public DetSancionCargoSie findDetSancionCargo(int id) {
		return objDetSancionCargoDao.findDetSancionCargo(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetSancionCargoService#listarDetSancionCargo()
	 */
	public List listarDetSancionCargo(int idSancion) {
		return objDetSancionCargoDao.listarDetSancionCargo(idSancion);
	}
}
