package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoImporteSie;
 

@Local
public interface TipoImporteDAO {
	
	public abstract void insertTipoImporte(TipoImporteSie a);
	public abstract void updateTipoImporte(TipoImporteSie a);
	public abstract TipoImporteSie findTipoImporte(int id);
	public abstract List  listarTipoImporte();
}
