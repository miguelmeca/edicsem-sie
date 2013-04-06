package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.HistorialParametroSistemaSie;
@Local
public interface HistorialParametroSistemaService {
	
	public abstract void insertHistorial(HistorialParametroSistemaSie h);
	public abstract void updateHistorial(HistorialParametroSistemaSie h);
	public abstract HistorialParametroSistemaSie findHistorial (int id);
	public abstract List  listarhistorial();
	
}
