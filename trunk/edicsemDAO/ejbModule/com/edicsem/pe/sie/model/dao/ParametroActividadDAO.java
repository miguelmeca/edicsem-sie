package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ParametroActividadSie;
 

@Local
public interface ParametroActividadDAO {
	
	public abstract void insertParametroActividad(ParametroActividadSie p);
	public abstract void updateParametroActividad(ParametroActividadSie p);
	public abstract ParametroActividadSie findParametroActividad(int id);
	public abstract List  listarParametroActividad();
	
}
