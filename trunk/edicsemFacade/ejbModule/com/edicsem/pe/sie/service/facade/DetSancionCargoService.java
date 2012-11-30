package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetSancionCargoSie;

@Local
public interface DetSancionCargoService {

	public abstract void insertDetSancionCargo(DetSancionCargoSie d);
	public abstract void updateDetSancionCargo(DetSancionCargoSie d);
	public abstract DetSancionCargoSie findDetSancionCargo (int id);
	public abstract List  listarDetSancionCargo();
}

