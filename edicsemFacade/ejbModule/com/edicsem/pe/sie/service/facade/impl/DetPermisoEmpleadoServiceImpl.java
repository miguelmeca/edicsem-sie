package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.beans.MenuDTO;
import com.edicsem.pe.sie.entity.DetPermisoEmpleadoSie;
import com.edicsem.pe.sie.model.dao.DetPermisoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.DetPermisoEmpleadoService;

@Stateless
public class DetPermisoEmpleadoServiceImpl implements DetPermisoEmpleadoService {

	@EJB
	private  DetPermisoEmpleadoDAO objPermisoEmpleadoDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetPermisoEmpleadoService#insertDetPermisoEmpleado(com.edicsem.pe.sie.entity.DetPermisoEmpleadoSie)
	 */
	public void insertDetPermisoEmpleado(DetPermisoEmpleadoSie p) {
		objPermisoEmpleadoDao.insertDetPermisoEmpleado(p);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetPermisoEmpleadoService#updateDetPermisoEmpleado(com.edicsem.pe.sie.entity.DetPermisoEmpleadoSie)
	 */
	public void updateDetPermisoEmpleado(DetPermisoEmpleadoSie p) {
		objPermisoEmpleadoDao.updateDetPermisoEmpleado(p);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetPermisoEmpleadoService#findDetPermisoEmpleado(int)
	 */
	public DetPermisoEmpleadoSie findDetPermisoEmpleado(int id) {
		return objPermisoEmpleadoDao.findDetPermisoEmpleado(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetPermisoEmpleadoService#listarPermisoXUsuario(java.lang.String)
	 */
	public List<MenuDTO> listarPermisoXUsuario(String usuario) {
		return objPermisoEmpleadoDao.listarPermisoXUsuario(usuario);
	}
	
}
