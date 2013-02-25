package com.edicsem.pe.sie.model.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CobranzaSie;
 

@Local
public interface CobranzaDAO {
	
	public abstract void insertCobranza(CobranzaSie Cobranza);
	public abstract void updateCobranza(CobranzaSie Cobranza);
	public abstract CobranzaSie findCobranza (int id);
	public abstract List<CobranzaSie>  listarCobranzas();
	public abstract List  listarCobranzasXidcontrato(int idcontrato);
	public abstract List  calcularEfectividad(int idEmpleado);
	public abstract CobranzaSie buscarCobranzaXcodigo(String codigo, Date fechaVencimiento,double montototalpagado);
}
