package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ModuloOpcionSie;
@Local
public interface ModuloOpcionService {
	
	public abstract void insertModuloOpcion(ModuloOpcionSie p);
	public abstract void updateModuloOpcion(ModuloOpcionSie p);
	public abstract ModuloOpcionSie findModuloOpcion (int id);
	public abstract List  listarModuloOpcion();
	
}
