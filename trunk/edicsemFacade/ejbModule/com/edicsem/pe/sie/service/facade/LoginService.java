package com.edicsem.pe.sie.service.facade;

import javax.ejb.Local;
import com.edicsem.pe.sie.entity.EmpleadoSie;

@Local
public interface LoginService {
	
	public abstract EmpleadoSie validacionLogin (String usuario, String contrasena);
	public abstract void updatePassword(int idEmpleado, String empleado);
}