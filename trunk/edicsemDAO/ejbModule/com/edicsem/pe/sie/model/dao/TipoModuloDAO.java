package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoModuloSie;

@Local
public interface TipoModuloDAO {
	
	public abstract void insertTipoModulo(TipoModuloSie p);
	public abstract void updateTipoModulo(TipoModuloSie p);
	public abstract TipoModuloSie findTipoModulo (int id);
	public abstract List  listarTipoModulo();
}
