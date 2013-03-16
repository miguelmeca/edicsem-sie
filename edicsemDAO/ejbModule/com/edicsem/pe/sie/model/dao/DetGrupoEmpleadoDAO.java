package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie;
 

@Local
public interface DetGrupoEmpleadoDAO {
	
	public abstract void insertDetGrupoEmpleado (DetGrupoEmpleadoSie d);
	public abstract void updateDetGrupoEmpleado (DetGrupoEmpleadoSie d);
	public abstract DetGrupoEmpleadoSie findDetGrupoEmpleado (int id);
	public abstract List  listarDetGrupoEmpleado(int idtipoevento);
	public abstract List  listarEmpleadosXGrupo(int idGrupo );
}
