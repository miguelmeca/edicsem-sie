package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.SancionSie;
 

@Local
public interface SancionDAO {
	
	public abstract void insertSancion(SancionSie s);
	public abstract void updateSancion(SancionSie s);
	public abstract SancionSie findSancion (int id);
	public abstract List  listarSanciones(int id);
}
