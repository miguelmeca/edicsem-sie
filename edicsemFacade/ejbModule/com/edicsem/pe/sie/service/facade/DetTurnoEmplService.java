package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetTurnoEmplSie;
@Local
public interface DetTurnoEmplService {

	public abstract void insertDetTurnoEmpl(DetTurnoEmplSie t);
	public abstract void updateDetTurnoEmpl(DetTurnoEmplSie t);
	public abstract DetTurnoEmplSie findDetTurnoEmpl (int id);
	public abstract List  listarDetTurnoEmpl();
	
}
