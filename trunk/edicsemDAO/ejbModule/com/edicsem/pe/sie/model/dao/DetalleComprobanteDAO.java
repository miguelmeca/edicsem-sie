package com.edicsem.pe.sie.model.dao;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetalleComprobanteSie;
 

@Local
public interface DetalleComprobanteDAO {
	
	public abstract void insertComprobante(DetalleComprobanteSie comp);
	
}
