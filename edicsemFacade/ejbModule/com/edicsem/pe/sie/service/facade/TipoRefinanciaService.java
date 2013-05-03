package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoRefinanciaSie;
@Local
public interface TipoRefinanciaService {

	public abstract void insertTipoRefinancia(TipoRefinanciaSie t, int tipocliente);
	public abstract void updateTipoRefinancia(TipoRefinanciaSie t, int tipocliente);
	public abstract TipoRefinanciaSie findTipoRefinancia (int id);
	public abstract List listarTipoRefinancia();
	public abstract List listarTipoRefinanciaXTipoCliente(int tipocliente);
}
