package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie;
@Local
public interface ConfigCobranzaService {

	public abstract void insertConfigCobranza(ConfigCobranzaOperaSie c);
	public abstract void updateConfigCobranza(ConfigCobranzaOperaSie c);
	public abstract ConfigCobranzaOperaSie findConfigCobranza(int id);
	public abstract List  listarConfigCobranza();
}
