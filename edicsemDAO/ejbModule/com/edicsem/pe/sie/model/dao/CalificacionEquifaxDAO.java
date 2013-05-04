package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CalificacionEquifaxSie;
 

@Local
public interface CalificacionEquifaxDAO {
	
	public abstract void insertCalificacion(CalificacionEquifaxSie c);
	public abstract void updateCalificacion(CalificacionEquifaxSie c);
	public abstract CalificacionEquifaxSie findCalificacion(int id);
	public abstract List  listarCalificacionEquifax();
	
}
