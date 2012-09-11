package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;


import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;

@Local
public interface TipoDocumentoDAO {
	
	public abstract void insertarTipoDocumento (TipoDocumentoIdentidadSie tipodocumento);
	public abstract void actualizarTipoDocumento (TipoDocumentoIdentidadSie tipodocumento);
	public abstract void eliminarTipoDocumento (int id);
	public abstract TipoDocumentoIdentidadSie buscarTipoDocumento (int id);
	public abstract List listarTipoDocumentos ();
}
