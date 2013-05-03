package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoRefinanciaSie;
 

@Local
public interface TipoRefinanciaDAO {
	
	public abstract void insertTipoRefinancia(TipoRefinanciaSie t);
	public abstract void updateTipoRefinancia(TipoRefinanciaSie t);
	public abstract TipoRefinanciaSie findTipoRefinancia (int id);
	public abstract List listarTipoRefinancia();
	public abstract List listarTipoRefinanciaXTipoCliente(int tipocliente);
}
