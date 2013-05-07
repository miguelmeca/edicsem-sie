package com.edicsem.pe.sie.model.dao;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;

@Local
public interface CobranzaOperaDAO {
	public abstract void insertCobranzaOpera(CobranzaOperadoraSie cobranzaopera);
	public abstract void updateCobranzaOpera(CobranzaOperadoraSie cobranzaopera);
	public abstract CobranzaOperadoraSie findCobranzaOpera (int id);
	public abstract List  listarCobranzasOpera(String usuario);
	public abstract int verificargeneracionDiaria();
	public abstract List listarCobranzasOperaPagada(String usuario);
	public abstract List listarCobranzasOperaFechaActual(Date dhoy);
}
