package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.HorarioPuntoVentaSie;

@Local
public interface HorarioPuntoVentaDAO {
	
	public abstract void insertHorarioPunto(HorarioPuntoVentaSie h);
	public abstract void updateHorarioPunto(HorarioPuntoVentaSie h);
	public abstract void eliminarHorarioPunto (int id);
	public abstract HorarioPuntoVentaSie findHorarioPunto(int id);
	public abstract List  listarHorarioPunto();
	public abstract List  listarHorarioPuntoVentaXidPV(int id); 
}
