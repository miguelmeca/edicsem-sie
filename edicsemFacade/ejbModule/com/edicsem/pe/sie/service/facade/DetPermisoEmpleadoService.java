package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.beans.MenuDTO;
import com.edicsem.pe.sie.entity.DetPermisoEmpleadoSie;
@Local
public interface DetPermisoEmpleadoService {
	
	public abstract void insertDetPermisoEmpleado(List<String> p, int idEmpleado);
	public abstract void updateDetPermisoEmpleado(DetPermisoEmpleadoSie p);
	public abstract DetPermisoEmpleadoSie findDetPermisoEmpleado (int id);
	public abstract List<MenuDTO>  listarPermisoXUsuario(String usuario);
	
}
