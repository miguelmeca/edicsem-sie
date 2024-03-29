package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie;
@Local
public interface ConfigCobranzaService {

	public abstract void insertConfigCobranza(ConfigCobranzaOperaSie c, int idtipocobranza,int idtipocliente);
	public abstract void updateConfigCobranza(ConfigCobranzaOperaSie c, int idtipocobranza,int idtipocliente);
	public abstract ConfigCobranzaOperaSie findConfigCobranza(int id);
	public abstract List  listarConfigCobranza();
	public abstract List  buscarConfigCobranza(int tipoCobranza);
}
