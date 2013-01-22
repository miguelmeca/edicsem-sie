package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;

@Local
public interface CobranzaOperaService {
	
	public abstract void insertCobranzaOpera(List<String> empleadoList);
	public abstract void updateCobranzaOpera(CobranzaOperadoraSie cobranzaopera);
	public abstract CobranzaOperadoraSie findCobranzaOpera (int id);
	public abstract List  listarCobranzasOpera(String usuario);
	public abstract int verificargeneracionDiaria();
}
