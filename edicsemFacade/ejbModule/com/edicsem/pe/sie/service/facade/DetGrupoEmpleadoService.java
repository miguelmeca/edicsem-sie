package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie;
@Local
public interface DetGrupoEmpleadoService {
	
	public abstract void insertDetGrupoEmpleado (DetGrupoEmpleadoSie d);
	public abstract void updateDetGrupoEmpleado (DetGrupoEmpleadoSie d);
	public abstract DetGrupoEmpleadoSie findDetGrupoEmpleado (int id);
	public abstract List  listarDetGrupoEmpleado();
	public abstract List  listarEmpleadosXGrupo(int idGrupo );
}
