package com.edicsem.pe.sie.model.dao;



import javax.ejb.Local;


@Local
public interface LoginDAO {
	
	public abstract boolean validacionLogin (String usuario, String contrasena);
	
}
