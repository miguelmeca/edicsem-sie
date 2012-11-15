package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.IncidenciaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;

@Local
public interface IncidenciaDAO {
	
	public abstract void insertIncidencia(IncidenciaSie incidencia);
	public abstract void updateIncidencia(IncidenciaSie incidencia);
	public abstract void eliminarIncidencia(int id);
	public abstract IncidenciaSie findIncidencia(int id);
	public abstract List  listarIncidencia(); 
}
