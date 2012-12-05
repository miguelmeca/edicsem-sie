package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetSancionCargoSie;
import com.edicsem.pe.sie.entity.SancionSie;
@Local
public interface SancionService {

	public abstract void insertSancion(SancionSie s, int factor, List<DetSancionCargoSie> detSancionCargo);
	public abstract void updateSancion(SancionSie s,List<DetSancionCargoSie> detSancionCargo);
	public abstract SancionSie findSancion (int id);
	public abstract List  listarSanciones(int id);
	
}
