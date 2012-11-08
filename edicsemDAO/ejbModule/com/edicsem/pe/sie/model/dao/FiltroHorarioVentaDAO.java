package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.FiltroHorarioVentaSie;
 

@Local
public interface FiltroHorarioVentaDAO {
	
	public abstract void insertFiltroHorarioVenta( FiltroHorarioVentaSie f);
	public abstract void updateFiltroHorarioVenta(FiltroHorarioVentaSie f);
	public abstract FiltroHorarioVentaSie findFiltroHorarioVenta (int id);
	public abstract List  listarFiltroHorarioVenta();
}
