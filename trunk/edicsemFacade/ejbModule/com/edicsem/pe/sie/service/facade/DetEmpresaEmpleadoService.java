package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetEmpresaEmpleadoSie;
@Local
public interface DetEmpresaEmpleadoService {

	public abstract void insertDetEmpresaEmpleadoSie(List<String> e , int idEmpresa, boolean lider);
	public abstract void updateDetEmpresaEmpleadoSie(DetEmpresaEmpleadoSie d);
	public abstract DetEmpresaEmpleadoSie findDetEmpresaEmpleadoSie(int id);
	public abstract List  listarDetEmpresaEmpleadoSie();
	public abstract List  listarDetEmpresaEmpleadoSieXEmpresa(int idempresa);
	public abstract int  filtrartipoventaPersonal(int idvendedor);
	public abstract boolean verificarEmpleadoConEmpresa (int idcargo);
}
