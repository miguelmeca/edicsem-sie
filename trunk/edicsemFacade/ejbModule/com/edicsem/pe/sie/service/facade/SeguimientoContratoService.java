package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.SeguimientoContratoSie;
@Local
public interface SeguimientoContratoService {
	
	public abstract void insertSeguimientoContrato(SeguimientoContratoSie s, int idMotivo, ContratoSie objContratoSie, int estadoRefinan, List<CobranzaSie> cobranzaList);
	public abstract void updateSeguimientoContrato(SeguimientoContratoSie s);
	public abstract SeguimientoContratoSie findSeguimientoContrato (int id);
	public abstract List  listarSeguimientoContrato(int codcontrato);
}
