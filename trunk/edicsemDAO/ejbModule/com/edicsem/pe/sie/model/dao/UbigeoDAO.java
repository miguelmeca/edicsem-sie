package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.UbigeoSie;

@Local
public interface UbigeoDAO {
	
	public abstract void insertUbigeo(UbigeoSie ubigeo);
	public abstract void updateUbigeo(UbigeoSie ubigeo);
	public abstract void eliminarUbigeo(int id);
	public abstract UbigeoSie findUbigeo(int id);
	public abstract List  listarUbigeo(); 
	
}
