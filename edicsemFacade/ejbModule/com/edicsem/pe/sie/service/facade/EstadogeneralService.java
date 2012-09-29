package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;

@Local
public interface EstadogeneralService {
	

	public abstract void insertEstadogeneral (EstadoGeneralSie estadogeneral);
	public abstract void updateEstadogeneral(EstadoGeneralSie estadogeneral);
	public abstract void eliminarEstadogeneral(int id);
	public abstract EstadoGeneralSie findEstadogeneral (int id);
	public abstract List  listarEstados(String codigo); 
	
}
