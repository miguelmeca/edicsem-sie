package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.FactorSancionSie;
 

@Local
public interface FactorSancionDAO {
	
	public abstract void insertFactorSancion(FactorSancionSie s);
	public abstract void updateFactorSancion(FactorSancionSie s);
	public abstract FactorSancionSie findFactorSancion (int id);
	public abstract List  listarFactorSancion();
}