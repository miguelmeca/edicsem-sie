package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoImporteSie;

@Local
public interface TipoImporteService {
	public abstract void insertTipoImporte(TipoImporteSie a);
	public abstract void updateTipoImporte(TipoImporteSie a);
	public abstract TipoImporteSie findTipoImporte(int id);
	public abstract List  listarTipoImporte();
}

