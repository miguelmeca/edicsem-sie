package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TurnoSie;
 

@Local
public interface TurnoDAO {
	
	public abstract void insertTurno(TurnoSie t);
	public abstract void updateTurno(TurnoSie t);
	public abstract TurnoSie findTurno (int id);
	public abstract List  listarTurno();
	
}
