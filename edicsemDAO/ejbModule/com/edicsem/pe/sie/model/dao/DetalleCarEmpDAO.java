package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetCargoEmpleadoSie;

@Local
public interface DetalleCarEmpDAO {
	
	public abstract void insertarDetalleCarEmp (DetCargoEmpleadoSie detallecaremp);
	public abstract void actualizarDetalleCarEmp (DetCargoEmpleadoSie detallecaremp);
	public abstract void eliminarDetalleCarEmp (int id);
	public abstract DetCargoEmpleadoSie findDetalleCarEmp (int id);
	public abstract List listarDetalleCarEmp ();
	public abstract List listarxCargo (int cargo);
	public abstract List listarCargoXEmp (int idEmpleado);
	public abstract boolean verificarEmpleadoConCargo (int idcargo);
}