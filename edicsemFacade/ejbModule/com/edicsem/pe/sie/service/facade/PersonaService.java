package com.edicsem.pe.sie.service.facade;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.Personalead;
@Local
public interface PersonaService {
	
	public abstract void insertPersona(Personalead p);
	
}
