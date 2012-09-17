package com.edicsem.pe.sie.service.facade;



import javax.ejb.Local;



@Local
public interface LoginService {
	
	public abstract boolean validacionLogin (String usuario, String contrasena);
}