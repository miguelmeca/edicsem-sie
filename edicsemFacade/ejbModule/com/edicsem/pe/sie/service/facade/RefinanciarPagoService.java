package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.RefinanciarPagoSie;
@Local
public interface RefinanciarPagoService {
	
	public abstract void insertRefinanPago(RefinanciarPagoSie r);
	public abstract void updateRefinanPago(RefinanciarPagoSie r);
	public abstract RefinanciarPagoSie findRefinanPago (int id);
	public abstract List listarRefinanciarPago();
	
}
