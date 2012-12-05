package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.FactorSancionSie;
@Local
public interface FactorSancionService {

	public abstract void insertFactorSancion(FactorSancionSie s);
	public abstract void updateFactorSancion(FactorSancionSie s);
	public abstract FactorSancionSie findFactorSancion (int id);
	public abstract List  listarFactorSancion();
	public abstract List listarFactorSancionXcargo(int idCargo);
}
