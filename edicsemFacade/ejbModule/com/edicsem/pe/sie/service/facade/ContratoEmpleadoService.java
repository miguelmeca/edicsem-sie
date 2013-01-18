package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;

@Local
public interface ContratoEmpleadoService {
	
	public abstract void insertContratoEmpleado(ContratoEmpleadoSie contrato);
	public abstract void updateContratoEmpleado(ContratoEmpleadoSie contrato);
	public abstract ContratoEmpleadoSie findContratoEmpleado (int id);
	public abstract List  listarContrato();
	public abstract List  listarPatrocinados(int idEmpleado,String fechaInicio,String fechaFin );
	public abstract List listarxCargo(int cargo) ;
	public abstract List listarCargoXEmp(int idEmpleado);
	public abstract boolean verificarEmpleadoConCargo(int idcargo);
	
}
