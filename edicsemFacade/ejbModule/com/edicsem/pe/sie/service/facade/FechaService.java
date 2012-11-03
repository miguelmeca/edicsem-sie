package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;
@Local
public interface FechaService {
	
	public abstract List  listarFechas();
	
}
