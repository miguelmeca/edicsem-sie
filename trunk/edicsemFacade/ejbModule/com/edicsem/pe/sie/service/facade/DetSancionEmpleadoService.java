package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetSancionEmpleadoSie;

@Local
public interface DetSancionEmpleadoService {
	
	public abstract void insertDetSancionEmpleado(DetSancionEmpleadoSie d, int idSancion, int idEmpleado);
	public abstract void updateDetSancionEmpleado(DetSancionEmpleadoSie d);
	public abstract DetSancionEmpleadoSie findContrato (int id);
	public abstract List  listarDetSancionEmpleado();
}
