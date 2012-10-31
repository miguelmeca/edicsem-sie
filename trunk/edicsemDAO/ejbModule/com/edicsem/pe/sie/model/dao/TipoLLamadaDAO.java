package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.entity.TipoLlamadaSie;

@Local
public interface TipoLLamadaDAO {
	
	public abstract void insertTipoLLamada(TipoLlamadaSie tipollamada);
	public abstract void updateTipoLLamada(TipoLlamadaSie tipollamada);
	public abstract void eliminarTipoLLamada(int id);
	public abstract TipoLlamadaSie findTipoLLamada(int id);
	public abstract List  listarTipoLLamada(); 
	
}
