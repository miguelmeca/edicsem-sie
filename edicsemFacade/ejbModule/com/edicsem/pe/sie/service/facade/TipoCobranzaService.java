package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoCobranzaSie;
@Local
public interface TipoCobranzaService {

	public abstract void insertTipoCobranza(TipoCobranzaSie t);
	public abstract void updateTipoCobranza(TipoCobranzaSie t);
	public abstract TipoCobranzaSie findTipoCobranza (int id);
	public abstract List  listarTipoCobranza();
	
}
