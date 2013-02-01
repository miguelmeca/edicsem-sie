package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.DetGrupoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.DetGrupoEmpleadoService;

@Stateless
public class DetGrupoEmpleadoServiceImpl implements DetGrupoEmpleadoService {

	@EJB
	private  DetGrupoEmpleadoDAO objDetGrupoDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetGrupoEmpleadoService#insertDetGrupoEmpleado(com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie)
	 */
	public void insertDetGrupoEmpleado(DetGrupoEmpleadoSie d) {
		objDetGrupoDao.insertDetGrupoEmpleado(d);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetGrupoEmpleadoService#updateDetGrupoEmpleado(com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie)
	 */
	public void updateDetGrupoEmpleado(DetGrupoEmpleadoSie d) {
		objDetGrupoDao.updateDetGrupoEmpleado(d);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetGrupoEmpleadoService#findDetGrupoEmpleado(int)
	 */
	public DetGrupoEmpleadoSie findDetGrupoEmpleado(int id) {
		return objDetGrupoDao.findDetGrupoEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetGrupoEmpleadoService#listarDetGrupoEmpleado()
	 */
	public List listarDetGrupoEmpleado() {
		return objDetGrupoDao.listarDetGrupoEmpleado();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetGrupoEmpleadoService#listarEmpleadosXGrupo(int)
	 */
	public List listarEmpleadosXGrupo(int idGrupo) {
		return objDetGrupoDao.listarEmpleadosXGrupo(idGrupo);
	}
}
