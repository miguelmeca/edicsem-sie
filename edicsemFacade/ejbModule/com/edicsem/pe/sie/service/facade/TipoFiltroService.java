package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoFiltroSie;
@Local
public interface TipoFiltroService {
	
	public abstract TipoFiltroSie findTipoFiltro(int id);
	public abstract List  listarTipoFiltro();
	
}
