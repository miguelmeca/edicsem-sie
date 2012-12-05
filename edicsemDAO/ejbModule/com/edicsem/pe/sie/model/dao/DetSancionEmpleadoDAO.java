package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetSancionEmpleadoSie;
 

@Local
public interface DetSancionEmpleadoDAO {
	
	public abstract void insertDetSancionEmpleado(DetSancionEmpleadoSie d);
	public abstract void updateDetSancionEmpleado(DetSancionEmpleadoSie d);
	public abstract DetSancionEmpleadoSie findContrato (int id);
	public abstract List  listarDetSancionEmpleado();
}
