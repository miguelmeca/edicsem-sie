package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CarroSie;
@Local
public interface CarroService {

	public abstract void insertCarro(CarroSie t);
	public abstract void updateCarro(CarroSie t);
	public abstract CarroSie findCarro (int id);
	public abstract List  listarCarro();
	
}
