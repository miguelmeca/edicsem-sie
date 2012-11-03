package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.entity.TipoCasaSie;

@Local
public interface HorarioPersonalService {
	

	public abstract void insertHorarioPersonal(HorarioPersonalSie horariopersonal);
	public abstract void updateHorarioPersonal(HorarioPersonalSie horariopersonal);
	public abstract void eliminarHorarioPersonal(int id);
	public abstract HorarioPersonalSie findHorarioPersonal(int id);
	public abstract List  listarHorarioPersonal(); 
	public abstract List  listarHorarioPersonalXempleado(int id); 
}
