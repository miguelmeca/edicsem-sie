package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetEmpresaEmpleadoSie;
 

@Local
public interface DetEmpresaEmpleadoDAO {
	
	public abstract void insertDetEmpresaEmpleadoSie(DetEmpresaEmpleadoSie d);
	public abstract void updateDetEmpresaEmpleadoSie(DetEmpresaEmpleadoSie d);
	public abstract DetEmpresaEmpleadoSie findDetEmpresaEmpleadoSie(int id);
	public abstract List  listarDetEmpresaEmpleadoSie();
	public abstract List  listarDetEmpresaEmpleadoSieXEmpresa(int idempresa);
	public abstract int  filtrartipoventaPersonal(int idvendedor); 
	public abstract boolean verificarEmpleadoConEmpresa (int idcargo);
}
