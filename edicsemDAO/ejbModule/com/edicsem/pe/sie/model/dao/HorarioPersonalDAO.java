package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.entity.TipoCasaSie;

@Local
public interface HorarioPersonalDAO {
	
	public abstract void insertHorarioPersonal(HorarioPersonalSie horariopersonal);
	public abstract void updateHorarioPersonal(HorarioPersonalSie horariopersonal);
	public abstract void eliminarHorarioPersonal(int id);
	public abstract HorarioPersonalSie findHorarioPersonal(int id);
	public abstract List  listarHorarioPersonal(); 
	public abstract List  listarHorarioPersonalXempleado(int id); 
	
}
