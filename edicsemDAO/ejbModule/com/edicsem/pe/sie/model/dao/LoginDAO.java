package com.edicsem.pe.sie.model.dao;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.EmpleadoSie;


@Local
public interface LoginDAO {
	
	public abstract EmpleadoSie validacionLogin (String usuario, String contrasena);
	public abstract void updatePassword(EmpleadoSie empleado) ;
}
