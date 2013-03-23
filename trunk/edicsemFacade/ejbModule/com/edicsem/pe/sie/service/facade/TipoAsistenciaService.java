package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoAsistenciaSie;
@Local
public interface TipoAsistenciaService {

	public abstract TipoAsistenciaSie findTipoAsistencia(int id);
	public abstract List  listarTipoAsistencia();
}
