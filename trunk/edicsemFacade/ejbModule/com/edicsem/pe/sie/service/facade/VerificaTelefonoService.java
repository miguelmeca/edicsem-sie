package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.VerificaTelefonoSie;
@Local
public interface VerificaTelefonoService {

	public abstract void insertVerificaTelefono(VerificaTelefonoSie v);
	public abstract void updateVerificaTelefono(VerificaTelefonoSie v);
	public abstract VerificaTelefonoSie findVerificaTelefono(int id);
	public abstract List  listarVerificaTelefono();
	
}
