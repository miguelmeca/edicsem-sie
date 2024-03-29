package com.edicsem.pe.sie.model.dao;

import java.util.List;
import javax.ejb.Local;
import com.edicsem.pe.sie.entity.EmpleadoSie;

@Local
public interface EmpleadoSieDAO {
	public abstract void insertarEmpleado (EmpleadoSie empleado);
	public abstract void actualizarEmpleado (EmpleadoSie empleado);
	public abstract void eliminarEmpleado (int id);
	public abstract EmpleadoSie buscarEmpleado (int id);
	public abstract EmpleadoSie buscarEmpleado (String nombreCompleto);
	public abstract List listarEmpleados ();
	public abstract List listarEmpleadosXCargo(int idCargo);
	public abstract List listarEmpleadoxEmpresas(int parametroObtenido);
	public abstract List listarDni();
	public abstract List listarUsuario();
	public abstract EmpleadoSie buscarEmpleadoPorNombreCompleto(String nombreCompleto);
	public abstract EmpleadoSie buscarEmpleadosPorUsuario(String usuario);
}
