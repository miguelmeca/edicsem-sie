package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.beans.RecaudacionDTO;
import com.edicsem.pe.sie.entity.CobranzaSie;

@Local
public interface CobranzaService {
	
	public abstract void insertCobranza(CobranzaSie Cobranza);
	public abstract void updateCobranza(CobranzaSie Cobranza);
	public abstract CobranzaSie findCobranza (int id);
	public abstract List<CobranzaSie>  listarCobranzas();
	public abstract List  listarCobranzasXidcontrato(int idcontrato);
	public abstract List  calcularEfectividad(int idEmpleado);
	public abstract String MigrarRecaudacion(List<RecaudacionDTO> lst);
}
