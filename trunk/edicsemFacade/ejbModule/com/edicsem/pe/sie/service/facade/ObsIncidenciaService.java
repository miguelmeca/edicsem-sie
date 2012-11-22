package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.IncidenciaSie;
import com.edicsem.pe.sie.entity.ObservacionIncidenciaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;

@Local
public interface ObsIncidenciaService {
	
	public abstract void insertObsIncidencia(ObservacionIncidenciaSie obsincidencia);
	public abstract void updateObsIncidencia(ObservacionIncidenciaSie obsincidencia);
	public abstract void eliminarObsIncidencia(int id);
	public abstract ObservacionIncidenciaSie findObsIncidencia(int id);
	public abstract List  listarObsIncidencia(int id); 
}
