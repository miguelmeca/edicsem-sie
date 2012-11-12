package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.HorarioPuntoVentaSie;
@Local
public interface HorarioPuntoVentaService {
	
	public abstract void insertHorarioPunto(HorarioPuntoVentaSie h);
	public abstract void updateHorarioPunto(HorarioPuntoVentaSie h);
	public abstract HorarioPuntoVentaSie findHorarioPunto(int id);
	public abstract List  listarHorarioPunto();
	public abstract List  listarHorarioPuntoVentaXidPV(int id); 
}
