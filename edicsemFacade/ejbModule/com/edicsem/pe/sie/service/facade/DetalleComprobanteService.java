package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetalleComprobanteSie;

@Local
public interface DetalleComprobanteService {
	
	public abstract void insertComprobante(DetalleComprobanteSie comp);
	public abstract List listarDetComprobantes(int codcomp);
}

