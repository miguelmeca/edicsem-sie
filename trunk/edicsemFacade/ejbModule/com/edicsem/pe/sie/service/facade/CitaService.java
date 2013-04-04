package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CitaSie;
@Local
public interface CitaService {

	public abstract void insertCita(CitaSie c);
	public abstract void updateCita(CitaSie c);
	public abstract CitaSie findCita (int id);
	public abstract List  listarCitas();
	
}
