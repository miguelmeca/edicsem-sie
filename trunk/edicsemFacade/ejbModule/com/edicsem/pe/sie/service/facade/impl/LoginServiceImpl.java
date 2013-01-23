package com.edicsem.pe.sie.service.facade.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.LoginDAO;
import com.edicsem.pe.sie.service.facade.LoginService;

@Stateless
public class LoginServiceImpl  implements LoginService{
	@EJB
	private LoginDAO objLoginDao;
	@EJB
	private EmpleadoSieDAO objEmpleadoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.LoginService#validacionLogin(java.lang.String, java.lang.String)
	 */
	public EmpleadoSie validacionLogin (String usuario, String contrasena) {
		return objLoginDao.validacionLogin(usuario, contrasena);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.LoginService#updatePassword(int, java.lang.String)
	 */
	public void updatePassword(int idEmpleado,String passwordNuevo) {
		EmpleadoSie empleado=  objEmpleadoDao.buscarEmpleado(idEmpleado);
		empleado.setContrasena(passwordNuevo);
		objLoginDao.updatePassword(empleado);
	}
	
}
