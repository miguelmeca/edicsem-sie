package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.beans.MenuDTO;
import com.edicsem.pe.sie.entity.DetPermisoEmpleadoSie;

@Local
public interface DetPermisoEmpleadoDAO {
	
	public abstract void insertDetPermisoEmpleado(DetPermisoEmpleadoSie p);
	public abstract void updateDetPermisoEmpleado(DetPermisoEmpleadoSie p);
	public abstract DetPermisoEmpleadoSie findDetPermisoEmpleado (int id);
	public abstract List<MenuDTO>  listarPermisoXUsuario(String usuario);
	public abstract DetPermisoEmpleadoSie findDetPermisoEmpleado(int idEmpleado,String nombrePermiso);
}
