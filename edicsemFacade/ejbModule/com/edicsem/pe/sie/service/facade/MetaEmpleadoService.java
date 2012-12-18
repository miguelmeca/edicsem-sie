package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.MetaEmpleadoSie;

@Local
public interface MetaEmpleadoService {
	
	public abstract void insertMetaEmpleado(MetaEmpleadoSie m,int  idmetames);
	public abstract void updateMetaEmpleado(MetaEmpleadoSie m);
	public abstract MetaEmpleadoSie findMetaEmpleado (int id);
	public abstract List  listarMetaEmpleado();
}
