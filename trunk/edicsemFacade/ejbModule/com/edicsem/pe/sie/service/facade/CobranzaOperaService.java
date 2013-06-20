package com.edicsem.pe.sie.service.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie;

@Local
public interface CobranzaOperaService {
	
	public abstract void insertCobranzaOpera(CobranzaOperadoraSie cobranzaopera,List<ConfigCobranzaOperaSie> configList );
	public abstract List<CobranzaSie> insertCobranzaOpera(List<String> empleadoList,List<ConfigCobranzaOperaSie> configList);
	public abstract void updateCobranzaOpera(CobranzaOperadoraSie cobranzaopera);
	public abstract CobranzaOperadoraSie findCobranzaOpera (int id);
	public abstract List  listarCobranzasOpera(String usuario);
	public abstract int verificargeneracionDiaria();
	public abstract List listarCobranzasOperaPagada(String usuario);
	public abstract List listarCobranzasOperaFechaActual(Date dhoy);
}
