package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetPaqueteSie;
 

@Local
public interface DetPaqueteDAO {
	
	public abstract void insertDetPaquete (DetPaqueteSie p);
	public abstract void updateDetPaquete(DetPaqueteSie p);
	public abstract DetPaqueteSie findDetPaquete (int id);
	public abstract List  listarDetPaquetes(int paquete);	
}
