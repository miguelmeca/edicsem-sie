package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.FechaSie;
 

@Local
public interface FechaDAO {
	public abstract FechaSie findFecha (int id);
	public abstract List  listarFechas();
}
