package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TelefonoPersonaSie;


@Local
public interface TelefonoEmpleadoDAO {
	
	public abstract void insertarTelefonoEmpleado (TelefonoPersonaSie telefonopersona);
	public abstract void actualizarTelefonoEmpleado (TelefonoPersonaSie telefonopersona);
	public abstract void eliminarTelefonoEmpleado (int id);
	public abstract TelefonoPersonaSie buscarTelefonoEmpleado (int id);
	public abstract List listarTelefonoEmpleados ();
}
