package com.edicsem.pe.sie.model.dao;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ComprobanteSie;
 

@Local
public interface ComprobanteDAO {
	
	public abstract void insertComprobante(ComprobanteSie comp);
	public abstract ComprobanteSie findComprobante (int id);
}
