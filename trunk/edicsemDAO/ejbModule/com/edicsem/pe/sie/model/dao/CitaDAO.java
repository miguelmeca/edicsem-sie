package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CitaSie;
 
@Local
public interface CitaDAO {
	
	public abstract void insertCita(CitaSie c);
	public abstract void updateCita(CitaSie c);
	public abstract CitaSie findCita (int id);
	public abstract List  listarCitas();
	
}
