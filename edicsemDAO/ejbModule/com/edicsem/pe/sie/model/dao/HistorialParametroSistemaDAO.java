package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.HistorialParametroSistemaSie;
 

@Local
public interface HistorialParametroSistemaDAO {
	
	public abstract void insertHistorial(HistorialParametroSistemaSie h);
	public abstract void updateHistorial(HistorialParametroSistemaSie h);
	public abstract HistorialParametroSistemaSie findHistorial (int id);
	public abstract List  listarhistorial();
	
}
