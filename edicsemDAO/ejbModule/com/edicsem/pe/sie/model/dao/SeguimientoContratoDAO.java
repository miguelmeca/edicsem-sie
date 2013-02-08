package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.SeguimientoContratoSie;
 

@Local
public interface SeguimientoContratoDAO {
	
	public abstract void insertSeguimientoContrato(SeguimientoContratoSie s);
	public abstract void updateSeguimientoContrato(SeguimientoContratoSie s);
	public abstract SeguimientoContratoSie findSeguimientoContrato (int id);
	public abstract List  listarSeguimientoContrato(int codcontrato);
}
