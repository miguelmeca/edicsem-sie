package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.FechaSie;
@Local
public interface FechaService {
	
	public abstract List  listarFechas();
	public abstract FechaSie findFecha(int id);
}
