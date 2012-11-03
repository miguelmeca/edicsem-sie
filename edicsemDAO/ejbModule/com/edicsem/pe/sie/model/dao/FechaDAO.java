package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;
 

@Local
public interface FechaDAO {
	
	public abstract List  listarFechas();
}
