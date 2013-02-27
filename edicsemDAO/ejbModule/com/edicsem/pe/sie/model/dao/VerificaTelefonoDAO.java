package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.VerificaTelefonoSie;


@Local
public interface VerificaTelefonoDAO {
	
	public abstract void insertVerificaTelefono(VerificaTelefonoSie v);
	public abstract void updateVerificaTelefono(VerificaTelefonoSie v);
	public abstract VerificaTelefonoSie findVerificaTelefono(int id);
	public abstract List  listarVerificaTelefono();
	
}
