package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.PaqueteSie;
 

@Local
public interface PaqueteDAO {
	
	public abstract void insertPaquete (PaqueteSie p);
	public abstract void updatePaquete(PaqueteSie p);
	public abstract PaqueteSie findPaquete (int id);
	public abstract List  listarPaquetes();
	public abstract PaqueteSie buscarXcodigoPaquete(String codPaquete);
}
