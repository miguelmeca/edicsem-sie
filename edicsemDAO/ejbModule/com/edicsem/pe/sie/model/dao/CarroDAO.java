package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CarroSie;
 

@Local
public interface CarroDAO {
	
	public abstract void insertCarro(CarroSie t);
	public abstract void updateCarro(CarroSie t);
	public abstract CarroSie findCarro (int id);
	public abstract List  listarCarro();
	
}
