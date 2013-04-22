package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoCobranzaSie;

@Local
public interface TipoCobranzaDAO {
	
	public abstract void insertTipoCobranza(TipoCobranzaSie t);
	public abstract void updateTipoCobranza(TipoCobranzaSie t);
	public abstract TipoCobranzaSie findTipoCobranza (int id);
	public abstract List  listarTipoCobranza();
	
}
