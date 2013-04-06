package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.HistoricoObservacionesSie;
@Local
public interface HistoricoObservacionesService {

	public abstract void insertHistoricoObservaciones(HistoricoObservacionesSie c);
	public abstract void updateHistoricoObservaciones(HistoricoObservacionesSie c);
	public abstract HistoricoObservacionesSie findHistoricoObservaciones (int id);
	public abstract List  listarHistoricoObservaciones();
	public abstract List listarHistorial(int idcontrato);
	
}
