package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
 

@Local
public interface CobranzaOperaDAO {
	
	public abstract void insertCobranzaOpera(CobranzaOperadoraSie cobranzaopera);
	public abstract void updateCobranzaOpera(CobranzaOperadoraSie cobranzaopera);
	public abstract CobranzaOperadoraSie findCobranzaOpera (int id);
	public abstract List  listarCobranzasOpera();
}