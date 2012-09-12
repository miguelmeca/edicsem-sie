package com.edicsem.pe.sie.service.facade;



import javax.ejb.Local;



@Local
public interface LoginService {
	
	public abstract boolean validacionLogin (String usuario, String contrasena);
}
//PONGAN GRABADORA
//ya esta !!E
//ah por cierto hace un rato cuando se debio eiminar el dat temp o dos mas por casualidad se booro la carpeta lib donde esta 
//postgresql-9.1-902.jdbc4 y luego se restauro desde la papelera de reciclaje ??? habra un problema..????