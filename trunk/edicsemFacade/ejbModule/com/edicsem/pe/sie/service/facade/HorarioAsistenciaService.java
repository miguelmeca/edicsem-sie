package com.edicsem.pe.sie.service.facade;

import java.util.List;
import javax.ejb.Local;
import com.edicsem.pe.sie.entity.HorarioAsistenciaSie;

@Local
public interface HorarioAsistenciaService {
	

	public abstract void insertHorarioAsistencia(HorarioAsistenciaSie horarioasistencia);
	public abstract void updateHorarioAsistencia(HorarioAsistenciaSie horarioasistencia);
	public abstract void eliminarHorarioAsistencia(int id);
	public abstract HorarioAsistenciaSie findHorarioAsistencia(int id);
	public abstract List  listarHorarioAsistencia(); 
	public abstract List  listarHorarioAsistenciaXempleado(int id); 
	public abstract List  obtenerFechaFinalXempleado(int id); 
}
