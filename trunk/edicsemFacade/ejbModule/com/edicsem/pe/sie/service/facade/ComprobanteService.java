package com.edicsem.pe.sie.service.facade;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ComprobanteSie;
@Local
public interface ComprobanteService {
	
	public abstract void insertComprobante(ComprobanteSie comp);
	public abstract ComprobanteSie findComprobante (int id);
}
