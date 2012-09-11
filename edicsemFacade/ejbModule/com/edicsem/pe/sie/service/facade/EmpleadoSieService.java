package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.EmpleadoSie; 

@Local
public interface EmpleadoSieService {
	
	public abstract void insertarEmpleado (EmpleadoSie empleado);
	public abstract void actualizarEmpleado (EmpleadoSie empleado);
	public abstract void eliminarEmpleado (int id);
	public abstract EmpleadoSie buscarEmpleado (int id);
	public abstract List listarEmpleados ();
}
