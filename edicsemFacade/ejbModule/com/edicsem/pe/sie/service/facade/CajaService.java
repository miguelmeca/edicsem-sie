package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CajaSie;
@Local
public interface CajaService {

	public abstract void insertCaja(CajaSie c, int idEmpleado);
	public abstract void updateCaja(CajaSie c);
	public abstract CajaSie findCaja (int id);
	public abstract List  listarCaja();
	public abstract List listarCajaPorEmpleado(Integer idempleado);
	
}
