package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetContratoEmpleadoSie;
@Local
public interface DetContratoEmpleadoService {

	public abstract void insertDetContratoEmpleado(DetContratoEmpleadoSie d);
	public abstract void updateDetContratoEmpleado(DetContratoEmpleadoSie d);
	public abstract DetContratoEmpleadoSie findDetContratoEmpleado(int id);
	public abstract List  listarDetContratoEmpleado();
	public abstract List listarContratoXEmpleado(int idempleado, int idMes);
}
