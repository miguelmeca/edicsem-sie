package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;

@Local
public interface CargoEmpleadoDAO {
	
	public abstract void insertarCargoEmpleado (CargoEmpleadoSie cargoempleado);
	public abstract void actualizarCargoEmpleado (CargoEmpleadoSie cargoempleado);
	public abstract void eliminarCargoEmpleado (int id);
	public abstract CargoEmpleadoSie buscarCargoEmpleado (int id);
	public abstract List listarCargoEmpleado ();
	public abstract List listarCargosXEmpleado(int idEmpleado);
	public abstract CargoEmpleadoSie buscarCargoEmpleado(String cargo);
}
