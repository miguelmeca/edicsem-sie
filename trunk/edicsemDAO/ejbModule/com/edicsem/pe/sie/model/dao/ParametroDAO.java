package com.edicsem.pe.sie.model.dao;

import java.util.List;
import javax.ejb.Local;
import com.edicsem.pe.sie.entity.ParametroSistemaSie;

@Local
public interface ParametroDAO {
	public abstract void insertarParametro (ParametroSistemaSie parametro);
	public abstract void actualizarParametro (ParametroSistemaSie parametro);
	public abstract void eliminarParametro (int id);
	public abstract ParametroSistemaSie findParametro (int id);
	public abstract List listarParametros ();
}