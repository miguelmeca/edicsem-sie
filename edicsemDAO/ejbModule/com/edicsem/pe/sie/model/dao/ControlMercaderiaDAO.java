package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ControlKardexSie;
 

@Local
public interface ControlMercaderiaDAO {
	
	public abstract void insertControlKardex(ControlKardexSie c);
	public abstract void updateControlKardex(ControlKardexSie c);
	public abstract ControlKardexSie findControlKardex (int id);
	public abstract List  listarControlKardex();
	
}
