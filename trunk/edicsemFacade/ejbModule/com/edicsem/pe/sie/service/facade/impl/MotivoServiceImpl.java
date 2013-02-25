package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.MotivoSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.MotivoDAO;
import com.edicsem.pe.sie.service.facade.MotivoService;

@Stateless
public class MotivoServiceImpl implements MotivoService {

	@EJB
	private  MotivoDAO objMotivoDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.MotivoService#insertMotivo(com.edicsem.pe.sie.entity.MotivoSie)
	 */
	public void insertMotivo(MotivoSie m) {
		m.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(74));
		objMotivoDao.insertMotivo(m);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.MotivoService#updateMotivo(com.edicsem.pe.sie.entity.MotivoSie)
	 */
	public void updateMotivo(MotivoSie m) {
		objMotivoDao.updateMotivo(m);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.MotivoService#findMotivo(int)
	 */
	public MotivoSie findMotivo(int id) {
		return objMotivoDao.findMotivo(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.MotivoService#listarMotivo()
	 */
	public List listarMotivo() {
		return objMotivoDao.listarMotivo();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.MotivoService#deleteCargoEmpleado(com.edicsem.pe.sie.entity.MotivoSie)
	 */
	public void deleteCargoEmpleado(MotivoSie m) {
		m.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(75));
		objMotivoDao.updateMotivo(m);
	}
	
}
