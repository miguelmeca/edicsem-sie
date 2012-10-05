package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.UbigeoSie;

@Local
public interface UbigeoService {
	

	public abstract void insertUbigeo(UbigeoSie ubigeo);
	public abstract void updateUbigeo(UbigeoSie ubigeo);
	public abstract void eliminarUbigeo(int id);
	public abstract UbigeoSie findUbigeo(int id);
	public abstract List  listarUbigeoDepartamentos(); 
	public abstract List  listarUbigeoProvincias( String idDepartamento);
	public abstract List  listarUbigeoDistritos( String idDepartamento, String idProvincia); 
}
