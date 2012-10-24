package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ComprobanteSie;
@Local
public interface ComprobanteService {
	
	public abstract void insertComprobante(ComprobanteSie comp);
	public abstract ComprobanteSie findComprobante (int id);
	public abstract void updateComprobante(ComprobanteSie comp);
	public abstract List listarComprobantes() ;
	public abstract ComprobanteSie findComprobantePorNumero(String num) ;
}
