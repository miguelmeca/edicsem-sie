package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoPagoSie;
 

@Local
public interface TipoPagoDAO {
	
	public abstract TipoPagoSie findTipoPago (int id);
	public abstract List  listarTipoPago();
}
