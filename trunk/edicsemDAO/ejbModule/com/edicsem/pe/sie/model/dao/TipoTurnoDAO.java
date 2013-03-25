package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoTurnoSie;
 

@Local
public interface TipoTurnoDAO {
	
	public abstract void insertTipoTurno(TipoTurnoSie t);
	public abstract void updateTipoTurno(TipoTurnoSie t);
	public abstract TipoTurnoSie findTipoTurno (int id);
	public abstract List  listarTipoTurno();
	
}
