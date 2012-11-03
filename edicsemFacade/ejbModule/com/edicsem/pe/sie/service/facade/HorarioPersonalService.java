package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.HorarioPersonalSie;

@Local
public interface HorarioPersonalService {
	

	public abstract void insertHorarioPersonal(List<String> diaList, HorarioPersonalSie horariopersonal, int idEmpleado);
	public abstract void updateHorarioPersonal(HorarioPersonalSie horariopersonal);
	public abstract void eliminarHorarioPersonal(int id);
	public abstract HorarioPersonalSie findHorarioPersonal(int id);
	public abstract List  listarHorarioPersonal(); 
	public abstract List  listarHorarioPersonalXempleado(int id); 
}
