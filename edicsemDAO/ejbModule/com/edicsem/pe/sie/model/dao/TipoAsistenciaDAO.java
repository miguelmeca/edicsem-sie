package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoAsistenciaSie;
 

@Local
public interface TipoAsistenciaDAO {
	
	public abstract TipoAsistenciaSie findTipoAsistencia(int id);
	public abstract List  listarTipoAsistencia();
	
}
