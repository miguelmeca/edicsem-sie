package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.MetaEmpleadoSie;
 

@Local
public interface MetaEmpleadoDAO {
	
	public abstract void insertMetaEpleado( MetaEmpleadoSie m);
	public abstract void updateMetaEpleado(MetaEmpleadoSie m);
	public abstract MetaEmpleadoSie findMetaEmpleado (int id);
	public abstract List  listarMetaEmpleado();
}
