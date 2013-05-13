package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CajaSie;
 
@Local
public interface CajaDAO {
	
	public abstract void insertCaja(CajaSie c);
	public abstract void updateCaja(CajaSie c);
	public abstract CajaSie findCaja (int id);
	public abstract List  listarCaja();
	
}
