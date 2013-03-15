package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;

@Local
public interface CargoEmpleadoService {
	
	public abstract void insertarCargoEmpleado (CargoEmpleadoSie cargoempleado);
	public abstract void actualizarCargoEmpleado (CargoEmpleadoSie cargoempleado);
	public abstract void eliminarCargoEmpleado (int id);
	public abstract CargoEmpleadoSie buscarCargoEmpleado (int id);
	public abstract List listarCargoEmpleado ();
	public abstract List listarCargosXEmpleado(int idEmpleado);
	public abstract CargoEmpleadoSie buscarCargoEmpleado(String cargo);
}
