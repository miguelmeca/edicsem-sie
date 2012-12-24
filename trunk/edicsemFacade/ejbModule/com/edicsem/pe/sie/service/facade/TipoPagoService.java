package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoPagoSie;
@Local
public interface TipoPagoService {

	public abstract TipoPagoSie findTipoPago (int id);
	public abstract List  listarTipoPago();
}
