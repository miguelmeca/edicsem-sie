package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoTurnoSie;

@Local
public interface TipoTurnoService {
	public abstract void insertTipoTurno(TipoTurnoSie t);
	public abstract void updateTipoTurno(TipoTurnoSie t);
	public abstract TipoTurnoSie findTipoTurno (int id);
	public abstract List  listarTipoTurno();
		
}

