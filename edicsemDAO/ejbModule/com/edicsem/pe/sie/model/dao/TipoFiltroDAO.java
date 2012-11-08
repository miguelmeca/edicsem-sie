package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoFiltroSie;
 

@Local
public interface TipoFiltroDAO {
	
	public abstract TipoFiltroSie findTipoFiltro(int id);
	public abstract List  listarTipoFiltro();
}
