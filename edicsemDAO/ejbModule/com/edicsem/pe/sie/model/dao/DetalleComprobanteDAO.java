package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetalleComprobanteSie;
 

@Local
public interface DetalleComprobanteDAO {
	
	public abstract void insertComprobante(DetalleComprobanteSie comp);
	public abstract List listarDetComprobantes(int codcomp);
}
