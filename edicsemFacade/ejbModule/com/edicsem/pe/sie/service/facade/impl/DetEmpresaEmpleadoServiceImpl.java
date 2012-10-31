package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.DetEmpresaEmpleadoSie;
import com.edicsem.pe.sie.model.dao.DetEmpresaEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EmpresaDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.DetEmpresaEmpleadoService;

@Stateless
public class DetEmpresaEmpleadoServiceImpl implements DetEmpresaEmpleadoService {

	@EJB
	private  DetEmpresaEmpleadoDAO objDetEmpresaEmpleadoDao;
	@EJB
	private EmpleadoSieDAO objEmpleadoDao;
	@EJB
	private EmpresaDAO objEmpresaDao;
	@EJB
	private EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetEmpresaEmpleadoService#insertDetEmpresaEmpleadoSie(java.util.List, int, boolean)
	 */
	public void insertDetEmpresaEmpleadoSie(List<String> e , int idEmpresa, boolean lider) {
		
		for (int i = 0; i < e.size(); i++) {
			DetEmpresaEmpleadoSie det =new DetEmpresaEmpleadoSie();
			if(lider)det.setLider("S");
			det.setTbEmpresa(objEmpresaDao.findEmpresa(idEmpresa));
			det.setTbEmpleado(objEmpleadoDao.buscarEmpleado(Integer.parseInt(e.get(i))));
			det.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(33));
			objDetEmpresaEmpleadoDao.insertDetEmpresaEmpleadoSie(det);
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetEmpresaEmpleadoService#updateDetEmpresaEmpleadoSie(com.edicsem.pe.sie.entity.DetEmpresaEmpleadoSie)
	 */
	public void updateDetEmpresaEmpleadoSie(DetEmpresaEmpleadoSie d) {
		objDetEmpresaEmpleadoDao.updateDetEmpresaEmpleadoSie(d);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetEmpresaEmpleadoService#findDetEmpresaEmpleadoSie(int)
	 */
	public DetEmpresaEmpleadoSie findDetEmpresaEmpleadoSie(int id) {
		return objDetEmpresaEmpleadoDao.findDetEmpresaEmpleadoSie(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetEmpresaEmpleadoService#listarDetEmpresaEmpleadoSie()
	 */
	public List listarDetEmpresaEmpleadoSie() {
		return objDetEmpresaEmpleadoDao.listarDetEmpresaEmpleadoSie();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetEmpresaEmpleadoService#listarDetEmpresaEmpleadoSieXEmpresa(int)
	 */
	public List listarDetEmpresaEmpleadoSieXEmpresa(int idempresa) {
		return objDetEmpresaEmpleadoDao.listarDetEmpresaEmpleadoSieXEmpresa(idempresa);
	}
	
}
