package com.edicsem.pe.sie.service.facade;

import java.util.List;
import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ParametroSistemaSie;
import com.edicsem.pe.sie.entity.ProveedorSie;

@Local
public interface ParametroService {
	
	public abstract void insertarParametro (ParametroSistemaSie parametro);
	public abstract void actualizarParametro (ParametroSistemaSie parametro);
	public abstract void eliminarParametro (int id);
	public abstract ParametroSistemaSie findParametro (int id);
	public abstract List listarParametros ();
}

